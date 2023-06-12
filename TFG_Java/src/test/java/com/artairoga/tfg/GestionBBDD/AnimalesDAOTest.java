package com.artairoga.tfg.GestionBBDD;

import com.artairoga.tfg.GestionBBDD.ConexionBD;
import com.artairoga.tfg.GestionBBDD.AnimalesDAO;
import com.artairoga.tfg.Modelos.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalesDAOTest {
    private static  Connection conexion;
    private static AnimalesDAO animalesDAO;

    private static Animal animal;

    @BeforeAll
    public static void setUp() throws SQLException {
        conexion = ConexionBD.getInstancia().getConexion();
        animalesDAO = new AnimalesDAO(conexion);
        animal = new Animal()
                .setIdCliente(1)
                .setNombreAnimal("Perro")
                .setCaracteristicas("Labrador")
                .setImagen("imagen.jpg");
    }

    @Test
    public void testListarAnimales() throws SQLException {

        List<Animal> listaAnimales = animalesDAO.listar(new HashMap<>());
        assertNotNull(listaAnimales);
        Assertions.assertTrue(listaAnimales.size() > 0);

        for (Animal animal : listaAnimales) {
            assertNotNull(animal.getIdAnimal());
            assertNotNull(animal.getIdCliente());
            assertNotNull(animal.getTipoAnimal());
            assertNotNull(animal.getCaracteristicas());
            assertNotNull(animal.getImagen());
        }
    }

    @Test
    public void buscarAnimalExistente() throws SQLException {
        // Dado que tengo un animal en la base de datos con id_animal = 1
        int idAnimal = 1;
        // Cuando ejecuto el método buscarAnimal con id_animal = 1
        Animal animalEncontrado = animalesDAO.buscarAnimal(idAnimal);

        // Entonces el animal devuelto debería ser igual al esperado
        assertNotNull(animalEncontrado);
    }

    @Test
    public void buscarAnimalNoExistente() throws SQLException {
        // Dado que no tengo un animal en la base de datos con id_animal = 99
        int idAnimal = 99;

        // Cuando ejecuto el método buscarAnimal con id_animal = 99
        Animal animalEncontrado = animalesDAO.buscarAnimal(idAnimal);

        // Entonces el animal devuelto debería ser null
        assertNull(animalEncontrado);
    }

    @Test
    public void testInsertarYEliminarAnimal() throws SQLException {
        //insertamos el animal,el propio metodo le da a esta objeto el id insertado
        animalesDAO.insertarAnimal(animal);
        //lo buscamos
        Animal animalObtenido = animalesDAO.buscarAnimal(animal.getIdAnimal());
        //confirmamos que todo esta bien
        assertNotNull(animalObtenido);
        assertEquals(animal.getIdAnimal(), animalObtenido.getIdAnimal());
        assertEquals(animal.getIdCliente(), animalObtenido.getIdCliente());
        assertEquals(animal.getTipoAnimal(), animalObtenido.getTipoAnimal());
        assertEquals(animal.getCaracteristicas(), animalObtenido.getCaracteristicas());
        assertEquals(animal.getImagen(), animalObtenido.getImagen());
        //borramos el animal que acabamos de insertar
        int resultado =  animalesDAO.eliminarAnimal(animal.getIdAnimal());
        assertEquals(1, resultado);
    }
}
