package com.handicraft.vernissage.domain.product;

import org.jetbrains.annotations.NotNull;

public interface ValueExistsRepo {

    Boolean nameAlreadyExists(@NotNull String name);

}
