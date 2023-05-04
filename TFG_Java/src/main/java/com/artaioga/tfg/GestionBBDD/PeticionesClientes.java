package com.artaioga.tfg.GestionBBDD;

import com.artaioga.tfg.Modelos.Animal;
import com.artaioga.tfg.Modelos.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeticionesClientes {
    private Connection conexion;

    public PeticionesClientes(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Cliente cliente = new Cliente()
                        .setId_cliente(resultSet.getInt("id_cliente"))
                        .setDni(resultSet.getString("dni"))
                        .setNombre_completo(resultSet.getString("nombre_completo"))
                        .setTelefono(resultSet.getString("telefono"))
                        .setImagen(resultSet.getString("imagen"));
                listaClientes.add(cliente);
            }
        }
        return listaClientes;
    }
}
