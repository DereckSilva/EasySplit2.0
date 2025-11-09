package com.easy_split.demo.enums;

public enum Action {
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    SEARCH("SEARCH");

    final String actionField;

    Action(String action) {
        this.actionField = action;
    }

    public String getActionField() {
        return this.actionField;
    }
}
