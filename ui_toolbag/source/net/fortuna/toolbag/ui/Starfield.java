package net.fortuna.toolbag.ui;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Starfield extends JComponent implements Runnable {

    // Default properties...
    private static final int DEFAULT_STARS = 25;

    private static final int DEFAULT_DELAY = 30;

    private static final int DEFAULT_ROTATION = 5;

    private static final int DEFAULT_VIEWER_DISTANCE = 2;

    Point[] stars;

    int[] z;

    int numberOfStars;

    int warpDelay;

    int viewerDistance;

    Thread warpDrive;
    
    private boolean running;

    Random rng;

    public Starfield() {
        this(DEFAULT_STARS, DEFAULT_DELAY, DEFAULT_VIEWER_DISTANCE);
    }

    public Starfield(int numberOfStars, int warpDelay, int viewerDistance) {
        setNumberOfStars(numberOfStars);
        setWarpDelay(warpDelay);
        setViewerDistance(viewerDistance);

        rng = new Random();

        stars = new Point[getNumberOfStars()];
        z = new int[getNumberOfStars()];

        setBackground(Color.black);
        setForeground(Color.white);

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }

    public void setNumberOfStars(int number) {
        numberOfStars = number;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setWarpDelay(int delay) {
        warpDelay = delay;
    }

    public int getWarpDelay() {
        return warpDelay;
    }

    public void setViewerDistance(int distance) {
        viewerDistance = distance;
    }

    public int getViewerDistance() {
        return viewerDistance;
    }

    public void start() {
        // Initialise star locations...
        for (int i = 0; i < getNumberOfStars(); i++) {
            // Create a star at a random (x,y) coordinate on the screen...
            int halfWidth = this.getSize().width / 2;
            int halfHeight = this.getSize().height / 2;
            stars[i] = new Point((halfWidth > 0) ? rng.nextInt() % halfWidth : 0,
                    (halfHeight > 0) ? rng.nextInt() % (this.getSize().height / 2) : 0);
            // Assign each star a random distance from the screen (from
            // 0-100)...
            z[i] = -(Math.abs(rng.nextInt()) % 100);
        }

        if (warpDrive == null) {
            warpDrive = new Thread(this);
            warpDrive.start();
        }
    }

    public void stop() {
        /*
        if (warpDrive != null && warpDrive.isAlive()) {
            warpDrive.stop();
            warpDrive = null;
        }
        */
        running = false;
    }

    public void run() {
        running = true;
        while (running) {
            try {
                // Sleep for specified duration...
                Thread.sleep(getWarpDelay());

                for (int i = 0; i < stars.length; i++) {
                    // If star is past screen, reset to new location...
                    if (z[i] >= 0) {
                        int halfWidth = this.getSize().width / 2;
                        int halfHeight = this.getSize().height / 2;
                        stars[i] = new Point((halfWidth > 0) ? rng.nextInt() % halfWidth : 0,
                                (halfHeight > 0) ? rng.nextInt() % (this.getSize().height / 2) : 0);
                        z[i] = -(Math.abs(rng.nextInt()) % 100);
                    }
                    // ...otherwise, move closer to screen
                    else {
                        z[i]++;
                    }
                }

                // Repaint starfield...
                repaint();
            }
            catch (InterruptedException e) {
                System.out.println("Exception " + e + " occurred!");
            }
        }
    }

    public void paintComponent(Graphics g) {
        // Clear screen...
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getSize().width, getSize().height);
        }

        Point star;

        for (int i = 0; i < stars.length; i++) {
            // Closest...
            if (z[i] > -10) {
                g.setColor(new Color(255, 255, 255));
                // 29-6-98:
                // g.drawRect((stars[i].x/(1-(z[i]/VIEWER_DISTANCE)))+(this.getSize().width/2)-1,(stars[i].y/(1-(z[i]/VIEWER_DISTANCE)))+(this.getSize().height/2)-1,2,2);
                g.drawLine((stars[i].x / (1 - (z[i] / getViewerDistance())))
                        + (getSize().width / 2) - 1,
                        (stars[i].y / (1 - (z[i] / getViewerDistance())))
                                + (getSize().height / 2) - 1,
                        (stars[i].x / (1 - (z[i] / getViewerDistance())))
                                + (getSize().width / 2) - 1,
                        (stars[i].y / (1 - (z[i] / getViewerDistance())))
                                + (getSize().height / 2) - 1);
            }
            // Further...
            else if (z[i] > -30) {
                g.setColor(new Color(180, 180, 180));
                g.drawLine((stars[i].x / (1 - (z[i] / getViewerDistance())))
                        + (getSize().width / 2) - 1,
                        (stars[i].y / (1 - (z[i] / getViewerDistance())))
                                + (getSize().height / 2) - 1,
                        (stars[i].x / (1 - (z[i] / getViewerDistance())))
                                + (getSize().width / 2) - 1,
                        (stars[i].y / (1 - (z[i] / getViewerDistance())))
                                + (getSize().height / 2) - 1);
            }
            // Furthest...
            else {
                g.setColor(new Color(100, 100, 100));
                g.drawLine((stars[i].x / (1 - (z[i] / getViewerDistance())))
                        + (getSize().width / 2) - 1,
                        (stars[i].y / (1 - (z[i] / getViewerDistance())))
                                + (getSize().height / 2) - 1,
                        (stars[i].x / (1 - (z[i] / getViewerDistance())))
                                + (getSize().width / 2) - 1,
                        (stars[i].y / (1 - (z[i] / getViewerDistance())))
                                + (getSize().height / 2) - 1);
            }
        }
    }

    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_CLICKED) {
            if (warpDrive != null)
                stop();
            else
                start();
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Starfield");
        Starfield starfield = new Starfield();
        f.getContentPane().add(starfield);
        f.setSize(300, 300);
        f.setVisible(true);

        starfield.start();
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#getPreferredSize()
     */
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }
}