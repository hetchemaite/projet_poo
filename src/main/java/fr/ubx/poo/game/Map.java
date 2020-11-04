/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;

import fr.ubx.poo.engine.Position;
import fr.ubx.poo.entity.decor.Decor;
import fr.ubx.poo.entity.decor.Stone;
import fr.ubx.poo.entity.decor.Tree;

import java.util.Collection;
import java.util.Hashtable;
import java.util.function.BiConsumer;

public class Map {
    private final Hashtable<Position, Decor> grid = new Hashtable<>();
    private final int height;
    private final int width;

    public Map(MapEntity[][] raw) {
        height = raw.length;
        width = raw[0].length;

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Position pos = new Position(x, y);
                Decor decor = processEntity(raw[y][x], pos);
                if (decor != null)
                    grid.put(pos, decor);
            }
        }
    }

    private Decor processEntity(MapEntity entity, Position pos) {
        switch (entity) {
            case Stone:
                return new Stone();
            case Tree:
                return new Tree();
            default:
                return null;
        }
    }

    public Position findPlayer(MapEntity[][] raw) {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if (raw[y][x] == MapEntity.Player) {
                    return new Position(x, y);
                }
            }
        }
        return null;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Decor get(Position position) {
        return grid.get(position);
    }

    public void set(Position position, Decor decor) {
        grid.put(position, decor);
    }

    public void clear(Position position) {
        grid.remove(position);
    }

    public void forEach(BiConsumer<Position, Decor> fn) {
        grid.forEach(fn);
    }

    public Collection<Decor> values() {
        return grid.values();
    }

    public boolean isInside(Position position) {
        return true; // to update
    }

    public boolean isEmpty(Position position) {
        return grid.get(position) == null;
    }
}
