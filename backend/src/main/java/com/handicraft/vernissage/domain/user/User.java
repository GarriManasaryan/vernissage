package com.handicraft.vernissage.domain.user;

import org.jetbrains.annotations.NotNull;

public record User(
    @NotNull String id,
    @NotNull String name,
    @NotNull Role role,
    @NotNull String timeZone
) implements UserBase {}