/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import modelo.Usuario;
import modelo.Libro;
import servicio.AuthService;
import datos.LibroDAO;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author SENA
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthService authService = new AuthService();

    public static void main(String[] args) {
        Usuario usuarioAutenticado = null;

        while (usuarioAutenticado == null) {
            System.out.println("=== BIBLIOTECA VIRTUAL ===");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Restablecer contrasena");
            System.out.print("Seleccione una opcion: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    usuarioAutenticado = authService.iniciarSesion();
                    break;
                case "2":
                    authService.restablecerContrasena();
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Ver libros disponibles");
            System.out.println("2. Apartar libro");
            System.out.println("3. Ver libros apartados");
            System.out.println("4. Devolver libro");
            System.out.println("5. salir");
            System.out.print("Elija una opcion: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    mostrarLibros();
                    break;
                case "2":
                    apartarLibro(usuarioAutenticado);
                    break;
                case "3":
                    usuarioAutenticado.mostrarLibrosApartados();
                    break;
                case "4":
                    devolverLibro(usuarioAutenticado);
                    break;
                case "5":
                    continuar = false;
                    System.out.println("Hasta pronto!");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    private static void mostrarLibros() {
        System.out.println("\n--- LIBROS DISPONIBLES ---");
        for (Libro libro : LibroDAO.obtenerLibros()) {
            if (libro.isDisponible()) {
                System.out.println("- " + libro.getTitulo() + " (Autor: " + libro.getAutor() + ")");
            }
        }
    }


    private static void apartarLibro(Usuario usuario) {
        LocalTime ahora = LocalTime.now();
        LocalTime inicio = LocalTime.of(8, 0);
        LocalTime fin = LocalTime.of(18, 0);

        System.out.println("Hora actual: " + ahora);
        if (ahora.isBefore(inicio) || ahora.isAfter(fin)) {
            System.out.println("Solo se pueden apartar libros entre las 9:30 a.m. y las 6:00 p.m.");
            return;
        }

        if (!usuario.puedeApartarLibro()) {
            System.out.println("Ya has apartado 2 libros. No puedes apartar mas.");
            return;
        }        

        mostrarLibros();

        System.out.print("Ingrese el titulo del libro que desea apartar: ");
        String titulo = scanner.nextLine();

        Libro libro = LibroDAO.buscarLibroPorTitulo(titulo);
        if (libro != null && libro.isDisponible()) {
            LibroDAO.actualizarDisponibilidad(libro.getId(), false);
            usuario.apartarLibro(libro.getTitulo());
            System.out.println("Libro apartado con exito.");
        } else {
            System.out.println("Libro no encontrado o no disponible.");
        }
    }
    
    private static void devolverLibro(Usuario usuario) {
    if (usuario.getLibrosApartados().isEmpty()) {
        System.out.println("No tienes libros para devolver.");
        return;
    }

    System.out.println("Libros que puedes devolver:");
    usuario.mostrarLibrosApartados();

    System.out.print("Ingresa el titulo del libro a devolver: ");
    String titulo = scanner.nextLine();

    boolean removido = usuario.devolverLibro(titulo);
    if (removido) {
        Libro libro = LibroDAO.buscarLibroPorTitulo(titulo);
        if (libro != null) {
            LibroDAO.actualizarDisponibilidad(libro.getId(), true);
        }
        System.out.println("Libro devuelto correctamente.");
    } else {
        System.out.println("No tienes un libro con ese titulo apartado.");
    }
}

}
