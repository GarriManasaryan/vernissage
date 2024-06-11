package com.handicraft.vernissage.port.adapters.backoffice.resource;

import com.handicraft.vernissage.application.ProductCategoryService;
import com.handicraft.vernissage.domain.product.category.ProductCategory;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.ProductCategoryBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.category.ProductCategoryCreationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/api/product-categories")
    public void save(@RequestBody ProductCategoryCreationRequest productCategoryCreationRequest) {
        productCategoryService.save(productCategoryCreationRequest);
    }

    @GetMapping("/api/product-categories")
    public List<ProductCategoryBackofficeModel> all() {
        return productCategoryService.all();
    }
}
