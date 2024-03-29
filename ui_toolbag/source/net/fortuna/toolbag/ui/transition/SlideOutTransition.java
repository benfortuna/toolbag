/*
 * $Id: SlideOutTransition.java [02-May-2004]
 *
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.ui.transition;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * An alert window transition.
 *
 * @author benfortuna
 */
public class SlideOutTransition extends Transition {

    private static final int BOTTOM_INSET = 30;

    private Dimension screenSize;

    /**
     * @param edge specifies the edge to transition from
     * @param frameCount the total number of frames in the transition
     */
    public SlideOutTransition(final int edge, final int frameCount) {
        super(edge, frameCount);

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }

    /**
     * @see net.fortuna.toolbag.ui.transition.Transition#display(Window, int)
     */
    public final void display(final Component c, final int frame) {
        int x = c.getLocation().x;
        int y = c.getLocation().y;

        if (getEdge() == EDGE_TOP) {
            y = -(int) ((frame / (float) getFrames()) * c.getSize().height);
        }
        else if (getEdge() == EDGE_LEFT) {
            x = -(int) ((frame / (float) getFrames()) * c.getSize().width);
        }
        else if (getEdge() == EDGE_BOTTOM) {
            y = screenSize.height
                    - (c.getSize().height + BOTTOM_INSET)
                    + (int) ((frame / (float) getFrames()) * (c.getSize().height + BOTTOM_INSET));
        }
        else if (getEdge() == EDGE_RIGHT) {
            x = screenSize.width - c.getSize().width
                    + (int) ((frame / (float) getFrames()) * c.getSize().width);
        }

        c.setLocation(x, y);
        //c.setVisible(true);

        if ((c instanceof Window) && !((Window) c).isShowing()) {
            ((Window) c).toFront();
        }
    }

}