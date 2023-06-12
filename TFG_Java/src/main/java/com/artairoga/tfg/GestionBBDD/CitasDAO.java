package com.artairoga.tfg.GestionBBDD;

import com.artairoga.tfg.GestionBBDD.Observers.AnimalesObserver;
import com.artairoga.tfg.GestionBBDD.Observers.CitasObserver;
import com.artairoga.tfg.Modelos.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CitasDAO {
    private Connection conexion;

    private List<CitasObserver> observadores = new ArrayList<>();
    private static CitasDAO instancia;
    public static CitasDAO getInstance(Connection conexion) {
        if (instancia == null) {
            instancia = new CitasDAO(conexion);
        }
        return instancia;
    }

    public CitasDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Cita> listarCitas(Map<String, ?> whereConditions) throws SQLException {
        List<Cita> listaCitas = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM citas");
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
                    Cita cita = new Cita()
                            .setIdCita(resultSet.getInt("id_cita"))
                            .setIdCliente(resultSet.getInt("id_cliente"))
                            .setIdAnimal(resultSet.getInt("id_animal"))
                            .setFecha(resultSet.getDate("fecha"))
                            .setHoraInicio(resultSet.getTime("hora_inicio"))
                            .setPendiente(resultSet.getBoolean("pendiente"))
                            .setDescripcion(resultSet.getString("descripcion"));
                    listaCitas.add(cita);
                }
            }
        }
        return listaCitas;
    }


    public Cita buscarCita(int idCita) throws SQLException {
        Cita cita = null;
        String sql = "SELECT * FROM citas WHERE id_cita = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idCita);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cita = new Cita()
                            .setIdCita(resultSet.getInt("id_cita"))
                            .setIdCliente(resultSet.getInt("id_cliente"))
                            .setIdAnimal(resultSet.getInt("id_animal"))
                            .setFecha(resultSet.getDate("fecha"))
                            .setHoraInicio(resultSet.getTime("hora_inicio"))
                            .setPendiente(resultSet.getBoolean("pendiente"))
                            .setDescripcion(resultSet.getString("descripcion"));
                }
            }
        }
        return cita;
    }

    public int insertarCita(Cita cita) throws SQLException {
        String sql = "INSERT INTO citas (id_cliente, id_animal, fecha, hora_inicio, pendiente, descripcion) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, cita.getIdCliente());
            statement.setInt(2, cita.getIdAnimal());
            statement.setDate(3, new java.sql.Date(cita.getFecha().getTime()));
            statement.setTime(4, new java.sql.Time(cita.getHoraInicio().getTime()));
            statement.setBoolean(5, cita.isPendiente());
            statement.setString(6, cita.getDescripcion());
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas == 0) {
                throw new SQLException("No se ha insertado ninguna fila en la base de datos");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cita.setIdCita(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("No se han generado claves primarias para la cita insertada");
                }
            }
            notificarObservadores();
            return filasInsertadas;
        }
    }


    public int actualizarCita(Cita cita) throws SQLException {
        String sql = "UPDATE citas SET id_cliente = ?, id_animal = ?, fecha = ?, hora_inicio = ?, pendiente = ?, " +
                "descripcion = ? WHERE id_cita = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, cita.getIdCliente());
            statement.setInt(2, cita.getIdAnimal());
            statement.setDate(3, cita.getFecha());
            statement.setTime(4, cita.getHoraInicio());
            statement.setBoolean(5, cita.isPendiente());
            statement.setString(6, cita.getDescripcion());
            statement.setInt(7, cita.getIdCita());
            int filasActualizadas = statement.executeUpdate();
            notificarObservadores();
            return filasActualizadas;
        }
    }

    public int eliminarCita(int idCita) throws SQLException {
        String sql = "DELETE FROM citas WHERE id_cita = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idCita);
            int filasEliminadas = statement.executeUpdate();
            notificarObservadores();
            return filasEliminadas;
        } catch (SQLException e) {
            throw e;
        }
    }
    public void agregarObservador(CitasObserver observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(CitasObserver observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores() {
        for (CitasObserver observador : observadores) {
            observador.actualizarCitas();
        }
    }
}
