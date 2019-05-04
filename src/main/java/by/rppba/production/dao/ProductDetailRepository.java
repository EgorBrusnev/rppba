package by.rppba.production.dao;

import by.rppba.production.model.ProductDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends CrudRepository<ProductDetail, Integer> {
    List<ProductDetail> findByProduct_Id(int productId);
}
