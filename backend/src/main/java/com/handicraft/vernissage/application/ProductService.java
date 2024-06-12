package com.handicraft.vernissage.application;

import com.handicraft.vernissage.domain.product.Product;
import com.handicraft.vernissage.domain.product.ProductRepo;
import com.handicraft.vernissage.domain.product.master.Master;
import com.handicraft.vernissage.domain.product.master.MasterRepo;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductCreationRequest;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.master.MasterBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.master.MasterCreationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void save(ProductCreationRequest productCreationRequest) {
        productRepo.save(Product.of(
                productCreationRequest.name(),
                productCreationRequest.description().orElse(null),
                productCreationRequest.priceId(),
                productCreationRequest.masterId(),
                productCreationRequest.categoryId()
        ));
    }

    public List<ProductBackofficeModel> all() {
        return productRepo.all().stream()
                .map(x -> new ProductBackofficeModel(
                        x.id(),
                        x.name(),
                        x.description(),
                        x.priceId(),
                        x.masterId(),
                        x.categoryId()
                ))
                .toList();
    }
}
