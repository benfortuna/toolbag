/*
 * Created on 21/11/2004
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
package net.fortuna.toolbag.ui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import net.fortuna.toolbag.format.DataSizeFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author benfortuna
 *
 * A component for displaying JVM statistics.
 */
public class JvmStatistics extends JComponent {

    private static final int MAX_ENTRIES = 100;
    
    private static Log log = LogFactory.getLog(JvmStatistics.class);
    
    private LinkedList stats;
    
    private class Stat implements Comparable {
        
        private long freeMemory;
        
        private long totalMemory;
        
        private int threadCount;
        
        public Stat(long free, long total, int threads) {
            freeMemory = free;
            totalMemory = total;
            threadCount = threads;
        }
        
        /**
         * @return Returns the freeMemory.
         */
        public final long getFreeMemory() {
            return freeMemory;
        }
        /**
         * @return Returns the totalMemory.
         */
        public final long getTotalMemory() {
            return totalMemory;
        }
        
        /* (non-Javadoc)
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        public int compareTo(Object arg0) {
            return compareTo((Stat) arg0);
        }

        public int compareTo(Stat arg0) {
            return new Long(getTotalMemory()).compareTo(new Long(arg0.getTotalMemory()));
        }
        /**
         * @return Returns the threadCount.
         */
        public final int getThreadCount() {
            return threadCount;
        }
    }
    
    public JvmStatistics() {
        stats = new LinkedList();
        
        Thread t = new Thread() {
            /* (non-Javadoc)
             * @see java.lang.Thread#run()
             */
            public void run() {
                for (;;) {
                    if (stats.size() > MAX_ENTRIES) {
                        stats.removeFirst();
                    }
                    stats.add(new Stat(Runtime.getRuntime().freeMemory(), Runtime.getRuntime().totalMemory(), getThreadGroup().activeCount()));
                    repaint();
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException ie) {                    
                    }
                }
            }
        };
        t.start();
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    protected void paintComponent(final Graphics g) {
        Dimension size = getSize();
        
        g.clearRect(0, 0, size.width, size.height);
        drawMemoryGraph(0, 0, size.width, size.height / 2, g);
        drawThreadGraph(0, size.height / 2, size.width, size.height / 2, g);
    }
    
    private void drawMemoryGraph(final int x, final int y, final int width, final int height, final Graphics g) {
//        Dimension size = getSize();
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10, new float[] {5, 3, 2, 3}, 0);
        g2d.setStroke(dashed);        
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setFont(new Font(g2d.getFont().getFamily(), g2d.getFont().getStyle(), 9));
        
        Stat maxStat = (Stat) Collections.max(stats);
        
        for (int i = 1; i <= 6; i++) {
            int yOffset = (i + 1) * (height / 6); 
            g2d.drawString(DataSizeFormat.getInstance().format(new Long(maxStat.getTotalMemory() / (long) i).intValue()), x, yOffset);
            g2d.drawLine(x + 30, yOffset, x + width, yOffset);
        }

        g2d.setStroke(new BasicStroke());        
        g2d.setColor(getForeground());
        Stat stat = null;
        Stat prevStat = null;
        int index = 0;
        
        log.info("Total Memory: " + DataSizeFormat.getInstance().format(new Long(Runtime.getRuntime().totalMemory()).intValue()));
        log.info("Free Memory: " + DataSizeFormat.getInstance().format(new Long(Runtime.getRuntime().freeMemory()).intValue()));
        log.info("Used Memory: " + DataSizeFormat.getInstance().format(new Long(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()).intValue()));

        for (Iterator i = stats.iterator(); i.hasNext(); index++) {
            prevStat = stat;
            stat = (Stat) i.next();
            
            if (prevStat != null) {                
                log.debug("stat 1 x: " + (int) (width * (index / (float) stats.size())));
                log.debug("stat 1 y: " + (int) (height * (prevStat.getFreeMemory() / (float) maxStat.getTotalMemory())));
                log.debug("stat 2 x: " + (int) (width * ((index + 1) / (float) stats.size())));
                log.debug("stat 2 y: " + (int) (height * (stat.getFreeMemory() / (float) maxStat.getTotalMemory())));
                
                g2d.drawLine(x + (int) (width * (index / (float) stats.size())),
                            y + (int) (height * (prevStat.getFreeMemory() / (float) maxStat.getTotalMemory())),
                            x + (int) (width * ((index + 1) / (float) stats.size())),
                            y + (int) (height * (stat.getFreeMemory() / (float) maxStat.getTotalMemory())));
            }
        }
        g2d.dispose();
    }

    private void drawThreadGraph(final int x, final int y, final int width, final int height, final Graphics g) {
        
        Stat maxStat = (Stat) Collections.max(stats);
        Stat stat = null;
        Stat prevStat = null;
        int index = 0;

        for (Iterator i = stats.iterator(); i.hasNext(); index++) {
            prevStat = stat;
            stat = (Stat) i.next();
            
            if (prevStat != null) {                
                g.drawLine(x + (int) (width * (index / (float) stats.size())),
                            y + (int) (height * (prevStat.getThreadCount() / (float) maxStat.getThreadCount())),
                            x + (int) (width * ((index + 1) / (float) stats.size())),
                            y + (int) (height * (stat.getThreadCount() / (float) maxStat.getThreadCount())));
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame("JvmStatistics Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(new JvmStatistics());
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
