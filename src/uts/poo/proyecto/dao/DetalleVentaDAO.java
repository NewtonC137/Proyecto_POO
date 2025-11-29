package uts.poo.proyecto.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.poo.proyecto.modelo.ConexionDB;
import uts.poo.proyecto.modelo.DetalleVenta;

public class DetalleVentaDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    
    public boolean insertar(DetalleVenta d) {

        String sql = "INSERT INTO detalle_venta(id_venta, id_producto, cantidad, precio, subtotal) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, d.getId_venta());
            ps.setInt(2, d.getId_producto());
            ps.setInt(3, d.getCantidad());
            ps.setDouble(4, d.getPrecio());
            ps.setDouble(5, d.getSubtotal());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error insertar detalle: " + e.getMessage());
        }

        return false;
    }

    
    public boolean insertar(int idVenta, int idProducto, int cantidad, double precio, double subtotal) {

        DetalleVenta d = new DetalleVenta();
        d.setId_venta(idVenta);
        d.setId_producto(idProducto);
        d.setCantidad(cantidad);
        d.setPrecio(precio);
        d.setSubtotal(subtotal);

        return insertar(d);  
    }

    
    public ArrayList<DetalleVenta> listarPorVenta(int idVenta) {

        ArrayList<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta WHERE id_venta = ?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);

            rs = ps.executeQuery();

            while (rs.next()) {

                DetalleVenta d = new DetalleVenta();
                d.setId_detalle(rs.getInt("id_detalle"));
                d.setId_venta(rs.getInt("id_venta"));
                d.setId_producto(rs.getInt("id_producto"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setPrecio(rs.getDouble("precio"));
                d.setSubtotal(rs.getDouble("subtotal"));

                lista.add(d);
            }

        } catch (SQLException e) {
            System.out.println("Error listar detalle: " + e.getMessage());
        }

        return lista;
    }
}
