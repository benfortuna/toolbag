/*
 * $Id: HtmlUtils.java [06-Dec-2003 22:41:43]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.util;

import java.util.StringTokenizer;

/**
 * HTML utility methods
 * 
 * @author Ben Fortuna
 */
public final class HtmlUtils {

    /**
     * Constructor made private to prevent instantiation.
     */
    private HtmlUtils() {
    }
    
    /**
     * Strips HTML as follows:
     *  - remove html tags - remove newline characters (as HTML would ignore
     * anyway!) - replace <br>
     * tags with newline
     * 
     * @param html
     *            the HTML string
     * @return a stripped version of the HTML string
     */
    public static String stripHtml(final String html) {

        StringTokenizer tokenizer = new StringTokenizer(html, "<>\n&;", true);

        StringBuffer b = new StringBuffer();

        while (tokenizer.hasMoreTokens()) {

            String token = tokenizer.nextToken();

            if ("<".equals(token) && tokenizer.hasMoreTokens()) {

                String tagToken = tokenizer.nextToken();

                if ("br".equalsIgnoreCase(tagToken)) {

                    b.append('\n');
                }
            }
            /*
             * else if ("&".equals(token) && tokenizer.hasMoreTokens()) {
             * 
             * StringBuffer codeBuffer = new StringBuffer(token);
             * codeBuffer.append(tokenizer.nextToken());
             * 
             * if (tokenizer.hasMoreTokens()) {
             * 
             * codeBuffer.append(tokenizer.nextToken()); }
             * 
             * b.append(getCharacter(codeBuffer.toString())); }
             */
            else if ("\n".equals(token)) {

                // put a space in place of newlines..
                b.append(' ');
            }
            else if (!">".equals(token)) {

                b.append(token);
            }
        }

        return b.toString().replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                .replaceAll("&quot;", "\"").replaceAll("&amp;", "&")
                .replaceAll("&#39;", "'")
                //.replaceAll("&#36;","$")
                .replaceAll("&apos;", "'");
    }

    /**
     * Returns the character represented by the specifed HTML
     * escape code.
     * @param code an HTML escape code
     * @return a string containing the character represented by
     * the specified escape code, or the code itself if it is
     * unrecognised
     */
    public static String getCharacter(final String code) {

        if ("&amp;".equalsIgnoreCase(code)) {

            return "&";
        }
        else if ("&quot;".equalsIgnoreCase(code)) {

            return "\"";
        }
        else if ("&apos;".equalsIgnoreCase(code)) {

        return "'"; }

        return code;
    }

    /**
     * Modifies the given string to escape all HTML characters.
     * 
     * @param html
     *            the html string
     * @return a modified string with all HTML characters replaced with escape
     *         sequences
     */
    public static String escape(final String html) {

        return html.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    /**
     * Renders the specified arguments as an HTML string.
     * @param value an object to render as HTML
     * @param itemCount indicates the number of items in the
     * rendered object
     * @return an HTML string
     */
    public static String toHtml(final Object value, final int itemCount) {
        StringBuffer b = new StringBuffer();
        b.append("<html>");
        b.append(HtmlUtils.escape(value.toString()));
        b.append("<font color=\"silver\">");
        b.append(" (");
        b.append(itemCount);
        b.append(')');
        b.append("</font>");
        b.append("</html>");
    
        return b.toString();
    }
}