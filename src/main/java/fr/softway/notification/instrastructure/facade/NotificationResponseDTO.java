package fr.softway.notification.instrastructure.facade;

import java.util.List;

public class NotificationResponseDTO {

    private String id;
    private String template;
    private String sender;
    private List<String> recipients;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public List<String> getRecipients() {
        return recipients;
    }
}
