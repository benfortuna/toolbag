package net.fortuna.toolbag.ui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Ben Fortuna
 * 
 * This class scrolls its embedded component horizontally.
 */
public class Scroller extends JPanel implements ActionListener { //Runnable,

    private Timer timer;

    private JComponent component;

    private int delay;

    public Scroller(JComponent c, int delay) {
        // set null layout as we will position manually..
        super(null, true);

        setComponent(c);
        //setDelay(delay);

        setOpaque(false);

        //add(getComponent());

        Rectangle bounds = c.getBounds();
        bounds.x = 0 + getInsets().left;
        bounds.y = 0 + getInsets().top;
        bounds.width = c.getPreferredSize().width;
        bounds.height = c.getPreferredSize().height;
        c.setBounds(bounds);

        Dimension size = c.getPreferredSize();

        setPreferredSize(new Dimension(size.width + getInsets().left,
                size.height + getInsets().top));

        timer = new Timer(delay, this);
    }

    //public void run()
    public void actionPerformed(ActionEvent e) {
        //while (isVisible()) {

        // get component's current positions..
        Rectangle bounds = component.getBounds();

        // if component already off-screen, reset on other side..
        if (bounds.x < -bounds.width)
            bounds.x = getSize().width;
        else
            bounds.x -= 1;

        // position in center of scroller..
        bounds.y = (getSize().height / 2) - (component.getSize().height / 2);

        component.setBounds(bounds);

        revalidate();

        /*
         * try { Thread.sleep(delay); } catch (InterruptedException e) {}
         */
        //}
    }

    public void start() {
        timer.start();
    }

    public static void main(String[] args) {
        //Scroller s = new Scroller(new JButton("This is a test!"));
        Scroller s = new Scroller(new JLabel(
                "<html><em>This is a test!</em></html>"), 25);

        JFrame f = new JFrame("Scroller Test");
        f.getContentPane().add(s);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        //Thread t = new Thread(s);
        s.start();
    }

    /**
     * Returns the component.
     * 
     * @return JComponent
     */
    public JComponent getComponent() {
        return component;
    }

    /**
     * Sets the component.
     * 
     * @param component
     *            The component to set
     */
    public void setComponent(JComponent component) {
        // remove old component..
        removeAll();

        // add new component..
        add(component);

        this.component = component;
    }

    /**
     * Gets the delay.
     * 
     * @return Returns a int
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Sets the delay.
     * 
     * @param delay
     *            The delay to set
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

}