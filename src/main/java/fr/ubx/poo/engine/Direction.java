/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.engine;

import java.util.Random;

public enum Direction {
    N(0), E(1), S(2), W(3);

    private static final Random randomGenerator = new Random();
    private final int code;

    Direction(int i) {
        this.code = i;
    }

    public static Direction random() {
        int i = randomGenerator.nextInt(4);
        return values()[i];
    }

    public int getCode() {
        return code;
    }

    public Position next(Position pos, int delta) {
        int x = pos.getX();
        int y = pos.getY();
        switch (this) {
            case N:
                return new Position(pos.getLevel(), x, y - delta);
            case E:
                return new Position(pos.getLevel(), x + delta, y);
            case S:
                return new Position(pos.getLevel(), x, y + delta);
            case W:
                return new Position(pos.getLevel(), x - delta, y);
        }
        throw new RuntimeException("Invalid enum member for Position.next: " + this);
    }

    public Position next(Position pos) {
        return next(pos, 1);
    }
}