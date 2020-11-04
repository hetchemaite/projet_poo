/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.entity.go.personage;

import fr.ubx.poo.engine.Direction;
import fr.ubx.poo.engine.Position;
import fr.ubx.poo.entity.Movable;
import fr.ubx.poo.entity.go.GameObject;
import fr.ubx.poo.game.Game;

public class Player extends GameObject implements Movable {

    private final boolean alive = true;
    Direction direction;
    private boolean moveRequested = false;
    private int lives = 1;
    private boolean winner;

    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
        this.lives = Game.PLAYER_LIVES;
    }

    public int getLives() {
        return lives;
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

    public boolean canMove(Direction direction) {
        return true;
    }

    public void doMove(Direction direction) {
        Position nextPos = direction.next(getPosition());
        setPosition(nextPos);
    }

    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean isAlive() {
        return alive;
    }


}
