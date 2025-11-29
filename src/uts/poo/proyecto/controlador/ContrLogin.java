package uts.poo.proyecto.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import uts.poo.proyecto.dao.EmpleadoDAO;
import uts.poo.proyecto.dao.GerenteDAO;
import uts.poo.proyecto.modelo.Empleado;
import uts.poo.proyecto.modelo.Gerente;
import uts.poo.proyecto.modelo.Session;
import uts.poo.proyecto.vista.FrmLogin;
import uts.poo.proyecto.vista.WalTechMenu;

public class ContrLogin implements ActionListener {

    FrmLogin vista;
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    GerenteDAO gerenteDAO = new GerenteDAO();

    public ContrLogin(FrmLogin vista) {
        this.vista = vista;
        this.vista.btnLogin.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnLogin) {

            String user = vista.txtUsuario.getText().trim();
            String pass = new String(vista.txtPassword.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete ambos campos.");
                return;
            }

            Empleado emp = empleadoDAO.validarLogin(user, pass);

            if (emp != null) {
                Session.iniciarSesion(emp);

                JOptionPane.showMessageDialog(vista,
                        "Bienvenido " + emp.getNombre() + " (" + emp.getCargo() + ")");

                abrirMenu(emp.getNombre(), emp.getCargo());
                vista.dispose();
                return;
            }

            Gerente ger = gerenteDAO.validarLogin(user, pass);

            if (ger != null) {
                Session.iniciarSesion(ger);

                JOptionPane.showMessageDialog(vista,
                        "Bienvenido gerente " + ger.getNombre());

                abrirMenu(ger.getNombre(), "GERENTE");
                vista.dispose();
                return;
            }

            JOptionPane.showMessageDialog(vista, "Credenciales incorrectas.");
        }

        if (e.getSource() == vista.btnCancelar) {
            System.exit(0);
        }
    }

    private void abrirMenu(String nombre, String rol) {
        WalTechMenu menu = new WalTechMenu();
        menu.lblUsuario.setText(nombre + " (" + rol + ")");

        menu.aplicarPermisos(rol);

        new Controlador(menu);

        menu.setVisible(true);
    }
}
