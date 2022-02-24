package fr.softway.user.domain.model;

import java.util.UUID;

public class UserId {

    private final String value;

    public UserId() {
        this.value = UUID.randomUUID().toString();
    }

    public UserId(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
