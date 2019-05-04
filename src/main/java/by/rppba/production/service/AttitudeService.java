package by.rppba.production.service;

import by.rppba.production.dao.AttitudeRepository;
import by.rppba.production.model.Attitude;
import by.rppba.production.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttitudeService {

    private final AttitudeRepository repository;

    @Autowired
    public AttitudeService(AttitudeRepository repository) {
        this.repository = repository;
    }

}
