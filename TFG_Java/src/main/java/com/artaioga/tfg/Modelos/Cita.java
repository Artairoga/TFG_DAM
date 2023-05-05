/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.artaioga.tfg.Modelos;
import java.sql.Date;
import java.sql.Time;
/**
 *
 * @author artai
 */
public class Cita {
    private int idCita;
    private int idCliente;
    private int idAnimal;
    private Date fecha;
    private Time horaInicio;
    private boolean pendiente;
    private String descripcion;

    public Cita() {}

    public Cita(int idCita, int idCliente, int idAnimal, Date fecha, Time horaInicio, boolean pendiente, String descripcion) {
        this.idCita = idCita;
        this.idCliente = idCliente;
        this.idAnimal = idAnimal;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.pendiente = pendiente;
        this.descripcion = descripcion;
    }

    public int getIdCita() {
        return idCita;
    }

    public Cita setIdCita(int idCita) {
        this.idCita = idCita;
        return this;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public Cita setIdCliente(int idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public Cita setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
        return this;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cita setFecha(Date fecha) {
        this.fecha = fecha;
        return this;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public Cita setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public Cita setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Cita setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    @Override
    public String toString() {
        return "Cita{" + "idCita=" + idCita + ", idCliente=" + idCliente + ", idAnimal=" + idAnimal + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", pendiente=" + pendiente + ", descripcion=" + descripcion + '}';
    }
    
}

