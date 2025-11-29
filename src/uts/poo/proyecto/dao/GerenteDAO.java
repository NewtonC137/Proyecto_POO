package uts.poo.proyecto.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.poo.proyecto.modelo.ConexionDB;
import uts.poo.proyecto.modelo.Gerente;

public class GerenteDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Gerente> listar() {
        ArrayList<Gerente> lista = new ArrayList<>();
        String sql = "SELECT * FROM gerente";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Gerente g = new Gerente();
                g.setId_gerente(rs.getInt("id_gerente"));
                g.setNombre(rs.getString("nombre"));
                g.setUsuario(rs.getString("usuario"));
                g.setPassword_hash(rs.getString("password_hash"));
                lista.add(g);
            }
        } catch (Exception e) {}
        return lista;
    }

    public boolean insertar(Gerente g) {
        String sql = "INSERT INTO gerente(nombre, usuario, password_hash) VALUES (?, ?, ?)";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, g.getNombre());
            ps.setString(2, g.getUsuario());
            ps.setString(3, g.getPassword_hash());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(Gerente g) {
        String sql = "UPDATE gerente SET nombre=?, usuario=?, password_hash=? WHERE id_gerente=?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, g.getNombre());
            ps.setString(2, g.getUsuario());
            ps.setString(3, g.getPassword_hash());
            ps.setInt(4, g.getId_gerente());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM gerente WHERE id_gerente=?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public Gerente validarLogin(String usuario, String password) {
        String sql = "SELECT * FROM gerente WHERE usuario=? AND password_hash=?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                Gerente g = new Gerente();
                g.setId_gerente(rs.getInt("id_gerente"));
                g.setNombre(rs.getString("nombre"));
                g.setUsuario(rs.getString("usuario"));
                g.setPassword_hash(rs.getString("password_hash"));
                return g;
            }

        } catch (Exception e) {}
        return null;
    }

    public boolean validarClaveActual(int id, String actualHash) {
        String sql = "SELECT * FROM gerente WHERE id_gerente=? AND password_hash=?";

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
        String sql = "UPDATE gerente SET password_hash=? WHERE id_gerente=?";

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
    String sql = "UPDATE gerente SET usuario=? WHERE id_gerente=?";

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
