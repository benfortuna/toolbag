/*
 * $Id: BorderedPane.java [02-Oct-2003 00:10:29]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Pane with a border
 * 
 * @author Ben Fortuna
 */
public abstract class BorderedPane extends JPanel {

    private JPanel contentPane;

    public BorderedPane() {

        super(new BorderLayout());

        PaneBorder border = PaneBorderFactory.getInstance().createPaneBorder(
                PaneBorder.GREY_THEME);

        add(border.topPane, BorderLayout.NORTH);
        add(border.leftPane, BorderLayout.WEST);
        add(border.rightPane, BorderLayout.EAST);
        add(border.bottomPane, BorderLayout.SOUTH);
        add(getContentPane(), BorderLayout.CENTER);
    }

    protected JPanel getContentPane() {

        if (contentPane == null) {

            contentPane = new JPanel(new BorderLayout());
        }

        return contentPane;
    }
}