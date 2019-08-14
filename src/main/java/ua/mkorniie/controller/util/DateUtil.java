package ua.mkorniie.controller.util;

import com.sun.istack.internal.NotNull;

import java.sql.Date;

public class DateUtil {
    /**
     * Checks if start date (first parameter) is before or same day as end date (second parameter)
     * @param startDate -  date that's supposed to be earlier
     * @param endDate - date that's supposed to be later
     * @return returns 'true' if start date is before or equals end date
     */
    public static boolean coherentDates(@NotNull Date startDate, @NotNull Date endDate) {
        return  startDate.before(endDate) || startDate.equals(endDate);
    }

    private DateUtil() {}
}
