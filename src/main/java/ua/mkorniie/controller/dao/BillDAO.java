package ua.mkorniie.controller.dao;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.mkorniie.model.pojo.Bill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDAO extends AbstractDAO<Bill> {
    private static final Logger logger = Logger.getLogger(BillDAO.class);

    private static final String INSERT = "INSERT INTO bills" + "  (sum, isPaid, requests_id, rooms_id) VALUES " +
            " (?, ?, ?, ?);";

    private static final String SELECT_BY_ID = "SELECT * FROM bills " +
            "WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT * FROM bills;";
    private static final String DELETE = "DELETE FROM bills WHERE id = ?;";
    private static final String UPDATE = "UPDATE bills SET sum=?, isPaid=?, requests_id=?, rooms_id=? " +
            "WHERE id = ?;";

    private static final String FIND = "SELECT id FROM bills " +
            "WHERE (sum=? AND isPaid=? AND requests_id=? AND  rooms_id=? );";


    Logger getLogger() { return logger; }

    String getInsert() { return INSERT; }

    String getSelectById() { return SELECT_BY_ID; }

    String getSelectAll() { return SELECT_ALL; }

    String getDelete() { return DELETE; }

    String getUpdate() { return UPDATE; }

    String getFind() { return FIND;}

    @Override
    void setStatement(@NotNull PreparedStatement preparedStatement, @NotNull Bill object) throws SQLException {
        preparedStatement.setDouble(1, object.getSum());
        preparedStatement.setBoolean(2, object.isPaid());
        preparedStatement.setInt(3, object.getRequest().getId());
        preparedStatement.setInt(4, object.getRoom().getId());
    }

    @Override
    void setUpdateStatementId(@NotNull PreparedStatement preparedStatement, @NotNull Bill object) throws SQLException {
        preparedStatement.setInt(5, object.getId());
    }

    @Override
    Bill setObjectParams(@NotNull ResultSet rs) throws SQLException {
        double sum = rs.getDouble("sum");
        boolean isPaid = rs.getBoolean("isPaid");
        int req_id = rs.getInt("requests_id");
        int room_id = rs.getInt("rooms_id");

        return new Bill(sum, isPaid,
                new RequestDAO().selectById(req_id),
                new RoomDAO().selectById(room_id));
    }

    @Override
    void setObjectId(@NotNull ResultSet rs, @NotNull Bill object) throws SQLException {
        int id = rs.getInt("id");
        object.setId(id);
    }
}