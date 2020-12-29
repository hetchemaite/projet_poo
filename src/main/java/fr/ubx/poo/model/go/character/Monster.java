/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go.character;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.decor.*;
import fr.ubx.poo.model.go.GameObject;
import fr.ubx.poo.game.Game;

public class Monster extends GameObject implements Movable {

    private boolean alive = true;
    Direction direction;
    private boolean moveRequested = false;

    public Monster(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
    }

    public Direction getDirection() {
        return direction;
    }

    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
        }
        moveRequested = true;
    }

    @Override
    public boolean canMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        Decor d=game.getWorld().get(nextPos);
        if( !(nextPos.inside(game.getWorld().dimension.get(game.getLevel()))) || d instanceof Stone || d instanceof Tree || d instanceof Box || d instanceof Key || d instanceof DoorPrevOpened || d instanceof  DoorNextOpened || d instanceof DoorNextClosed || d instanceof Princess ) {
            return false;
        }
        return true;
    }

    public void doMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);
    }
    public int i=0;
    public void update(long now) {
        i++;
        if(i==60) {
        	this.requestMove(Direction.random());
        	i=0;
        }
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    	//faire bouger al�atoirement
    }

    public boolean isAlive() {
        return alive;
    }

}
