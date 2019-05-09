package by.rppba.production.controller;

import by.rppba.production.dto.DetailDto;
import by.rppba.production.dto.NewOrderDto;
import by.rppba.production.dto.OrderDto;
import by.rppba.production.model.Plan;
import by.rppba.production.model.ProductionOrder;
import by.rppba.production.service.OrderService;
import by.rppba.production.service.PlanService;
import by.rppba.production.service.ProductService;
import by.rppba.production.util.Status;
import by.rppba.production.util.exception.NotEnoughTimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final PlanService planService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService, PlanService planService) {
        this.orderService = orderService;
        this.productService = productService;
        this.planService = planService;
    }

    @GetMapping
    public String allOrders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority grantedAuthority = auth.getAuthorities().stream().findFirst().get();
        String status;
        switch (grantedAuthority.getAuthority()) {
            case "MASTER_ASSAMBLY":
                status = "Progress";
                break;
            case "MASTER_SHOP":
                status = "Done";
                break;
            default:
                status = "";
        }
        model.addAttribute("newStatus", status);
        return "ordersAll";
    }

    @GetMapping("/new")
    public String newOrderPage(Model model) {
        model.addAttribute("products", productService.getAll());
        return "newOrder";
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void newOrders(@RequestBody OrderDto[] orders) {
        orderService.addOrders(Arrays.asList(orders));
    }

    @GetMapping("/plan")
    public String orders(Model model) {
        model.addAttribute("orders", orderService.getByStatus(Status.CREATED));
        return "orders";
    }

    @GetMapping("/plan/{id}")
    public String planOrder(@PathVariable int id, Model model) {
        ProductionOrder order = orderService.getOrderById(id);
        List<DetailDto> details = orderService.getDetailDto(order);
        List<Plan> plans = planService.getAllPlans();
        boolean isReady = details.stream().anyMatch(it -> !it.isEnoughQty());
        model.addAttribute("order", order);
        model.addAttribute("details", details);
        model.addAttribute("orderId", id);
        model.addAttribute("isReady", isReady);
        model.addAttribute("plans", plans);
        return "planOrder";
    }

    @PostMapping(value = "/plan", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public void calculatePlan(NewOrderDto orderDto) throws NotEnoughTimeException {
        orderService.createNewOrder(orderDto);
    }

    @GetMapping("/{id}/progress")
    public RedirectView changeInProgress(@PathVariable int id) {
        orderService.changeStatus(id, Status.IN_PROGRESS, -1);
        return new RedirectView("/order");
    }

    @PostMapping("/{id}/done")
    public void changeDone(@PathVariable int id) {
        orderService.changeStatus(id, Status.DONE, -1);
        // send to store
    }
}
