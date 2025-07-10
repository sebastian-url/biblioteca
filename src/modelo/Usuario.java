/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author SENA
 */
public class Usuario {
    private int id;
    private String nombre;
    private String usuario;
    private String contrasena;
    private List<String> librosApartados;

    // Constructor vacío (necesario para DAO)
    public Usuario() {
        this.librosApartados = new ArrayList<>();
    }

    // Constructor completo con ID
    public Usuario(int id, String nombre, String usuario, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.librosApartados = new ArrayList<>();
    }

    // Constructor sin ID (opcional para registros nuevos)
    public Usuario(String nombre, String usuario, String contrasena) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.librosApartados = new ArrayList<>();
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public List<String> getLibrosApartados() {
        return librosApartados;
    }

    // Funcionalidad para préstamo
    public boolean puedeApartarLibro() {
        return librosApartados.size() < 2;
    }

    public void apartarLibro(String titulo) {
        if (puedeApartarLibro()) {
            librosApartados.add(titulo);
        }
    }

    public boolean devolverLibro(String titulo) {
        return librosApartados.remove(titulo);
    }

    public void mostrarLibrosApartados() {
        if (librosApartados.isEmpty()) {
            System.out.println("No has apartado ningún libro.");
        } else {
            System.out.println("Libros apartados:");
            for (String libro : librosApartados) {
                System.out.println("- " + libro);
            }
        }
    }
}