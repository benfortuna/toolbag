package net.fortuna.toolbag.ui.util;

import java.awt.Font;

/**
 * Utility methods for working with fonts.
 * @author benfortuna
 */
public final class Fonts {

    /**
     * Constructor made private to prevent instantiation.
     */
    private Fonts() {
    }
    
    /**
     * Encodes a font to a string representation
     * @param font a font to encode
     * @return a string representation of the specified font
     */
    public static String encode(final Font font) {
        
        if (font == null) {
            return null;
        }

        StringBuffer fontBuffer = new StringBuffer();

        fontBuffer.append(font.getName());
        fontBuffer.append('-');
        
        if (font.isPlain()) {
            fontBuffer.append("PLAIN");
        }
        
        if (font.isBold()) {
            fontBuffer.append("BOLD");
        }
        
        if (font.isItalic()) {
            fontBuffer.append("ITALIC");
        }
        
        fontBuffer.append('-');
        fontBuffer.append(font.getSize());

        return fontBuffer.toString();
    }

    /**
     * Returns a bold instance of the specified font.
     * @param font
     *            used as a base for creating a bold instance
     * @return a font that is a bold instance of the font specified
     */
    public static Font getBoldInstance(final Font font) {

        if ((Font.ITALIC & font.getStyle()) > 0) {

        return new Font(font.getFontName(), Font.ITALIC | Font.BOLD, font
                .getSize()); }

        return new Font(font.getFontName(), Font.BOLD, font.getSize());
    }
}
