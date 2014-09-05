/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vbmscode.sssu.env;

/**
 *
 * @author Administrator
 */
public class PulseImmiter {
    
    double pulseStrength;
    double pulseDuration;
    int pulseX = 10, pulseY = 10, pulseZ = 10;
    long startTime;
    
    int state;
    final int state_stopped = 0;
    final int state_running = 1;
    
    public void start () {
        state = state_running;
        startTime = System.nanoTime();
    }
    
    public void update (Environment environment) {
        
        if (state == state_running) {
            
            long currentTime = System.nanoTime();
            long phase = currentTime - startTime;
            
            if (phase < pulseDuration) {
                
                long halfPulseDuration = (long) (pulseDuration / 2d);
                double charge;
                
                if (phase < halfPulseDuration) {

                    double ratio = (double) phase / halfPulseDuration;
                    charge = ratio * pulseStrength;

                } else {

                    double ratio = (double) (phase - halfPulseDuration) / halfPulseDuration;
                    charge = pulseStrength - ratio * pulseStrength;
                }

                environment.setCharge(pulseX, pulseY, pulseZ, charge);
            
            } else {
                
                environment.setCharge(pulseX, pulseY, pulseZ, 0);
                state = state_stopped;
            }
        }
    }

    public double getPulseStrength() {
        return pulseStrength;
    }

    public void setPulseStrength(double pulseStrength) {
        this.pulseStrength = pulseStrength;
    }

    public double getPulseDuration() {
        return pulseDuration;
    }

    public void setPulseDuration(double pulseDuration) {
        this.pulseDuration = pulseDuration;
    }

    public int getPulseX() {
        return pulseX;
    }

    public void setPulseX(int pulseX) {
        this.pulseX = pulseX;
    }

    public int getPulseY() {
        return pulseY;
    }

    public void setPulseY(int pulseY) {
        this.pulseY = pulseY;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    
    
    
    
    
    
}
