package com.artairoga.tfg.GestionBBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.artaioga.tfg.GestionBBDD.ConexionBD;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConexionBDTest {
    private ConexionBD conexion;

    @Before
    public void setUp() {
        conexion = ConexionBD.getInstancia();
    }

    @Test
    public void testConexion() {
        Connection conn = conexion.getConexion();
        Assert.assertNotNull(conn);
    }

    @After
    public void tearDown() {
        conexion = null;
    }
}

