package by.rppba.production.dao;

import by.rppba.production.model.CountryCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<CountryCode, Integer> {
}
