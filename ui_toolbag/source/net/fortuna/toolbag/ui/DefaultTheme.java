/*
 * Created on 28-Aug-2003
 * 
 * Copyright 2003.
 */
package net.fortuna.toolbag.ui;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * @author Ben Fortuna
 *  
 */
public class DefaultTheme extends DefaultMetalTheme {

    /**
     *  
     */
    public DefaultTheme() {

        super();
    }

    public ColorUIResource getSecondary3() {

        return new ColorUIResource(175, 175, 175);
    }

    public FontUIResource getControlTextFont() {

        FontUIResource fuir = super.getControlTextFont();

        return new FontUIResource(fuir.getFontName(), FontUIResource.PLAIN, 10);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.plaf.metal.MetalTheme#getUserTextFont()
     */
    public FontUIResource getUserTextFont() {

        FontUIResource fuir = super.getUserTextFont();

        return new FontUIResource(fuir.getFontName(), FontUIResource.PLAIN, 10);
    }

}