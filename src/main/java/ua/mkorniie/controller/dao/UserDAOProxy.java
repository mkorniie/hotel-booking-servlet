package ua.mkorniie.controller.dao;


import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.mkorniie.model.enums.Role;
import ua.mkorniie.model.pojo.User;

import java.util.Optional;

import static ua.mkorniie.controller.util.StringConverter.strToInt;

public class UserDAOProxy extends UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAOProxy.class);

    public void delete(@NotNull String id) {
        Optional<Integer> intId = strToInt(id);
        if (intId.isPresent()) {
            delete(intId.get());
            logger.info("User object with id " + id + " deleted successfully");
        } else {
            logger.error("User object with id " + id + " hasn't been deleted. Wrong id");
        }
    }

    public void toAdmin(@NotNull String id) {
        Optional<Integer> intId = strToInt(id);
        if (intId.isPresent()) {
            User object = selectById(intId.get());
            object.setRole(Role.ADMIN);
            update(object);
            logger.info("User object with id " + id + " has been granted ADMIN rights successfully");
        } else {
            logger.error("User object with id " + id + " hasn't been promoted to ADMIN. Wrong id");
        }
    }

    public void toUser(@NotNull String id) {
        Optional<Integer> intId = strToInt(id);
        if (intId.isPresent()) {
            User object = selectById(intId.get());
            object.setRole(Role.USER);
            update(object);
            logger.info("User object with id " + id + " has been granted ADMIN rights successfully");
        } else {
            logger.error("User object with id " + id + " hasn't been promoted to ADMIN. Wrong id");
        }
    }
}
