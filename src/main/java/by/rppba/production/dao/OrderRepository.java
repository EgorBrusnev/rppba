package by.rppba.production.dao;

import by.rppba.production.model.ProductionOrder;
import by.rppba.production.util.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<ProductionOrder, Integer> {
    List<ProductionOrder> findByStatus(Status status);
}
