package com.sakila.data;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Actor extends DataContext<Actor> {
    private int actorId;
    private String firstName;
    private String lastName;

    public Actor(int actorId, String firstName, String lastName) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters y Setters

    public int getActorId() { return actorId; }
    public void setActorId(int actorId) { this.actorId = actorId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public void post(Actor entity) {
        String query = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Actor get(int id) {
        String query = "SELECT * FROM actor WHERE actor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Actor> getAll() {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT * FROM actor";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                actors.add(new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    @Override
    public void put(Actor entity) {
        String query = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.setInt(3, entity.getActorId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "UPDATE actor SET active = 0 WHERE actor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
