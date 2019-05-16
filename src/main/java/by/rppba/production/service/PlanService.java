package by.rppba.production.service;

import by.rppba.production.dao.ExecutableOrderRepository;
import by.rppba.production.dao.PlanProductRepository;
import by.rppba.production.dao.PlanRepository;
import by.rppba.production.model.ExecutableOrder;
import by.rppba.production.model.Plan;
import by.rppba.production.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanProductRepository planProductRepository;
    private final ExecutableOrderRepository executableOrderRepository;

    @Autowired
    public PlanService(PlanRepository planRepository, PlanProductRepository planProductRepository, ExecutableOrderRepository executableOrderRepository) {
        this.planRepository = planRepository;
        this.planProductRepository = planProductRepository;
        this.executableOrderRepository = executableOrderRepository;
    }

    public List<Plan> getAllPlans() {
        List<Plan> plans = (List<Plan>) planRepository.findAll();
        List<ExecutableOrder> plannedOrders = getAllExecutableOrders().stream().filter(it -> it.getStatus().equals(Status.PLANNED)).collect(Collectors.toList());
        List<ExecutableOrder> doneOrders = getAllExecutableOrders().stream().filter(it -> it.getStatus().equals(Status.DONE)).collect(Collectors.toList());
        List<ExecutableOrder> inProgressOrders = getAllExecutableOrders().stream().filter(it -> it.getStatus().equals(Status.IN_PROGRESS)).collect(Collectors.toList());
//        collapseList(doneOrders);
//        collapseList(plannedOrders);
//        collapseList(inProgressOrders);
        plans.forEach(plan -> plan.setDoneOrders(doneOrders.stream().filter(it -> it.getPlan().getId() == plan.getId()).collect(Collectors.toList())));
        plans.forEach(plan -> plan.setPlannedOrders(plannedOrders.stream().filter(it -> it.getPlan().getId() == plan.getId()).collect(Collectors.toList())));
        plans.forEach(plan -> plan.setInProgressOrders(inProgressOrders.stream().filter(it -> it.getPlan().getId() == plan.getId()).collect(Collectors.toList())));
        return plans;
    }

    private List<ExecutableOrder> collapseList(List<ExecutableOrder> list) {
        Map<Integer, List<ExecutableOrder>> map = list.stream().collect(Collectors.groupingBy(it -> it.getProduct().getId()));
        list.clear();
        map.forEach((integer, executableOrders) -> {
            int sum = executableOrders.stream().mapToInt(ExecutableOrder::getProductQty).sum();
            ExecutableOrder order = executableOrders.get(0);
            order.setProductQty(sum);
            list.add(order);
        });
        return list;
    }

    public List<ExecutableOrder> getPlanOrders(int planId) {
        return executableOrderRepository.findByPlan_Id(planId);
    }

    public List<ExecutableOrder> getAllExecutableOrders() {
        return (List<ExecutableOrder>) executableOrderRepository.findAll();
    }
}
