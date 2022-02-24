package fr.softway.notification.domain.model;

import fr.softway.template.domain.model.Template;
import fr.softway.template.domain.model.TemplateId;
import fr.softway.user.domain.model.User;
import fr.softway.user.domain.model.UserId;

import java.util.List;
import java.util.stream.Stream;

public class NotificationDetails {

    private final NotificationId id;
    private final Template template;
    private final User sender;
    private final List<User> recipients;

    public NotificationDetails(NotificationId id, Template template, User sender, List<User> recipients) {
        this.id = id;
        this.template = template;
        this.sender = sender;
        this.recipients = recipients;
    }

    public String idValue() {
        return id.value();
    }

    public String templateCode() {
        return template.code();
    }

    public String templateFrLabel() {
        return template.frLabel();
    }

    public String senderFullName() {
        return sender.fullName();
    }

    public String firstRecipientFullName() {
        return recipients.get(0).fullName();
    }
}
