/*
 * $Id: TimerFormat.java [15/07/2004]
 *
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Used for formatting dates in a timer format.
 * @author benfortuna
 */
public final class TimerFormat {

    private static final String TIMER_FORMAT = "H:mm:ss";
    
    private static TimerFormat instance = new TimerFormat();

    // format for use in timers..
    private DateFormat format;
    
    /**
     * Constructor made private to enforce singleton.
     */
    private TimerFormat() {
        format = new SimpleDateFormat(TIMER_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    /**
     * @return Returns the instance.
     */
    public static TimerFormat getInstance() {
        return instance;
    }

    /**
     * Constructs a string representation of the specified date.
     * @param time a date to construct a string representation
     * for
     * @return a string representation of the specified date
     */
    public String formatTimer(final Date time) {
        return format.format(time);
    }
}
