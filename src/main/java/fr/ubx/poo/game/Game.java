/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;


import fr.ubx.poo.engine.Position;
import fr.ubx.poo.model.go.personage.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static fr.ubx.poo.game.WorldEntity.*;

public class Game {
    public static final WorldEntity[][] mapEntities =
            {
                    {Stone, Empty, Heart, Empty, Empty, Empty, Empty, Empty, Empty, Empty, BombRangeDec, Empty},
                    {WorldEntity.Player, Stone, Stone, Empty, Stone, Empty, Stone, Stone, Stone, Stone, Empty, Empty},
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
            };
    private final World world;
    private final Player player;
    private final String worldPath;
    public int initPlayerLives;

    public Game(String worldPath) {
        world = new World(mapEntities);
        this.worldPath = worldPath;
        loadConfig(worldPath);
        Position positionPlayer = null;
        try {
            positionPlayer = world.findPlayer(mapEntities);
            player = new Player(this, positionPlayer);
        } catch (PositionNotFoundException e) {
            System.err.println("Position not found : " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public int getInitPlayerLives() {
        return initPlayerLives;
    }

    private void loadConfig(String path) {
        try (InputStream input = new FileInputStream(new File(path, "config.properties"))) {
            Properties prop = new Properties();
            // load the configuration file
            prop.load(input);
            initPlayerLives = Integer.parseInt(prop.getProperty("lives", "3"));
        } catch (IOException ex) {
            System.err.println("Error loading configuration");
        }
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return this.player;
    }


}
