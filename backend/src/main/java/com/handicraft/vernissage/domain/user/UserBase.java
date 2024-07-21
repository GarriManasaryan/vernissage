package com.handicraft.vernissage.domain.user;

import org.jetbrains.annotations.NotNull;

public interface UserBase {
    @NotNull String id();
    @NotNull String name();
    @NotNull Role role();
    @NotNull String timeZone();
}

