package net.fortuna.toolbag.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility methods for working with dates.
 * @author Ben Fortuna
 */
public final class InformalDateFormat {

    private static final String DISTANT_PAST_FORMAT = "dd/MM/yyyy h:mm a";

    private static final String RECENT_PAST_FORMAT = "d MMMM h:mm a";

    private static final String THIS_WEEK_FORMAT = "EEEE h:mm a";

    private static final String YESTERDAY_FORMAT = "'Yesterday' h:mm a";

    private static final String TODAY_FORMAT = "'Today' h:mm a";

    private static final String TOMORROW_FORMAT = "'Tomorrow' h:mm a";

    private static final String NEAR_FUTURE_FORMAT = "d MMMM h:mm a";

    private static final String DISTANT_FUTURE_FORMAT = "dd/MM/yyyy h:mm a";
    
    private static InformalDateFormat instance = new InformalDateFormat();

    private DateFormat distantPastFormat = new SimpleDateFormat(DISTANT_PAST_FORMAT);

    private DateFormat recentPastFormat = new SimpleDateFormat(RECENT_PAST_FORMAT);

    private DateFormat thisWeekFormat = new SimpleDateFormat(THIS_WEEK_FORMAT);

    private DateFormat yesterdayFormat = new SimpleDateFormat(YESTERDAY_FORMAT);

    private DateFormat todayFormat = new SimpleDateFormat(TODAY_FORMAT);

    private DateFormat tomorrowFormat = new SimpleDateFormat(TOMORROW_FORMAT);

    private DateFormat nearFutureFormat = new SimpleDateFormat(NEAR_FUTURE_FORMAT);

    private DateFormat distantFutureFormat = new SimpleDateFormat(DISTANT_FUTURE_FORMAT);
    
    /**
     * Constructor made private to prevent instantiation.
     */
    private InformalDateFormat() {
    }
    
    /**
     * @return Returns the instance.
     */
    public static InformalDateFormat getInstance() {
        return instance;
    }
    
    /**
     * Constructs a string representation of the specified date.
     * @param date a date to construct a string representation
     * for
     * @return a string representation of the specified date
     */
    public String format(final Date date) {

        // Get the calendar for the specified date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        if (isDistantPast(calendar)) {
            return distantPastFormat.format(date);
        }
        else if (isRecentPast(calendar)) {
            return recentPastFormat.format(date);
        }
        else if (isThisWeek(calendar)) {
            return thisWeekFormat.format(date);
        }
        else if (isYesterday(calendar)) {
            return yesterdayFormat.format(date);
        }
        else if (isToday(calendar)) {
            return todayFormat.format(date);
        }
        else if (isTomorrow(calendar)) {
            return tomorrowFormat.format(date);
        }
        else if (isNearFuture(calendar)) {
            return nearFutureFormat.format(date);
        }
        else { //if (isDistantFuture(calendar)) {
            return distantFutureFormat.format(date);
        }
    }
    
    private boolean isDistantPast(Calendar calendar) {
        Calendar now = Calendar.getInstance();
        return (calendar.get(Calendar.YEAR) < now.get(Calendar.YEAR));
    }
    
    private boolean isRecentPast(Calendar calendar) {
        Calendar now = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && calendar.get(Calendar.WEEK_OF_YEAR) < now.get(Calendar.WEEK_OF_YEAR);
    }
    
    private boolean isThisWeek(Calendar calendar) {
        Calendar now = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && calendar.get(Calendar.WEEK_OF_YEAR) == now.get(Calendar.WEEK_OF_YEAR);
    }
    
    private boolean isYesterday(Calendar calendar) {
        Calendar now = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && calendar.get(Calendar.DAY_OF_YEAR) == (now.get(Calendar.DAY_OF_YEAR) - 1);
    }
    
    private boolean isToday(Calendar calendar) {
        Calendar now = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && calendar.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR);
    }
    
    private boolean isTomorrow(Calendar calendar) {
        Calendar now = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && calendar.get(Calendar.DAY_OF_YEAR) == (now.get(Calendar.DAY_OF_YEAR) + 1);
    }
    
    private boolean isNearFuture(Calendar calendar) {
        Calendar now = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && calendar.get(Calendar.WEEK_OF_YEAR) > now.get(Calendar.WEEK_OF_YEAR);
    }
    
    private boolean isDistantFuture(Calendar calendar) {
        Calendar now = Calendar.getInstance();
        return (calendar.get(Calendar.YEAR) > now.get(Calendar.YEAR));
    }
}