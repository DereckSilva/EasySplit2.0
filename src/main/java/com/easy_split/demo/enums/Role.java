package com.easy_split.demo.enums;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    final String roleUser;

    Role(String role) {
        this.roleUser = role;
    }

    public String getRole() {
        return this.roleUser;
    }
}
