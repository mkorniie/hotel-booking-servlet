package ua.mkorniie.controller.dao;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.mkorniie.model.enums.RoomClass;
import ua.mkorniie.model.pojo.DatePair;
import ua.mkorniie.model.pojo.Request;
import ua.mkorniie.model.pojo.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestDAO extends AbstractDAO<Request> {
    private static final Logger logger = Logger.getLogger(RequestDAO.class);

    private static final String INSERT = "INSERT INTO requests" + "  (users_id, places, class, start_date, end_date, isApproved) VALUES " +
            " (?, ?, ?, ?, ?, ?);";

    private static final String SELECT_BY_ID = "SELECT * FROM requests " +
            "WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT * FROM requests;";
    private static final String DELETE = "DELETE FROM requests WHERE id = ?;";
    private static final String UPDATE = "UPDATE requests SET users_id=?, places=?, class=?, start_date=?, end_date=?, isApproved=? " +
            "WHERE id = ?;";
    private static final String FIND = "SELECT id FROM requests " +
            "WHERE (users_id=? AND places=? AND class=? AND start_date=? AND end_date=? AND isApproved=?);";

    Logger getLogger() { return logger; }

    String getInsert() { return INSERT; }

    String getSelectById() { return SELECT_BY_ID; }

    String getSelectAll() { return SELECT_ALL; }

    String getDelete() { return DELETE; }

    String getUpdate() { return UPDATE; }

    String getFind() { return FIND;}

    @Override
    void setStatement(@NotNull PreparedStatement preparedStatement, @NotNull Request object) throws SQLException {
            preparedStatement.setInt(1, object.getUser().getId());
            preparedStatement.setInt(2, object.getPlaces());
            preparedStatement.setString(3, object.getRoomClass().name());
            preparedStatement.setString(4, object.getDatePair().getStartDate().toString());
            preparedStatement.setString(5, object.getDatePair().getEndDate().toString());
            preparedStatement.setBoolean(6, object.isApproved());
    }

    @Override
    void setUpdateStatementId(@NotNull PreparedStatement preparedStatement, @NotNull Request object) throws SQLException {
        preparedStatement.setInt(7, object.getId());
    }

    @Override
    Request setObjectParams(@NotNull ResultSet rs) throws SQLException {
        User user = new UserDAO().selectById(rs.getInt("users_id"));
        int places = rs.getInt("places");
        RoomClass roomClass = RoomClass.valueOf(rs.getString("class"));
        Date startDate = rs.getDate("start_date");
        Date endtDate = rs.getDate("end_date");
        boolean isApproved = rs.getBoolean("isApproved");

        return new Request(user, places, roomClass, new DatePair(startDate, endtDate), isApproved);
    }

    @Override
    void setObjectId(@NotNull ResultSet rs, @NotNull Request object) throws SQLException {
        int id = rs.getInt("id");
        object.setId(id);
    }
}
