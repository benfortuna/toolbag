/*
 * $Id: AlertProcessor.java [22/05/2004]
 * 
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.ui.alert;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;

import net.fortuna.toolbag.ui.transition.Transition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Used for processing a queue of alerts.
 * 
 * @author Ben
 */
public class AlertProcessor extends Thread {

    private static final int WINDOW_GAP = 2;

    private static final int TASKBAR_HEIGHT = 30;

    private static final int WINDOW_TITLEBAR_HEIGHT = 20;

    private static final int WINDOW_BUTTONS_WIDTH = 60;

    private static Log log = LogFactory.getLog(AlertProcessor.class);

    private int slot;

    private LinkedList alerts;

    private boolean running;

    /**
     * Constructor.
     * 
     * @param alertList
     *            a list of alerts to process
     * @param slot
     *            an allocated window space for this processor to work in
     */
    public AlertProcessor(final LinkedList alertList, final int slot) {

        this.alerts = alertList;
        this.slot = slot;
        this.running = true;
    }

    /**
     * @see java.lang.Thread#run()
     */
    public final void run() {

        while (running) {

            try {

                synchronized (alerts) {
                    if (alerts.isEmpty()) {
                        alerts.wait();
                    }
                }

                Alert alert = (Alert) alerts.removeLast();

                int xOffset = 0;
                int yOffset = 0;
                Dimension screenSize = Toolkit.getDefaultToolkit()
                .getScreenSize();

                if (alert.getEdge() == Transition.EDGE_TOP) {
                    xOffset = screenSize.width - alert.getWidth()
                            - WINDOW_BUTTONS_WIDTH - (alert.getWidth() * slot)
                            - (WINDOW_GAP * slot);
                }
                else if (alert.getEdge() == Transition.EDGE_BOTTOM) {
                    xOffset = screenSize.width - alert.getWidth() - (alert.getWidth() * slot) - (WINDOW_GAP * slot);
                    yOffset = screenSize.height - TASKBAR_HEIGHT;
                }
                else if (alert.getEdge() == Transition.EDGE_LEFT) {
                    yOffset = WINDOW_TITLEBAR_HEIGHT
                            + (alert.getHeight() * slot) + (WINDOW_GAP * slot);
                }
                else if (alert.getEdge() == Transition.EDGE_RIGHT) {
                    yOffset = screenSize.height - TASKBAR_HEIGHT
                            - alert.getHeight() - (alert.getHeight() * slot)
                            - (WINDOW_GAP * slot);
                }

                log.debug("Initial alert offset: [" + xOffset + ", " + yOffset
                        + "]");

                alert.setLocation(alert.getLocation().x + xOffset, alert
                        .getLocation().y
                        + yOffset);

                alert.display();
            }
            catch (InterruptedException ie) {
                log.warn("Interrupted whilst processing alerts", ie);
            }
        }
    }

    /**
     * @return Returns the running.
     */
    public final boolean isRunning() {
        return running;
    }

    /**
     * @param running
     *            The running to set.
     */
    public final void setRunning(final boolean running) {
        this.running = running;
    }
}