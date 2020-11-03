/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.go.decor;

import fr.ubx.poo.engine.Position;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.go.GameObject;

public class Decor extends GameObject {
    public Decor(Game game, Position position) {
        super(game, position);
    }

    @Override
    public void update(long now) {
    }

}
