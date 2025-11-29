package uts.poo.proyecto.vista;

import javax.swing.*;

public class FrmGerente extends JFrame {

    public JTextField txtId, txtNombre, txtUsuario, txtPassword;
    public JButton btnGuardar, btnActualizar, btnEliminar, btnLimpiar;
    public JTable tablaGerente;

    public FrmGerente() {

        setTitle("Gestión de Gerentes - WalTech");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("CRUD GERENTES");
        lblTitulo.setBounds(260, 20, 250, 30);
        add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 80, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(120, 80, 150, 25);
        txtId.setEnabled(false);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 120, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 120, 200, 25);
        add(txtNombre);

        JLabel lblUsu = new JLabel("Usuario:");
        lblUsu.setBounds(30, 160, 100, 25);
        add(lblUsu);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 160, 200, 25);
        add(txtUsuario);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(30, 200, 100, 25);
        add(lblPass);

        txtPassword = new JTextField();
        txtPassword.setBounds(120, 200, 200, 25);
        add(txtPassword);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(30, 250, 120, 35);
        add(btnGuardar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(160, 250, 120, 35);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(290, 250, 120, 35);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(420, 250, 120, 35);
        add(btnLimpiar);

        tablaGerente = new JTable();
        JScrollPane scroll = new JScrollPane(tablaGerente);
        scroll.setBounds(30, 310, 630, 130);
        add(scroll);
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
            java.util.logging.Logger.getLogger(FrmGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGerente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
