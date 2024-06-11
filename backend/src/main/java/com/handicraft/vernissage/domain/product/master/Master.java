package com.handicraft.vernissage.domain.product.master;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;

public record Master(
        @NotNull String id,
        @NotNull String name,
        @NotNull String description
) {

    public static Master of(
            @NotNull String name,
            @NotNull String description
    ){
        return new Master(
                IdGenerator.generate("mst"),
                name,
                description
        );
    }

}
