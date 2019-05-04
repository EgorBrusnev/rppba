package by.rppba.production.controller;

import by.rppba.production.model.Stage;
import by.rppba.production.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @ResponseBody
    public void putStage(Stage stage) {
        stageService.putStage(stage);
    }
}
