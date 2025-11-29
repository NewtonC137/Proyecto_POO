
package uts.poo.proyecto.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmRegistrarVenta extends JFrame {

    public JComboBox<String> cboProductos;
    public JTextField txtNombre;
    public JTextField txtPrecio;
    public JTextField txtStock;
    public JTextField txtCantidad;

    public JTable tblDetalle;
    public JLabel lblTotal;

    public JButton btnAgregar;
    public JButton btnRegistrar;
    public JButton btnCancelar;

    public FrmRegistrarVenta() {
        initComponents();              
        getContentPane().setLayout(null);  
        armarInterfazManual();         
    }



    
    private void armarInterfazManual() {

        setTitle("Registrar Venta");
        setSize(750, 600);
        setLocationRelativeTo(null);


        JLabel lblProd = new JLabel("Producto:");
        lblProd.setBounds(30, 20, 100, 25);
        add(lblProd);

        cboProductos = new JComboBox<>();
        cboProductos.setBounds(120, 20, 200, 25);
        add(cboProductos);

        JLabel lblNom = new JLabel("Nombre:");
        lblNom.setBounds(30, 60, 100, 25);
        add(lblNom);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 60, 200, 25);
        txtNombre.setEditable(false);
        add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(30, 100, 100, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(120, 100, 200, 25);
        txtPrecio.setEditable(false);
        add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(30, 140, 100, 25);
        add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(120, 140, 200, 25);
        txtStock.setEditable(false);
        add(txtStock);

        JLabel lblCant = new JLabel("Cantidad:");
        lblCant.setBounds(30, 180, 100, 25);
        add(lblCant);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(120, 180, 200, 25);
        add(txtCantidad);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(350, 180, 120, 30);
        add(btnAgregar);


        tblDetalle = new JTable();
        tblDetalle.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Producto", "Cantidad", "Precio", "Subtotal"}
        ));

        JScrollPane sp = new JScrollPane(tblDetalle);
        sp.setBounds(30, 230, 680, 230);
        add(sp);

        lblTotal = new JLabel("TOTAL: $0");
        lblTotal.setBounds(30, 470, 200, 30);
        add(lblTotal);

        btnRegistrar = new JButton("Registrar Venta");
        btnRegistrar.setBounds(400, 470, 150, 40);
        add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(560, 470, 150, 40);
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
            java.util.logging.Logger.getLogger(FrmRegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRegistrarVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
