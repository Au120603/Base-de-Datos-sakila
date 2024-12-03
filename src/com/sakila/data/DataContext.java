package com.sakila.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DataContext<T> implements iDataPost<T> {
    private static final String URL = "jdbc:mysql://localhost:3306/sakila";
    private static final String USER = "root";
    private static final String PASSWORD = "aap271104...";
    protected Connection connection;

    public DataContext() {
        try {
            if (connection == null || connection.isClosed()) {
                this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexion a la base de datos establecida con exito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            System.err.println("Estado del codigo de error: " + e.getSQLState());
            System.err.println("Error de MySQL: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    protected void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}