/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vbmscode.sssu;

/**
 *
 * @author Administrator
 */
public class LayoutPanel extends javax.swing.JPanel {

    /**
     * Creates new form LayoutPanel
     */
    public LayoutPanel() {
        initComponents();
    }
    
    public CanvasPanel getCanvasPanel () {
        return canvasPanel1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        canvasPanel1 = new com.vbmscode.sssu.CanvasPanel();
        attributesPanel1 = new com.vbmscode.sssu.AttributesPanel();

        jSplitPane1.setRightComponent(canvasPanel1);
        jSplitPane1.setLeftComponent(attributesPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.vbmscode.sssu.AttributesPanel attributesPanel1;
    private com.vbmscode.sssu.CanvasPanel canvasPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}
