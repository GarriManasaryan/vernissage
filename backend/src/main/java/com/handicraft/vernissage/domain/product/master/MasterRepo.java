package com.handicraft.vernissage.domain.product.master;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface MasterRepo {

    void save(@NotNull Master master);

    List<Master> all();

}
