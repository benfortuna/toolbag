/*
 * $Id: AlertManager.java [02-May-2004]
 *
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.ui.alert;

import java.util.LinkedList;

/**
 * Manages alert windows.
 * @author benfortuna
 */
public final class AlertManager {

    private static final int MAX_ACTIVE = 2;
    
    private static AlertManager instance = new AlertManager();
    
    private LinkedList alerts;
    
    private AlertProcessor[] processors;
    
    /**
     * Constructor made private to prevent instantiation.
     */
    private AlertManager() {
        alerts = new LinkedList();
        
        processors = new AlertProcessor[MAX_ACTIVE];
        
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new AlertProcessor(alerts, i);
            processors[i].start();
        }
    }

    /**
     * @return Returns the instance.
     */
    public static AlertManager getInstance() {
        return instance;
    }
    
    /**
     * @param alert an alert to register for display
     */
    public void register(final Alert alert) {
        
        synchronized (alerts) {
            alerts.addFirst(alert);
            alerts.notify();
        }
    }
}
