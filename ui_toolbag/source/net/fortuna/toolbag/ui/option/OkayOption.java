/*
 * $Id: OkayOption.java [07-Dec-2003 14:47:15]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.option;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Indicate the user selected ok
 * 
 * @author Ben Fortuna
 */
public class OkayOption extends AbstractAction {

    private OptionPane pane;

    public OkayOption(OptionPane pane) {

        super("OK");

        this.pane = pane;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {

        pane.setOption(OptionPane.OKAY_OPTION);
        pane.selectionMade();
    }

}