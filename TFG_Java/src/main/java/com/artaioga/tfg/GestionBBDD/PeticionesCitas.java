package com.artaioga.tfg.GestionBBDD;

import com.artaioga.tfg.Modelos.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeticionesCitas {
    private Connection conexion;

    public PeticionesCitas(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Cita> listarCitas() throws SQLException {
        List<Cita> listaCitas = new ArrayList<>();
        String sql = "SELECT * FROM citas";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
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
            return statement.executeUpdate();
        }
    }

    public int eliminarCita(int idCita) throws SQLException {
        String sql = "DELETE FROM citas WHERE id_cita = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idCita);
            return statement.executeUpdate();
        }
    }
}
