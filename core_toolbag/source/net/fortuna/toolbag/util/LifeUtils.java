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
 * A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.fortuna.toolbag.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Type description.
 * @author Ben_Fortuna
 */
public class LifeUtils {
    
    private static Log log = LogFactory.getLog(LifeUtils.class);

    public static boolean[][] generate(boolean[][] matrix) {
        boolean[][] ng = new boolean[matrix.length][matrix[0].length];

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                int neighbours = getNeighbourCount(x, y, matrix);
                
                // debugging..
                if (neighbours > 0) {
                    log.debug("Neighbour count: [" + neighbours + "]");
                }
                
                // populated..
                if (matrix[x][y]) {
                    if (neighbours >= 2 && neighbours <= 3) {
                        ng[x][y] = true;
                    }
                }
                // unpopulated..
                else {
                    if (neighbours == 3) {
                        ng[x][y] = true;
                    }
                }
            }
        }
        
        return ng;
    }
    
    private static int getNeighbourCount(int x, int y, boolean[][] matrix) {
        int count = 0;
        
        if (x > 0) {
            if (y > 0 && matrix[x-1][y-1]) {
                count++;
            }
            if (matrix[x-1][y]) {
                count++;
            }
            if (y < (matrix[x-1].length - 1) && matrix[x-1][y+1]) {
                count++;
            }
        }
        
        if (x < (matrix.length - 1)) {
            if (y > 0 && matrix[x+1][y-1]) {
                count++;
            }
            if (matrix[x+1][y]) {
                count++;
            }
            if (y < (matrix[x+1].length - 1) && matrix[x+1][y+1]) {
                count++;
            }
        }
        if (y > 0 && matrix[x][y-1]) {
            count++;
        }
        if (y < (matrix[x].length - 1) && matrix[x][y+1]) {
            count++;
        }
        
        return count;
    }
}
