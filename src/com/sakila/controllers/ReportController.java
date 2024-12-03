package com.sakila.controllers;

import com.sakila.data.Film;  
import com.sakila.reports.ReportGenerator;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportController {
    private ReportGenerator reportGenerator;

    public ReportController() {
        this.reportGenerator = new ReportGenerator();
    }

    public void generateFilmReport() {
        List<Film> films = getFilmsFromDatabase(); 
        reportGenerator.generateReport(films);  
    }

    private List<Film> getFilmsFromDatabase() {
        List<Film> films = new ArrayList<>();
        try {
            Connection connection = connect(); 
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM film");
                while (resultSet.next()) {
                    Film film = new Film(resultSet.getInt("film_id"), resultSet.getString("title"), resultSet.getString("description"));
                    films.add(film);
                }
            } else {
                System.err.println("Conexion no establecida.");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los datos: " + e.getMessage());
        }
        return films;
    }
}

