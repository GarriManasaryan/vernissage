package com.handicraft.vernissage.domain.user;

import org.jetbrains.annotations.NotNull;

public record Master(
    @NotNull String id,
    @NotNull String name,
    @NotNull Role role,
    @NotNull String timeZone
) implements UserBase {

    public Master {
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

// если бы делали без интерфейсов
//public class Master extends UserBase {
//    private String masterSpecificField; // Example of a field specific to Master
//
//    public Master() {
//        super();
//        setRole(Role.MASTER);
//    }
//
//    // Getters and setters for masterSpecificField
//
//    // Override the setRole method to prevent changing the role after it's set to MASTER
//    @Override
//    public void setRole(Role role) {
//        if (role != Role.MASTER) {
//            throw new IllegalArgumentException("Role cannot be changed. It must be MASTER for Master class.");
//        }
//        super.setRole(role);
//    }
//}