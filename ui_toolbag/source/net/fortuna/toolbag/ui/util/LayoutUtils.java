/*
 * Created on 31-Aug-2003
 * 
 * Copyright 2003.
 */
package net.fortuna.toolbag.ui.util;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @author Ben Fortuna
 *  
 */
public class LayoutUtils {

    public static final Insets TOP_INSETS = new Insets(15, 5, 5, 5);

    public static final Insets DEFAULT_INSETS = new Insets(5, 5, 5, 5);

    public static final Insets LEFT_INSETS = new Insets(5, 5, 5, 2);

    public static final Insets RIGHT_INSETS = new Insets(5, 2, 5, 5);

    private static GridBagConstraints constraints = new GridBagConstraints();

    public static GridBagConstraints getConstraints(int x, int y, int width,
            int height, double weightx, double weighty, int anchor, int fill) {

        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.anchor = anchor;
        constraints.fill = fill;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.insets = new Insets(0, 0, 0, 0);

        return constraints;
    }

    public static GridBagConstraints getConstraints(int x, int y, int width,
            int height, double weightx, double weighty, int anchor, int fill,
            int padx, int pady, Insets insets) {

        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.anchor = anchor;
        constraints.fill = fill;
        constraints.ipadx = padx;
        constraints.ipady = pady;
        constraints.insets = insets;

        return constraints;
    }

}