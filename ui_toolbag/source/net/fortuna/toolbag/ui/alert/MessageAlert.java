/*
 * $Id: MessageAlert.java [02-May-2004]
 * 
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.ui.alert;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import net.fortuna.toolbag.ui.transition.Transition;

/**
 * Support for displaying a message as an alert.
 * 
 * @author benfortuna
 */
public class MessageAlert extends Alert {

    private static final long DEFAULT_DISPLAY_TIME = 1000;

    private static final int BACKGROUND_R = 255;
    //private static final int BACKGROUND_R = 153;

    private static final int BACKGROUND_G = 255;
    //private static final int BACKGROUND_G = 204;

    private static final int BACKGROUND_B = 204;
    //private static final int BACKGROUND_B = 255;
    
    private String message;

    /**
     * Constructor.
     * 
     * @param message
     *            the message to display in the alert
     */
    public MessageAlert(final String message) {
        super(Transition.EDGE_RIGHT, DEFAULT_DISPLAY_TIME);

        this.message = message;

        Border messageBorder = BorderFactory.createCompoundBorder(BorderFactory
                .createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
                        BorderFactory.createLineBorder(Color.black, 2)),
                BorderFactory.createEmptyBorder(2, 2, 2, 2));

        JLabel messageLabel = new JLabel(message);
        //messageLabel.setFont(new Font("sans-serif",Font.BOLD,14));
        messageLabel.setBackground(new Color(BACKGROUND_R, BACKGROUND_G, BACKGROUND_B));
        messageLabel.setOpaque(true);
        messageLabel.setBorder(messageBorder);

        /*
         * JPanel panel = new JPanel(new BorderLayout()) {
         *  /* (non-Javadoc)
         * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
         * 
         * protected void paintComponent(Graphics g) { super.paintComponent(g);
         * 
         * GraphicsUtils.clear(g, getBounds(), getBackground(), true); } };
         * panel.setBackground(Color.YELLOW); panel.setOpaque(true);
         * panel.add(messageLabel);
         */

        getContentPane().add(messageLabel);

        //setSize(100,100);
        pack();

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //setLocation(screenSize.width-getSize().width,getLocation().y);
        //setLocation(getLocation().x, 20);
        setLocation(0, 0);
    }
}