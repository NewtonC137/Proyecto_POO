package uts.poo.proyecto.controlador;

import java.awt.event.*;
import javax.swing.*;

import uts.poo.proyecto.dao.*;
import uts.poo.proyecto.modelo.*;
import uts.poo.proyecto.vista.FrmCambiarUsuario;

public class ContrCambiarUsuario implements ActionListener {

    FrmCambiarUsuario vista;

    public ContrCambiarUsuario(FrmCambiarUsuario vista) {
        this.vista = vista;

        vista.btnGuardar.addActionListener(this);
        vista.btnCancelar.addActionListener(this);

        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnCancelar) {
            vista.dispose();
            return;
        }

        if (e.getSource() == vista.btnGuardar) {

            String nuevo = vista.txtNuevoUsuario.getText().trim();

            if (nuevo.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar un usuario.");
                return;
            }

            int id = Session.getId();

            boolean ok;

            if (Session.esGerente()) {
                ok = new GerenteDAO().actualizarUsuario(id, nuevo);
            } else {
                ok = new EmpleadoDAO().actualizarUsuario(id, nuevo);
            }

            if (ok) {
                JOptionPane.showMessageDialog(vista, "Usuario actualizado.");
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo actualizar.");
            }
        }
    }
}
