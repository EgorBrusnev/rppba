package by.rppba.production.dao;

import by.rppba.production.model.Request;
import by.rppba.production.util.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request, Integer> {
    List<Request> findByAttitude_Status(Status status);
}
