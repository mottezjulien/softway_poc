package fr.softway;

import fr.softway.template.domain.model.Template;
import fr.softway.template.domain.model.TemplateRepository;
import fr.softway.user.domain.builder.UserBuilder;
import fr.softway.user.infrastructure.persistence.UserRepository;
import fr.softway.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        User pierre = UserBuilder.createWithName("Pierre")
                        .withMail("pierre@softway.fr")
                        .withPhone("0609080706")
                        .make();
        userRepository.create(pierre);
        System.out.println("Pierre:" + pierre.idValue());

        User alice = UserBuilder.createWithName("Alice")
                .withMail("alice@softway.fr")
                .withPhone("0601020304")
                .make();
        userRepository.create(alice);
        System.out.println("Alice:" + alice.idValue());

        Template template = new Template("CODE_001", "Mon message de code 001");
        templateRepository.create(template);
        System.out.println("template:" + template.idValue());

    }

}
