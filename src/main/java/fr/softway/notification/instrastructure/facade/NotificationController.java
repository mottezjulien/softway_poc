package fr.softway.notification.instrastructure.facade;

import fr.softway.notification.domain.model.NotificationDetails;
import fr.softway.notification.domain.model.NotificationId;
import fr.softway.notification.domain.model.NotificationSimplify;
import fr.softway.notification.instrastructure.persistence.NotificationRepository;
import fr.softway.template.domain.model.TemplateId;
import fr.softway.user.domain.model.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository repository;

    @PostMapping("/")
    public NotificationResponseDTO create(@RequestBody NotificationRequestDTO request) {
        return toResponse(repository.create(fromRequest(request)));
    }

   //TODO Test
    @GetMapping("/")
    public List<NotificationResponseDetailsDTO> findAll() {
        return repository.findAllDetails()
                .map(notification -> toDetailsResponse(notification))
                .collect(Collectors.toList());
    }

    @GetMapping("/recipient/{id}/")
    public List<NotificationResponseDetailsDTO> findByRecipient(@PathVariable("id") String recipient) {
        return repository.findDetailsByRecipient(recipient)
                .map(notification -> toDetailsResponse(notification))
                .collect(Collectors.toList());
    }

    private NotificationSimplify fromRequest(NotificationRequestDTO request) {
        TemplateId templateId = new TemplateId(request.getTemplate());
        UserId sender = new UserId(request.getSender());
        List<UserId> recipients = request.getRecipients().stream().map(UserId::new).collect(Collectors.toList());
        return new NotificationSimplify(new NotificationId(), templateId, sender, recipients);
    }

    private NotificationResponseDTO toResponse(NotificationSimplify notification) {
        NotificationResponseDTO response = new NotificationResponseDTO();
        response.setId(notification.idValue());
        response.setTemplate(notification.templateIdValue());
        response.setSender(notification.senderValue());
        response.setRecipients(notification.recipientValues().collect(Collectors.toList()));
        return response;
    }

    private NotificationResponseDetailsDTO toDetailsResponse(NotificationDetails notification) {
        NotificationResponseDetailsDTO response = new NotificationResponseDetailsDTO();
        response.setId(notification.idValue());
        response.setTemplateCode(notification.templateCode());
        response.setTemplateFrLabel(notification.templateFrLabel());
        response.setSenderFullName(notification.senderFullName());
        response.setFirstRecipient(notification.firstRecipientFullName());
        return response;
    }

}
