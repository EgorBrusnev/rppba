package by.rppba.production.dao;

import by.rppba.production.model.ExecutableOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExecutableOrderRepository extends CrudRepository<ExecutableOrder, Integer> {
    List<ExecutableOrder> findByPlan_IdAndProduct_Id(int planId, int productId);
    List<ExecutableOrder> findByPlan_Id(int planId);
}
