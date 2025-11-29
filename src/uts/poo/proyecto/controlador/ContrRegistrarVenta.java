package uts.poo.proyecto.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import uts.poo.proyecto.dao.*;
import uts.poo.proyecto.modelo.*;
import uts.poo.proyecto.vista.FrmRegistrarVenta;

public class ContrRegistrarVenta implements ActionListener {

    private FrmRegistrarVenta vista;
    private ProductoDAO productoDAO = new ProductoDAO();
    private VentaDAO ventaDAO = new VentaDAO();
    private DetalleVentaDAO detalleDAO = new DetalleVentaDAO();

    private double totalVenta = 0;

    public ContrRegistrarVenta(FrmRegistrarVenta vista) {
        this.vista = vista;

        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.cboProductos.addActionListener(this);

        cargarProductos();
        this.vista.setVisible(true);
    }

    
    private void cargarProductos() {
        vista.cboProductos.removeAllItems();
        vista.cboProductos.addItem("Seleccione...");

        for (Producto p : productoDAO.listar()) {
            vista.cboProductos.addItem(p.getId_producto() + " - " + p.getNombre());
        }
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.cboProductos) {
            cargarDatosProducto();
        }

        if (e.getSource() == vista.btnAgregar) {
            agregarProductoATabla();
        }

        if (e.getSource() == vista.btnRegistrar) {
            registrarVentaCompleta();
        }

        if (e.getSource() == vista.btnCancelar) {
            vista.dispose();
        }
    }

    
    private void cargarDatosProducto() {
        if (vista.cboProductos.getSelectedIndex() <= 0) {
            limpiarDatosProducto();
            return;
        }

        String item = vista.cboProductos.getSelectedItem().toString();
        int id = Integer.parseInt(item.split(" - ")[0]);

        Producto p = productoDAO.obtenerPorId(id);

        if (p != null) {
            vista.txtNombre.setText(p.getNombre());
            vista.txtPrecio.setText(String.valueOf(p.getPrecio()));
            vista.txtStock.setText(String.valueOf(p.getStock()));
        }
    }

    
    private void agregarProductoATabla() {

        if (vista.cboProductos.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto.");
            return;
        }

        int cantidad;

        try {
            cantidad = Integer.parseInt(vista.txtCantidad.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Cantidad inválida.");
            return;
        }

        int stock = Integer.parseInt(vista.txtStock.getText());
        double precio = Double.parseDouble(vista.txtPrecio.getText());

        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(vista, "La cantidad debe ser mayor a cero.");
            return;
        }

        if (cantidad > stock) {
            JOptionPane.showMessageDialog(vista, "No hay suficiente stock.");
            return;
        }

        String item = vista.cboProductos.getSelectedItem().toString();
        int idProducto = Integer.parseInt(item.split(" - ")[0]);
        String nombre = vista.txtNombre.getText();
        double subtotal = cantidad * precio;

        DefaultTableModel modelo = (DefaultTableModel) vista.tblDetalle.getModel();
        modelo.addRow(new Object[]{idProducto, nombre, cantidad, precio, subtotal});

        totalVenta += subtotal;
        vista.lblTotal.setText("TOTAL: $" + totalVenta);

        limpiarDatosProducto();
    }

    
    private void registrarVentaCompleta() {

        if (vista.tblDetalle.getRowCount() == 0) {
            JOptionPane.showMessageDialog(vista, "Debe agregar al menos un producto.");
            return;
        }

        if (!Session.haySesion()) {
            JOptionPane.showMessageDialog(vista, "Error: no hay empleado autenticado.");
            return;
        }

        Venta venta = new Venta();
        venta.setId_cliente(1);  
        venta.setId_empleado(Session.getId()); 
        String fechaHora = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        venta.setFecha(fechaHora);
        venta.setTotal(totalVenta);

        int idVenta = ventaDAO.insertar(venta);

        if (idVenta == -1) {
            JOptionPane.showMessageDialog(vista, "Error al registrar la venta.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) vista.tblDetalle.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {

            int idProducto = Integer.parseInt(modelo.getValueAt(i, 0).toString());
            int cantidad = Integer.parseInt(modelo.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(modelo.getValueAt(i, 3).toString());
            double subtotal = Double.parseDouble(modelo.getValueAt(i, 4).toString());

            DetalleVenta d = new DetalleVenta();
            d.setId_venta(idVenta);
            d.setId_producto(idProducto);
            d.setCantidad(cantidad);
            d.setPrecio(precio);
            d.setSubtotal(subtotal);

            detalleDAO.insertar(d);

            productoDAO.restarStock(idProducto, cantidad);
        }

        JOptionPane.showMessageDialog(vista, "Venta registrada exitosamente.");

        limpiarTodo();
        cargarProductos();
    }

   
    private void limpiarDatosProducto() {
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
        vista.txtStock.setText("");
        vista.txtCantidad.setText("");

        if (vista.cboProductos.getItemCount() > 0) {
            vista.cboProductos.setSelectedIndex(0);
        }
    }

    private void limpiarTodo() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblDetalle.getModel();
        modelo.setRowCount(0);
        totalVenta = 0;
        vista.lblTotal.setText("TOTAL: $0");
        limpiarDatosProducto();
    }
}
