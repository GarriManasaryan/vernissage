package com.handicraft.vernissage.domain.product.price;

import com.handicraft.vernissage.domain.product.category.ProductCategory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface PriceRepo {

    void save(@NotNull Price price);

    List<Price> all();

}
