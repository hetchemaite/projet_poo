/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go;

import fr.ubx.poo.engine.Position;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.model.Entity;

public abstract class GameObject extends Entity {
    protected final Game game;
    private Position position;


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public GameObject(Game game, Position position) {
        this.game = game;
        this.position = position;
    }
}
