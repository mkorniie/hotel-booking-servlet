package ua.mkorniie.model.pojo;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import ua.mkorniie.controller.util.DateUtil;

import java.sql.Date;
import java.util.Objects;

@Getter
public class DatePair {
    private Date startDate;
    private Date endDate;

    public DatePair(@NotNull Date startDate, @NotNull Date endDate) {
        if (DateUtil.coherentDates(startDate, endDate)) {
            this.startDate = startDate;
            this.endDate = endDate;
        } else {
            throw new IllegalArgumentException("The dates are not coherent:\n" +
                    "\tstart date: " + startDate + "\n" +
                    "\tend date: " + endDate);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatePair datePair = (DatePair) o;
        return startDate.toString().equals(datePair.startDate.toString()) &&
                endDate.toString().equals(datePair.endDate.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
