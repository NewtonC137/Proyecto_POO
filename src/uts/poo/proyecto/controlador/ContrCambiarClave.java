package uts.poo.proyecto.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import uts.poo.proyecto.dao.EmpleadoDAO;
import uts.poo.proyecto.dao.GerenteDAO;
import uts.poo.proyecto.modelo.Session;
import uts.poo.proyecto.vista.FrmCambiarClave;

public class ContrCambiarClave implements ActionListener {

    FrmCambiarClave vista;

    public ContrCambiarClave(FrmCambiarClave vista) {
        this.vista = vista;

        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);

        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnCancelar) {
            vista.dispose();
            return;
        }

        if (e.getSource() == vista.btnGuardar) {

            String actual = new String(vista.txtActual.getPassword()).trim();
            String nueva1 = new String(vista.txtNueva.getPassword()).trim();
            String nueva2 = new String(vista.txtRepetir.getPassword()).trim();

            if (actual.isEmpty() || nueva1.isEmpty() || nueva2.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "Complete todos los campos.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!nueva1.equals(nueva2)) {
                JOptionPane.showMessageDialog(vista,
                        "La nueva contraseña no coincide.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Session.getId();
            boolean coincide;

            if (Session.esGerente()) {
                coincide = new GerenteDAO().validarClaveActual(id, actual);
            } else {
                coincide = new EmpleadoDAO().validarClaveActual(id, actual);
            }

            if (!coincide) {
                JOptionPane.showMessageDialog(vista,
                        "La contraseña actual es incorrecta.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean actualizado;
            if (Session.esGerente()) {
                actualizado = new GerenteDAO().actualizarPassword(id, nueva1);
            } else {
                actualizado = new EmpleadoDAO().actualizarPassword(id, nueva1);
            }

            if (actualizado) {
                JOptionPane.showMessageDialog(vista,
                        "Contraseña actualizada correctamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista,
                        "No fue posible actualizar la contraseña.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
