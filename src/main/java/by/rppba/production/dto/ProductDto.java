package by.rppba.production.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public class ProductDto {
    private String name;
    private String model;
    private List<SaveDetailDto> details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<SaveDetailDto> getDetails() {
        return details;
    }

    public void setDetails(List<SaveDetailDto> details) {
        this.details = details;
    }
}
