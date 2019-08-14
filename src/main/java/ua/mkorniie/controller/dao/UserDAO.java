package ua.mkorniie.controller.dao;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.mkorniie.model.enums.Language;
import ua.mkorniie.model.enums.Role;
import ua.mkorniie.model.pojo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO<User> {
    private static final Logger logger = Logger.getLogger(UserDAO.class);

    private static final String INSERT = "INSERT INTO users" + "  (name, role, pass_encoded,  email, language) VALUES " +
            " (?, ?, ?, ?, ?);";

    private static final String SELECT_BY_ID = "SELECT id, name, role, pass_encoded, email, language FROM users " +
            "WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT * FROM users;";
    private static final String DELETE = "DELETE FROM users " +
            "WHERE id = ?;";
    private static final String UPDATE = "UPDATE users SET name=?, role=?, pass_encoded=?, email=?, language=? " +
            "WHERE id = ?;";
    private static final String FIND = "SELECT id FROM users " +
            "WHERE (name=? AND role=? AND pass_encoded=? AND email=? AND language=?);";

    Logger getLogger() {
        return logger;
    }

    String getInsert() {
        return INSERT;
    }

    String getSelectById() {
        return SELECT_BY_ID;
    }

    String getSelectAll() {
        return SELECT_ALL;
    }

    String getDelete() {
        return DELETE;
    }

    String getUpdate() {
        return UPDATE;
    }

    String getFind() {
        return FIND;
    }

    @Override
    void setStatement(@NotNull PreparedStatement preparedStatement, @NotNull User object) throws SQLException {
        preparedStatement.setString(1, object.getName());
        preparedStatement.setString(2, object.getRole().toString());
        preparedStatement.setString(3, object.getPasswordEncoded());
        preparedStatement.setString(4, object.getEmail());
        preparedStatement.setString(5, object.getLanguage().toString());
    }

    @Override
    void setUpdateStatementId(@NotNull PreparedStatement preparedStatement, @NotNull User object) throws SQLException {
        preparedStatement.setInt(6, object.getId());
    }

    @Override
    User setObjectParams(@NotNull ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        Role role = Role.valueOf(rs.getString("role"));
        String pass = rs.getString("pass_encoded");
        String email = rs.getString("email");
        Language language = Language.valueOf(rs.getString("language"));

        User object = new User();
        object.setName(name);
        object.setRole(role);
        object.setPasswordEncoded(pass);
        object.setEmail(email);
        object.setLanguage(language);
        return object;
    }

    @Override
    void setObjectId(@NotNull ResultSet rs, @NotNull User object) throws SQLException {
        int id = rs.getInt("id");
        object.setId(id);
    }
}