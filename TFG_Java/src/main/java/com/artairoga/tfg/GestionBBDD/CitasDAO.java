package com.artairoga.tfg.GestionBBDD;

import com.artairoga.tfg.GestionBBDD.Observers.AnimalesObserver;
import com.artairoga.tfg.GestionBBDD.Observers.CitasObserver;
import com.artairoga.tfg.Modelos.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase que gestiona las operaciones de la tabla citas de la base de datos
 */
public class CitasDAO {
    private Connection conexion;

    private List<CitasObserver> observadores = new ArrayList<>();
    private static CitasDAO instancia;

    /**
     * Método que devuelve la instancia de la clase
     * @param conexion conexión a la base de datos
     * @return instancia de la clase
     */
    public static CitasDAO getInstance(Connection conexion) {
        if (instancia == null) {
            instancia = new CitasDAO(conexion);
        }
        return instancia;
    }

    /**
     * Constructor de la clase
      * @param conexion conexión a la base de datos
     */
    public CitasDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Método que devuelve una lista de citas
     * @param whereConditions condiciones de la consulta
     * @return lista de citas
     * @throws SQLException
     */
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

    /**
     * Método que devuelve una lista de citas
     * @param idCita identificador de la cita
     * @return lista de citas
     * @throws SQLException
     */
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

    /**
     * Método que inserta una cita en la base de datos
     * @param cita cita a insertar
     * @return número de filas insertadas
     * @throws SQLException
     */
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

    /**
     * Método que elimina una cita de la base de datos
     * @param cita cita a eliminar
     * @return número de filas eliminadas
     * @throws SQLException
     */

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

    /**
     * Método que elimina una cita de la base de datos
     * @param idCita identificador de la cita a eliminar
     * @return número de filas eliminadas
     * @throws SQLException
     */
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

    /**
     * Método que devuelve una lista de citas
     * @param observador
     */
    public void agregarObservador(CitasObserver observador) {
        observadores.add(observador);
    }

    /**
     * Método que elimina un observador
     * @param observador
     */
    public void eliminarObservador(CitasObserver observador) {
        observadores.remove(observador);
    }

    /**
     * Metodo que nortifica a los observadores
     */
    private void notificarObservadores() {
        for (CitasObserver observador : observadores) {
            observador.actualizarCitas();
        }
    }
}
