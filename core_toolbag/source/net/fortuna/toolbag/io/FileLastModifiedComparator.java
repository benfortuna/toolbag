package net.fortuna.toolbag.io;

import java.io.File;
import java.util.Comparator;

/**
 * A Comparator used to compare files based on their modification date.
 * @author Ben Fortuna
 */
public class FileLastModifiedComparator implements Comparator {

    /**
     * @see java.util.Comparator#compare(Object, Object)
     */
    public final int compare(final Object arg0, final Object arg1) {

        File file1 = (File) arg0;
        File file2 = (File) arg1;

        return new Long(file1.lastModified() - file2.lastModified()).intValue();
    }

}