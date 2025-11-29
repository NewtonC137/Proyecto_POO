package uts.poo.proyecto.controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import uts.poo.proyecto.vista.FrmClientes;
import uts.poo.proyecto.dao.ClienteDAO;
import uts.poo.proyecto.modelo.Cliente;

public class ContrClientes {

    private FrmClientes vista;
    private ClienteDAO dao = new ClienteDAO();

    public ContrClientes(FrmClientes vista) {
        this.vista = vista;

        cargarTabla();

        this.vista.btnGuardar.addActionListener(e -> guardar());
        this.vista.btnActualizar.addActionListener(e -> actualizar());
        this.vista.btnEliminar.addActionListener(e -> eliminar());
        this.vista.btnLimpiar.addActionListener(e -> limpiar());

        this.vista.tablaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarSeleccion();
            }
        });

        this.vista.setVisible(true);
    }

    private void cargarTabla() {
        String[] columnas = {"ID", "Nombre", "Teléfono", "Dirección"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Cliente c : dao.listar()) {
            modelo.addRow(new Object[]{
                c.getId_cliente(),
                c.getNombre(),
                c.getTelefono(),
                c.getDireccion()
            });
        }

        vista.tablaClientes.setModel(modelo);
    }

    private void guardar() {
        if (vista.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El nombre no puede estar vacío");
            return;
        }

        Cliente c = new Cliente();
        c.setNombre(vista.txtNombre.getText());
        c.setTelefono(vista.txtTelefono.getText());
        c.setDireccion(vista.txtDireccion.getText());

        if (dao.insertar(c)) {
            JOptionPane.showMessageDialog(vista, "Cliente guardado correctamente");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar");
        }
    }

    private void actualizar() {
        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente primero");
            return;
        }

        Cliente c = new Cliente();
        c.setId_cliente(Integer.parseInt(vista.txtId.getText()));
        c.setNombre(vista.txtNombre.getText());
        c.setTelefono(vista.txtTelefono.getText());
        c.setDireccion(vista.txtDireccion.getText());

        if (dao.actualizar(c)) {
            JOptionPane.showMessageDialog(vista, "Cliente actualizado");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar");
        }
    }

    private void eliminar() {
        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente primero");
            return;
        }

        int id = Integer.parseInt(vista.txtId.getText());

        if (dao.eliminar(id)) {
            JOptionPane.showMessageDialog(vista, "Cliente eliminado");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al eliminar");
        }
    }

    private void limpiar() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtTelefono.setText("");
        vista.txtDireccion.setText("");
    }

    private void cargarSeleccion() {
        int fila = vista.tablaClientes.getSelectedRow();

        vista.txtId.setText(vista.tablaClientes.getValueAt(fila, 0).toString());
        vista.txtNombre.setText(vista.tablaClientes.getValueAt(fila, 1).toString());
        vista.txtTelefono.setText(vista.tablaClientes.getValueAt(fila, 2).toString());
        vista.txtDireccion.setText(vista.tablaClientes.getValueAt(fila, 3).toString());
    }
}
