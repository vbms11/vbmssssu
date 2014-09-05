/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vbmscode.sssu.env;

import java.util.LinkedList;
import java.util.List;

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
                    nodes[x][y][z].update(this);
                }
            }
        }
        
        // even step
        for (int x=1; x<size; x+=2) {
            for (int y=1; y<size; y+=2) {
                for (int z=1; z<size; z+=2) {
                    nodes[x][y][z].update(this);
                }
            }
        }
    }
    
    public double getCharge (int x, int y, int z) {
        
        return nodes[x][y][z].getCharge();
    }
    
    public void setCharge (int x, int y, int z, double charge) {
        
        nodes[x][y][z].setCharge(charge);
    }
    
    public double getChargeRange () {
        
        return chargeRange;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public EnvironmentNode[][][] getNodes() {
        return nodes;
    }

    public void setNodes(EnvironmentNode[][][] nodes) {
        this.nodes = nodes;
    }
    
    public List<Double> getSuroundingCharges (int x, int y, int z) {
        
        List<Double> lst_charges = new LinkedList();
        
        if (x > 0) {
            lst_charges.add(nodes[x-1][y][z].getCharge());
        }
        
        if (y > 0) {
            lst_charges.add(nodes[x][y-1][z].getCharge());
        }
        
        if (z > 0) {
            lst_charges.add(nodes[x][y][z-1].getCharge());
        }
        
        if (x < size-1) {
            lst_charges.add(nodes[x+1][y][z].getCharge());
        }
        
        if (y < size-1) {
            lst_charges.add(nodes[x][y+1][z].getCharge());
        }
        
        if (z < size-1) {
            lst_charges.add(nodes[x][y][z+1].getCharge());
        }
        
        for (Double charge : lst_charges) {
            if (charge > 0) {
                System.out.println("x: "+x+" y: "+y+" z: "+z+" value: "+charge);
            }
            
        }
        
        return lst_charges;
    }
    
}
