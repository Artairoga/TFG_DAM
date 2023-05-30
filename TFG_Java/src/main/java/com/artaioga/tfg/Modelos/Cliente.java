/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.artaioga.tfg.Modelos;

/**
 *
 * @author artai
 */
public class Cliente {

    private int id_cliente;
    private String dni;
    private String nombre_completo;
    private String telefono;
    private String imagen;

    public Cliente() {
    }

    public Cliente(int id_cliente, String dni, String nombre_completo, String telefono, String imagen) {
        this.id_cliente = id_cliente;
        this.dni = dni;
        this.nombre_completo = nombre_completo;
        this.telefono = telefono;
        this.imagen = imagen;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public Cliente setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
        return this;
    }

    public String getDni() {
        return dni;
    }

    public Cliente setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public Cliente setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public Cliente setTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public String getImagen() {
        return imagen;
    }

    public Cliente setImagen(String imagen) {
        this.imagen = imagen;
        return this;
    }

    @Override
    public String toString() {
        String tieneImagen = (imagen != null && !imagen.isEmpty()) ? "Sí" : "No";

        return "Cliente{"
                + "id_cliente=" + id_cliente
                + ", dni='" + dni + '\''
                + ", nombre_completo='" + nombre_completo + '\''
                + ", telefono='" + telefono + '\''
                + ", imagen=" + tieneImagen
                + '}';
    }

}
