/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.entity;

import fr.ubx.poo.engine.Direction;

public interface Movable {
    void doMove(Direction direction);
}
