/*
 * $Id: OpenOption.java [07-Dec-2003 14:47:59]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.option;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Indicate the user selected open
 * 
 * @author Ben Fortuna
 */
public class OpenOption extends AbstractAction {

    private OptionPane pane;

    public OpenOption(OptionPane pane) {

        super("Open");

        this.pane = pane;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {

        pane.setOption(OptionPane.OPEN_OPTION);
        pane.selectionMade();
    }

}