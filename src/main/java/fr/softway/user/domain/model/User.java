package fr.softway.user.domain.model;

public class User {

    private final UserId id;
    private final String fullName;
    private final Mail mail;
    private final Phone phone;

    public User(UserId id, String fullName, String strMail, String strPhone) {
        this.id = id;
        this.fullName = fullName;
        this.mail = new Mail(strMail);
        this.phone = new Phone(strPhone);
    }

    public String idValue() {
        return id.value();
    }

    public String fullName() {
        return fullName;
    }

    public String mailValue() {
        return mail.value();
    }

    public String phoneValue() {
        return phone.value();
    }

}
