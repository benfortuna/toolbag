package net.fortuna.toolbag.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
// 5-12-98: import com.sun.java.swing.*;
import javax.swing.*;

import net.fortuna.toolbag.math.GeometryUtils;

public class BooFar extends JComponent implements Runnable {

    private static final boolean JAVA_VERSION = true;

    class Eye {

        private Point socketCenter;

        private Dimension socketSize;

        private Point eyeballCenter;

        private Dimension eyeballSize;

        public Eye() {
        }

        public Eye(Point socketCenter, Dimension socketSize,
                Point eyeballCenter, Dimension eyeballSize) {
            setSocketCenter(socketCenter);
            setSocketSize(socketSize);
            setEyeballCenter(eyeballCenter);
            setEyeballSize(eyeballSize);
        }

        public void setSocketCenter(Point center) {
            socketCenter = center;
        }

        public Point getSocketCenter() {
            return socketCenter;
        }

        public void setSocketSize(Dimension size) {
            socketSize = size;
        }

        public Dimension getSocketSize() {
            return socketSize;
        }

        public void setEyeballCenter(Point center) {
            eyeballCenter = center;
        }

        public Point getEyeballCenter() {
            return eyeballCenter;
        }

        public void setEyeballSize(Dimension size) {
            eyeballSize = size;
        }

        public Dimension getEyeballSize() {
            return eyeballSize;
        }
    }

    private Eye leftEye;

    private Eye rightEye;

    Thread motionDetector;

    // Time of last mouse event, etc.
    long lastMotion;

    // Random number generator..
    Random rng;

    public BooFar() {
        setLastMotion(System.currentTimeMillis());

        motionDetector = new Thread(this);
        motionDetector.start();

        enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
        
        setMinimumSize(new Dimension(50, 50));
        setPreferredSize(new Dimension(75, 50));
    }

    public Eye getLeftEye() {
        if (leftEye == null) {
            /*
             * 4-11-98: Dimension socketSize = new
             * Dimension(getSize().width/2,getSize().height); Point socketCenter =
             * new Point(getSize().width/4,getSize().height/2);
             * 
             * leftEye = new Eye(socketCenter,socketSize,socketCenter,new
             * Dimension(20,20));
             */
            leftEye = new Eye();

            // Set eye properties based on current size...
            int width = getSize().width - getInsets().left - getInsets().right;
            int height = getSize().height - getInsets().top - getInsets().bottom;
            
            leftEye.setSocketCenter(new Point(width / 4,
                    height / 2));
            leftEye.setSocketSize(new Dimension((width / 2) - 4,
                    height - 4));
            leftEye.setEyeballCenter(new Point(width / 4,
                    height / 2));
            leftEye.setEyeballSize(new Dimension(height / 4,
                    height / 4));
        }

        return leftEye;
    }

    public Eye getRightEye() {
        if (rightEye == null) {
            /*
             * 4-11-98: Dimension socketSize = new
             * Dimension(getSize().width/2,getSize().height); Point socketCenter =
             * new
             * Point(getSize().width/2+getSize().width/4,getSize().height/2);
             * 
             * rightEye = new Eye(socketCenter,socketSize,socketCenter,new
             * Dimension(20,20));
             */
            rightEye = new Eye();

            // Set eye properties based on current size...
            int width = getSize().width - getInsets().left - getInsets().right;
            int height = getSize().height - getInsets().top - getInsets().bottom;

            rightEye.setSocketCenter(new Point(width / 2
                    + width / 4, height / 2));
            rightEye.setSocketSize(new Dimension((width / 2) - 4,
                    height - 4));
            rightEye.setEyeballCenter(new Point(width / 2
                    + width / 4, height / 2));
            rightEye.setEyeballSize(new Dimension(height / 4,
                    height / 4));
        }

        return rightEye;
    }

    public void setLastMotion(long time) {
        lastMotion = time;
    }

    public long getLastMotion() {
        return lastMotion;
    }

    public void lookAt(Point point) {
        double angle;
        Point translation;

        // Calculate the left eye...
        // Find the angle between the socket center and mouse point..
        angle = GeometryUtils.getAngleBetweenPoints(getLeftEye()
                .getSocketCenter(), point);
        // Calculate distance to translate eyeball from socket center..
        translation = GeometryUtils.getPointFromOrigin(getLeftEye()
                .getSocketCenter(), angle,
                getLeftEye().getSocketSize().width / 4);
        getLeftEye().setEyeballCenter(translation);

        // Do the same for the right eye...
        angle = GeometryUtils.getAngleBetweenPoints(getRightEye()
                .getSocketCenter(), point);
        translation = GeometryUtils.getPointFromOrigin(getRightEye()
                .getSocketCenter(), angle,
                getRightEye().getSocketSize().width / 4);
        getRightEye().setEyeballCenter(translation);

        repaint();
    }

