package com.vbmscode.sssu;

/**
 *
 * @author Administrator
 */
public class Main {
    
    public static void main (String[] args) {
        
        //System.setProperty("java.library.path", "./native/windows/");
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Application.getInstance().init();
            }
        });
    }
}
