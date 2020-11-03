/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;

import fr.ubx.poo.engine.Position;
import fr.ubx.poo.go.GameObject;
import fr.ubx.poo.go.decor.Stone;
import fr.ubx.poo.go.decor.Tree;
import fr.ubx.poo.go.personage.Player;
import java.util.function.Consumer;

public class Map {
    private final Tile[][] grid;
    private final int height;
    private final int width;
    private final Game game;

    public Map(Game game, int level, MapEntity[][] raw) {
        this.game = game;
        height = raw.length;
        width = raw[0].length;
        grid = new Tile[width][height];

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Position pos = new Position(level, x, y);
                GameObject go = processEntity(raw[y][x], pos);
                grid[x][y] = new Tile(go);
            }
        }
    }


    private GameObject processEntity(MapEntity entity, Position pos) {
        switch (entity) {
            case Stone:
                return new Stone(game, pos);
            case Tree:
                return new Tree(game, pos);
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

    public Tile get(int x, int y) {
        if (isInside(x,y))
            return grid[x][y];
        else
            return null;
    }

    public void forEach(Consumer<GameObject> fn) {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                grid[x][y].getGameObjects().forEach(fn);
            }
        }
    }

    public boolean isInside(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
