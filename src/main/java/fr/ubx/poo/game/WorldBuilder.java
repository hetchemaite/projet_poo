package fr.ubx.poo.game;

import fr.ubx.poo.engine.Position;
import fr.ubx.poo.entity.decor.Decor;
import fr.ubx.poo.entity.decor.Stone;
import fr.ubx.poo.entity.decor.Tree;

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
                Decor decor = processEntity(raw[y][x], pos);
                if (decor != null)
                    builder.grid.put(pos, decor);
            }
        }
        return builder.grid;
    }

    private static Decor processEntity(WorldEntity entity, Position pos) {
        switch (entity) {
            case Stone:
                return new Stone();
            case Tree:
                return new Tree();
            default:
                return null;
        }
    }
}
