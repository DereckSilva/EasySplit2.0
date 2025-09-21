package com.easy_split.demo.enums;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
