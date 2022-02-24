package fr.softway.template.domain.model;

import java.util.UUID;

public class TemplateId {

    private final String value;

    public TemplateId() {
        this.value = UUID.randomUUID().toString();
    }

    public TemplateId(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
