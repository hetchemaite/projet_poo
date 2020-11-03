/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;

import java.util.Arrays;
import java.util.Optional;

public enum MapEntity {
    Empty('_'),
    Box('B'),
    Heart('H'),
    Key('K'),
    Monster('M'),
    DoorPrevOpened('V'),
    DoorNextOpened('N'),
    DoorNextClosed('n'),
    Player('P'),
    Stone('S'),
    Tree('T'),
    Princess('W'),
    BombRangeInc('>'),
    BombRangeDec('<'),
    BombNumberInc('+'),
    BombNumberDec('-')
        ;


    private char getCode() {
        return code;
    }

    private final char code;

    MapEntity(char code) {
        this.code = code;
    }

    public static Optional<MapEntity> fromCode(char code) {
        return Arrays.stream(values())
                .filter(c -> c.getCode() == code)
                .findFirst();
    }

    @Override
    public String toString() {
        return ""+code;
    }
}
