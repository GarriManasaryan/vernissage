package com.handicraft.vernissage.port.adapters.backoffice.models.product.master;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;

public record MasterBackofficeModel(
        @NotNull String id,
        @NotNull String name,
        @NotNull String description
) {

}
