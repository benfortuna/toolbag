package net.fortuna.toolbag.math;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 * @author Ben Fortuna
 *  
 */
public final class GeometryUtils {

    private static Random rng = new Random();

    /**
     * Constructor made private to prevent instantiation.
     */
    private GeometryUtils() {
    }

    /**
     * Returns a random rectangle contained in the specified bounding Rectangle
     * and with minimum width and height as specified.
     * 
     * @param bounds
     *            bounds within which to contain the generated rectangle
     * @param minWidth
     *            minimum width of rectangle
     * @param minHeight
     *            minimum height of rectangle
     * @param ratio
     *            aspect ratio to be maintained if positive
     * @return a rectangle conforming to the specified requirements
     */
    public static Rectangle getRectangle(final Rectangle bounds,
            final int minWidth, final int minHeight, final float ratio) {

        int x, y, width, height;

        if (ratio > 1) {
            // determine a random height..
            height = rng.nextInt(bounds.height - minHeight) + minHeight;
            width = (int) (height * ratio);
        }
        else if (ratio > 0) {
            // determine a random width..
            width = rng.nextInt(bounds.width - minWidth) + minWidth;
            height = (int) (width * ratio);
        }
        else {
            // determine a random width..
            width = rng.nextInt(bounds.width - minWidth) + minWidth;

            // determine a random height..
            height = rng.nextInt(bounds.height - minHeight) + minHeight;
        }

        x = rng.nextInt(bounds.width - width);

        y = rng.nextInt(bounds.height - height);

        return new Rectangle(x, y, width, height);
    }

    /**
     * Calculate angle based on quadrant of second point wrt first point.
     * ie.  3|4
     *      ---
     *      2|1
     * @param point1
     * @param point2
     * @return
     */
    public static double getAngleBetweenPoints(final Point point1, final Point point2) {
        // Calculate distance between two points...
        double distance = Math.sqrt(Math.pow(point2.x - point1.x, 2)
                + Math.pow(point2.y - point1.y, 2));

        // Debugging...
        //System.out.println("Distance between point = "+distance);

        // First quadrant...
        if (point2.x >= point1.x && point2.y > point1.y) {
            return Math.acos((point2.x - point1.x) / distance);
        }
        // Second quadrant...
        else if (point1.x > point2.x && point2.y >= point1.y) {
            return (Math.PI / 2) + Math.acos((point2.y - point1.y) / distance);
        }
        // Third quadrant...
        else if (point1.x >= point2.x && point1.y > point2.y) {
            return Math.PI + Math.acos((point1.x - point2.x) / distance);
        }
        // Fourth quadrant...
        else { //if (point2.x>point1.x && point1.y>=point2.y) {
            return 2 * (Math.PI) - Math.acos((point2.x - point1.x) / distance);
        }
    }

    /**
     * @param origin
     * @param angle
     * @param distance
     * @return
     */
    public static Point getPointFromOrigin(final Point origin, final double angle,
            final int distance) {
        return new Point(origin.x + (int) (distance * Math.cos(angle)),
                origin.y + (int) (distance * Math.sin(angle)));
    }
}