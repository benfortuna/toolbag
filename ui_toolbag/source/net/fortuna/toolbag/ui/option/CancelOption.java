/*
 * $Id: CancelOption.java [07-Dec-2003 14:45:18]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.option;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Indicate the user selected cancel
 * 
 * @author Ben Fortuna
 */
public class CancelOption extends AbstractAction {

    private OptionPane pane;

    public CancelOption(OptionPane pane) {

        super("Cancel");

        this.pane = pane;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {

        pane.setOption(OptionPane.CANCEL_OPTION);
        pane.selectionMade();
    }

}