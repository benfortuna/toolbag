package net.fortuna.toolbag.format;

/**
 * Used for formatting data size values.
 * @author Ben Fortuna
 */
public final class DataSizeFormat {

    private static final int KILOBYTE = 1024;

    private static final int MEGABYTE = KILOBYTE * KILOBYTE;

    private static DataSizeFormat instance = new DataSizeFormat();
    
    /**
     * Constructor made private to enforce singleton.
     */
    private DataSizeFormat() {
    }
    
    /**
     * @return Returns the instance.
     */
    public static DataSizeFormat getInstance() {
        return instance;
    }
    /**
     * Returns a string representation of the given byte size.
     * @param size the size to create a string representation
     * for
     * @return a string representation of the specified size
     */
    public String format(final int size) {
        StringBuffer b = new StringBuffer();

        if (size > MEGABYTE) {
            b.append(size / MEGABYTE);
            b.append(" mb");
        }
        else if (size > KILOBYTE) {
            b.append(size / KILOBYTE);
            b.append(" kb");
        }
        else {
            b.append(size);
            b.append(" bytes");
        }

        return b.toString();
    }
}