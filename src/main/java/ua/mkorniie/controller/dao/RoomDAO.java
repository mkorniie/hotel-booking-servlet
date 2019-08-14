package ua.mkorniie.controller.dao;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.mkorniie.model.enums.RoomClass;
import ua.mkorniie.model.pojo.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAO extends AbstractDAO<Room> {
    private static final Logger logger = Logger.getLogger(RoomDAO.class);

    private static final String INSERT = "INSERT INTO rooms" + "  (places, class, isOccupied,  picURL, price) VALUES " +
            " (?, ?, ?, ?, ?);";

    private static final String SELECT_BY_ID = "SELECT id, places, class, isOccupied, picURL, price FROM rooms " +
            "WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT * FROM rooms;";
    private static final String DELETE = "DELETE FROM rooms " +
            "WHERE id = ?;";
    private static final String UPDATE = "UPDATE rooms SET places=?, class=?, isOccupied=?, picURL=?, price=? " +
            "WHERE id = ?;";
    private static final String FIND = "SELECT id FROM rooms " +
            "WHERE (places=? AND class=? AND isOccupied=? AND picURL=? AND price=?);";

    Logger getLogger() { return logger; }

    String getInsert() { return INSERT; }

    String getSelectById() { return SELECT_BY_ID; }

    String getSelectAll() { return SELECT_ALL; }

    String getDelete() { return DELETE; }

    String getUpdate() { return UPDATE; }

    String getFind() { return FIND;}

    @Override
    void setStatement(@NotNull PreparedStatement preparedStatement, @NotNull Room object) throws SQLException {
        preparedStatement.setInt(1, object.getPlaces());
        preparedStatement.setString(2, object.getRoomClass().toString());
        preparedStatement.setBoolean(3, object.isOccupied() );
        preparedStatement.setString(4, object.getPicURL());
        preparedStatement.setDouble(5, object.getPrice());
    }

    @Override
    void setUpdateStatementId(@NotNull PreparedStatement preparedStatement, @NotNull Room object) throws SQLException {
        preparedStatement.setInt(6, object.getId());
    }

    @Override
    Room setObjectParams(@NotNull ResultSet rs) throws SQLException {
        int places = rs.getInt("places");
        RoomClass roomClass = RoomClass.valueOf(rs.getString("class"));
        boolean isOccupied = rs.getBoolean("isOccupied");
        String picURL = rs.getString("picURL");
        double price = rs.getDouble("price");

        Room object = new Room();
        object.setPlaces(places);
        object.setRoomClass(roomClass);
        object.setOccupied(isOccupied);
        object.setPicURL(picURL);
        object.setPrice(price);
        return object;
    }

    @Override
    void setObjectId(@NotNull ResultSet rs, @NotNull Room object) throws SQLException {
        int id = rs.getInt("id");
        object.setId(id);
    }
}
