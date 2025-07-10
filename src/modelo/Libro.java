/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author SENA
 */

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private boolean disponible;

    public Libro() {}

    public Libro(int id, String titulo, String autor, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
    }

    // Getters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponible() { return disponible; }

    // Setters
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return titulo + " (Autor: " + autor + ")";
    }
}
