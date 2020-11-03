/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.go;

import fr.ubx.poo.engine.Position;
import fr.ubx.poo.game.Game;

public abstract class GameObject {
    protected final Game game;
    private Position position;
    public GameObject(Game game, Position position) {
        this.game = game;
        this.position = position;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void update(long now);


}
