package com.artairoga.tfg.GestionBBDD;

import com.artaioga.tfg.GestionBBDD.ConexionBD;
import com.artaioga.tfg.GestionBBDD.CitasDAO;
import com.artaioga.tfg.Modelos.Cita;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CitasDAOTest {
    private Connection conexion;
    private CitasDAO citasDAO;
    private Cita cita = new Cita()
            .setIdCliente(1)
            .setIdAnimal(1)
            .setFecha(java.sql.Date.valueOf("2021-05-01"))
            .setHoraInicio(Time.valueOf("10:00:00"))
            .setPendiente(true)
            .setDescripcion("Cita de prueba");

    @BeforeEach
    public void setUp() throws SQLException {
        conexion = ConexionBD.getInstancia().getConexion();
        citasDAO = new CitasDAO(conexion);
    }

    @Test
    public void testListarCitas() throws SQLException {
        List<Cita> listaCitas = citasDAO.listarCitas();
        assertNotNull(listaCitas);
        Assertions.assertTrue(listaCitas.size() > 0);

        for (Cita cita : listaCitas) {
            assertNotNull(cita.getIdCita());
            assertNotNull(cita.getIdCliente());
            assertNotNull(cita.getIdAnimal());
            assertNotNull(cita.getFecha());
            assertNotNull(cita.getHoraInicio());
            assertNotNull(cita.isPendiente());
            assertNotNull(cita.getDescripcion());
        }
    }

    @Test
    public void buscarCitaExistente() throws SQLException {
        // Dado que tengo una cita en la base de datos con id_cita = 1
        int idCita = 1;
        // Cuando ejecuto el método buscarCita con id_cita = 1
        Cita citaEncontrada = citasDAO.buscarCita(idCita);

        // Entonces la cita devuelta debería ser igual a la esperada
        assertNotNull(citaEncontrada);
        assertEquals(idCita, citaEncontrada.getIdCita());
    }

    @Test
    public void buscarCitaNoExistente() throws SQLException {
        // Dado que no tengo una cita en la base de datos con id_cita = 99
        int idCita = 99;

        // Cuando ejecuto el método buscarCita con id_cita = 99
        Cita citaEncontrada = citasDAO.buscarCita(idCita);

        // Entonces la cita devuelta debería ser null
        assertNull(citaEncontrada);
    }

    @Test
    public void testInsertarYEliminarCita() throws SQLException {
        // Insertamos la cita, el propio método le da a esta objeto el id insertado
        citasDAO.insertarCita(cita);
        // Buscamos la cita
        Cita citaObtenida = citasDAO.buscarCita(cita.getIdCita());
        // Confirmamos que todo está bien
        assertNotNull(citaObtenida);
        assertEquals(cita.getIdCita(), citaObtenida.getIdCita());
        assertEquals(cita.getIdCliente(), citaObtenida.getIdCliente());
        assertEquals(cita.getIdAnimal(), citaObtenida.getIdAnimal());
        assertEquals(cita.getFecha(), citaObtenida.getFecha());
        assertEquals(cita.getHoraInicio(), citaObtenida.getHoraInicio());
        assertEquals(cita.isPendiente(), citaObtenida.isPendiente());
        assertEquals(cita.getDescripcion(), citaObtenida.getDescripcion());
        // Borramos la cita que acabamos de insertar
        int resultado = citasDAO.eliminarCita(cita.getIdCita());
        assertEquals(1, resultado);
    }

}
