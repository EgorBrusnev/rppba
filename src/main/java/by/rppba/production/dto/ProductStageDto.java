package by.rppba.production.dto;

import by.rppba.production.model.Detail;
import by.rppba.production.model.Product;
import by.rppba.production.model.ProductDetail;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;
import java.util.stream.Collectors;

@JsonAutoDetect
public class ProductStageDto {
    private int id;
    private String name;
    private int stageNumber;
    private List<ProductDetail> detailList;

    public ProductStageDto() {
    }

    public ProductStageDto(int id, String name, int stageNumber, List<ProductDetail> detailList) {
        this.id = id;
        this.name = name;
        this.stageNumber = stageNumber;
        this.detailList = detailList;
    }

    public static ProductStageDto create(StageDto stageDto, Product product) {
        /*dto.setName(stageDto.getName());
        dto.setId(stageDto.getId());
        dto.setStageNumber(stageDto.getStageNumber());
//        List<ProductDetail> details = product.getDetails().stream()
//                .filter(it -> it.getStage().getId() == stageDto.getId() && it.getStageNumber() == stageDto.getStageNumber())
//                .collect(Collectors.toList());
        dto.setDetailList(details);*/

        return new ProductStageDto();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public List<ProductDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ProductDetail> detailList) {
        this.detailList = detailList;
    }
}
