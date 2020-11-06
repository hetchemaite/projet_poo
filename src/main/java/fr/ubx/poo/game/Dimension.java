package fr.ubx.poo.game;

import fr.ubx.poo.engine.Position;

public class Dimension {
    public final int height;
    public final int width;

    public Dimension(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public boolean isInside(Position position) {
        return position.x > 0 && position.x < width && position.y > 0 && position.y < height;
    }
}
