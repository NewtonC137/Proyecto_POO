package uts.poo.proyecto.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import uts.poo.proyecto.vista.WalTechMenu;

/**
 * Controlador central del menú principal.
 * Usa reflection para intentar abrir formularios/controladores opcionales
 * evitando dependencias de compilación con clases que puedan faltar.
 */
public class Controlador implements ActionListener {

    private WalTechMenu vista;

    public Controlador(WalTechMenu vista) {
        this.vista = vista;

        // Registrar listeners
        this.vista.itemClientes.addActionListener(this);
        this.vista.itemGerente.addActionListener(this);
        this.vista.itemProductos.addActionListener(this);
        this.vista.itemEmpleados.addActionListener(this);

        this.vista.itemRegistrarVenta.addActionListener(this);
        this.vista.itemListaVentas.addActionListener(this);

        this.vista.itemCambiarClave.addActionListener(this);
        this.vista.itemCambiarUsuario.addActionListener(this);
        this.vista.itemSalir.addActionListener(this);

        this.vista.itemAcerca.addActionListener(this);

        
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        
        if (src == vista.itemSalir) {
            int r = JOptionPane.showConfirmDialog(vista, "¿Seguro que deseas salir?", "Salir", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) System.exit(0);
            return;
        }

        
        if (src == vista.itemAcerca) {

        String info = "<html>"
                + "-----------------------------------------<br>"
                + "<b>   WALTECH SYSTEM</b><br>"
                + "-----------------------------------------<br>"
                + "Versión: 1.0<br>"
                + "Proyecto de programación orientada a objetos<br>"
                + "Autoevaluación: 5.0<br><br>"
                + "Desarrollado por: Walter Esteban Leon Niño<br>"
                + "Institución: UTS<br>"
                + "-----------------------------------------"
                + "</html>";

            JOptionPane.showMessageDialog(
                    vista,
                    info,
                    "Acerca de WalTech",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }   


        
        try {
            if (src == vista.itemClientes) {
                openModule("uts.poo.proyecto.vista.FrmClientes", "uts.poo.proyecto.controlador.ContrClientes");
                return;
            }

            if (src == vista.itemGerente) {
                openModule("uts.poo.proyecto.vista.FrmGerente", "uts.poo.proyecto.controlador.ContrGerente");
                return;
            }

            if (src == vista.itemProductos) {
                openModule("uts.poo.proyecto.vista.FrmProductos", "uts.poo.proyecto.controlador.ContrProductos");
                return;
            }

            if (src == vista.itemEmpleados) {
                openModule("uts.poo.proyecto.vista.FrmEmpleados", "uts.poo.proyecto.controlador.ContrEmpleados");
                return;
            }

            if (src == vista.itemRegistrarVenta) {
                openModule("uts.poo.proyecto.vista.FrmRegistrarVenta", "uts.poo.proyecto.controlador.ContrRegistrarVenta");
                return;
            }

            if (src == vista.itemListaVentas) {
                openModule("uts.poo.proyecto.vista.FrmListaVentas", "uts.poo.proyecto.controlador.ContrListaVentas");
                return;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al abrir módulo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }

        if (src == vista.itemCambiarClave) {
            if (!openModule("uts.poo.proyecto.vista.FrmCambiarClave", "uts.poo.proyecto.controlador.ContrCambiarClave")) {
                String idStr = JOptionPane.showInputDialog(vista, "Ingrese su ID de usuario (o cancelar):");
                if (idStr == null) return;
                String nueva = JOptionPane.showInputDialog(vista, "Nueva contraseña:");
                if (nueva == null) return;
                JOptionPane.showMessageDialog(vista, "Solicitud de cambio de contraseña recibida (implementa la DAO para aplicar).");
            }
            return;
        }

        if (src == vista.itemCambiarUsuario) {
            if (!openModule("uts.poo.proyecto.vista.FrmCambiarUsuario", "uts.poo.proyecto.controlador.ContrCambiarUsuario")) {
                String idStr = JOptionPane.showInputDialog(vista, "Ingrese su ID de usuario (o cancelar):");
                if (idStr == null) return;
                String nuevo = JOptionPane.showInputDialog(vista, "Nuevo nombre de usuario:");
                if (nuevo == null) return;
                JOptionPane.showMessageDialog(vista, "Solicitud de cambio de usuario recibida (implementa la DAO para aplicar).");
            }
            return;
        }
    }

    
    private boolean openModule(String formClassName, String controllerClassName) {
        try {
            Class<?> formClass = Class.forName(formClassName);
            Object formInstance = formClass.getDeclaredConstructor().newInstance();

            if (controllerClassName != null && !controllerClassName.isEmpty()) {
                try {
                    Class<?> ctrlClass = Class.forName(controllerClassName);
                    boolean instantiated = false;
                    try {
                        ctrlClass.getDeclaredConstructor(formClass).newInstance(formInstance);
                        instantiated = true;
                    } catch (NoSuchMethodException nm) {
                        try {
                            ctrlClass.getDeclaredConstructor(Object.class).newInstance(formInstance);
                            instantiated = true;
                        } catch (NoSuchMethodException nm2) {
                            try {
                                Object ctrl = ctrlClass.getDeclaredConstructor().newInstance();
                                instantiated = true;
                            } catch (NoSuchMethodException nm3) {
                                instantiated = false;
                            }
                        }
                    }

                    if (instantiated) return true;
                } catch (ClassNotFoundException cnf) {
                }
            }

            if (formInstance instanceof JFrame) {
                ((JFrame) formInstance).setVisible(true);
                return true;
            } else {
                try {
                    formClass.getMethod("setVisible", boolean.class).invoke(formInstance, true);
                    return true;
                } catch (Exception ex) {
                }
            }

            JOptionPane.showMessageDialog(vista, "Módulo encontrado pero no se pudo abrir correctamente.", "Atención", JOptionPane.WARNING_MESSAGE);
            return true;

        } catch (ClassNotFoundException cnf) {
            JOptionPane.showMessageDialog(vista, "Módulo " + extractSimpleName(formClassName) + " todavía no está implementado.", "No implementado", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al abrir módulo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return false;
        }
    }

    private String extractSimpleName(String fqcn) {
        if (fqcn == null) return "Módulo";
        int p = fqcn.lastIndexOf('.');
        return (p >= 0) ? fqcn.substring(p + 1) : fqcn;
    }
}
