/*
 * Created on 25-Aug-2003
 * 
 * Copyright 2003.
 */
package net.fortuna.toolbag.ui;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 * @author Ben Fortuna
 *  
 */
public class StyleFactory {

    private static StyleFactory instance = new StyleFactory();

    private StyleContext context;

    public StyleFactory() {

        context = new StyleContext();
    }

    public static StyleFactory getInstance() {

        return instance;
    }

    public Style addStyle(String name) {

        return context.addStyle(name, null);
    }

    public Style getStyle(String name) {

        return context.getStyle(name);
    }

    public StyledDocument createDocument() {

        return new DefaultStyledDocument(context);
    }
}