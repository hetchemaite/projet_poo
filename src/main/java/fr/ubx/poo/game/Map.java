/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;

import fr.ubx.poo.engine.Position;
import fr.ubx.poo.entity.Entity;
import fr.ubx.poo.entity.decor.Stone;
import fr.ubx.poo.entity.decor.Tree;
import fr.ubx.poo.entity.go.personage.Player;

import java.util.Collection;
import java.util.Hashtable;
import java.util.function.BiConsumer;

public class Map {
    private final Hashtable<Position, Entity> grid = new Hashtable<>();
    private final int height;
    private final int width;
    private final Game game;

    public Map(Game game, MapEntity[][] raw) {
        this.game = game;
        height = raw.length;
        width = raw[0].length;

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Position pos = new Position(x, y);
                Entity entity = processEntity(raw[y][x], pos);
                if (entity != null)
                    grid.put(pos, entity);
            }
        }
    }

    private Entity processEntity(MapEntity entity, Position pos) {
        switch (entity) {
            case Stone:
                return new Stone();
            case Tree:
                return new Tree();
            case Player:
                return new Player(game, pos);
            default:
                return null;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Entity get(Position position) {
        return grid.get(position);
    }

    public void set(Position position, Entity go) {
        grid.put(position, go);
    }

    public void clear(Position position) {
        grid.remove(position);
    }

    public void forEach(BiConsumer<Position, Entity> fn) {
        grid.forEach(fn);
    }

    public Collection<Entity> values() {
        return grid.values();
    }

    public boolean isInside(Position position) {
        return true; // to update
    }

    public boolean isEmpty(Position position) {
        return grid.get(position) == null;
    }
}
