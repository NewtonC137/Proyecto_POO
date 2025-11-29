package uts.poo.proyecto.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import uts.poo.proyecto.modelo.ConexionDB;
import uts.poo.proyecto.modelo.Venta;

public class VentaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    
    public int insertar(Venta v) {

        String sql = "INSERT INTO venta(id_cliente, id_empleado, fecha, total) "
                   + "VALUES(?, ?, ?, ?)";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, v.getId_cliente());
            ps.setInt(2, v.getId_empleado()); 
            ps.setString(3, v.getFecha());
            ps.setDouble(4, v.getTotal());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); 
            }

        } catch (SQLException e) {
            System.out.println("Error insertar venta: " + e.getMessage());
        }

        return -1;
    }

    
    public List<String[]> listarVentasResumen() {

        List<String[]> lista = new ArrayList<>();

        String sql = "SELECT id_venta, fecha, total "
                   + "FROM venta ORDER BY id_venta DESC";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                String[] fila = {
                    String.valueOf(rs.getInt("id_venta")),
                    rs.getString("fecha"),
                    String.valueOf(rs.getDouble("total"))
                };

                lista.add(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error listar ventas: " + e.getMessage());
        }

        return lista;
    }

    
    public Venta obtenerPorId(int idVenta) {

        String sql = "SELECT * FROM venta WHERE id_venta = ?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);

            rs = ps.executeQuery();

            if (rs.next()) {
                Venta v = new Venta();
                v.setId_venta(rs.getInt("id_venta"));
                v.setId_cliente(rs.getInt("id_cliente"));
                v.setId_empleado(rs.getInt("id_empleado")); 
                v.setFecha(rs.getString("fecha"));
                v.setTotal(rs.getDouble("total"));
                return v;
            }

        } catch (SQLException e) {
            System.out.println("Error obtener venta: " + e.getMessage());
        }

        return null;
    }

    
    public boolean eliminar(int idVenta) {

        String sql = "DELETE FROM venta WHERE id_venta = ?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar venta: " + e.getMessage());
            return false;
        }
    }
}
