package fr.softway.template.domain.model;

import fr.softway.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void create(Template template) {
        String query = "INSERT INTO TEMPLATE (id, code, fr_label) VALUES (?, ?, ?)";
        jdbc.execute(query, (PreparedStatementCallback<Boolean>) ps -> {
            ps.setString(1, template.idValue());
            ps.setString(2, template.code());
            ps.setString(3, template.frLabel());
            return ps.execute();
        });
    }




}
