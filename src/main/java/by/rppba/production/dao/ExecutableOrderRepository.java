package by.rppba.production.dao;

import by.rppba.production.model.ExecutableOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutableOrderRepository extends CrudRepository<ExecutableOrder, Integer> {
}
