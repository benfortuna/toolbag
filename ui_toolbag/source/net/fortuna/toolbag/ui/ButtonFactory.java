/*
 * $Id: ButtonFactory.java [04-Oct-2003 21:55:26]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import net.fortuna.toolbag.ui.icon.AbstractIconSetFactory;
import net.fortuna.toolbag.ui.icon.IconSet;

/**
 * Factory for creating buttons with icons
 * 
 * @author Ben Fortuna
 */
public class ButtonFactory {

    private static ButtonFactory instance = new ButtonFactory();

    /*
     * Constructor made private to enforce singleton
     */
    private ButtonFactory() {
    }

    public static ButtonFactory getInstance() {

        return instance;
    }

    public JButton createButton(Action action) {

        return createButton(action, false);
    }

    public JButton createButton(Action action, boolean showText) {

        String iconKey = (String) action.getValue("icon.key");

        JButton button = new JButton(action);

        if (iconKey != null) {

            if (!showText) {

                button.setText(null);
            }
            
            IconSet iconSet = AbstractIconSetFactory.getDefaultInstance().getDefaultIconSet();

            button.setDisabledIcon(iconSet.getDisabledIcon(
                    iconKey));
            button.setRolloverIcon(iconSet.getRolloverIcon(
                    iconKey));
            button.setPressedIcon(iconSet.getPressedIcon(
                    iconKey));
            button.setRolloverEnabled(true);
        }

        return button;
    }

    public JMenuItem createMenuItem(Action action) {

        String iconKey = (String) action.getValue("icon.key");

        JMenuItem item = new JMenuItem(action);

        if (iconKey != null) {
            
            IconSet iconSet = AbstractIconSetFactory.getDefaultInstance().getDefaultIconSet();

            // use the rollover icon as default icon as rollover
            // doesn't seem to work in menus..
            item.setIcon(iconSet.getRolloverIcon(iconKey));

            item.setDisabledIcon(iconSet.getDisabledIcon(
                    iconKey));
            item.setRolloverIcon(iconSet.getRolloverIcon(
                    iconKey));
            item.setRolloverEnabled(true);
        }

        return item;
    }
}