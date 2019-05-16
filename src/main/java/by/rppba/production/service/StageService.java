package by.rppba.production.service;

import by.rppba.production.dao.StageRepository;
import by.rppba.production.dto.StageDto;
import by.rppba.production.model.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StageService {
    private final StageRepository stageRepository;

    @Autowired
    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public void putStage(StageDto dto) {
        Stage stage = new Stage();
        stage.setId(dto.getId());
        stage.setName(dto.getName());
//        stage.setDetailQty(dto.getQuantity());
//        stage.setUnitQty(dto.getUnitQty());
        stage.setStageTime(dto.getTime());
        stage.setStageTimeUnit(dto.getTimeUnit());
        stage.setStageNumber(dto.getStageNumber());
        stageRepository.save(stage);
    }

    public List<StageDto> getAllStages() {
        return ((List<Stage>) stageRepository.findAll()).stream().map(Stage::toDto).collect(Collectors.toList());
    }

    public void deleteStage(int id) {
        stageRepository.deleteById(id);
    }
}
