/*
 * $Id: JarFilter.java [02-May-2004]
 *
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.io;

import java.io.File;
import java.io.FileFilter;

/**
 * A file filter for jar files.
 * @author benfortuna
 */
public class JarFilter implements FileFilter {

    /**
     * @see java.io.FileFilter#accept(java.io.File)
     */
    public final boolean accept(final File file) {

        if (file.getName().endsWith(".jar")) {

        return true; }

        return false;
    }
}