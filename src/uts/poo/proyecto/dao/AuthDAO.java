package uts.poo.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uts.poo.proyecto.modelo.AuthUser;
import uts.poo.proyecto.modelo.ConexionDB;
import uts.poo.proyecto.util.HashUtil;

public class AuthDAO {

    
    public AuthUser authenticate(String usuario, String password) {

        String hash = HashUtil.sha256(password);

        String sqlGer = "SELECT id_gerente, usuario FROM gerente WHERE usuario = ? AND password_hash = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlGer)) {

            ps.setString(1, usuario);
            ps.setString(2, hash);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id_gerente");
                    return new AuthUser(id, usuario, "GERENTE");
                }
            }

        } catch (Exception e) {
            System.out.println("Auth error (gerente): " + e.getMessage());
        }

        String sqlEmp = "SELECT id_empleado, usuario FROM empleado WHERE usuario = ? AND password_hash = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlEmp)) {

            ps.setString(1, usuario);
            ps.setString(2, hash);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id_empleado");
                    return new AuthUser(id, usuario, "EMPLEADO");
                }
            }

        } catch (Exception e) {
            System.out.println("Auth error (empleado): " + e.getMessage());
        }

        return null;
    }
}
