package by.rppba.production.dao;

import by.rppba.production.model.UnitQty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitQtyRepository extends CrudRepository<UnitQty, Integer> {
}
