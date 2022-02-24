package fr.softway.user.infrastructure.persistence;

import fr.softway.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void create(User user) {
        String query = "INSERT INTO USER (id, full_name, mail, phone) VALUES (?, ?, ?, ?)";
        jdbc.execute(query, (PreparedStatementCallback<Boolean>) ps -> {
            ps.setString(1, user.idValue());
            ps.setString(2, user.fullName());
            ps.setString(3, user.mailValue());
            ps.setString(4, user.phoneValue());
            return ps.execute();
        });
    }



}
