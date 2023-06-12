package com.artairoga.tfg.GestionBBDD;

import com.artairoga.tfg.GestionBBDD.Observers.CitasObserver;
import com.artairoga.tfg.GestionBBDD.Observers.ClientesObserver;
import com.artairoga.tfg.Modelos.Animal;
import com.artairoga.tfg.Modelos.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientesDAO {
    private Connection conexion;
    private List<ClientesObserver> observadores = new ArrayList<>();
    private static ClientesDAO instancia;
    public static ClientesDAO getInstance(Connection conexion) {
        if (instancia == null) {
            instancia = new ClientesDAO(conexion);
        }
        return instancia;
    }

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
            notificarObservadores();
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
            notificarObservadores();
            return statement.executeUpdate();
        }
    }

    public int eliminarCliente(int idCliente) throws SQLException {
        try {
            // En caso de borrar un cliente, primero obtengo la lista de animales que le pertenecen
            AnimalesDAO animalesDAO = new AnimalesDAO(conexion);
            Map<String, Object> whereConditions = Map.of("id_cliente", idCliente);
            List<Animal> listaAnimales = animalesDAO.listar(whereConditions);

            // Eliminar los animales del cliente
            for (Animal animal : listaAnimales) {
                animalesDAO.eliminarAnimal(animal.getIdAnimal());
            }
            conexion.setAutoCommit(false); // Iniciar transacción manualmente
            // Eliminar el cliente
            String sql = "DELETE FROM clientes WHERE id_cliente = ?";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setInt(1, idCliente);
                int filasEliminadas = statement.executeUpdate();
                conexion.commit(); // Confirmar la transacción
                notificarObservadores();

                return filasEliminadas;
            } catch (SQLException e) {
                conexion.rollback(); // Deshacer la transacción en caso de error
                throw e; // Propagar la excepción
            }
        } finally {
            conexion.setAutoCommit(true); // Restaurar el modo de auto-commit
        }
    }
    public void agregarObservador(ClientesObserver observador) {
        observadores.add(observador);
    }
    public void eliminarObservador(ClientesObserver observador) {
        observadores.remove(observador);
    }
    public void notificarObservadores() {
        for (ClientesObserver observador : observadores) {
            observador.actualizarClientes();
        }
    }

}
