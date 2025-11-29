package uts.poo.proyecto.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import uts.poo.proyecto.modelo.Cliente;
import uts.poo.proyecto.modelo.ConexionDB;

public class ClienteDAO {

    public boolean insertar(Cliente c) {
        String sql = "INSERT INTO cliente(nombre, telefono, direccion) VALUES(?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getDireccion());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error insertar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Cliente c) {
        String sql = "UPDATE cliente SET nombre=?, telefono=?, direccion=? WHERE id_cliente=?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getDireccion());
            ps.setInt(4, c.getId_cliente());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente=?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = ConexionDB.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error listar clientes: " + e.getMessage());
        }

        return lista;
    }
}
