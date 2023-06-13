package com.artairoga.tfg.GestionBBDD;

import com.artairoga.tfg.GestionBBDD.Observers.AnimalesObserver;
import com.artairoga.tfg.Modelos.Animal;
import com.artairoga.tfg.Modelos.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase que gestiona las operaciones de la tabla animales de la base de datos
 */
public class AnimalesDAO {
    private Connection conexion;
    private List<AnimalesObserver> observadores = new ArrayList<>();
    private static AnimalesDAO instancia;
    public static AnimalesDAO getInstance(Connection conexion) {
        if (instancia == null) {
            instancia = new AnimalesDAO(conexion);
        }
        return instancia;
    }
    public AnimalesDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Animal> listar(Map<String, ?> whereConditions) throws SQLException {
        List<Animal> listaAnimales = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM animales");
        if (!whereConditions.isEmpty()) {
            sql.append(" WHERE");
            int conditionCount = 0;
            for (Map.Entry<String, ?> entry : whereConditions.entrySet()) {
                String columnName = entry.getKey();
                Object value = entry.getValue();
                if (conditionCount > 0) {
                    sql.append(" AND");
                }
                sql.append(" ").append(columnName).append(" = ?");
                conditionCount++;
            }
        }
        try (PreparedStatement statement = conexion.prepareStatement(sql.toString())) {
            int parameterIndex = 1;
            for (Object value : whereConditions.values()) {
                statement.setObject(parameterIndex, value);
                parameterIndex++;
            }
            try (ResultSet resultSet = statement.executeQuery()) {
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
            notificarObservadores();
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
        notificarObservadores();
        return filasActualizadas;
    }
}
    public int eliminarAnimal(int idAnimal) throws SQLException {
        try {
            // En caso de borrar un animal, primero listo sus citas y las borro
            CitasDAO citasDAO = new CitasDAO(conexion);
            Map<String, Object> whereConditions = Map.of("id_animal", idAnimal);
            List<Cita> listaCitas = citasDAO.listarCitas(whereConditions);

            // Eliminar las citas del animal
            for (Cita cita : listaCitas) {
                citasDAO.eliminarCita(cita.getIdCita());
            }
            conexion.setAutoCommit(false); // Iniciar transacción manualmente
            // Eliminar el animal
            String sql = "DELETE FROM animales WHERE id_animal = ?";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setInt(1, idAnimal);
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

    public void agregarObservador(AnimalesObserver observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(AnimalesObserver observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores() {
        for (AnimalesObserver observador : observadores) {
            observador.actualizarAnimales();
        }
    }

}
