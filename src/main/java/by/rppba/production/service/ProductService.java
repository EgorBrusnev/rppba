package by.rppba.production.service;

import by.rppba.production.dao.ProductDetailRepository;
import by.rppba.production.dao.ProductRepository;
import by.rppba.production.model.Product;
import by.rppba.production.model.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
    }

    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Product getById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }
}
