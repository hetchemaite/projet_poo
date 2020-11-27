package fr.ubx.poo.game;

import fr.ubx.poo.model.decor.BombNumberDec;
import fr.ubx.poo.model.decor.BombNumberInc;
import fr.ubx.poo.model.decor.BombRangeDec;
import fr.ubx.poo.model.decor.BombRangeInc;
import fr.ubx.poo.model.decor.Box;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.decor.DoorNextClosed;
import fr.ubx.poo.model.decor.DoorNextOpened;
import fr.ubx.poo.model.decor.DoorPrevOpened;
import fr.ubx.poo.model.decor.Heart;
import fr.ubx.poo.model.decor.Key;
import fr.ubx.poo.model.decor.Princess;
import fr.ubx.poo.model.decor.Stone;
import fr.ubx.poo.model.decor.Tree;
import fr.ubx.poo.model.decor.Monster;

import java.util.Hashtable;
import java.util.Map;

public class WorldBuilder {
    private final Map<Position, Decor> grid = new Hashtable<>();

    private WorldBuilder() {
    }

    public static Map<Position, Decor> build(WorldEntity[][] raw, Dimension dimension) {
        WorldBuilder builder = new WorldBuilder();
        for (int x = 0; x < dimension.width; x++) {
            for (int y = 0; y < dimension.height; y++) {
                Position pos = new Position(x, y);
                Decor decor = processEntity(raw[y][x]);
                if (decor != null)
                    builder.grid.put(pos, decor);
            }
        }
        return builder.grid;
    }

    private static Decor processEntity(WorldEntity entity) {
        switch (entity) {
            case Stone:
                return new Stone();
            case Tree:
                return new Tree();
            case Box:
            	return new Box();
            case Heart:
            	return new Heart();
            case Key:
            	return new Key();
            case BombRangeInc:
            	return new BombRangeInc();
            case BombRangeDec:
            	return new BombRangeDec();
            case BombNumberInc:
            	return new BombNumberInc();
            case BombNumberDec:
            	return new BombNumberDec();
            case Princess:
            	return new Princess();
            case DoorNextClosed:
            	return new DoorNextClosed();
            case DoorNextOpened:
            	return new DoorNextOpened();
            case DoorPrevOpened:
            	return new DoorPrevOpened();
            case Monster:
            	return new Monster();
            default:
                return null;
        }
    }
}
