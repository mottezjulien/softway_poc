package fr.softway.notification.instrastructure.facade;

public class NotificationResponseDetailsDTO {

    private String id;
    private String templateCode;
    private String templateFrLabel;
    private String senderFullName;
    private String firstRecipient;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateFrLabel(String templateFrLabel) {
        this.templateFrLabel = templateFrLabel;
    }

    public String getTemplateFrLabel() {
        return templateFrLabel;
    }

    public void setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public void setFirstRecipient(String firstRecipient) {
        this.firstRecipient = firstRecipient;
    }

    public String getFirstRecipient() {
        return firstRecipient;
    }
}
