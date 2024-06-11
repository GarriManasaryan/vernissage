package com.handicraft.vernissage.domain.product.category;

import java.util.List;

public interface ProductCategoryRepo {

    void save(ProductCategory productCategory);

    List<ProductCategory> all();

}
