/*
 * $Id: MessageAlert.java [02-May-2004]
 * 
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.ui.alert;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;

import net.fortuna.toolbag.ui.BooFar;
import net.fortuna.toolbag.ui.transition.Transition;

/**
 * Support for displaying a message as an alert.
 * 
 * @author benfortuna
 */
public class BooAlert extends Alert {

    private static final long DEFAULT_DISPLAY_TIME = 10000;

    //private static final int BACKGROUND_R = 255;
    private static final int BACKGROUND_R = 153;

    //private static final int BACKGROUND_G = 255;
    private static final int BACKGROUND_G = 204;

    //private static final int BACKGROUND_B = 204;
    private static final int BACKGROUND_B = 255;
    
    private String message;
    
    private BooFar boo;

    /**
     * Constructor.
     * 
     * @param message
     *            the message to display in the alert
     */
    public BooAlert() {
        super(Transition.EDGE_TOP, DEFAULT_DISPLAY_TIME);

        this.message = "Boo!";

        Border border = BorderFactory.createCompoundBorder(BorderFactory
                .createCompoundBorder(BorderFactory.createLoweredBevelBorder(),
                        BorderFactory.createLineBorder(Color.black, 2)),
                BorderFactory.createEmptyBorder(2, 2, 2, 2));

        /*
        JLabel messageLabel = new JLabel(message);
        //messageLabel.setFont(new Font("sans-serif",Font.BOLD,14));
        messageLabel.setBackground(new Color(BACKGROUND_R, BACKGROUND_G, BACKGROUND_B));
        messageLabel.setOpaque(true);
        
        JPanel booPanel = new JPanel(new BorderLayout());
        booPanel.add(new BooFar(), BorderLayout.CENTER);
        booPanel.add(messageLabel, BorderLayout.SOUTH);
        booPanel.setBorder(border);
        */
        
        boo = new BooFar();
        boo.setBackground(new Color(BACKGROUND_R, BACKGROUND_G, BACKGROUND_B));
        boo.setOpaque(true);
        boo.setBorder(border);
        boo.setToolTipText(message);
        
        ToolTipManager.sharedInstance().registerComponent(boo);

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

        getContentPane().add(boo);

        //setSize(100,100);
        pack();

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //setLocation(screenSize.width-getSize().width,getLocation().y);
        //setLocation(getLocation().x, 20);
        setLocation(0, 0);
    }
    
    /**
     * @return Returns the boo.
     */
    public BooFar getBoo() {
        return boo;
    }
}