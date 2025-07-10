/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;
import datos.UsuarioDAO;
import modelo.Usuario;
import java.util.Scanner;
/**
 *
 * @author SENA
 */
public class AuthService {
    private Scanner scanner = new Scanner(System.in);

    public Usuario iniciarSesion() {
        System.out.print("Ingrese su usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese su contrasena: ");
        String contrasena = scanner.nextLine();

        UsuarioDAO dao = new UsuarioDAO();
        Usuario encontrado = dao.buscarUsuarioPorCredenciales(usuario, contrasena);

        if (encontrado != null) {
            System.out.println("Inicio de sesion exitoso. Bienvenido " + encontrado.getNombre() + "!");
            return encontrado;
        } else {
            System.out.println("Usuario o contrasena incorrectos.");
            return null;
        }
    }

    public void restablecerContrasena() {
        System.out.print("Ingrese su usuario para restablecer la contrasena: ");
        String usuario = scanner.nextLine();

        UsuarioDAO dao = new UsuarioDAO();
        Usuario encontrado = dao.buscarUsuarioPorNombreUsuario(usuario);

        if (encontrado != null) {
            System.out.print("Ingrese la nueva contrasena: ");
            String nueva = scanner.nextLine();
            encontrado.setContrasena(nueva);
            dao.actualizarContrasena(encontrado.getId(), nueva);
            System.out.println("Contrasena actualizada correctamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}