package fr.softway.notification.instrastructure.persistence;

import fr.softway.notification.domain.model.NotificationDetails;
import fr.softway.notification.domain.model.NotificationId;
import fr.softway.notification.domain.model.NotificationSimplify;
import fr.softway.template.domain.model.Template;
import fr.softway.template.domain.model.TemplateId;
import fr.softway.user.domain.model.User;
import fr.softway.user.domain.model.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class NotificationRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public NotificationSimplify create(NotificationSimplify notification) {
        String query = "INSERT INTO NOTIFICATION (id, sender, template) VALUES (?, ?, ?)";
        jdbc.execute(query, (PreparedStatementCallback<Boolean>) ps -> {
            ps.setString(1, notification.idValue());
            ps.setString(2, notification.senderValue());
            ps.setString(3, notification.templateIdValue());
            return ps.execute();
        });

        notification.recipientValues().forEach(recipientValue -> {
            String queryRecipient = "INSERT INTO NOTIFICATION_RECIPIENT (notification_id, user_id) VALUES (?, ?)";
            jdbc.execute(queryRecipient, (PreparedStatementCallback<Boolean>) ps -> {
                ps.setString(1, notification.idValue());
                ps.setString(2, recipientValue);
                return ps.execute();
            });
        });
        return notification;
    }

    public Stream<NotificationDetails> findAllDetails() {
        String query = "SELECT *, " + selectUser("user_sender")  + ", " + selectUser("user_rec") + "  FROM NOTIFICATION" +
                " LEFT JOIN NOTIFICATION_RECIPIENT ON NOTIFICATION.id = NOTIFICATION_RECIPIENT.notification_id" +
                " LEFT JOIN TEMPLATE ON NOTIFICATION.template = TEMPLATE.id" +
                " LEFT JOIN USER user_sender ON NOTIFICATION.sender = user_sender.id" +
                " LEFT JOIN USER user_rec ON NOTIFICATION_RECIPIENT.user_id = user_rec.id";
        System.out.println("query:" + query);
        RowMapper<NotificationDetails> rowMapper = (resultSet, index) -> {
            String idValue = resultSet.getString("NOTIFICATION.id");
            return new NotificationDetails(new NotificationId(idValue), template(resultSet), user("user_sender", resultSet), List.of(user("user_rec", resultSet)));
        };
        return jdbc.queryForStream(query, rowMapper);

    }

    public Stream<NotificationDetails> findDetailsByRecipient(String recipient) {
        String query = "SELECT *, " + selectUser("user_sender")  + ", " + selectUser("user_rec") + "  FROM NOTIFICATION" +
                " LEFT JOIN NOTIFICATION_RECIPIENT ON NOTIFICATION.id = NOTIFICATION_RECIPIENT.notification_id" +
                " LEFT JOIN TEMPLATE ON NOTIFICATION.template = TEMPLATE.id" +
                " LEFT JOIN USER user_sender ON NOTIFICATION.sender = user_sender.id" +
                " LEFT JOIN USER user_rec ON NOTIFICATION_RECIPIENT.user_id = user_rec.id" +
                " WHERE user_rec.id = ?";
        System.out.println("query:" + query);
        RowMapper<NotificationDetails> rowMapper = (resultSet, index) -> {
            String idValue = resultSet.getString("NOTIFICATION.id");
            return new NotificationDetails(new NotificationId(idValue), template(resultSet), user("user_sender", resultSet), List.of(user("user_rec", resultSet)));
        };
        return jdbc.queryForStream(query, rowMapper, recipient);
    }




    private Template template(ResultSet resultSet) throws SQLException {
        String templateId = resultSet.getString("TEMPLATE.id");
        String templateCode = resultSet.getString("TEMPLATE.code");
        String templateFrLabel = resultSet.getString("TEMPLATE.fr_label");
        Template template = new Template(new TemplateId(templateId), templateCode, templateFrLabel);
        return template;
    }

    private String selectUser(String prefix) {
        return prefix + ".id AS " + prefix + "_id"
                + ", " + prefix + ".full_name AS " + prefix + "_full_name"
                + ", " + prefix + ".mail AS " + prefix + "_mail"
                + ", " + prefix + ".phone AS " + prefix + "_phone";
    }

    private User user(String prefix, ResultSet resultSet) throws SQLException {
        String senderId = resultSet.getString(prefix + "_id");
        String senderFullName = resultSet.getString(prefix + "_full_name");
        String senderMail = resultSet.getString(prefix + "_mail");
        String senderPhone = resultSet.getString(prefix + "_phone");
        User sender = new User(new UserId(senderId), senderFullName, senderMail, senderPhone);
        return sender;
    }
}
