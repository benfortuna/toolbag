/*
 * $Id: DateUtils.java [15/07/2004]
 *
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.util;

import java.util.Date;

/**
 * Utility methods regarding dates.
 * @author benfortuna
 */
public final class DateUtils {

    private static final long MILLIS_PER_DAY = 1000 * 60 * 60 * 24;

    /**
     * Constructor made private to enforce static nature.
     */
    private DateUtils() {
    }

    /**
     * Returns the number of days between the two specified dates (inclusive)
     * @param date1 a date
     * @param date2 a date
     * @return int indicating the number of days between the two
     * specified dates
     */
    public static int getDaysBetween(final Date date1, final Date date2) {
        return new Long((date2.getTime() - date1.getTime()) / MILLIS_PER_DAY)
                .intValue();
    }

}
