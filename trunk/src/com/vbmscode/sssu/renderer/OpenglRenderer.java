package com.vbmscode.sssu.renderer;

import com.vbmscode.sssu.core.AbstractRenderer;
import com.vbmscode.sssu.core.Application;
import com.vbmscode.sssu.env.Environment;
import java.awt.Canvas;
import java.awt.Dimension;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author Administrator
 */
public class OpenglRenderer extends AbstractRenderer {
    
    Camera camera;
    
    public OpenglRenderer () {
        
        camera = new Camera();
    }
    
    public boolean init () {
        
        boolean result = true;
        
        try {
            
            Canvas canvas = Application.getInstance().getCanvas();
            
            Display.setResizable(true);
            Display.setParent(canvas);
            Display.create();
            
            Dimension canvasSize = canvas.getSize();
            
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glEnable(GL11.GL_DEPTH_TEST);

            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);

            GL11.glViewport(0, 0, canvasSize.width, canvasSize.height);

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();

            GLU.gluPerspective(45.0f, (float) canvasSize.width / canvasSize.height, 0.2f, 100.0f);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
            
        } catch (LWJGLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }
    
    public void render (Environment environment) {
        
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glLoadIdentity();
        
        camera.loadIdentity();
        
        GL11.glPushMatrix();
        
        // draw environment charges
        
        double chargeRange = environment.getChargeRange();
        int size = environment.getSize();
        int z = level;
        
        for (int x=0; x<size-1; x++) {
            
            GL11.glBegin(GL11.GL_QUAD_STRIP);
            
            for (int y=0; y<size; y++) {
                
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
        for (int x=0; x<size+1; x++) {
            GL11.glVertex3i(x, 0, 0);
            GL11.glVertex3i(x, size, 0);
            GL11.glVertex3i(0, x, 0);
            GL11.glVertex3i(size, x, 0);
        }
        GL11.glEnd();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        
        GL11.glPopMatrix();
        
        Display.update();
        Display.sync(60);
    }
    
    public void destroy () {
        
    }
    
    public Camera getCamera () {
       
        return camera;
    }
    
}
