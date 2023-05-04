package com.artaioga.tfg.GestionBBDD;

import com.artaioga.tfg.GestionBBDD.ConexionBD;
import com.artaioga.tfg.GestionBBDD.PeticionesClientes;
import com.artaioga.tfg.Modelos.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PeticionesClientesTest {
    private Connection conexion;
    private PeticionesClientes peticionesClientes;

    @BeforeEach
    public void setUp() throws SQLException {
        conexion = ConexionBD.getInstancia().getConexion();
        peticionesClientes = new PeticionesClientes(conexion);
    }

    @Test
    public void testListarClientes() throws SQLException {
        List<Cliente> listaClientes = peticionesClientes.listarClientes();
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
}

