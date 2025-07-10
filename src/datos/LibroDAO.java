/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import modelo.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SENA
 */
public class LibroDAO {
    public static List<Libro> obtenerLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros";

        try (Connection conn = conexionBd.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Libro libro = new Libro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getBoolean("disponible")
                );
                libros.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener libros: " + e.getMessage());
        }
        return libros;
    }

    public static Libro buscarLibroPorTitulo(String titulo) {
        String sql = "SELECT * FROM libros WHERE titulo = ?";

        try (Connection conn = conexionBd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Libro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getBoolean("disponible")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar libro: " + e.getMessage());
        }
        return null;
    }

    public static void actualizarDisponibilidad(int idLibro, boolean disponible) {
        String sql = "UPDATE libros SET disponible = ? WHERE id = ?";

        try (Connection conn = conexionBd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, disponible);
            stmt.setInt(2, idLibro);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar disponibilidad: " + e.getMessage());
        }
    }
}
