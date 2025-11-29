package uts.poo.proyecto.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.poo.proyecto.modelo.ConexionDB;
import uts.poo.proyecto.modelo.Producto;

public class ProductoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    
    public boolean insertar(Producto p) {

        String sql = "INSERT INTO producto(nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error insertar producto: " + e.getMessage());
            return false;
        }
    }

    
    public boolean actualizar(Producto p) {

        String sql = "UPDATE producto SET nombre=?, descripcion=?, precio=?, stock=? WHERE id_producto=?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getId_producto());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error actualizar producto: " + e.getMessage());
            return false;
        }
    }

    
    public boolean eliminar(int id) {

        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error eliminar producto: " + e.getMessage());
            return false;
        }
    }

    
    public Producto obtenerPorId(int id) {

        String sql = "SELECT * FROM producto WHERE id_producto = ?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Producto p = new Producto();
                p.setId_producto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                return p;
            }

        } catch (Exception e) {
            System.out.println("Error obtener producto: " + e.getMessage());
        }

        return null;
    }

    
    public boolean restarStock(int id, int cantidad) {

        String sql = "UPDATE producto SET stock = stock - ? WHERE id_producto = ?";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setInt(2, id);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error restar stock: " + e.getMessage());
            return false;
        }
    }

    
    public ArrayList<Producto> listar() {

        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();
                p.setId_producto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error listar productos: " + e.getMessage());
        }

        return lista;
    }
}
