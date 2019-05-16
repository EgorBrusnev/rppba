package by.rppba.production.controller;

import by.rppba.production.dto.ProductDto;
import by.rppba.production.dto.ProductStageDto;
import by.rppba.production.dto.SaveDetailDto;
import by.rppba.production.dto.StageDto;
import by.rppba.production.model.Product;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
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
        Product product = productService.getById(id);
        model.addAttribute(product);
        List<StageDto> stages = stageService.getAllStages();
        List<ProductStageDto> stageList = new ArrayList<>();
        Map<Integer, List<ProductDetail>> map = product.getDetails().stream().collect(Collectors.groupingBy(it -> it.getStage().getId()));
        map.forEach((integer, productDetails) -> {
            StageDto stage = stages.stream().filter(it -> it.getId() == integer).findFirst().orElse(null);
            if (stage != null) {
                stageList.add(new ProductStageDto(stage.getId(), stage.getName(), stage.getStageNumber(), productDetails));
            }
        });
        stageList.sort(Comparator.comparing(ProductStageDto::getStageNumber));
        model.addAttribute("stageList", stageList);
        model.addAttribute("stages", stages);
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
        return "redirect:/product/" + id;
    }

    @PostMapping("/{id}/detail/delete")
    public String deleteDetail(@PathVariable("id") int productId, @RequestParam Integer detailId, Model model) {
        productService.deleteDetail(detailId);
        return "redirect:/product/" + productId;
    }

    @PostMapping("/{id}/empty")
    public String addEmptyStage(@PathVariable int id, @RequestParam Integer stage) {
        productService.addDetail(id, null, 0, "0", stage);
        return "redirect:/product/" + id;
    }
}
