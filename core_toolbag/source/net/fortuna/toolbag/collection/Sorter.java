/*
 * $Id: Sorter.java [27/06/2004]
 *
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A collection sorter.
 * @author benfortuna
 */
public class Sorter {

    private Comparator comparator;

    private boolean reverse;

    /**
     * Constructs a new sorter based on the specified comparator.
     * @param c a comparator used to sort
     */
    public Sorter(final Comparator c) {
        this(c, false);
    }
    
    /**
     * Constructs a new sorter based on the specified comparator.
     * @param c a comparator used to sort
     * @param reverse specifies whether to reverse the sort
     */
    public Sorter(final Comparator c, final boolean reverse) {
        this.comparator = c;
        this.reverse = reverse;
    }

    /**
     * Returns the currently specified comparator.
     * @return a comparator
     */
    public Comparator getComparator() {
        return comparator;
    }
    
    /**
     * @param comparator The comparator to set.
     */
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }
    
    /**
     * Returns a list of items sorted using the selected comparator and
     * ordering.
     * @param aList
     *            a list of items to be sorted
     * @return a list of sorted items
     */
    public List sort(List aList) {        
        if (getComparator() != null) {
            Collections.sort(aList, getComparator());

            if (isReverse()) {
                Collections.reverse(aList);
            }
        }
        
        return aList;
    }

    /**
     * @return Returns the reverse.
     */
    public boolean isReverse() {
        return reverse;
    }

    /**
     * @param reverse
     *            The reverse to set.
     */
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }
}