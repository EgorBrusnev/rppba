package by.rppba.production.service;

import by.rppba.production.dao.*;
import by.rppba.production.dto.SaveDetailDto;
import by.rppba.production.model.*;
import by.rppba.production.util.DetailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DetailService {

    private final RequestRepository requestRepository;
    private final AttitudeRepository attitudeRepository;
    private final DetailRepository detailRepository;
    private final CurrencyRepository currencyRepository;
    private final UnitQtyRepository unitQtyRepository;

    @Autowired
    public DetailService(RequestRepository requestRepository, AttitudeRepository attitudeRepository, DetailRepository detailRepository, CurrencyRepository currencyRepository, UnitQtyRepository unitQtyRepository) {
        this.requestRepository = requestRepository;
        this.attitudeRepository = attitudeRepository;
        this.detailRepository = detailRepository;
        this.currencyRepository = currencyRepository;
        this.unitQtyRepository = unitQtyRepository;
    }

    public List<Detail> getAllDetails() {
        return (List<Detail>) detailRepository.findAll();
    }

    public void requestDetail(int detailId, int count, String unit) {
        Detail detail = detailRepository.findById(detailId).orElse(null);
        if (detail != null) {
            Attitude attitude = new Attitude(detail, DetailStatus.REQUESTED, count, unit);
//            Request request = new Request(attitude, new Date());
            requestRepository.save(new Request(attitudeRepository.save(attitude), new Date()));
        }
    }

    public void saveDetail(SaveDetailDto dto) {
        Detail detail = detailRepository.findById(dto.getId()).get();
        Currency currency = currencyRepository.findById(dto.getCurrencyCode()).get();
        UnitQty unitQty = unitQtyRepository.findById(dto.getUnitQty()).get();
        detail.setCurrency(currency);
        detail.setUnitQty(unitQty);
        detail.setName(dto.getName());
        detail.setMaterial(dto.getMaterial());
        detail.setQuantity(dto.getQuantity());
        detail.setSize(dto.getSize());
        detail.setUnitPrice(dto.getUnitPrice());
        detail.setUnitSize(dto.getUnitSize());
        detailRepository.save(detail);
    }
}
