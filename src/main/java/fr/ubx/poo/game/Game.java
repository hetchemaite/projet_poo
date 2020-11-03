/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;


import fr.ubx.poo.engine.Position;
import fr.ubx.poo.go.GameObject;
import fr.ubx.poo.go.personage.Player;

import java.util.HashMap;
import java.util.List;

import static fr.ubx.poo.game.MapEntity.*;

public class Game {
    public static final int PLAYER_LIVES = 3;


    public static final MapEntity[][][] mapEntities =
            {
                    {
                            {Stone, Empty, Heart, Empty, Empty, Empty, Empty, Empty, Empty, Empty, BombRangeDec, Empty},
                            {MapEntity.Player, Stone, Stone, Empty, Stone, Empty, Stone, Stone, Stone, Stone, Empty, Empty},
                            {Empty, Empty, Empty, Empty, Stone, Box, Stone, Empty, Empty, Stone, Empty, Empty},
                            {Empty, Empty, Empty, Empty, Stone, Box, Stone, Empty, Empty, Stone, Empty, Empty},
                            {Empty, Box, Empty, Empty, Stone, Stone, Stone, Empty, Empty, Empty, Empty, Empty},
                            {Empty, Empty, Empty, Empty, Empty, Empty, Empty, Key, Empty, Stone, Empty, Empty},
                            {Empty, Tree, Empty, Tree, Empty, Empty, Empty, Empty, Empty, Stone, Empty, Empty},
                            {Empty, Empty, Box, Tree, Empty, Empty, Empty, Empty, Empty, Stone, Empty, Empty},
                            {Empty, Tree, Tree, Tree, Empty, Empty, Empty, Empty, Empty, Stone, Empty, Empty},
                            {Empty, Empty, Empty, Empty, Empty, Empty, BombRangeInc, Empty, Empty, Empty, Empty, Empty},
                            {Stone, Stone, Stone, Empty, Stone, Empty, Box, Box, Stone, Stone, Box, Stone},
                            {Empty, DoorNextClosed, Empty, Empty, Empty, Empty, Empty, Empty, Monster, Empty, Empty, Empty},
                            {Empty, BombNumberDec, Empty, Empty, Empty, Empty, Empty, Empty, BombNumberInc, Empty, Empty, Princess}
                    },
                    {
                            {Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, BombRangeDec, Empty},
                            {Empty, Empty, Empty, Empty, Empty, Stone, Empty, Stone, Stone, Stone, Stone, Empty, Empty},
                            {Empty, Empty, Empty, Empty, Empty, Stone, Box, Stone, Empty, Empty, Stone, Empty, DoorPrevOpened},
                            {Empty, Empty, Empty, Empty, Empty, Stone, Box, Stone, Empty, Empty, Stone, Empty, Empty},
                            {Empty, Empty, Empty, Empty, Empty, Stone, Stone, Stone, Empty, Empty, Empty, Empty, Empty},
                            {Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Stone, Empty, Empty},
                            {Empty, Empty, Empty, Empty, Empty, Empty, Empty, BombRangeInc, Empty, Empty, Empty, Empty, Empty},
                            {Empty, Empty, Empty, Empty, Empty, Stone, Empty, Box, Box, Stone, Stone, Box, Stone},
                            {Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Monster, Empty, Empty, Empty},
                            {Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, BombNumberInc, Empty, Empty, Princess}
                    }
            };

    private final HashMap<Integer, Map> maps = new HashMap<>();
    private int currentLevel;
    // Direct reference to the player
    private Player player;
    public Game() {
        for (int level = 1; level <= mapEntities.length; level++) {
            maps.put(level, new Map(this, level, mapEntities[level - 1]));
        }
        postProcess();
    }

    // Go through the maps to find where is the player
    private void postProcess() {
        for (int level = 1; level < mapEntities.length; level++) {
            Map map = maps.get(level);
            for (int x = 0; x < map.getWidth(); x++)
                for (int y = 0; y < map.getHeight(); y++) {
                    List<GameObject> gos = map.get(x, y).getGameObjects();
                    {
                        GameObject go = gos.stream().findAny().orElse(null);
                        if (go instanceof Player) {
                            player = (Player) go;
                            currentLevel = level;
                            return;
                        }
                    }
                }
        }
    }

    public Map getMap(int level) {
        return maps.get(level);
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Tile getTile(Position position) {
        return maps.get(position.getLevel()).get(position.getX(), position.getY());
    }

}
