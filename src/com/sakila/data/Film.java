package com.sakila.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

public class Film extends DataContext<Film> {
    private int filmId;
    private String title;
    private String description;
    public Film(int filmId, String title, String description) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
    }
    public Film() {
    }

    public int getFilmId() { return filmId; }
    public void setFilmId(int filmId) { this.filmId = filmId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public void post(Film entity) {
        String query = "INSERT INTO film (title, description) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, entity.getTitle());
            stmt.setString(2, entity.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Film get(int id) {
        String query = "SELECT * FROM film WHERE film_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Film(rs.getInt("film_id"), rs.getString("title"), rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    private List<Film> getFilmsFromDatabase() {
        List<Film> films = new ArrayList<>();
        try (Connection connection = connect()) {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'film';");
                if (!resultSet.next()) {
                    System.err.println("La tabla 'film' no existe en la base de datos.");
                    return films;
                }
    
                resultSet = statement.executeQuery("SELECT * FROM film");
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
    

    @Override
    public void put(Film entity) {
        String query = "UPDATE film SET title = ?, description = ? WHERE film_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, entity.getTitle());
            stmt.setString(2, entity.getDescription());
            stmt.setInt(3, entity.getFilmId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "UPDATE film SET active = 0 WHERE film_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
