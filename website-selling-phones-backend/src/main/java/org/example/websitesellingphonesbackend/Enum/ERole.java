package org.example.websitesellingphonesbackend.Enum;

public enum ERole {
    USER(0),
    ADMIN(1);
    private final int value;
    ERole(int value) {
        this.value = value;
    }
}
