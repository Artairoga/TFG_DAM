/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.artaioga.tfg.Modelos;

/**
 *
 * @author artai
 */
public class Animal {
    private int idAnimal;
    private int idCliente;
    private String nombreAnimal;
    private String caracteristicas;
    private String imagen;

    public Animal() {}

    public Animal(int idAnimal, int idCliente, String nombreAnimal, String caracteristicas, String imagen) {
        this.idAnimal = idAnimal;
        this.idCliente = idCliente;
        this.nombreAnimal = nombreAnimal;
        this.caracteristicas = caracteristicas;
        this.imagen = imagen;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public Animal setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
        return this;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public Animal setIdCliente(int idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public String getTipoAnimal() {
        return nombreAnimal;
    }

    public Animal setNombreAnimal(String nombreAnimal) {
        this.nombreAnimal = nombreAnimal;
        return this;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public Animal setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
        return this;
    }

    public String getImagen() {
        return imagen;
    }

    public Animal setImagen(String imagen) {
        this.imagen = imagen;
        return this;
    }

    @Override
    public String toString() {
        String tieneImagen = (imagen != null && !imagen.isEmpty()) ? "Sí" : "No";
        return "Animal{" + "idAnimal=" + idAnimal + ", idCliente=" + idCliente + ", nombreAnimal=" + nombreAnimal + ", caracteristicas=" + caracteristicas + ", imagen=" + tieneImagen + '}';
    }
    
}

