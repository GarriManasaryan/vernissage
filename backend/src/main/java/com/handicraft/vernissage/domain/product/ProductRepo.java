package com.handicraft.vernissage.domain.product;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ProductRepo {

    void save(Product product);

    List<Product> all();

}
