/*
 * $Id: FinishOption.java [03-Jan-2004 21:01:00]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.option;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Indicate the user selected finish
 * 
 * @author Ben Fortuna
 */
public class FinishOption extends AbstractAction {

    private OptionPane pane;

    public FinishOption(OptionPane pane) {

        super("Finish");

        this.pane = pane;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {

        pane.setOption(OptionPane.FINISH_OPTION);
        pane.selectionMade();
    }

}