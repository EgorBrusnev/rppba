package by.rppba.production.controller;

import by.rppba.production.dto.StageDto;
import by.rppba.production.model.Stage;
import by.rppba.production.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stage")
public class StageController {

    private final StageService stageService;

    @Autowired
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @PostMapping(consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public String putStage(StageDto stage) {
        stageService.putStage(stage);
        return "redirect:/stage";
    }

    @GetMapping
    public String stages(Model model) {
        model.addAttribute("stages", stageService.getAllStages());
        return "stages";
    }

    @PostMapping("/{id}/delete")
    public String deleteStage(@PathVariable int id) {
        stageService.deleteStage(id);
        return "redirect:/stage";
    }
}
