package uts.poo.proyecto.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.poo.proyecto.modelo.ConexionDB;
import uts.poo.proyecto.modelo.Empleado;

public class EmpleadoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Empleado> listar() {
        ArrayList<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setUsuario(rs.getString("usuario"));
                e.setPassword_hash(rs.getString("password_hash"));
                e.setCargo(rs.getString("cargo"));
                e.setId_gerente(rs.getInt("id_gerente"));
                lista.add(e);
            }
        } catch (Exception e) {}
        return lista;
    }

    public boolean insertar(Empleado e) {
        String sql = "INSERT INTO empleado(nombre, usuario, password_hash, cargo, id_gerente) VALUES (?, ?, ?, ?, ?)";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getUsuario());
            ps.setString(3, e.getPassword_hash());
            ps.setString(4, e.getCargo());
            ps.setInt(5, e.getId_gerente());

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean actualizar(Empleado e) {
        String sql = "UPDATE empleado SET nombre=?, usuario=?, password_hash=?, cargo=?, id_gerente=? WHERE id_empleado=?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getUsuario());
            ps.setString(3, e.getPassword_hash());
            ps.setString(4, e.getCargo());
            ps.setInt(5, e.getId_gerente());
            ps.setInt(6, e.getId_empleado());

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM empleado WHERE id_empleado=?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            return false;
        }
    }

    public Empleado validarLogin(String usuario, String password) {
        String sql = "SELECT * FROM empleado WHERE usuario=? AND password_hash=?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setUsuario(rs.getString("usuario"));
                e.setPassword_hash(rs.getString("password_hash"));
                e.setCargo(rs.getString("cargo"));
                e.setId_gerente(rs.getInt("id_gerente"));
                return e;
            }

        } catch (Exception e) {}
        return null;
    }

    public boolean validarClaveActual(int id, String actualHash) {
        String sql = "SELECT * FROM empleado WHERE id_empleado=? AND password_hash=?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, actualHash);

            rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizarPassword(int id, String nuevaClave) {
        String sql = "UPDATE empleado SET password_hash=? WHERE id_empleado=?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, nuevaClave);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean actualizarUsuario(int id, String nuevoUsuario) {
    String sql = "UPDATE empleado SET usuario=? WHERE id_empleado=?";

    try {
        con = ConexionDB.getConnection();
        ps = con.prepareStatement(sql);

        ps.setString(1, nuevoUsuario);
        ps.setInt(2, id);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        return false;
    }
}

    
}
