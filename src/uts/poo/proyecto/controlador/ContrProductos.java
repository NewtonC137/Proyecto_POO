package uts.poo.proyecto.controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import uts.poo.proyecto.dao.ProductoDAO;
import uts.poo.proyecto.modelo.Producto;
import uts.poo.proyecto.vista.FrmProductos;

public class ContrProductos {

    private FrmProductos vista;
    private ProductoDAO dao = new ProductoDAO();

    public ContrProductos(FrmProductos vista) {
        this.vista = vista;

        cargarTabla();

        vista.btnGuardar.addActionListener(e -> guardar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        vista.btnLimpiar.addActionListener(e -> limpiar());

        vista.tablaProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarSeleccion();
            }
        });

        vista.setVisible(true);
    }

    private void cargarTabla() {
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Stock"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Producto p : dao.listar()) {
            modelo.addRow(new Object[]{
                p.getId_producto(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getStock()
            });
        }

        vista.tablaProductos.setModel(modelo);
    }

    private void guardar() {
        Producto p = new Producto();
        p.setNombre(vista.txtNombre.getText());
        p.setDescripcion(vista.txtDescripcion.getText());
        p.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
        p.setStock(Integer.parseInt(vista.txtStock.getText()));

        if (dao.insertar(p)) {
            JOptionPane.showMessageDialog(vista, "Producto guardado");
            cargarTabla();
            limpiar();
        }
    }

    private void actualizar() {
        Producto p = new Producto();
        p.setId_producto(Integer.parseInt(vista.txtId.getText()));
        p.setNombre(vista.txtNombre.getText());
        p.setDescripcion(vista.txtDescripcion.getText());
        p.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
        p.setStock(Integer.parseInt(vista.txtStock.getText()));

        if (dao.actualizar(p)) {
            JOptionPane.showMessageDialog(vista, "Producto actualizado");
            cargarTabla();
            limpiar();
        }
    }

    private void eliminar() {
        int id = Integer.parseInt(vista.txtId.getText());

        if (dao.eliminar(id)) {
            JOptionPane.showMessageDialog(vista, "Producto eliminado");
            cargarTabla();
            limpiar();
        }
    }

    private void limpiar() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtDescripcion.setText("");
        vista.txtPrecio.setText("");
        vista.txtStock.setText("");
    }

    private void cargarSeleccion() {
        int fila = vista.tablaProductos.getSelectedRow();
        vista.txtId.setText(vista.tablaProductos.getValueAt(fila, 0).toString());
        vista.txtNombre.setText(vista.tablaProductos.getValueAt(fila, 1).toString());
        vista.txtDescripcion.setText(vista.tablaProductos.getValueAt(fila, 2).toString());
        vista.txtPrecio.setText(vista.tablaProductos.getValueAt(fila, 3).toString());
        vista.txtStock.setText(vista.tablaProductos.getValueAt(fila, 4).toString());
    }
}
