package uts.poo.proyecto.vista;

import javax.swing.*;
import java.awt.*;

public class WalTechMenu extends JFrame {

    public JLabel lblUsuario; 

    public JMenuItem itemGerente;
    public JMenuItem itemEmpleados;
    public JMenuItem itemClientes;
    public JMenuItem itemProductos;

    public JMenuItem itemRegistrarVenta;
    public JMenuItem itemListaVentas;

    public JMenuItem itemCambiarClave;
    public JMenuItem itemCambiarUsuario;
    public JMenuItem itemSalir;

    public JMenuItem itemAcerca;

    public WalTechMenu() {
        initComponents();
        personalizarVentana();
        crearMenu();
        crearHeaderUsuario();
    }

    private void personalizarVentana() {
        setTitle("WalTech — Sistema de Gestión");
        setSize(900, 600);                  
        setLocationRelativeTo(null);        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void crearHeaderUsuario() {

        JPanel panelTop = new JPanel();
        panelTop.setPreferredSize(new Dimension(900, 50));
        panelTop.setBackground(new Color(25, 118, 210));   
        panelTop.setLayout(new FlowLayout(FlowLayout.LEFT));

        lblUsuario = new JLabel("No autenticado");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 16));

        panelTop.add(lblUsuario);

        add(panelTop, BorderLayout.NORTH);
    }

    private void crearMenu() {

        JMenuBar barra = new JMenuBar();
        barra.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JMenu mArchivos = new JMenu("Archivos");
        mArchivos.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        itemGerente = new JMenuItem("Gerente");
        itemEmpleados = new JMenuItem("Empleados");
        itemClientes = new JMenuItem("Clientes");
        itemProductos = new JMenuItem("Productos");

        mArchivos.add(itemGerente);
        mArchivos.add(itemEmpleados);
        mArchivos.add(itemClientes);
        mArchivos.add(itemProductos);

        JMenu mMov = new JMenu("Movimientos");
        mMov.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        itemRegistrarVenta = new JMenuItem("Registrar Venta");
        itemListaVentas = new JMenuItem("Lista de Ventas");

        mMov.add(itemRegistrarVenta);
        mMov.add(itemListaVentas);

        JMenu mCuenta = new JMenu("Cuenta");
        itemCambiarClave = new JMenuItem("Cambiar Clave");
        itemCambiarUsuario = new JMenuItem("Cambiar Usuario");
        itemSalir = new JMenuItem("Cerrar Sesión");

        mCuenta.add(itemCambiarClave);
        mCuenta.add(itemCambiarUsuario);
        mCuenta.addSeparator();
        mCuenta.add(itemSalir);

        JMenu mAyuda = new JMenu("Ayuda");
        itemAcerca = new JMenuItem("Acerca de WalTech");
        mAyuda.add(itemAcerca);

        barra.add(mArchivos);
        barra.add(mMov);
        barra.add(mCuenta);
        barra.add(mAyuda);

        setJMenuBar(barra);
    }

    public void aplicarPermisos(String rol) {
        boolean esGerente = "GERENTE".equalsIgnoreCase(rol);

        itemGerente.setEnabled(esGerente);
        itemEmpleados.setEnabled(esGerente);
        itemClientes.setEnabled(esGerente);
        itemProductos.setEnabled(esGerente);

        itemRegistrarVenta.setEnabled(true);
        itemListaVentas.setEnabled(true);

        itemCambiarClave.setEnabled(true);
        itemCambiarUsuario.setEnabled(true);
        itemSalir.setEnabled(true);
        itemAcerca.setEnabled(true);
    }

    
    


    /* @SuppressWarnings("unchecked")*/
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

    
    public static void main(String args[]) {
        // ... look and feel code ...
        java.awt.EventQueue.invokeLater(() -> {
                uts.poo.proyecto.vista.FrmLogin login = new uts.poo.proyecto.vista.FrmLogin();
                new uts.poo.proyecto.controlador.ContrLogin(login);
            });
    }   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
