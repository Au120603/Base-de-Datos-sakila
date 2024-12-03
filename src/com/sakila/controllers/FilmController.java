package com.sakila.controllers;

import com.sakila.data.Film;
import java.util.List;
import java.util.ArrayList;


public class FilmController {
    private Film filmModel;

    public FilmController() {
        this.filmModel = new Film(0, "", "");
    }

    public void addFilm(Film film) {
        filmModel.post(film);
    }

    public Film getFilm(int id) {
        return filmModel.get(id);
    }

    public List<Film> getAllFilms() {
        return filmModel.getAll();
    }

    public void updateFilm(Film film) {
        filmModel.put(film);
    }

    public void deleteFilm(int id) {
        filmModel.delete(id);
    }
}