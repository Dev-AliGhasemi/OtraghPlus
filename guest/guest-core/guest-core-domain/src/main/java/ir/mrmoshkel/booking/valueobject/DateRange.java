package ir.mrmoshkel.booking.valueobject;

import java.sql.Date;
import java.time.temporal.ChronoUnit;

public record DateRange(Date startDate,
                        Date endDate) {

    public long lengthInDays() {
        return ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
    }

    public boolean isInProgressAt(Date date) {
        return isStartedAt(date) && date.before(endDate);
    }

    public boolean isStartedAt(Date date) {
        return date.after(startDate);
    }

    public boolean isNotStartedAt(Date date) {
        return date.before(startDate);
    }

    public boolean isFinishedAt(Date date) {
        return date.after(endDate);
    }

}
