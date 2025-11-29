package uts.poo.proyecto.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmEmpleados extends JFrame {

    
    public JTextField txtId;
    public JTextField txtNombre;
    public JTextField txtUsuario;
    public JPasswordField txtPassword;
    public JTextField txtCargo;
    public JTextField txtIdGerente;

    public JButton btnGuardar;
    public JButton btnActualizar;
    public JButton btnEliminar;
    public JButton btnLimpiar;

    public JTable tablaEmpleados;
    public DefaultTableModel modelo;

    public FrmEmpleados() {
        setTitle("Gestión de Empleados - WalTech");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        JLabel titulo = new JLabel("CRUD EMPLEADOS");
        titulo.setBounds(340, 20, 200, 30);
        add(titulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(50, 80, 100, 25);
        add(lblId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 120, 100, 25);
        add(lblNombre);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 160, 100, 25);
        add(lblUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 200, 100, 25);
        add(lblPassword);

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(50, 240, 100, 25);
        add(lblCargo);

        JLabel lblIdGerente = new JLabel("ID Gerente:");
        lblIdGerente.setBounds(50, 280, 100, 25);
        add(lblIdGerente);

        txtId = new JTextField();
        txtId.setBounds(150, 80, 120, 25);
        txtId.setEnabled(false);
        add(txtId);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 120, 200, 25);
        add(txtNombre);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 160, 200, 25);
        add(txtUsuario);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 200, 200, 25);
        add(txtPassword);

        txtCargo = new JTextField();
        txtCargo.setBounds(150, 240, 200, 25);
        add(txtCargo);

        txtIdGerente = new JTextField();
        txtIdGerente.setBounds(150, 280, 80, 25);
        add(txtIdGerente);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 330, 120, 35);
        add(btnGuardar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(180, 330, 120, 35);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(310, 330, 120, 35);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(440, 330, 120, 35);
        add(btnLimpiar);

        String columnas[] = {
            "ID", "Nombre", "Usuario", "Contraseña", "Cargo", "Gerente"
        };

        modelo = new DefaultTableModel(null, columnas);
        tablaEmpleados = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tablaEmpleados);
        scroll.setBounds(50, 380, 740, 150);
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
            java.util.logging.Logger.getLogger(FrmEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
