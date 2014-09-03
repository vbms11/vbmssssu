/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vbmscode.sssu;

import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Administrator
 */
public class Camera {
    
    float x, y, z, hAngel, vAngel;
    Vector3f direction;
    
    public Camera () {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        direction.set(0, 0, 0);
        direction.normalise();
    }
    
    public Camera (float x, float y, float z, float px, float py, float pz) {
        this.x = x;
        this.y = y;
        this.z = z;
        direction.set(px - x, py - y, pz - z);
        direction.normalise();
    }
    
    public void loadIdentity () {
        GLU.gluLookAt(x, y, z, direction.x, direction.y, direction.z, 0, 1, 0);
    }
    
    public synchronized void rotateVertical (double degrees) {
        vAngel += degrees;
    }
    
    public synchronized void rotateHorizontal (double degrees) {
        hAngel += degrees;
    }
    
    public synchronized void rotate (double x, double y) {
        rotateHorizontal(x);
        rotateVertical(y);
    }
    
    public synchronized void refreshDirection () {
        float vx = (float) Math.cos(hAngel);
        float vz = (float) Math.sin(hAngel);
        float vy = (float) Math.sin(vAngel);
        vx *= vy;
        vz *= vz;
        direction.setX(vx);
        direction.setZ(vz);
        direction.setY(vy);
    }
}
