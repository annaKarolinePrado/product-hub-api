package com.games.enums;

public enum Profile {
    FREE("Free"),
    PREMIUM ("Premium"),
    ROOT ("Root");

    private final String description;

    Profile(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
