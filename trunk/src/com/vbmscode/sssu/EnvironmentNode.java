/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vbmscode.sssu;

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
    
    public void update () {
        
    }
    
    double getCharge () {
    
        return charge;
    }
    
}
