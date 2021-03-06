package by.rppba.production.controller;

import by.rppba.production.dto.RequestDto;
import by.rppba.production.dto.SaveDetailDto;
import by.rppba.production.service.DetailService;
import by.rppba.production.service.ExtenstionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/detail")
public class DetailController {

    private final DetailService detailService;
    private final ExtenstionsService extenstionsService;

    @Autowired
    public DetailController(DetailService detailService, ExtenstionsService extenstionsService) {
        this.detailService = detailService;
        this.extenstionsService = extenstionsService;
    }

    @GetMapping
    public String details(Model model) {
        model.addAttribute("details", detailService.getAllDetails());
        model.addAttribute("units", extenstionsService.getUnitQty());
        return "details";
    }

    @GetMapping("/catalog")
    public String detailCatalog(Model model) {
        model.addAttribute("producers", detailService.getProducers());
        model.addAttribute("details", detailService.getAllDetails());
        model.addAttribute("map", extenstionsService.getAllAsMap());
        return "detailCatalog";
    }

    @PostMapping
    public String saveDetail(SaveDetailDto detail) {
        detailService.saveDetail(detail);
        return "redirect:/detail/catalog";
    }

    @PostMapping("/request")
    @ResponseBody
    public void requestDetails(RequestDto requestDto) {
        detailService.requestDetail(requestDto.getDetailId(), requestDto.getCount(), requestDto.getUnit());
    }

    @PostMapping("/{id}/delete")
    public String deleteDetail(@PathVariable int id, Model model) {
        detailService.removeDetail(id);
        return "redirect:/detail/catalog";
    }
}
