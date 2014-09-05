package com.vbmscode.sssu.core;

import com.vbmscode.sssu.env.Environment;
import com.vbmscode.sssu.env.PulseImmiter;
import com.vbmscode.sssu.renderer.Graphics2dRenderer;
import com.vbmscode.sssu.renderer.OpenglRenderer;
import com.vbmscode.sssu.ui.ApplicationFrame;
import com.vbmscode.sssu.ui.LayoutPanel;
import java.awt.Canvas;

/**
 *
 * @author Administrator
 */
public class Application implements Runnable {
    
    static Application application = null;
    
    final int rendererType_graphics2d = 0;
    final int rendererType_opengl = 1;
    
    final int state_initComponents = 0;
    final int state_initRenderer = 1;
    final int state_run = 2;
    final int state_exit = 3;
    
    int state = state_initComponents;
    int rendererType = rendererType_graphics2d;
    
    ApplicationFrame applicationFrame;
    Environment environment;
    Canvas canvas;
    AbstractRenderer renderer;
    PulseImmiter pulseImmiter;
    
    Thread frameThread;
    
    long frameStartTime;
    long frameEndTime;
    long frameCount;
    
    double frameDuration;
    double frameRate;
    double cameraRotateSpeed = 0.5;
    
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
        startRenderThread();
    }
    
    public void initComponents () {
        
        state = state_initComponents;
        
        applicationFrame = new ApplicationFrame();
        applicationFrame.setVisible(true);
        
        LayoutPanel layoutPanel = applicationFrame.getLayoutPanel();
        // settings = layoutPanel.getSettingsPanel
        canvas = layoutPanel.getCanvasPanel().getCanvas();
    }
    
    public void initRenderer () {
        
        state = state_initRenderer;
        
        try {

            // init the scene
            environment = new Environment();
            environment.init();
            
            // 
            pulseImmiter = new PulseImmiter();
            
            // get renderer and initialize
            renderer = (AbstractRenderer) getRendererClass().newInstance();
            renderer.init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Class getRendererClass () {
        
        switch (rendererType) {
            case rendererType_opengl:
                return OpenglRenderer.class;
            case rendererType_graphics2d:
                return Graphics2dRenderer.class;
            default:
                throw new UnsupportedOperationException("Renderer not supported: "+rendererType);
        }
    }
    
    public void startRenderThread () {
        
        state = state_run;
        
        try {
            
            // start the renderer thread
            frameThread = new Thread(this);
            frameThread.start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run () {
        
        frameCount = 0;
        
        while (state != state_exit) {
            
            while (state == state_run) {
            
                frameCount++;
                frameStartTime = System.nanoTime();

                environment.update();
                pulseImmiter.update(environment);
                renderer.render(environment);

                frameEndTime = System.nanoTime();

                frameDuration = frameEndTime - frameStartTime;
                frameRate = 1000000 / frameDuration;

            }
            
            if (state == state_initRenderer) {
                
                initRenderer();
                state = state_run;
            }
        }
        
        
    }
    
    public void triggerExit () {
        
        state = state_exit;
    }
    
    public void moveCamera (int x, int y) {
        
        // renderer.getCamera().rotate(x,y);
    }

    public ApplicationFrame getApplicationFrame() {
        return applicationFrame;
    }

    public void setApplicationFrame(ApplicationFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Thread getFrameThread() {
        return frameThread;
    }

    public void setFrameThread(Thread frameThread) {
        this.frameThread = frameThread;
    }

    public long getFrameStartTime() {
        return frameStartTime;
    }

    public void setFrameStartTime(long frameStartTime) {
        this.frameStartTime = frameStartTime;
    }

    public long getFrameEndTime() {
        return frameEndTime;
    }

    public void setFrameEndTime(long frameEndTime) {
        this.frameEndTime = frameEndTime;
    }

    public long getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(long frameCount) {
        this.frameCount = frameCount;
    }

    public double getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(double frameDuration) {
        this.frameDuration = frameDuration;
    }

    public double getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(double frameRate) {
        this.frameRate = frameRate;
    }

    public PulseImmiter getPulseImmiter() {
        return pulseImmiter;
    }

    public void setPulseImmiter(PulseImmiter pulseImmiter) {
        this.pulseImmiter = pulseImmiter;
    }
    
    
}
