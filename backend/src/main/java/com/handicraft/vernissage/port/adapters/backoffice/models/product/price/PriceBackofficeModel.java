package com.handicraft.vernissage.port.adapters.backoffice.models.product.price;

import com.handicraft.vernissage.application.IdGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.Option;
import java.util.Optional;

public record PriceBackofficeModel(
        @NotNull String id,
        @NotNull Double value,
        @NotNull String currency,
        @Nullable Double discount
) {

}
