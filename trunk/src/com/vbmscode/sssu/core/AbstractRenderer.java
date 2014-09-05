/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vbmscode.sssu.core;

import com.vbmscode.sssu.env.Environment;

/**
 *
 * @author Administrator
 */
public abstract class AbstractRenderer {
    
    public int level = 10;
    
    /**
     * called when the renderer is initialized
     * @return true on success
     */
    public abstract boolean init ();
    
    /**
     * called when the environment is to be rendererd
     * @param environment the dataset to be rendered
     */
    public abstract void render (Environment environment);
    
    /**
     * called when the renderer is to be destroyed
     */
    public abstract void destroy ();
    
    /**
     * returns rgb color for a given charge in a range
     * @param charge
     * @param chargeRange 
     * @return rgb color
     */
    public double[] getChargeColor (double charge, double chargeRange) {
        
        double[] colors = {0,0,0};
        
        if (charge > 0) {
            colors[0] = charge / chargeRange;
            colors[1] = 1 - colors[0];        
        } else if (charge < 0) {
            colors[2] = -charge / chargeRange;
            colors[1] = 1 - colors[2];        
        } else {
            colors[1] = 1;
        }
        
        return colors;
    }
}
