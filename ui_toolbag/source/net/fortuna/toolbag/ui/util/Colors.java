/*
 * Created on 2/12/2004
 *
 * $Id$
 *
 * Copyright (c) 2004, Ben Fortuna
 * All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.fortuna.toolbag.ui.util;

import java.awt.Color;

/**
 * @author benfortuna
 *
 * A collection of utility methods for working with colours.
 */
public final class Colors {

    /**
     * Constructor made private to enforce static nature.
     */
    private Colors() {        
    }
    
    /**
     * Returns a hexidecimal string representation of the
     * specified colour.
     * @param colour a colour to encode
     * @return a string representation of the colour
     */
    public static final String encode(final Color colour) {
        StringBuffer b = new StringBuffer();
        if (colour != null) {
            b.append('#');
            b.append(Integer.toHexString(colour.getRed()));
            b.append(Integer.toHexString(colour.getGreen()));
            b.append(Integer.toHexString(colour.getBlue()));
        }
        return b.toString();
    }
}
