package net.fortuna.toolbag.ui.util;

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import net.fortuna.toolbag.ui.prefs.PrefsManager;

/**
 * @author Ben Fortuna
 *  
 */
public final class WindowUtils {

    /**
     * Constructor made private to prevent instantiation.
     */
    private WindowUtils() {
    }
    
    public interface Constants {

        Dimension DIALOG_SIZE = new Dimension(320, 350);
    }

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
         * bufferStrategy.getDrawGraphics(); if (!bufferStrategy.contentsLost()) {
         *  // render here!
         * 
         * bufferStrategy.show(); g.dispose();
         */

        return f;
    }

    public static void destroyFullScreenWindow() {
        GraphicsEnvironment env = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        device.setFullScreenWindow(null);
    }

    public static void clear(Window w) throws InterruptedException {
        clear(w, Color.BLACK);
    }

    public static void clear(Window w, Color c) throws InterruptedException {
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

    public static void center(Window window) {
        Window ancestor = SwingUtilities.getWindowAncestor(window);

        if (ancestor != null && ancestor.isVisible()) {
            window.setLocation(ancestor.getX()
                    + (ancestor.getWidth() - window.getWidth()) / 2, ancestor
                    .getY()
                    + (ancestor.getHeight() - window.getHeight()) / 2);
        }
        else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            window.setLocation((screenSize.width - window.getWidth()) / 2,
                    (screenSize.height - window.getHeight()) / 2);
        }
    }

    public static JFrame createFrame(Component c, String title) {

        return createFrame(c, title, new Point(0, 0), new Dimension());
    }

    public static JFrame createFrame(Component c, String title, Point location,
            Dimension size) {

        JFrame frame = new JFrame(title);
        frame.getContentPane().add(c);
        frame.pack();
        frame.setLocation(location);
        frame.setSize(size);

        PrefsManager.getInstance().registerComponent(frame, c.getName());

        return frame;
    }

    public static JDialog createDialog(Frame parent, Component c, String title,
            boolean modal) {

        JDialog dialog = new JDialog(parent, title, true);
        dialog.getContentPane().add(c);
        //dialog.pack();
        dialog.setSize(Constants.DIALOG_SIZE);
        dialog.setModal(modal);

        center(dialog);

        return dialog;
    }

    public static JDialog createDialog(Dialog parent, Component c,
            String title, boolean modal) {

        JDialog dialog = new JDialog(parent, title, true);
        dialog.getContentPane().add(c);
        //dialog.pack();
        dialog.setSize(Constants.DIALOG_SIZE);
        dialog.setModal(modal);

        center(dialog);

        return dialog;
    }
}