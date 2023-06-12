package com.artairoga.tfg.GestionBBDD;

import com.artairoga.tfg.GestionBBDD.ConexionBD;
import com.artairoga.tfg.Modelos.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ClientesDAOTest {
    private Connection conexion;
    private com.artairoga.tfg.GestionBBDD.ClientesDAO clientesDAO;

    private Cliente cliente = new Cliente()
            .setDni("12345678A")
            .setNombre_completo("Juan Pérez")
            .setTelefono("987654321")
            .setImagen("imagen.png");

    @BeforeEach
    public void setUp() throws SQLException {
        conexion = ConexionBD.getInstancia().getConexion();
        clientesDAO = new com.artairoga.tfg.GestionBBDD.ClientesDAO(conexion);
    }

    @Test
    public void testListarClientes() throws SQLException {
        List<Cliente> listaClientes = clientesDAO.listarClientes();
        Assertions.assertNotNull(listaClientes);
        Assertions.assertTrue(listaClientes.size() > 0);

        for (Cliente cliente : listaClientes) {
            Assertions.assertNotNull(cliente.getId_cliente());
            Assertions.assertNotNull(cliente.getDni());
            Assertions.assertNotNull(cliente.getNombre_completo());
            Assertions.assertNotNull(cliente.getTelefono());
            Assertions.assertNotNull(cliente.getImagen());
        }
    }
    @Test
    public void buscarClienteExistente() throws SQLException {
        // Dado que tengo un cliente en la base de datos con id=1
        int idCliente = 1;
        Cliente clienteEncontrado = clientesDAO.buscarCliente(idCliente);

        // Entonces el cliente devuelto debería ser igual al esperado
        assertNotNull(clienteEncontrado);
        assertEquals(idCliente, clienteEncontrado.getId_cliente());
    }

    @Test
    public void buscarClienteNoExistente() throws SQLException {
        // Dado que no tengo un cliente en la base de datos id=99999
        int idCliente = 99999;
        Cliente clienteEncontrado = clientesDAO.buscarCliente(idCliente);

        // Entonces el cliente devuelto debería ser null
        assertNull(clienteEncontrado);
    }

    @Test
    public void testInsertarYEliminarCliente() throws SQLException {
        // Insertamos el cliente, el propio método le da a este objeto el id insertado
        clientesDAO.insertarCliente(cliente);
        // Buscamos el cliente
        Cliente clienteObtenido = clientesDAO.buscarCliente(cliente.getId_cliente());
        // Confirmamos que todo está bien
        assertNotNull(clienteObtenido);
        assertEquals(cliente.getDni(), clienteObtenido.getDni());
        assertEquals(cliente.getNombre_completo(), clienteObtenido.getNombre_completo());
        assertEquals(cliente.getTelefono(), clienteObtenido.getTelefono());
        assertEquals(cliente.getImagen(), clienteObtenido.getImagen());
        // Borramos el cliente que acabamos de insertar
        int resultado = clientesDAO.eliminarCliente(cliente.getId_cliente());
        assertEquals(1, resultado);
    }
}

