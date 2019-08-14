package ua.mkorniie.model.pojo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import ua.mkorniie.model.enums.RoomClass;

import java.sql.Date;
import java.util.Objects;

// TODO: Finish adding @NotNull

@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private static final Logger logger = Logger.getLogger(Request.class);

    @Getter @Setter private int                 id;
    @Getter         private User                user;
    @Getter         private int                 places;
    @Getter         private RoomClass           roomClass;
    @Getter         private DatePair            datePair;
    @Getter @Setter private boolean             isApproved;

    public Request(@NotNull User user, int places, @NotNull RoomClass roomClass, @NotNull DatePair datePair, boolean isApproved) {
        setUser(user);
        setPlaces(places);
        setRoomClass(roomClass);
        setDatePair(datePair);
        this.isApproved = isApproved;

        logger.info("Object Request successfully created");
    }

    public void setDatePair(@NotNull DatePair datePair) {
        this.datePair = datePair;
    }

    //Шаблон Делегат?
    public Date getStartDate() {
        return  datePair == null? null : this.datePair.getStartDate();
    }

    //Шаблон Делегат?
    public Date getEndDate() {
        return  datePair == null? null : this.datePair.getEndDate();
    }

    public void setUser(@NotNull User user) {
            this.user = user;
    }

    public void setRoomClass(@NotNull RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public void setPlaces(int places) {
        if (places > 0) {
            this.places = places;
        } else {
            throw new IllegalArgumentException("Number of places is less then zero");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id &&
                places == request.places &&
                isApproved == request.isApproved &&
                user.equals(request.user) &&
                roomClass == request.roomClass &&
                datePair.equals(request.datePair);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, places, roomClass, datePair, isApproved);
    }
}
