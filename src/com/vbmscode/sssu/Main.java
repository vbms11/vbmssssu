package com.vbmscode.sssu;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author Administrator
 */
public class Main {
    
    public static void main (String[] args) {
        
        //System.setProperty("java.library.path", "./native/windows/");
        
        try {
            System.setOut(new PrintStream(new FileOutputStream("log.txt")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Application.getInstance().init();
            }
        });
    }
}
