package fr.softway.notification.domain.model;

import java.util.UUID;

public class NotificationId {

    private final String value;

    public NotificationId() {
        this.value = UUID.randomUUID().toString();
    }

    public NotificationId(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
