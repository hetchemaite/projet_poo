/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;



import fr.ubx.poo.engine.Position;
import fr.ubx.poo.entity.go.personage.Player;

import static fr.ubx.poo.game.MapEntity.*;

public class Game {
    public static final int PLAYER_LIVES = 3;


    public static final MapEntity[][] mapEntities =
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
            };

    private final Map map;
    private Player player;

    public Game() {
        map = new Map(mapEntities);
        Position positionPlayer = map.findPlayer(mapEntities);
        if (positionPlayer == null)
            throw new RuntimeException("No player found");
        player = new Player(this, positionPlayer);
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return this.player;
    }


}
