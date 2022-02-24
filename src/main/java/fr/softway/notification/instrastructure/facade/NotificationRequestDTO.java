package fr.softway.notification.instrastructure.facade;


import java.util.ArrayList;
import java.util.List;

public class NotificationRequestDTO {

    private String template;
    private String sender;
    private List<String> recipients = new ArrayList<>();

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }


}
