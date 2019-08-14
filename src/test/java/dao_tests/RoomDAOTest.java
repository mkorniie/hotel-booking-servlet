package dao_tests;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.mkorniie.controller.dao.RoomDAO;
import ua.mkorniie.model.enums.RoomClass;
import ua.mkorniie.model.pojo.Room;

import java.util.List;


class RoomDAOTest {
    private static final Logger logger = Logger.getLogger(RoomDAOTest.class);
    private static List<Room> before;
    static RoomDAO roomDAO = new RoomDAO();

    static Room getTestObject() {
        Room test = new Room();

        test.setPlaces(2);
        test.setRoomClass(RoomClass.lux);
        test.setOccupied(false);
        test.setPicURL("testURL");
        test.setPrice(29.999);
        return test;
    }

    @BeforeAll
    static void setUp() {
        logger.info("Starting test suite: before");
        before = roomDAO.selectAll();
        for(Room r : before) {
            logger.info("Contains entry: " + r);
        }
    }

    @Test
    void selectAllTest() {

        assert (roomDAO.selectAll() != null);
        int size = roomDAO.selectAll().size();

        Room test = getTestObject();
        roomDAO.insert(test);
        int id = roomDAO.findId(test);
        assert (id != -1);

        assert(roomDAO.selectAll().size() == (size + 1));

        roomDAO.delete(id);
        id = roomDAO.findId(test);
        assert (id == -1);
        assert(roomDAO.selectAll().size() == (size));
    }

    @Test
    void insertDeleteFindTest() {
        Room test = getTestObject();
        roomDAO.insert(test);

        int id = roomDAO.findId(test);
        assert (id != -1);

        roomDAO.delete(id);
        id = roomDAO.findId(test);
        assert (id == -1);
    }

    @Test
    void updateTest() {
        Room test = getTestObject();
        roomDAO.insert(test);

        int id = roomDAO.findId(test);
        assert (id != -1);
        test.setId(id);

        test.setPlaces(1);
        test.setRoomClass(RoomClass.second);
        roomDAO.update(test);

        int id2 = roomDAO.findId(test);
        assert (id == id2);

        roomDAO.delete(id);
        id = roomDAO.findId(test);
        assert (id == -1);
    }

    @Test
    void selectByIdTest() {
        Room test = getTestObject();
        roomDAO.insert(test);

        int id = roomDAO.findId(test);
        assert (id != -1);
        test.setId(id);

        Room testCopy = roomDAO.selectById(id);

        assert (test.equals(testCopy));

        roomDAO.delete(id);
        id = roomDAO.findId(test);
        assert (id == -1);
    }

    @AfterAll
    static void after() {
        logger.info("After executing test suite:");
        List<Room> after = roomDAO.selectAll();
        assert (before.equals(after));
        logger.info("The database hasn't changed!");

        for(Room r : after) {
            logger.info("Contains entry: " + r);
        }
    }
}
