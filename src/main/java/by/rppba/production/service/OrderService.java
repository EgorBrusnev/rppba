package by.rppba.production.service;

import by.rppba.production.dao.*;
import by.rppba.production.dto.DetailDto;
import by.rppba.production.dto.NewOrderDto;
import by.rppba.production.dto.OrderDto;
import by.rppba.production.model.*;
import by.rppba.production.util.Status;
import by.rppba.production.util.exception.NotEnoughTimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ExecutableOrderRepository executableOrderRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;
    private final DetailRepository detailRepository;
    private final StageRepository stageRepository;
    private final PlanRepository planRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ExecutableOrderRepository executableOrderRepository, ProductRepository productRepository, EmployeeRepository employeeRepository, DetailRepository detailRepository, StageRepository stageRepository, PlanRepository planRepository) {
        this.orderRepository = orderRepository;
        this.executableOrderRepository = executableOrderRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.detailRepository = detailRepository;
        this.stageRepository = stageRepository;
        this.planRepository = planRepository;
    }

    public void addOrders(List<OrderDto> orders) {
        List<ProductionOrder> productionOrders = orders.stream().map(it -> {
            Product product = productRepository.findById(it.getProductId()).orElse(null);
            if (product != null) {
                return new ProductionOrder(it.getCount(), product, Status.CREATED, "шт");
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        orderRepository.saveAll(productionOrders);
    }

    public List<ExecutableOrder> getAll() {
        return (List<ExecutableOrder>) executableOrderRepository.findAll();
    }

    public List<ProductionOrder> getByStatus(Status status) {
        return orderRepository.findByStatus(status);
    }

    public ProductionOrder getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void createNewOrder(NewOrderDto orderDto) throws NotEnoughTimeException {
        ProductionOrder order = orderRepository.findById(orderDto.getId()).orElseThrow(NullPointerException::new);
        if (order.getStatus().equals(Status.CREATED)) {
            List<DetailDto> details = getDetailDto(order);
            int duration = details.stream().mapToInt(it -> it.getStageDuration() * it.getStageDurationUnit().getDivider()).sum();
            Date currentDate = new Date();
            Plan plan = planRepository.findById(orderDto.getPlan()).get();
            if (currentDate.toInstant().plus(Duration.ofSeconds(duration)).isBefore(plan.getEndDate().toInstant())) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                Employee loggedInUser = employeeRepository.findByUser_Username("admin"); //TODO username
                order.setStatus(Status.PLANNED);
                ExecutableOrder eOrder = new ExecutableOrder(new Date(), plan, duration, order.getProductQty(), order.getProduct(), loggedInUser, Status.PLANNED, order);
                removeDetailsFromStock(details);
                orderRepository.save(order);
                executableOrderRepository.save(eOrder);
            } else {
                throw new NotEnoughTimeException();
            }
        }
    }

    private void removeDetailsFromStock(List<DetailDto> details) {
        List<Detail> detailList = (List<Detail>) detailRepository.findAllById(details.stream().map(DetailDto::getId).collect(Collectors.toList()));
        detailList.forEach(it -> {
            int qty = it.getQuantity();
            DetailDto detailDto = details.stream().filter(detail -> detail.getId() == it.getId()).findFirst().get();
            it.setQuantity(qty - detailDto.getQtyForProduct());
        });
        detailRepository.saveAll(detailList);
    }

    public List<DetailDto> getDetailDto(ProductionOrder order) {
        List<DetailDto> details = order.getProduct().getDetails().stream().map(ProductDetail::toDto).collect(Collectors.toList());
        details.forEach(it -> {
            it.setQtyForProduct(it.getQtyForProduct() * order.getProductQty());
            it.setStageDuration(it.getStageDuration() * order.getProductQty());
        });
        return details;
    }

    public void changeStatus(int orderId, Status status, int stageId) {
        Stage stage = stageRepository.findById(stageId).orElse(null);
        ExecutableOrder order = executableOrderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStage(stage);
            order.setStatus(status);
            ProductionOrder productionOrder = order.getProductionOrder();
            productionOrder.setStatus(status);
            orderRepository.save(productionOrder);
            executableOrderRepository.save(order);
        }
    }
}
