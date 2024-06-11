package com.handicraft.vernissage.application;

import com.handicraft.vernissage.domain.product.category.ProductCategory;
import com.handicraft.vernissage.domain.product.category.ProductCategoryRepo;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.ProductCategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.ProductCategoryCreationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepo productCategoryRepo;

    public ProductCategoryService(ProductCategoryRepo productCategoryRepo) {
        this.productCategoryRepo = productCategoryRepo;
    }

    public void save(ProductCategoryCreationRequest productCategoryCreationRequest) {
        productCategoryRepo.save(ProductCategory.of(
                productCategoryCreationRequest.name(),
                productCategoryCreationRequest.description(),
                productCategoryCreationRequest.parentId()
        ));
    }

    public List<ProductCategoryBackofficeModel> all() {
        return productCategoryRepo.all().stream()
                .map(x -> new ProductCategoryBackofficeModel(
                        x.id(),
                        x.name(),
                        x.description(),
                        x.parentId()
                ))
                .toList();
    }
}
