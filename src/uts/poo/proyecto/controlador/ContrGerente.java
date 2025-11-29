package uts.poo.proyecto.controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import uts.poo.proyecto.vista.FrmGerente;
import uts.poo.proyecto.dao.GerenteDAO;
import uts.poo.proyecto.modelo.Gerente;

public class ContrGerente {

    private FrmGerente vista;
    private GerenteDAO dao = new GerenteDAO();

    public ContrGerente(FrmGerente vista) {
        this.vista = vista;

        cargarTabla();

        vista.btnGuardar.addActionListener(e -> guardar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        vista.btnLimpiar.addActionListener(e -> limpiar());

        vista.tablaGerente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarSeleccion();
            }
        });

        vista.setVisible(true);
    }

    private void cargarTabla() {
        String[] columnas = {"ID", "Nombre", "Usuario", "Password"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Gerente g : dao.listar()) {
            modelo.addRow(new Object[]{
                g.getId_gerente(),
                g.getNombre(),
                g.getUsuario(),
                g.getPassword_hash()
            });
        }

        vista.tablaGerente.setModel(modelo);
    }

    private void guardar() {
        Gerente g = new Gerente();
        g.setNombre(vista.txtNombre.getText());
        g.setUsuario(vista.txtUsuario.getText());
        g.setPassword_hash(vista.txtPassword.getText());

        if (dao.insertar(g)) {
            JOptionPane.showMessageDialog(vista, "Gerente registrado");
            cargarTabla();
            limpiar();
        }
    }

    private void actualizar() {
        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro");
            return;
        }

        Gerente g = new Gerente();
        g.setId_gerente(Integer.parseInt(vista.txtId.getText()));
        g.setNombre(vista.txtNombre.getText());
        g.setUsuario(vista.txtUsuario.getText());
        g.setPassword_hash(vista.txtPassword.getText());

        if (dao.actualizar(g)) {
            JOptionPane.showMessageDialog(vista, "Gerente actualizado");
            cargarTabla();
            limpiar();
        }
    }

    private void eliminar() {
        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro");
            return;
        }

        int id = Integer.parseInt(vista.txtId.getText());

        if (dao.eliminar(id)) {
            JOptionPane.showMessageDialog(vista, "Gerente eliminado");
            cargarTabla();
            limpiar();
        }
    }

    private void limpiar() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtUsuario.setText("");
        vista.txtPassword.setText("");
    }

    private void cargarSeleccion() {
        int fila = vista.tablaGerente.getSelectedRow();

        vista.txtId.setText(vista.tablaGerente.getValueAt(fila, 0).toString());
        vista.txtNombre.setText(vista.tablaGerente.getValueAt(fila, 1).toString());
        vista.txtUsuario.setText(vista.tablaGerente.getValueAt(fila, 2).toString());
        vista.txtPassword.setText(vista.tablaGerente.getValueAt(fila, 3).toString());
    }
}
