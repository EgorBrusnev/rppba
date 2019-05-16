package by.rppba.production.service;

import by.rppba.production.dao.DetailRepository;
import by.rppba.production.dao.ProductDetailRepository;
import by.rppba.production.dao.ProductRepository;
import by.rppba.production.dao.StageRepository;
import by.rppba.production.dto.ProductDto;
import by.rppba.production.model.Detail;
import by.rppba.production.model.Product;
import by.rppba.production.model.ProductDetail;
import by.rppba.production.model.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StageRepository stageRepository;
    private final DetailRepository detailRepository;
    private final ProductDetailRepository productDetailRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, StageRepository stageRepository, DetailRepository detailRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.stageRepository = stageRepository;
        this.detailRepository = detailRepository;
        this.productDetailRepository = productDetailRepository;
    }

    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Product getById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public void addProduct(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setModel(dto.getModel());
        productRepository.save(product);
    }

    public void addDetail(int productId, @Nullable Integer detailId, int count, String unit, int stageId) {
        Product product = productRepository.findById(productId).orElse(null);
        Detail detail = null;
        ProductDetail productDetail = null;
        if (detailId != null) {
            detail = detailRepository.findById(detailId).orElse(null);
            productDetail = productDetailRepository.findByProduct_IdAndDetail_IdAndStage_Id(productId, detailId, stageId);
        } else if (!productDetailRepository.findByStage_IdAndProduct_Id(stageId, productId).isEmpty()) {
            return;
        }
        Stage stage = stageRepository.findById(stageId).orElse(null);
        if (productDetail != null) {
            productDetail.setDetailQty(productDetail.getDetailQty() + count);
        } else {
            productDetail = new ProductDetail(product, count, stage, detail, unit);
        }
        productDetailRepository.save(productDetail);
    }

    public void deleteDetail(int id) {
        productDetailRepository.deleteById(id);
    }
}
