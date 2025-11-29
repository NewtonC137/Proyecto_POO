package uts.poo.proyecto.main;

import javax.swing.SwingUtilities;
import uts.poo.proyecto.vista.FrmLogin;
import uts.poo.proyecto.controlador.ContrLogin;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmLogin login = new FrmLogin();
            new ContrLogin(login);
        });
    }
}
