package puig.xeill.Clinic.Model.Enums;

import java.util.Calendar;
import java.util.Date;

public enum WeekDay {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY;


    public static WeekDay fromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        // Mapear Calendar.DAY_OF_WEEK a WeekDay, asumiendo que WeekDay empieza en MONDAY=0
        switch (dayOfWeek) {
            case Calendar.MONDAY: return WeekDay.MONDAY;
            case Calendar.TUESDAY: return WeekDay.TUESDAY;
            case Calendar.WEDNESDAY: return WeekDay.WEDNESDAY;
            case Calendar.THURSDAY: return WeekDay.THURSDAY;
            case Calendar.FRIDAY: return WeekDay.FRIDAY;
            default: throw new IllegalArgumentException("Invalid day of week");
        }
    }
}



