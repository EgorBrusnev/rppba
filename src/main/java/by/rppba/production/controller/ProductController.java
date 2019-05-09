package by.rppba.production.controller;

import by.rppba.production.dto.ProductDto;
import by.rppba.production.dto.SaveDetailDto;
import by.rppba.production.model.ProductDetail;
import by.rppba.production.model.Stage;
import by.rppba.production.service.DetailService;
import by.rppba.production.service.ExtenstionsService;
import by.rppba.production.service.ProductService;
import by.rppba.production.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final DetailService detailService;
    private final ExtenstionsService extenstionsService;
    private final StageService stageService;

    @Autowired
    public ProductController(ProductService productService, DetailService detailService, ExtenstionsService extenstionsService, StageService stageService) {
        this.productService = productService;
        this.detailService = detailService;
        this.extenstionsService = extenstionsService;
        this.stageService = stageService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("newProduct", new ProductDto());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("details", detailService.getAllDetails());
        return "products";
    }

    @GetMapping("/{id}")
    public String product(@PathVariable Integer id, Model model) {
        model.addAttribute(productService.getById(id));
        model.addAttribute("stages", stageService.getAllStages());
        model.addAttribute("details", detailService.getAllDetails());
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

    @PostMapping
    public String addProduct(@ModelAttribute ProductDto product, Model model) {
        productService.addProduct(product);
        return getAllProducts(model);
    }

    @PostMapping("/{id}/detail")
    public String addDetailToProduct(@PathVariable int id,
                                     @RequestParam Integer detail,
                                     @RequestParam Integer stage,
                                     @RequestParam Integer count,
                                     @RequestParam String unit,
                                     Model model) {
        productService.addDetail(id, detail, count, unit, stage);
        return product(id, model);
    }

    @PostMapping("/{id}/detail/delete")
    public String deleteDetail(@PathVariable("id") int productId, @RequestParam Integer detailId, Model model) {
        productService.deleteDetail(detailId);
        return product(productId, model);
    }
}
