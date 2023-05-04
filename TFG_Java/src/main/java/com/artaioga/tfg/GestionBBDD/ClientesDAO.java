package com.artaioga.tfg.GestionBBDD;

import com.artaioga.tfg.Modelos.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {
    private Connection conexion;

    public ClientesDAO(Connection conexion) {
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

    public Cliente buscarCliente(int id_cliente) throws SQLException {
        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id_cliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = new Cliente()
                            .setId_cliente(resultSet.getInt("id_cliente"))
                            .setDni(resultSet.getString("dni"))
                            .setNombre_completo(resultSet.getString("nombre_completo"))
                            .setTelefono(resultSet.getString("telefono"))
                            .setImagen(resultSet.getString("imagen"));
                }
            }
        }
        return cliente;
    }
    public int insertarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (dni, nombre_completo, telefono, imagen) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cliente.getDni());
            statement.setString(2, cliente.getNombre_completo());
            statement.setString(3, cliente.getTelefono());
            statement.setString(4, cliente.getImagen());
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas == 0) {
                throw new SQLException("No se ha insertado ninguna fila en la base de datos");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId_cliente(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("No se han generado claves primarias para el cliente insertado");
                }
            }
            return filasInsertadas;
        }
    }

    public int actualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET dni = ?, nombre_completo = ?, telefono = ?, imagen = ? " +
                "WHERE id_cliente = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, cliente.getDni());
            statement.setString(2, cliente.getNombre_completo());
            statement.setString(3, cliente.getTelefono());
            statement.setString(4, cliente.getImagen());
            statement.setInt(5, cliente.getId_cliente());
            return statement.executeUpdate();
        }
    }

    public int eliminarCliente(int id_cliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id_cliente);
            return statement.executeUpdate();
        }
    }
}
