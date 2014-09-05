package com.vbmscode.sssu.renderer;

import com.vbmscode.sssu.core.AbstractRenderer;
import com.vbmscode.sssu.core.Application;
import com.vbmscode.sssu.env.Environment;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author Administrator
 */
public class Graphics2dRenderer extends AbstractRenderer {
    
    public Graphics2dRenderer () {
    }
    
    @Override
    public boolean init () {
        
        return true;
    }
    
    @Override
    public void render (Environment environment) {
        
        // draw environment charges
        Application application = Application.getInstance();
        Canvas canvas = application.getCanvas();
        Dimension dimensions = canvas.getSize();
        
        double chargeRange = environment.getChargeRange();
        int size = environment.getSize();
        int z = level;
        
        int gridSize;
        if (dimensions.width < dimensions.height) {
            gridSize = dimensions.width;
        } else {
            gridSize = dimensions.height;
        }
        gridSize /= size;
        
        Graphics g = canvas.getGraphics();
        
        for (int x=0; x<size; x++) {
            for (int y=0; y<size; y++) {
                
                double charge = environment.getCharge(x, y, z);
                double[] color = getChargeColor(charge, chargeRange);
                
                g.setColor(new Color((float)color[0],(float)color[1],(float)color[2]));
                g.fillRect(x*gridSize, y*gridSize, gridSize, gridSize);
            }
        }
        
    }
    
    @Override
    public void destroy () {
    }
    
}
