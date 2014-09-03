/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vbmscode.sssu;

/**
 *
 * @author Administrator
 */
public class Environment {
    
    int size = 21;
    double chargeRange = 100;
    
    EnvironmentNode[][][] nodes;
    
    public void init () {
        
        // create the nodes
        nodes = new EnvironmentNode[size][size][size];
        for (int x=0; x<size; x++) {
            for (int y=0; y<size; y++) {
                for (int z=0; z<size; z++) {
                    nodes[x][y][z] = new EnvironmentNode(x,y,z);
                }
            }
        }
    }
    
    public void update () {
        
        // odd step
        for (int x=0; x<size; x+=2) {
            for (int y=0; y<size; y+=2) {
                for (int z=0; z<size; z+=2) {
                    nodes[x][y][z].update();
                }
            }
        }
        
        // even step
        for (int x=1; x<size; x+=2) {
            for (int y=1; y<size; y+=2) {
                for (int z=1; z<size; z+=2) {
                    nodes[x][y][z].update();
                }
            }
        }
    }
    
    public double getCharge (int x, int y, int z) {
        
        return nodes[x][y][z].getCharge();
    }
    
    public double getChargeRange () {
        
        return chargeRange;
    }
    
}
