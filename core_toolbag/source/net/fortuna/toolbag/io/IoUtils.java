package net.fortuna.toolbag.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Ben Fortuna
 *  
 */
public final class IoUtils {
    
    /**
     * Constructor made private to prevent instantiation.
     */
    private IoUtils() {
    }

    /**
     * Attempts to open an InputStream to the specified resource.
     * @param source string representation of a resource
     * @return an input stream to the specified resource  
     */
    public static InputStream openInputStream(final String source) {
        // attempt to open stream from url..
        try {
            return new URL(source).openStream();
        }
        catch (Exception e) {
            // attempt to open stream from file..
            try {
                return new FileInputStream(source);
            }
            catch (FileNotFoundException fnfe) {
                // if cannot establish stream, do nothing..
                return null;
            }
        }
    }

    /**
     * Attempts to open an input stream to the specified resource.
     * @param path base location of resource
     * @param source string representation of a resource
     * @return an input stream to the specified resource  
     */
    public static InputStream openInputStream(final String path,
            final String source) {
        // attempt to open stream from url..
        try {
            return new URL(new URL(path), source).openStream();
        }
        catch (Exception e) {

            // attempt to open stream from file..
            try {
                return new FileInputStream(new File(path, source));
            }
            catch (FileNotFoundException fnfe) {
                // if cannot establish stream, do nothing..
                return null;
            }
        }
    }

    /**
     * Returns the base path of the specified file.
     * 
     * @param file
     *            a file
     * @return a file representing the base path of the specified file (ie. the
     *         directory in which it is located)
     */
    public static File getFileBase(final File file) {
        String fileString = file.getAbsolutePath();

        return new File(fileString.substring(0, fileString
                .lastIndexOf(File.separatorChar)));
    }

    /**
     * Determines if the specified path (file or URL) is relative.
     * 
     * @param path
     *            a string representation of a path
     * @return boolean indicates whether the specified string represents a
     *         relative path
     */
    public static boolean isPathRelative(final String path) {

        // if a URL can be constructed the path is not relative..
        try {
            new URL(path);
            return false;
        }
        catch (MalformedURLException mue) {

            // if a file can be found from the path it is not relative..
            File f = new File(path);
            return !f.exists();
        }
    }
}