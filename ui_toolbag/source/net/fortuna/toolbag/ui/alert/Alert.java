/*
 * $Id: AlertWindow.java [02-May-2004]
 * 
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.ui.alert;

import javax.swing.JWindow;

import net.fortuna.toolbag.ui.transition.SlideInTransition;
import net.fortuna.toolbag.ui.transition.SlideOutTransition;
import net.fortuna.toolbag.ui.transition.Transition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A transient window for displaying alerts.
 * 
 * @author benfortuna
 */
public class Alert extends JWindow {

    private static final int FRAME_COUNT = 50;

    private static final int FRAME_DELAY = 20;

    private static Log log = LogFactory.getLog(Alert.class);

    private long displayTime;

    private Transition showTransition;

    private Transition hideTransition;

    /**
     * Constructor.
     * @param edge the edge to transition from
     * @param time amount of time to display alert
     */
    public Alert(final int edge, final long time) {
        this.showTransition = new SlideInTransition(edge, FRAME_COUNT);
        this.hideTransition = new SlideOutTransition(edge, FRAME_COUNT);
        this.displayTime = time;
        setLocation(0, 0);
    }

    /**
     * Returns the edge to transition from.
     * @return an int
     * @see Transition#EDGE_TOP
     * @see Transition#EDGE_LEFT
     * @see Transition#EDGE_BOTTOM
     * @see Transition#EDGE_RIGHT
     */
    public final int getEdge() {
        return showTransition.getEdge();
    }

    /**
     * Displays this alert by applying the in transition, display
     * delay, and out transition. 
     */
    public final void display() {

        // set initial location..
        showTransition.display(this, 0);
        
        setVisible(true);

        for (int i = 1; i <= showTransition.getFrames(); i++) {
            showTransition.display(this, i);

            try {
                Thread.sleep(FRAME_DELAY);
            }
            catch (Exception e) {
                log.error("Error displaying alert [in, " + i + "]", e);
            }
        }

        try {
            Thread.sleep(displayTime);
        }
        catch (Exception e) {
            log.error("Error displaying alert [sleep, " + displayTime + "]", e);
        }

        for (int i = 1; i <= hideTransition.getFrames(); i++) {
            hideTransition.display(this, i);

            try {
                Thread.sleep(FRAME_DELAY);
            }
            catch (Exception e) {
                log.error("Error displaying alert [out, " + i + "]", e);
            }
        }

        // destroy window after display..
        dispose();
    }
}