package dao_tests;

import com.sun.istack.internal.NotNull;
import ua.mkorniie.controller.dao.BillDAO;
import ua.mkorniie.controller.dao.RequestDAO;
import ua.mkorniie.controller.dao.RoomDAO;
import ua.mkorniie.controller.dao.UserDAO;
import ua.mkorniie.model.pojo.Bill;
import ua.mkorniie.model.pojo.Request;
import ua.mkorniie.model.pojo.Room;
import ua.mkorniie.model.pojo.User;

public class TestUtil {

    static void insertObject(@NotNull User testObject, @NotNull UserDAO userDAO) {
        userDAO.insert(testObject);
        testObject.setId(userDAO.findId(testObject));
    }

    static void deleteObject(@NotNull User testObject, @NotNull UserDAO userDAO) {
        userDAO.delete(testObject.getId());
    }

    static void insertObject(@NotNull Room testObject, @NotNull RoomDAO roomDAO) {
        roomDAO.insert(testObject);
        testObject.setId(roomDAO.findId(testObject));
    }

    static void deleteObject(@NotNull Room testObject, @NotNull RoomDAO roomDAO) {
        roomDAO.delete(testObject.getId());
    }

    static void insertObject(@NotNull Request testObject, @NotNull RequestDAO requestDAO) {
        insertObject(testObject.getUser(), new UserDAO());
        requestDAO.insert(testObject);
        testObject.setId(requestDAO.findId(testObject));
    }

    static void deleteObject(@NotNull Request testObject, @NotNull RequestDAO requestDAO) {
        requestDAO.delete(testObject.getId());
        deleteObject(testObject.getUser(), new UserDAO());
    }

    static void insertObject(@NotNull Bill testObject, @NotNull BillDAO billDAO) {
        insertObject(testObject.getRoom(), new RoomDAO());
        insertObject(testObject.getRequest(), new RequestDAO());
        billDAO.insert(testObject);
        testObject.setId(billDAO.findId(testObject));
    }

    static void deleteObject(@NotNull Bill testObject, @NotNull BillDAO billDAO) {
        billDAO.delete(testObject.getId());
        deleteObject(testObject.getRequest(), new RequestDAO());
        deleteObject(testObject.getRoom(), new RoomDAO());
    }
}
