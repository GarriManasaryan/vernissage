package com.handicraft.vernissage.domain.product.category;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record Category(
        @NotNull String id,
        @NotNull String name,
        @Nullable String description,
        @Nullable String parentId
) {

    public static Category of(
            @NotNull String name,
            @Nullable String description,
            @Nullable String parentId
    ){
        return new Category(
                IdGenerator.generate("pct"),
                name,
                description,
                parentId
        );
    }

}
