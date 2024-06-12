package com.handicraft.vernissage.port.adapters.backoffice.resource;

import com.handicraft.vernissage.application.MasterService;
import com.handicraft.vernissage.application.ProductService;
import com.handicraft.vernissage.domain.product.Product;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.ProductCreationRequest;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.master.MasterBackofficeModel;
import com.handicraft.vernissage.port.adapters.backoffice.models.product.master.MasterCreationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/products")
    public void save(@RequestBody ProductCreationRequest productCreationRequest) {
        productService.save(productCreationRequest);
    }

    @GetMapping("/api/products")
    public List<ProductBackofficeModel> all() {
        return productService.all();
    }
}
