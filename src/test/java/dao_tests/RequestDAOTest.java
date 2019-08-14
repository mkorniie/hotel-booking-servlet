package dao_tests;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.mkorniie.controller.dao.RequestDAO;
import ua.mkorniie.controller.dao.UserDAO;
import ua.mkorniie.model.enums.RoomClass;
import ua.mkorniie.model.pojo.DatePair;
import ua.mkorniie.model.pojo.Request;
import ua.mkorniie.model.pojo.User;

import java.sql.Date;
import java.util.List;


class RequestDAOTest {
    private static final Logger logger = Logger.getLogger(RequestDAOTest.class);
    private static List<Request> before;
    private static RequestDAO requestDAO = new RequestDAO();

    private static User userTestObject;

    static Request getTestObject() {
        if (userTestObject == null)  {
            userTestObject = UserDAOTest.getTestObject();
        }
        return new Request( userTestObject,
                2,
                RoomClass.lux,
                new DatePair(Date.valueOf("2015-03-29"), Date.valueOf("2015-03-30")),
                true );
    }

    @BeforeAll
    static void setUp() {
        logger.info("Starting test suite: before");
        userTestObject = UserDAOTest.getTestObject();
        TestUtil.insertObject(userTestObject, new UserDAO());

        before = requestDAO.selectAll();
        for(Request r : before) {
            logger.info("Contains entry: " + r);
        }
    }

    @Test
    void selectAllTest() {

        assert (requestDAO.selectAll() != null);
        int size = requestDAO.selectAll().size();

        Request test = getTestObject();

        requestDAO.insert(test);
        int id = requestDAO.findId(test);
        assert (id != -1);

        assert(requestDAO.selectAll().size() == (size + 1));

        requestDAO.delete(id);
        id = requestDAO.findId(test);
        assert (id == -1);
        assert(requestDAO.selectAll().size() == (size));
    }

    @Test
    void insertDeleteFindTest() {
        Request test = getTestObject();
        requestDAO.insert(test);

        int id = requestDAO.findId(test);
        assert (id != -1);

        requestDAO.delete(id);
        id = requestDAO.findId(test);
        assert (id == -1);
    }

    @Test
    void updateTest() {
        Request test = getTestObject();
        requestDAO.insert(test);

        int id = requestDAO.findId(test);
        assert (id != -1);
        test.setId(id);

        test.setApproved(false);
        requestDAO.update(test);

        int id2 = requestDAO.findId(test);
        assert (id == id2);

        requestDAO.delete(id);
        id = requestDAO.findId(test);
        assert (id == -1);
    }

    @Test
    void selectByIdTest() {
        Request test = getTestObject();

        requestDAO.insert(test);

        int id = requestDAO.findId(test);
        assert (id != -1);
        test.setId(id);

        Request testCopy = requestDAO.selectById(id);

        assert (test.equals(testCopy));

        requestDAO.delete(id);
        id = requestDAO.findId(test);
        assert (id == -1);
    }

    @AfterAll
    static void after() {
        logger.info("After executing test suite:");
        List<Request> after = requestDAO.selectAll();
        assert (before.equals(after));
        logger.info("The database hasn't changed!");

        for(Request r : after) {
            logger.info("Contains entry: " + r);
        }

        TestUtil.deleteObject(userTestObject, new UserDAO());
    }

}
