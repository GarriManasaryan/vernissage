package com.handicraft.vernissage.port.adapters.backoffice.models.user;

import com.handicraft.vernissage.domain.user.Role;
import com.handicraft.vernissage.domain.user.UserBase;
import org.jetbrains.annotations.NotNull;

public record MasterBackofficeModel(
    @NotNull String id,
    @NotNull String name,
    @NotNull Role role,
    @NotNull String timeZone
) implements UserBase {

    public MasterBackofficeModel {
        // Role is always MASTER for the Master record
        if (role != Role.MASTER) {
            throw new IllegalArgumentException("Role cannot be changed. It must be MASTER for Master class.");
        }
    }

    @Override
    public @NotNull Role role() {
        return Role.MASTER;
    }
}
