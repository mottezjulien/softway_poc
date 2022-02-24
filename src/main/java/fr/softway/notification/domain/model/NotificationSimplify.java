package fr.softway.notification.domain.model;

import fr.softway.template.domain.model.TemplateId;
import fr.softway.user.domain.model.UserId;

import java.util.List;
import java.util.stream.Stream;

public class NotificationSimplify {

    private final NotificationId id;
    private final TemplateId templateId;
    private final UserId sender;
    private final List<UserId> recipients;

    public NotificationSimplify(NotificationId id, TemplateId templateId, UserId sender, List<UserId> recipients) {
        this.id = id;
        this.templateId = templateId;
        this.sender = sender;
        this.recipients = recipients;
    }

    public String idValue() {
        return id.value();
    }

    public String templateIdValue() {
        return templateId.value();
    }

    public String senderValue() {
        return sender.value();
    }

    public Stream<String> recipientValues() {
        return recipients.stream().map(recipient -> recipient.value());
    }


}
