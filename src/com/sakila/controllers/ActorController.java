package com.sakila.controllers;

import com.sakila.data.Actor;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

public class ActorController {
    private Actor actorModel;

    public ActorController() {
        this.actorModel = new Actor(0, "", "");
    }

    public void addActor(Actor actor) {
        actorModel.post(actor);
    }

    public Actor getActor(int id) {
        return actorModel.get(id);
    }

    public List<Actor> getAllActors() {
        return actorModel.getAll();
    }

    public void updateActor(Actor actor) {
        actorModel.put(actor);
    }

    public void deleteActor(int id) {
        actorModel.delete(id);
    }
}