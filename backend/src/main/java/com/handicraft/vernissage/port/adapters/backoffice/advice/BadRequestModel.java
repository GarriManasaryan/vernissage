package com.handicraft.vernissage.port.adapters.backoffice.advice;

import org.jetbrains.annotations.NotNull;

public record BadRequestModel(
        @NotNull String message
) {
}
