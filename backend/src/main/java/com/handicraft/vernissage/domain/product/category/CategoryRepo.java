package com.handicraft.vernissage.domain.product.category;

import java.util.List;

public interface CategoryRepo {

    void save(Category category);

    List<Category> all();

}
