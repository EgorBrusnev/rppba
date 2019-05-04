package by.rppba.production.service;

import by.rppba.production.dao.StageRepository;
import by.rppba.production.model.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StageService {
    private final StageRepository stageRepository;

    @Autowired
    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public void putStage(Stage stage) {
        stageRepository.save(stage);
    }
}
