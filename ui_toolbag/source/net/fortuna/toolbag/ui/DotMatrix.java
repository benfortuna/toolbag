/*
 * $Id$
 *
 * Created: 22/10/2004
 *
 * Copyright (c) 2004, Ben Fortuna
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  o Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  o Neither the name of Ben Fortuna nor the names of any other contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.fortuna.toolbag.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import net.fortuna.toolbag.util.LifeUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * A visual component for displaying a specified matrix of dots.
 * @author Ben_Fortuna
 */
public class DotMatrix extends JComponent {
    
    private static Log log = LogFactory.getLog(DotMatrix.class);

    private boolean[][] matrix;

    private boolean[][] burned;
    
    private Color burnColour;
    
    private int gapSize;
    
    /**
     * @return Returns the matrix.
     */
    protected boolean[][] getMatrix() {
        return matrix;
    }
    
    /**
     * @param matrix The matrix to set.
     */
    protected void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }
    
    /**
     * @return Returns the burned.
     */
    protected boolean[][] getBurned() {
        return burned;
    }
    /**
     * @param burned The burned to set.
     */
    protected void setBurned(boolean[][] burned) {
        this.burned = burned;
    }
    /**
     * @return Returns the burnColour.
     */
    protected Color getBurnColour() {
        return burnColour;
    }
    /**
     * @param burnColour The burnColour to set.
     */
    protected void setBurnColour(Color burnColour) {
        this.burnColour = burnColour;
    }
    /**
     * @return Returns the gap.
     */
    protected int getGapSize() {
        return gapSize;
    }
    
    /**
     * @param gap The gap to set.
     */
    protected void setGapSize(int gap) {
        this.gapSize = gap;
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    protected void paintComponent(final Graphics g) {
        
        // debugging..
        log.debug("Component size: [" + getSize() + "]");
        
        draw(matrix, getForeground(), g);
    }
    
    private void draw(boolean[][] matrix, Color colour, Graphics g) {
        
        if (matrix != null) {
            
            Dimension dotSize = new Dimension((getSize().width - gapSize * matrix.length) / matrix.length, (getSize().height - gapSize * matrix[0].length) / matrix[0].length);
            
            // debugging..
            log.debug("Dot size: [" + dotSize + "]");
            
            for (int x = 0; x < matrix.length; x++) {
                for (int y = 0; y < matrix[x].length; y++) {
                    if (matrix[x][y]) {
                        g.setColor(colour);
                        g.fillRect(x * dotSize.width + x * gapSize, y * dotSize.height + y * gapSize, dotSize.width, dotSize.height);
                    }
                    // slow burn..
                    else if (burnColour != null && burned[x][y]) {
                        g.setColor(burnColour);
                        g.fillRect(x * dotSize.width + x * gapSize, y * dotSize.height + y * gapSize, dotSize.width, dotSize.height);
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {

        boolean[][] matrix1 = new boolean[70][60];
        
        final DotMatrix dm = new DotMatrix();
        dm.setGapSize(1);
        dm.setBurnColour(Color.RED);
//        dm.setBurnColour(dm.getBackground());
        
        JButton button = new JButton("Next");
        /*
        button.addActionListener(new ActionListener() {
            
            /* (non-Javadoc)
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             *
            public void actionPerformed(ActionEvent arg0) {
                boolean[][] matrix = LifeUtils.generate(matrix1);
                dm.setMatrix(matrix);
                dm.repaint();
            }
        });
        */
        
        JFrame f = new JFrame("DotMatrix Test");
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(dm, BorderLayout.CENTER);
        f.getContentPane().add(button, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.pack();
        f.setSize(300, 300);
        f.setVisible(true);
        
        Random rng = new Random();
        
        /*
        matrix[rng.nextInt(100)][rng.nextInt(100)] = true;
        matrix[rng.nextInt(100)][rng.nextInt(100)] = true;
        matrix[rng.nextInt(100)][rng.nextInt(100)] = true;
        matrix[rng.nextInt(100)][rng.nextInt(100)] = true;
        matrix[rng.nextInt(100)][rng.nextInt(100)] = true;
        */
        
        // glider..
        /*
        matrix1[0][2] = true;
        matrix1[1][0] = true;
        matrix1[1][2] = true;
        matrix1[2][1] = true;
        matrix1[2][2] = true;
        */

        // r-pentomino
        matrix1[40][40] = true;
        matrix1[41][40] = true;
        matrix1[39][41] = true;
        matrix1[40][41] = true;
        matrix1[40][42] = true;
        
        boolean[][] burned = new boolean[70][60];

        int[][] burnable = new int[][] {
                // t
                {30,30},{29,31},{30,31},{31,31},{30,32},{30,33},{30,34},
                // o
                {34,31},{35,31},{33,32},{36,32},{33,33},{36,33},{34,34},{35,34},
                // u
                {38,31},{41,31},{38,32},{41,32},{38,33},{41,33},{39,34},{40,34},
                // c
                {44,31},{45,31},{43,32},{43,33},{44,34},{45,34},
                // h
                {47,30},{47,31},{47,32},{48,32},{47,33},{49,33},{47,34},{49,34},
                // b
                {51,30},{51,31},{51,32},{52,32},{51,33},{53,33},{51,34},{52,34},
                // a
                {55,31},{56,31},{57,31},{54,32},{57,32},{54,33},{57,33},{55,34},{56,34},{57,34},
                // s
                {60,31},{61,31},{60,32},{61,33},{59,34},{60,34},
                // e
                {64,31},{65,31},{63,32},{65,32},{66,32},{63,33},{64,34},{65,34}
        };
        
        for (;;) {

            dm.setMatrix(matrix1);
            dm.repaint();

            Thread.sleep(50);
            
            matrix1 = LifeUtils.generate(matrix1);
            
            for (int i = 0; i < burnable.length; i++) {
                if (matrix1[burnable[i][0]][burnable[i][1]]) {
                    burned[burnable[i][0]][burnable[i][1]] = true;
                }
            }
            /*
            burned[10][10] = true;
            burned[9][11] = true;
            burned[10][11] = true;
            burned[11][11] = true;
            burned[10][12] = true;
            burned[10][13] = true;
            burned[10][14] = true;
            */
            dm.setBurned(burned);
        }

    }
}
