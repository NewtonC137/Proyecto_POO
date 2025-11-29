package uts.poo.proyecto.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmVentas extends javax.swing.JFrame {

    public JTable tablaVentas;

    public JTextField txtIdVenta;
    public JTextField txtIdCliente;
    public JTextField txtFecha;
    public JTextField txtTotal;

    public JButton btnRegistrar;
    public JButton btnActualizar;
    public JButton btnEliminar;
    public JButton btnVerDetalle;
    public JButton btnLimpiar;

    public FrmVentas() {
        initComponents();          
        armarInterfazManual();     
    }
    
    private void armarInterfazManual() {
        setTitle("Gestión de Ventas");
        setLayout(null);

        tablaVentas = new JTable();
        tablaVentas.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Cliente", "Fecha", "Total"}
        ));
        JScrollPane sp = new JScrollPane(tablaVentas);
        sp.setBounds(20, 20, 740, 250);
        add(sp);

        JLabel lblId = new JLabel("ID Venta:");
        lblId.setBounds(20, 300, 100, 25);
        add(lblId);

        txtIdVenta = new JTextField();
        txtIdVenta.setBounds(120, 300, 150, 25);
        txtIdVenta.setEditable(false);
        add(txtIdVenta);

        JLabel lblCli = new JLabel("ID Cliente:");
        lblCli.setBounds(20, 340, 100, 25);
        add(lblCli);

        txtIdCliente = new JTextField();
        txtIdCliente.setBounds(120, 340, 150, 25);
        add(txtIdCliente);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(20, 380, 100, 25);
        add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(120, 380, 150, 25);
        add(txtFecha);

        JLabel lblTot = new JLabel("Total:");
        lblTot.setBounds(20, 420, 100, 25);
        add(lblTot);

        txtTotal = new JTextField();
        txtTotal.setBounds(120, 420, 150, 25);
        add(txtTotal);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(320, 300, 120, 30);
        add(btnRegistrar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(320, 340, 120, 30);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(320, 380, 120, 30);
        add(btnEliminar);

        btnVerDetalle = new JButton("Ver Detalle");
        btnVerDetalle.setBounds(320, 420, 120, 30);
        add(btnVerDetalle);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(320, 460, 120, 30);
        add(btnLimpiar);
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
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
