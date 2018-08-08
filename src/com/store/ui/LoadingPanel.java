/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui;

import com.store.logger.StoreLogger;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chenjingjun
 */
public class LoadingPanel extends javax.swing.JPanel {

    private int progress = 0;
    private Runnable runnable;
    private final Map<Integer, Integer> coutMap = new HashMap<>();
    
    public LoadingPanel() {
        initComponents();
    }
    
    public void initCount(int type, int count){
        coutMap.put(type, count);
        progress += count;
        bar_progress.setMinimum(0);
        bar_progress.setMaximum(progress);
        bar_progress.setValue(0);
    }
    
    public void start(Runnable runnable){
        this.runnable = runnable;
    }
    
    public void updateCount(int type, int value){
         Integer current = coutMap.get(type);
         if(current <= 0){
             return;
         }
         current -= value;
         coutMap.put(type, (current < 0) ? 0 : current);
         int caculate = 0;
         for(int count : coutMap.values()){
             caculate += count;
         }
         bar_progress.setValue(progress - caculate);
         StoreLogger.infof("当前的进度(%d/%d)", progress - caculate, progress);
         if(caculate > 0){
             return;
         }
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
        }
        runnable.run();
    }
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_describe = new javax.swing.JLabel();
        bar_progress = new javax.swing.JProgressBar();

        setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 255, 255), null));

        txt_describe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_describe.setText("正在加载中......");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_describe, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(bar_progress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txt_describe, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bar_progress, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bar_progress;
    private javax.swing.JLabel txt_describe;
    // End of variables declaration//GEN-END:variables
}