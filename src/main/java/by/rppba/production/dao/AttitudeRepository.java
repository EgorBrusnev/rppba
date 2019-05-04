package by.rppba.production.dao;

import by.rppba.production.model.Attitude;
import by.rppba.production.util.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttitudeRepository extends CrudRepository<Attitude, Integer> {
    List<Attitude> findByStatus(Status status);
}
