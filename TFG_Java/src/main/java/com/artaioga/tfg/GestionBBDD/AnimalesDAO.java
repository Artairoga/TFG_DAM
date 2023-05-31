package com.artaioga.tfg.GestionBBDD;

import com.artaioga.tfg.Modelos.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalesDAO {
    private Connection conexion;

    public AnimalesDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Animal> listar() throws SQLException {
        List<Animal> listaAnimales = new ArrayList<>();
        String sql = "SELECT * FROM animales";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Animal animal = new Animal()
                .setIdAnimal(resultSet.getInt("id_animal"))
                .setIdCliente(resultSet.getInt("id_cliente"))
                .setNombreAnimal(resultSet.getString("nombre_animal"))
                .setCaracteristicas(resultSet.getString("caracteristicas"))
                .setImagen(resultSet.getString("imagen"));
                listaAnimales.add(animal);
            }
        }
        return listaAnimales;
    }

    public Animal buscarAnimal(int idAnimal) throws SQLException {
        Animal animal = null;
        String sql = "SELECT * FROM animales WHERE id_animal = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idAnimal);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    animal = new Animal()
                            .setIdAnimal(resultSet.getInt("id_animal"))
                            .setIdCliente(resultSet.getInt("id_cliente"))
                            .setNombreAnimal(resultSet.getString("nombre_animal"))
                            .setCaracteristicas(resultSet.getString("caracteristicas"))
                            .setImagen(resultSet.getString("imagen"));
                }
            }
        }
        return animal;
    }
    public int insertarAnimal(Animal animal) throws SQLException {
        String sql = "INSERT INTO animales (id_cliente, nombre_animal, caracteristicas, imagen) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, animal.getIdCliente());
            statement.setString(2, animal.getTipoAnimal());
            statement.setString(3, animal.getCaracteristicas());
            statement.setString(4, animal.getImagen());
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas == 0) {
                throw new SQLException("No se ha insertado ninguna fila en la base de datos");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    animal.setIdAnimal(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("No se han generado claves primarias para el animal insertado");
                }
            }
            return filasInsertadas;
        }
    }
    public int actualizarAnimal(Animal animal) throws SQLException {
    String sql = "UPDATE animales SET id_cliente = ?, nombre_animal = ?, caracteristicas = ?, imagen = ? WHERE id_animal = ?";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setInt(1, animal.getIdCliente());
        statement.setString(2, animal.getTipoAnimal());
        statement.setString(3, animal.getCaracteristicas());
        statement.setString(4, animal.getImagen());
        statement.setInt(5, animal.getIdAnimal());
        int filasActualizadas = statement.executeUpdate();
        if (filasActualizadas == 0) {
            throw new SQLException("No se ha actualizado ninguna fila en la base de datos");
        }
        return filasActualizadas;
    }
}
    public int eliminarAnimal(int idAnimal) throws SQLException {
        String sql = "DELETE FROM animales WHERE id_animal = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            conexion.setAutoCommit(false); // Desactivar el modo de confirmación automática

            statement.setInt(1, idAnimal);
            int filasEliminadas = statement.executeUpdate();

            conexion.commit(); // Confirmar la transacción

            return filasEliminadas;
        } catch (SQLException e) {
            conexion.rollback(); // Revertir la transacción en caso de error
            throw e;
        } finally {
            conexion.setAutoCommit(true); // Restaurar el modo de confirmación automática
        }
    }

}
