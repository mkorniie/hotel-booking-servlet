package ua.mkorniie.model.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import ua.mkorniie.controller.util.Rounder;
import ua.mkorniie.model.enums.RoomClass;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private static final Logger logger = Logger.getLogger(Room.class);


    @Getter @Setter private int                 id;
    @Getter @Setter private int                 places;
    @Getter         private RoomClass           roomClass;
    @Getter @Setter private boolean             isOccupied;
    @Getter         private String              picURL;
    @Getter         private double              price;

    public Room(int places, RoomClass roomClass, boolean isOccupied, String picURL, double price) {
        this.places = places;
        setRoomClass(roomClass);
        this.isOccupied = isOccupied;
        setPicURL(picURL);
        setPrice(price);

        logger.info("Object Room successfully created");
    }

    public void setPrice(double price) {
        this.price = Rounder.round(price);
        logger.info("Double value of 'price' field set succesfully: " + this.price);
    }

    public void setRoomClass(RoomClass roomClass) {
        if (roomClass != null) {
            this.roomClass = roomClass;
        } else {
            logger.error("Error: 'roomClass' object can not be null");
            throw new NullPointerException();
        }
    }

    public void setPicURL(String picURL) {
        if (picURL != null) {
            this.picURL = picURL;
        } else {
            logger.error("Error: 'picURL' object can not be null");
            throw new NullPointerException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                places == room.places &&
                isOccupied == room.isOccupied &&
                Double.compare(room.price, price) == 0 &&
                roomClass == room.roomClass &&
                picURL.equals(room.picURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, places, roomClass, isOccupied, picURL, price);
    }
}
