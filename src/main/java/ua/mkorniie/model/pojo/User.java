package ua.mkorniie.model.pojo;

import lombok.*;
import org.apache.log4j.Logger;
import ua.mkorniie.model.enums.Language;
import ua.mkorniie.model.enums.Role;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private static final Logger logger = Logger.getLogger(Room.class);


    @Getter @Setter private int         id;
    @Getter         private String      name;
    @Getter         private Role        role;
    @Getter         private String      passwordEncoded;
    @Getter         private String      email;
    @Getter         private Language    language;

    public User(String name, Role role, String passwordEncoded, String email, Language language) {
        setName(name);
        setRole(role);
        setPasswordEncoded(passwordEncoded);
        setEmail(email);
        setLanguage(language);

        logger.info("Object User successfully created");
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            logger.error("Error: 'name' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setPasswordEncoded(String passwordEncoded) {
        if (passwordEncoded != null) {
            this.passwordEncoded = passwordEncoded;
        } else {
            logger.error("Error: 'passwordEncoded' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        } else {
            logger.error("Error: 'email' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setRole(Role role) {
        if (role != null) {
            this.role = role;
        } else {
            logger.error("Error: 'role' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setLanguage(Language language) {
        if (language != null) {
            this.language = language;
        } else {
            logger.error("Error: 'language' object can not be null");
            throw new NullPointerException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                name.equals(user.name) &&
                role == user.role &&
                passwordEncoded.equals(user.passwordEncoded) &&
                email.equals(user.email) &&
                language == user.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role, passwordEncoded, email, language);
    }
}

