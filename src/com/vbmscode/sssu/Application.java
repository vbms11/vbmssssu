package com.vbmscode.sssu;

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
public class Application implements Runnable {
    
    static Application application = null;
    
    ApplicationFrame applicationFrame;
    Environment environment;
    Canvas canvas;
    Scene scene;
    
    Thread frameThread;
    
    long frameStartTime;
    long frameEndTime;
    long frameCount;
    
    double frameDuration;
    double frameRate;
    
    private Application () {
    }
    
    public static Application getInstance () {
        
        if (application == null) {
            application = new Application();
        }
        return application;
    }
    
    public void init () {
        
        initComponents();
        initRenderer();
    }
    
    public void initComponents () {
        
        applicationFrame = new ApplicationFrame();
        applicationFrame.setVisible(true);
        
        LayoutPanel layoutPanel = applicationFrame.getLayoutPanel();
        // settings = layoutPanel.getSettingsPanel
        canvas = layoutPanel.getCanvasPanel().getCanvas();
    }
    
    public void initRenderer () {
        
        try {
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
        }

        try {
            frameThread = new Thread(this);
            frameThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run () {
        
        environment = new Environment();
        environment.init();
        
        scene = new Scene();
        
        frameCount = 0;
        
        while (!Display.isCloseRequested()) {
            
            frameCount++;
            frameStartTime = System.nanoTime();
            
            environment.update();
            scene.render(environment);
            
            frameEndTime = System.nanoTime();
            
            frameDuration = frameEndTime - frameStartTime;
            frameRate = 1000000 / frameDuration;
            
            Display.update();
            Display.sync(60);
        }
    }
}
