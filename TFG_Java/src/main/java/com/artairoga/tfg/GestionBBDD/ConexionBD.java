package com.artairoga.tfg.GestionBBDD;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class ConexionBD {

    private static ConexionBD instancia = null;

    private Connection conexion;

    private ConexionBD() {
        Properties propiedades = new Properties();
        try (InputStream entrada = new FileInputStream("./Resources/config.properties")) {
            propiedades.load(entrada);
            String ip = propiedades.getProperty("db.ip");
            String puerto = propiedades.getProperty("db.port");
            String nombreBD = propiedades.getProperty("db.name");
            String usuario = propiedades.getProperty("db.user");
            String password = propiedades.getProperty("db.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + puerto + "/" + nombreBD, usuario, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problemas con la conexion revise los ajustes");
        }
    }

    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
}
