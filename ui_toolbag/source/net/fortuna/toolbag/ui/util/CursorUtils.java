package net.fortuna.toolbag.ui.util;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

/**
 * Utility methods for working with cursors.
 * 
 * @author benfortuna
 */
public final class CursorUtils {

    private static final int CURSOR_IMAGE_WIDTH = 16;

    private static final int CURSOR_IMAGE_HEIGHT = 16;

    /**
     * Constructor made private to prevent instantiation.
     */
    private CursorUtils() {
    }

    /**
     * Creates a blank cursor typically used to hide the on-screen cursor.
     * @return a cursor
     */
    public static Cursor getBlankCursor() {
        return Toolkit.getDefaultToolkit().createCustomCursor(
                new BufferedImage(CURSOR_IMAGE_WIDTH, CURSOR_IMAGE_HEIGHT,
                        BufferedImage.TYPE_4BYTE_ABGR), new Point(),
                "blank_cursor");
    }
}