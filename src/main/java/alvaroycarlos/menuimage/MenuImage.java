/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alvaroycarlos.menuimage;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
//import org.opencv.core.Core;

/**
 *
 * @author alvaroafonsolopez
 */
public class MenuImage extends javax.swing.JFrame {
    
    JFileChooser fc = new JFileChooser();
    FileNameExtensionFilter filter = null;

    /** Creates new form MenuImage */
    public MenuImage() {
        //nu.pattern.OpenCV.loadShared();
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        initComponents();
        filter = new FileNameExtensionFilter("Imágenes","*.jpg","*.png","*.jpeg","jpg","png","jpeg");
        fc.addChoosableFileFilter(filter);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lienzo1 = new alvaroycarlos.menuimage.Lienzo();
        jMenuBar1 = new javax.swing.JMenuBar();
        files = new javax.swing.JMenu();
        abrir = new javax.swing.JMenuItem();
        guardar = new javax.swing.JMenuItem();
        vista = new javax.swing.JMenu();
        vistaOriginal = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();
        editar = new javax.swing.JMenu();
        umbralizar = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout lienzo1Layout = new javax.swing.GroupLayout(lienzo1);
        lienzo1.setLayout(lienzo1Layout);
        lienzo1Layout.setHorizontalGroup(
            lienzo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        lienzo1Layout.setVerticalGroup(
            lienzo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );

        files.setText("Imágenes");

        abrir.setText("Abrir");
        abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        files.add(abrir);

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        files.add(guardar);

        vista.setText("Vista");

        vistaOriginal.setText("Imagen original");
        vista.add(vistaOriginal);

        files.add(vista);
        files.add(jSeparator1);

        salir.setText("Salir");
        files.add(salir);

        jMenuBar1.add(files);

        editar.setText("Editar");

        umbralizar.setText("Umbralizar");
        umbralizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                umbralizarActionPerformed(evt);
            }
        });
        editar.add(umbralizar);

        jMenuBar1.add(editar);

        jMenu1.setText("Ayuda");

        jMenuItem3.setText("Información de uso");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lienzo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lienzo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        int res = fc.showSaveDialog(null);
      
        if(res == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            System.out.println(file.getAbsolutePath());
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirActionPerformed
        int res = fc.showOpenDialog(null);
        int redimension = 1;
        if(res == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            System.out.println(file.getAbsolutePath());
            if(!lienzo1.correctSize(file)){
                int rp = JOptionPane.showConfirmDialog(this, "La imagen debe ser inferior a 1024x768, desea redimensionar", "Redimensionar imagen", JOptionPane.YES_NO_OPTION);
                System.out.println(rp);
                if(rp == 1){
                    return;
                }
                redimension = 0;
            }
            lienzo1.loadImg(file, redimension);
        }
        
    }//GEN-LAST:event_abrirActionPerformed

    private void umbralizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_umbralizarActionPerformed
        int res = Integer.parseInt(JOptionPane.showInputDialog(this, "Introduce un umbral", "Umbral", JOptionPane.OK_CANCEL_OPTION));
        System.out.println("Umbralizando");
        lienzo1.umbralizar(res);
    }//GEN-LAST:event_umbralizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuImage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrir;
    private javax.swing.JMenu editar;
    private javax.swing.JMenu files;
    private javax.swing.JMenuItem guardar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private alvaroycarlos.menuimage.Lienzo lienzo1;
    private javax.swing.JMenuItem salir;
    private javax.swing.JMenuItem umbralizar;
    private javax.swing.JMenu vista;
    private javax.swing.JMenuItem vistaOriginal;
    // End of variables declaration//GEN-END:variables

}