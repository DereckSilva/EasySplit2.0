package com.easy_split.demo.enums;

public enum Action {
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    SEARCH("SEARCH");

    private String action;

    Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }
}
