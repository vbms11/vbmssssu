/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vbmscode.sssu.renderer;

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
        direction = new Vector3f(0, 0, 1);
        direction.normalise();
    }
    
    public Camera (float x, float y, float z, float px, float py, float pz) {
        this.x = x;
        this.y = y;
        this.z = z;
        direction = new Vector3f(px - x, py - y, pz - z);
        direction.normalise();
    }
    
    public void loadIdentity () {
        GLU.gluLookAt(x, y, z, direction.x, direction.y, direction.z, 0, 1, 0);
    }
    
    public synchronized void rotateVertical (float degrees) {
        vAngel += degrees;
        refreshDirection();
    }
    
    public synchronized void rotateHorizontal (float degrees) {
        hAngel += degrees;
        refreshDirection();
    }
    
    public synchronized void rotate (float x, float y) {
        rotateHorizontal(x);
        rotateVertical(y);
    }
    
    public synchronized void move (float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        refreshDirection();
    }
    
    public synchronized void moveForwald (float distance) {
        float vx = (float) Math.cos(hAngel);
        float vz = (float) Math.sin(hAngel);
        float vy = (float) Math.cos(vAngel);
        vx *= vy;
        vz *= vy;
        x = vx * distance;
        y = vy * distance;
        z = vz * distance;
        refreshDirection();
    }
    
    public synchronized void refreshDirection () {
        float vx = (float) Math.cos(hAngel);
        float vz = (float) Math.sin(hAngel);
        float vy = (float) Math.cos(vAngel);
        vx *= vy;
        vz *= vy;
        direction.setX(vx);
        direction.setZ(vz);
        direction.setY(vy);
    }
}