    public Random getRNG() {
        if (rng == null) {
            rng = new Random();
        }

        return rng;
    }

    public Point getRandomPoint() {
        return new Point(getSize().width / 2 + (getRNG().nextInt() % 100),
                getSize().height / 2 + (getRNG().nextInt() % 100));
    }

    /**
     * This method causes the eye to blink by reducing the eyeball height to
     * zero, and returning it to its original size.
     */
    public void blink() {
        Dimension originalLeftEyeSize = getLeftEye().getEyeballSize();
        Dimension originalRightEyeSize = getRightEye().getEyeballSize();

        while (getLeftEye().getEyeballSize().height > 0
                && getRightEye().getEyeballSize().height > 0) {
            getLeftEye().setEyeballSize(
                    new Dimension(getLeftEye().getEyeballSize().width,
                            getLeftEye().getEyeballSize().height - 10));
            getRightEye().setEyeballSize(
                    new Dimension(getRightEye().getEyeballSize().width,
                            getRightEye().getEyeballSize().height - 10));
            repaint();

            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
            }
        }

        // Return eye to original size...
        getLeftEye().setEyeballSize(originalLeftEyeSize);
        getRightEye().setEyeballSize(originalRightEyeSize);
        repaint();
    }

    public void paintComponent(Graphics g) {        
        super.paintComponent(g);        
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        
        Object origRh = null;
        if (JAVA_VERSION) {
            // If Java 1.2 or greater, set Graphics2D properties...
            Graphics2D g2d = (Graphics2D) g;
            origRh = g2d.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        g.setColor(getForeground());

        //////////////////
        // Draw the left eye...
        // Resize eye according to current size...

        // Draw eye socket...
        g.drawOval(getInsets().left + getLeftEye().getSocketCenter().x
                - getLeftEye().getSocketSize().width / 2, getInsets().top + getLeftEye()
                .getSocketCenter().y
                - getLeftEye().getSocketSize().height / 2, getLeftEye()
                .getSocketSize().width, getLeftEye().getSocketSize().height);
        // Draw eyeball...
        //g.drawLine(0,0,eye1.x,eye1.y);
        g.fillOval(getInsets().left + getLeftEye().getEyeballCenter().x
                - getLeftEye().getEyeballSize().width / 2, getInsets().top + getLeftEye()
                .getEyeballCenter().y
                - getLeftEye().getEyeballSize().height / 2, getLeftEye()
                .getEyeballSize().width, getLeftEye().getEyeballSize().height);

        //////////////////
        // Draw the right eye...
        // Draw eye socket...
        g.drawOval(getInsets().left + getRightEye().getSocketCenter().x
                - getRightEye().getSocketSize().width / 2, getInsets().top + getRightEye()
                .getSocketCenter().y
                - getRightEye().getSocketSize().height / 2, getRightEye()
                .getSocketSize().width, getRightEye().getSocketSize().height);
        // Draw eyeball...
        //g.drawLine(0,0,eye1.x,eye1.y);
        g.fillOval(getInsets().left + getRightEye().getEyeballCenter().x
                - getRightEye().getEyeballSize().width / 2, getInsets().top + getRightEye()
                .getEyeballCenter().y
                - getRightEye().getEyeballSize().height / 2, getRightEye()
                .getEyeballSize().width, getRightEye().getEyeballSize().height);
        
        if (origRh != null) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, origRh);
        }
    }

    protected void processMouseMotionEvent(MouseEvent e) {
        setLastMotion(System.currentTimeMillis());

        lookAt(e.getPoint());
    }

    public void run() {
        while (true) {
            try {
                // If idle for 5 seconds, do something..
                if (System.currentTimeMillis() - getLastMotion() > 5000) {
                    if (getRNG().nextInt() > 0) {
                        //lookAt(new
                        // Point(getSize().width/2,getSize().height/2));
                        lookAt(getRandomPoint());
                    }
                    else {
                        blink();
                    }
                    setLastMotion(System.currentTimeMillis());
                }
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Eyes");
        f.getContentPane().add(new BooFar());
        f.setSize(100, 100);
        f.setVisible(true);
    }
}