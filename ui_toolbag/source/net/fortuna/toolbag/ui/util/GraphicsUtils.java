package net.fortuna.toolbag.ui.util;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Utility methods for working with graphics.
 * 
 * @author benfortuna
 */
public final class GraphicsUtils {

    /**
     * Constructor made private to prevent instantiation.
     */
    private GraphicsUtils() {
    }

    /**
     * Convenience method for creating a full-screen window.
     * 
     * @return a window that is full-screen
     */
    public static Window createFullScreenWindow() {
        GraphicsEnvironment env = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration gc = device.getDefaultConfiguration();
        Frame f = new Frame(gc);
        f.setUndecorated(true);
        f.setIgnoreRepaint(true);
        device.setFullScreenWindow(f);
        //f.setSize(200,200);
        //f.setVisible(true);

        //f.createBufferStrategy(2);

        /*
         * mainFrame.createBufferStrategy(numBuffers); BufferStrategy
         * bufferStrategy = mainFrame.getBufferStrategy(); Graphics g =
         * bufferStrategy.getDrawGraphics(); 
         * if (!bufferStrategy.contentsLost()) { //
         * render here!
         * 
         * bufferStrategy.show(); g.dispose();
         */

        return f;
    }

    /**
     * Convenience method for destroying any active full-screen window.
     */
    public static void destroyFullScreenWindow() {
        GraphicsEnvironment env = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        device.setFullScreenWindow(null);
    }

    /**
     * Clear the specified window by painting it black.
     * 
     * @param w
     *            a window to clear
     * @throws InterruptedException
     *             thrown when clear is interrupted
     */
    public static void clear(final Window w) throws InterruptedException {
        clear(w, Color.BLACK);
    }

    /**
     * Clear the specified window by painting it the specified colour.
     * 
     * @param w
     *            a window to clear
     * @param c
     *            the colour to clear the window with
     * @throws InterruptedException
     *             thrown when clear is interrupted
     */
    public static void clear(final Window w, final Color c)
            throws InterruptedException {
        BufferStrategy bufferStrategy = w.getBufferStrategy();
        Rectangle bounds = w.getBounds();

        Graphics g = bufferStrategy.getDrawGraphics();

        if (!bufferStrategy.contentsLost()) {
            g.setColor(c);

            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            //g.fillRect(0,0,bounds.width,bounds.height);
            bufferStrategy.show();
            g.dispose();
        }
    }

    /**
     * Changes the display mode to the width and height specified.
     * 
     * @param width
     *            the new width of the display
     * @param height
     *            the new height of the display
     */
    public static void changeDisplayMode(final int width, final int height) {
        GraphicsEnvironment env = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        if (!device.isDisplayChangeSupported()) { return; }

        DisplayMode[] modes = device.getDisplayModes();
        DisplayMode bestMode = null;
        DisplayMode currentMode = device.getDisplayMode();

        for (int i = 0; i < modes.length; i++) {

            if (modes[i].getWidth() == width 
                    && modes[i].getHeight() == height) {
                // find the closest match to current display mode..
                if (bestMode == null) {
                    bestMode = modes[i];
                }
                else {
                    int rdifBest = Math.abs(currentMode.getRefreshRate()
                            - bestMode.getRefreshRate());
                    int rdifi = Math.abs(currentMode.getRefreshRate()
                            - modes[i].getRefreshRate());

                    // find closest refresh rate
                    if (rdifi < rdifBest) {
                        bestMode = modes[i];
                    }
                    else if (rdifi == rdifBest) {
                        // find closest bit depth..
                        if (Math.abs(currentMode.getBitDepth()
                                - modes[i].getBitDepth()) < Math
                                .abs(currentMode.getBitDepth()
                                        - bestMode.getBitDepth())) {
                            bestMode = modes[i];
                        }
                    }
                }
            }
        }

        System.out.println("Best mode: [" + bestMode.getWidth() + "x"
                + bestMode.getHeight() + "," + bestMode.getBitDepth() + ","
                + bestMode.getRefreshRate() + "]");

        if (bestMode != null) {
            device.setDisplayMode(bestMode);
        }
    }

    /**
     * Clears the specified graphics by filling with the specified colour. If
     * gradient is true, a gradient paint is using the given colour and the
     * inverse of this colour.
     */
    public static void clear(final Graphics g, final Rectangle bounds,
            final Color colour, final boolean gradient) {
        Graphics2D g2d = (Graphics2D) g.create();
        Paint paint;
        if (gradient) {
            paint = new GradientPaint(bounds.x, bounds.y, colour, bounds.width,
                    bounds.height, new Color(~colour.getRGB()));
        }
        else {
            paint = colour;
        }
        g2d.setPaint(paint);
        g2d.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g2d.dispose();
    }
}