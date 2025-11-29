package uts.poo.proyecto.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import uts.poo.proyecto.vista.FrmEmpleados;
import uts.poo.proyecto.modelo.Empleado;
import uts.poo.proyecto.dao.EmpleadoDAO;

public class ContrEmpleados implements ActionListener {

    private FrmEmpleados vista;
    private EmpleadoDAO dao = new EmpleadoDAO();

    public ContrEmpleados(FrmEmpleados vista) {
        this.vista = vista;

        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);

        this.vista.tablaEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarSeleccion();
            }
        });

        cargarTabla();
        this.vista.setVisible(true);
    }

    
    private void cargarTabla() {
        vista.modelo.setRowCount(0);

        ArrayList<Empleado> lista = dao.listar();

        for (Empleado e : lista) {
            vista.modelo.addRow(new Object[]{
                e.getId_empleado(),
                e.getNombre(),
                e.getUsuario(),
                e.getPassword_hash(),
                e.getCargo(),
                e.getId_gerente()
            });
        }
    }

    
    private void limpiar() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtUsuario.setText("");
        vista.txtPassword.setText("");
        vista.txtCargo.setText("");
        vista.txtIdGerente.setText("");

        vista.tablaEmpleados.clearSelection();
    }

    
    private void cargarSeleccion() {
        int fila = vista.tablaEmpleados.getSelectedRow();
        if (fila < 0) return;

        vista.txtId.setText(vista.tablaEmpleados.getValueAt(fila, 0).toString());
        vista.txtNombre.setText(vista.tablaEmpleados.getValueAt(fila, 1).toString());
        vista.txtUsuario.setText(vista.tablaEmpleados.getValueAt(fila, 2).toString());
        vista.txtPassword.setText(vista.tablaEmpleados.getValueAt(fila, 3).toString());
        vista.txtCargo.setText(vista.tablaEmpleados.getValueAt(fila, 4).toString());
        vista.txtIdGerente.setText(vista.tablaEmpleados.getValueAt(fila, 5).toString());
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnGuardar) guardarEmpleado();
        if (e.getSource() == vista.btnActualizar) actualizarEmpleado();
        if (e.getSource() == vista.btnEliminar) eliminarEmpleado();
        if (e.getSource() == vista.btnLimpiar) limpiar();
    }

    
    private void guardarEmpleado() {

        try {
            Empleado emp = new Empleado();
            emp.setNombre(vista.txtNombre.getText());
            emp.setUsuario(vista.txtUsuario.getText());
            emp.setPassword_hash(vista.txtPassword.getText());
            emp.setCargo(vista.txtCargo.getText());
            emp.setId_gerente(Integer.parseInt(vista.txtIdGerente.getText()));

            if (dao.insertar(emp)) {
                JOptionPane.showMessageDialog(vista, "Empleado registrado.");
                cargarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos correctamente.");
        }
    }

    
    private void actualizarEmpleado() {

        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro.");
            return;
        }

        try {
            Empleado emp = new Empleado();
            emp.setId_empleado(Integer.parseInt(vista.txtId.getText()));
            emp.setNombre(vista.txtNombre.getText());
            emp.setUsuario(vista.txtUsuario.getText());
            emp.setPassword_hash(vista.txtPassword.getText());
            emp.setCargo(vista.txtCargo.getText());
            emp.setId_gerente(Integer.parseInt(vista.txtIdGerente.getText()));

            if (dao.actualizar(emp)) {
                JOptionPane.showMessageDialog(vista, "Empleado actualizado.");
                cargarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al actualizar.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Completa los datos correctamente.");
        }
    }

    
    private void eliminarEmpleado() {

        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro.");
            return;
        }

        int id = Integer.parseInt(vista.txtId.getText());

        if (dao.eliminar(id)) {
            JOptionPane.showMessageDialog(vista, "Empleado eliminado.");
            cargarTabla();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo eliminar.");
        }
    }
}
