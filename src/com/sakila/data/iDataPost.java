package com.sakila.data;
import java.sql.*;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

public interface iDataPost<T> {
    void post(T entity);
    T get(int id);
    List<T> getAll();
    void put(T entity);
    void delete(int id);
}