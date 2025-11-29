package uts.poo.proyecto.vista;

import javax.swing.*;

public class FrmCambiarClave extends JFrame {

    public JPasswordField txtActual;
    public JPasswordField txtNueva;
    public JPasswordField txtRepetir;
    public JButton btnGuardar;
    public JButton btnCancelar;

    public FrmCambiarClave() {

        setTitle("Cambiar contraseña");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel l1 = new JLabel("Clave actual:");
        l1.setBounds(40, 40, 120, 30);
        add(l1);

        txtActual = new JPasswordField();
        txtActual.setBounds(170, 40, 160, 30);
        add(txtActual);

        JLabel l2 = new JLabel("Nueva clave:");
        l2.setBounds(40, 90, 120, 30);
        add(l2);

        txtNueva = new JPasswordField();
        txtNueva.setBounds(170, 90, 160, 30);
        add(txtNueva);

        JLabel l3 = new JLabel("Repetir nueva:");
        l3.setBounds(40, 140, 120, 30);
        add(l3);

        txtRepetir = new JPasswordField();
        txtRepetir.setBounds(170, 140, 160, 30);
        add(txtRepetir);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(70, 200, 100, 35);
        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(200, 200, 100, 35);
        add(btnCancelar);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FrmCambiarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCambiarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCambiarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCambiarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCambiarClave().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
