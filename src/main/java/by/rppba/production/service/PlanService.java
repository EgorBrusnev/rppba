package by.rppba.production.service;

import by.rppba.production.dao.PlanProductRepository;
import by.rppba.production.dao.PlanRepository;
import by.rppba.production.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanProductRepository planProductRepository;

    @Autowired
    public PlanService(PlanRepository planRepository, PlanProductRepository planProductRepository) {
        this.planRepository = planRepository;
        this.planProductRepository = planProductRepository;
    }

    public List<Plan> getAllPlans() {
        return (List<Plan>) planRepository.findAll();
    }
}
