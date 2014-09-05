/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vbmscode.sssu.env;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class EnvironmentNode {
    
    double charge;
    int x, y, z;
    
    public EnvironmentNode (int x, int y, int z) {
        
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void update (Environment environment) {
        
        List<Double> lst_charges = environment.getSuroundingCharges(x, y, z);
        
        double maxCharge = 0;
        for (Double otherCharge : lst_charges) {
            if (otherCharge > maxCharge) {
                maxCharge = otherCharge;
            }
        }
        
        setCharge(maxCharge / 2);
    }
    
    double getCharge () {
    
        return charge;
    }
    
    void setCharge (double charge) {
        
        this.charge = charge;
    }
    
}
