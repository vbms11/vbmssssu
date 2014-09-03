package com.vbmscode.sssu;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author Administrator
 */
public class Scene {
    
    int level = 10;
    
    public void render (Environment environment) {
        
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glLoadIdentity();
        
        //GLU.gluLookAt(level, level, level, level, level, level, level, level, level);
        //GL11.glTranslatef(0.0f, 0.0f, zTranslate);
        
        GL11.glPushMatrix();
        GL11.glTranslatef(-50, -50, 100);
        
        // draw environment charges
        
        double chargeRange = environment.getChargeRange();
        int z = level;
        
        for (int x=0; x<environment.size-1; x++) {
            
            GL11.glBegin(GL11.GL_QUAD_STRIP);
            
            for (int y=0; y<environment.size; y++) {
                
                double charge = environment.getCharge(x, y, z);
                double[] color = getChargeColor(charge, chargeRange);
                
                GL11.glColor3d(color[0], color[1], color[2]);
		GL11.glVertex3i(x, y, z);
                
                charge = environment.getCharge(x+1, y, z);
                color = getChargeColor(charge, chargeRange);
                
                GL11.glColor3d(color[0], color[1], color[2]);
		GL11.glVertex3i(x, y, z);
            }
            
            GL11.glEnd();
        }
        
        // draw environment grid
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glColor3d(1.0,1.0,1.0);
	GL11.glBegin(GL11.GL_LINES);
        for (int x=0; x<environment.size+1; x++) {
            GL11.glVertex3i(x, 0, 0);
            GL11.glVertex3i(x, environment.size, 0);
            GL11.glVertex3i(0, x, 0);
            GL11.glVertex3i(environment.size, x, 0);
        }
        GL11.glEnd();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        
        GL11.glPopMatrix();
    }
    
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
