/*
 * $Id: Transition.java [02-May-2004]
 * 
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.ui.transition;

import java.awt.Component;

/**
 * Base class for all alert window transitions.
 * 
 * @author benfortuna
 */
public abstract class Transition {

    public static final int EDGE_TOP = 1;

    public static final int EDGE_LEFT = 2;

    public static final int EDGE_BOTTOM = 4;

    public static final int EDGE_RIGHT = 8;

    private int edge;

    private int frames;

    /**
     * Constructor.
     * 
     * @param edge
     *            the screen edge to use as the starting point for this
     *            transition
     * @param frameCount
     *            the total number of frames for this transition
     */
    public Transition(final int edge, final int frameCount) {
        this.edge = edge;
        this.frames = frameCount;
    }

    /**
     * @param c the component to apply the transition to
     * @param frame the current frame of the transition
     */
    public abstract void display(final Component c, final int frame);

    /**
     * @return Returns the frames.
     */
    public final int getFrames() {
        return frames;
    }

    /**
     * @return Returns the edge.
     */
    public final int getEdge() {
        return edge;
    }
}