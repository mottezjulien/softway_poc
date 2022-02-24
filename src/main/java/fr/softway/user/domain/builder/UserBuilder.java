package fr.softway.user.domain.builder;

import fr.softway.user.domain.model.User;
import fr.softway.user.domain.model.UserId;

public class UserBuilder {

    public static UserBuilder create() {
        return new UserBuilder();
    }

    public static UserBuilder createWithName(String fullName) {
        return create().withName(fullName);
    }

    private UserId id = new UserId();
    private String fullName = "";
    private String mail = "";
    private String phone = "";

    private UserBuilder() {
    }

    private UserBuilder withId(String strId) {
        this.id = new UserId(strId);
        return this;
    }

    public UserBuilder withName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserBuilder withMail(String strMail) {
        this.mail = strMail;
        return this;
    }

    public UserBuilder withPhone(String strPhone) {
        this.phone = strPhone;
        return this;
    }

    public User make() {
        return new User(id, fullName, mail, phone);
    }

}
