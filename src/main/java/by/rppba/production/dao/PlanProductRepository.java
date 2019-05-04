package by.rppba.production.dao;

import by.rppba.production.model.ProductPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanProductRepository extends CrudRepository<ProductPlan, Integer> {
}
