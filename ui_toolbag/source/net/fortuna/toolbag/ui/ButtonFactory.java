/*
 * $Id: ButtonFactory.java [04-Oct-2003 21:55:26]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui;

import java.awt.Font;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import net.fortuna.toolbag.ui.icon.AbstractIconSetFactory;
import net.fortuna.toolbag.ui.icon.IconSet;
import net.fortuna.toolbag.ui.util.Actions;
import net.fortuna.toolbag.ui.util.Fonts;

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
        String iconKey = (String) action.getValue("icon.key");

        JButton button = new JButton(action);

        IconSet iconSet = AbstractIconSetFactory.getDefaultInstance().getDefaultIconSet();

        if (action.getValue(Actions.DISABLED_ICON) != null) {
            button.setDisabledIcon((Icon) action.getValue(Actions.DISABLED_ICON));
        }
        else if (iconKey != null) {
            button.setDisabledIcon(iconSet.getDisabledIcon(iconKey));
        }

        if (action.getValue(Actions.ROLLOVER_ICON) != null) {
            button.setRolloverIcon((Icon) action.getValue(Actions.ROLLOVER_ICON));
            button.setRolloverEnabled(true);
        }
        else if (iconKey != null) {
            button.setRolloverIcon(iconSet.getRolloverIcon(iconKey));
            button.setRolloverEnabled(true);
        }
        
        if (action.getValue(Actions.PRESSED_ICON) != null) {
            button.setPressedIcon((Icon) action.getValue(Actions.PRESSED_ICON));
        }
        else if (iconKey != null) {
            button.setPressedIcon(iconSet.getPressedIcon(iconKey));
        }
        
        if (action.getValue(Actions.HORIZONTAL_TEXT_POSITION) != null) {
            button.setHorizontalTextPosition(((Integer) action.getValue(Actions.HORIZONTAL_TEXT_POSITION)).intValue());
        }
        
        if (action.getValue(Actions.VERTICAL_TEXT_POSITION) != null) {
            button.setVerticalTextPosition(((Integer) action.getValue(Actions.VERTICAL_TEXT_POSITION)).intValue());
        }
        
        if (new Integer(Font.BOLD).equals(action.getValue(Actions.FONT_WEIGHT))) {
            button.setFont(Fonts.getBoldInstance(button.getFont()));
        }
        
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

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