package by.rppba.production.controller;

import by.rppba.production.model.ProductDetail;
import by.rppba.production.model.Stage;
import by.rppba.production.service.ExtenstionsService;
import by.rppba.production.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final ExtenstionsService extenstionsService;

    @Autowired
    public ProductController(ProductService productService, ExtenstionsService extenstionsService) {
        this.productService = productService;
        this.extenstionsService = extenstionsService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    @GetMapping("/{id}")
    public String product(@PathVariable Integer id, Model model) {
        model.addAttribute(productService.getById(id));
        model.addAttribute("ext", extenstionsService.getAllAsMap());
        return "singleProduct";
    }

    @GetMapping("/{id}/stages")
    public String stages(@PathVariable Integer id, Model model) {
        List<Stage> stages = productService.getById(id).getDetails()
                .stream()
                .map(ProductDetail::getStage)
                .sorted(Comparator.comparing(Stage::getStageNumber)).collect(Collectors.toList());
        model.addAttribute("stages", stages);
        return "productStages";
    }
}
