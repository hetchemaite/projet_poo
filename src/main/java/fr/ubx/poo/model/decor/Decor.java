/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.decor;

import fr.ubx.poo.model.Entity;

public class Decor extends Entity {
    @Override
    public boolean Walkable() {
        return false;
    }
}
