package uts.poo.proyecto.modelo;

import uts.poo.proyecto.modelo.Empleado;
import uts.poo.proyecto.modelo.Gerente;

/**
 * Manejo global de sesión para el sistema.
 */
public class Session {

    private static Gerente gerenteActual = null;
    private static Empleado empleadoActual = null;

    public static void iniciarSesion(Gerente g) {
        gerenteActual = g;
        empleadoActual = null;
    }

    public static void iniciarSesion(Empleado e) {
        empleadoActual = e;
        gerenteActual = null;
    }

    public static void cerrarSesion() {
        gerenteActual = null;
        empleadoActual = null;
    }

    public static boolean haySesion() {
        return gerenteActual != null || empleadoActual != null;
    }

    public static boolean esGerente() {
        return gerenteActual != null;
    }

    public static boolean esEmpleado() {
        return empleadoActual != null;
    }

    public static Gerente getGerente() {
        return gerenteActual;
    }

    public static Empleado getEmpleado() {
        return empleadoActual;
    }

    public static int getId() {
        if (gerenteActual != null) return gerenteActual.getId_gerente();
        if (empleadoActual != null) return empleadoActual.getId_empleado();
        return -1;
    }

    public static String getUsuario() {
        if (gerenteActual != null) return gerenteActual.getUsuario();
        if (empleadoActual != null) return empleadoActual.getUsuario();
        return "";
    }

    public static String getRol() {
        if (gerenteActual != null) return "GERENTE";
        if (empleadoActual != null) return "EMPLEADO";
        return "SIN_SESION";
    }
}
