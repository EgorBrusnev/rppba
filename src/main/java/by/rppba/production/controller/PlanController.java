package by.rppba.production.controller;

import by.rppba.production.service.OrderService;
import by.rppba.production.service.PlanService;
import by.rppba.production.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan")
public class PlanController {

    private final ProductService productService;
    private final OrderService orderService;
    private final PlanService planService;

    @Autowired
    public PlanController(ProductService productService, OrderService orderService, PlanService planService) {
        this.productService = productService;
        this.orderService = orderService;
        this.planService = planService;
    }

    @GetMapping
    public String getPlans(Model model) {
        model.addAttribute("plans", planService.getAllPlans());
        return "plans";
    }

    @GetMapping("/requests")
    public String makeRequests(Model model) {
        model.addAttribute("products", productService.getAll());
        return "newOrder";
    }
}
