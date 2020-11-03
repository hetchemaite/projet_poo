/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.engine;

import java.util.Objects;

public class Position {
    private final int level;
    private final int x;
    private final int y;
    public Position(int level, int x, int y) {
        this.level = level;
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this.level = position.level;
        this.x = position.x;
        this.y = position.y;
    }

    public int getLevel() {
        return level;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return level == position.level &&
                x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "level=" + level +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
