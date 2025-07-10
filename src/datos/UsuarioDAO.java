/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import modelo.Usuario;

import java.sql.*;
/**
 *
 * @author SENA
 */
public class UsuarioDAO {

    public static Usuario autenticar(String usuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";

        try (Connection conn = conexionBd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setContrasena(rs.getString("contrasena"));
                return u;
            }

        } catch (SQLException e) {
            System.out.println("Error al autenticar usuario: " + e.getMessage());
        }

        return null;
    }

    public Usuario buscarUsuarioPorCredenciales(String usuario, String contrasena) {
        return autenticar(usuario, contrasena);
    }

    public Usuario buscarUsuarioPorNombreUsuario(String usuario) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";
        try (Connection conn = conexionBd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setContrasena(rs.getString("contrasena"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario por nombre: " + e.getMessage());
        }
        return null;
    }

    public static boolean restablecerContrasena(String usuario, String nuevaContrasena) {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE usuario = ?";
        try (Connection conn = conexionBd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevaContrasena);
            stmt.setString(2, usuario);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al restablecer contraseña: " + e.getMessage());
        }
        return false;
    }

    public void actualizarContrasena(int idUsuario, String nuevaContrasena) {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE id = ?";
        try (Connection conn = conexionBd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevaContrasena);
            stmt.setInt(2, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar contraseña por ID: " + e.getMessage());
        }
    }
}
