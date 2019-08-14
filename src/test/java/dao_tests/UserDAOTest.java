package dao_tests;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.mkorniie.controller.dao.UserDAO;
import ua.mkorniie.controller.util.PasswordEncoder;
import ua.mkorniie.model.enums.Language;
import ua.mkorniie.model.enums.Role;
import ua.mkorniie.model.pojo.User;

import java.util.List;

class UserDAOTest {
    private static final Logger logger = Logger.getLogger(UserDAOTest.class);
    private static List<User> before;
    static UserDAO userDAO = new UserDAO();

    static User getTestObject() {
        User test = new User();
        test.setName("Test");
        test.setRole(Role.USER);
        test.setPasswordEncoded(PasswordEncoder.getSHA("12345"));
        test.setEmail("test@test.com");
        test.setLanguage(Language.en);
        return test;
    }

    @BeforeAll
    static void setUp() {
        logger.info("Starting test suite: before");
        before = userDAO.selectAll();
        for(User b : before) {
            logger.info("Contains entry: " + b);
        }
    }

    @Test
    void selectAllTest() {

        assert (userDAO.selectAll() != null);
        int size = userDAO.selectAll().size();

        User test = getTestObject();
        userDAO.insert(test);
        int id = userDAO.findId(test);
        assert (id != -1);

        assert(userDAO.selectAll().size() == (size + 1));

        userDAO.delete(id);
        id = userDAO.findId(test);
        assert (id == -1);
        assert(userDAO.selectAll().size() == (size));
    }

    @Test
    void insertDeleteFindTest() {
        User test = getTestObject();
        userDAO.insert(test);

        int id = userDAO.findId(test);
        assert (id != -1);

        userDAO.delete(id);
        id = userDAO.findId(test);
        assert (id == -1);
    }

    @Test
    void updateTest() {
        User test = getTestObject();
        userDAO.insert(test);

        int id = userDAO.findId(test);
        assert (id != -1);
        test.setId(id);

        test.setName("Test2");
        test.setLanguage(Language.ua);
        userDAO.update(test);

        int id2 = userDAO.findId(test);
        assert (id == id2);

        userDAO.delete(id);
        id = userDAO.findId(test);
        assert (id == -1);
    }

    @Test
    void selectByIdTest() {
        User test = getTestObject();
        userDAO.insert(test);

        int id = userDAO.findId(test);
        assert (id != -1);
        test.setId(id);

        User testCopy = userDAO.selectById(id);

        assert (test.equals(testCopy));

        userDAO.delete(id);
        id = userDAO.findId(test);
        assert (id == -1);
    }

    @AfterAll
    static void after() {
        logger.info("After executing test suite:");
        List<User> after = userDAO.selectAll();
        assert (before.equals(after));
        logger.info("The database hasn't changed!");

        for(User b : after) {
            logger.info("Contains entry: " + b);
        }
    }
}
