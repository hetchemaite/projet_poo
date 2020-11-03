/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.go.personage;

import fr.ubx.poo.engine.Direction;
import fr.ubx.poo.engine.Position;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Tile;
import fr.ubx.poo.go.GameObject;
import fr.ubx.poo.go.Movable;

public class Player extends GameObject implements Movable {

    Direction direction;
    private boolean moveRequested = false;
    private int lives = 1;

    private boolean alive = true;
    private boolean winner;

    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
        this.lives = Game.PLAYER_LIVES;
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
        Tile tile = game.getTile(direction.next(getPosition()));
        if (tile == null) {
            return false;
        }
        if (tile.isEmpty()) {
            return true;
        }
        return true;
    }

    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    private void decLives() {
        if (lives > 1) {
            lives--;
            injury();
        } else {
            kill();
        }
    }


    public boolean isWinner() {
        return winner;
    }

    public void win() {
        winner = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
    }

    protected void injury() {
    }

    public void doMove(Direction direction) {
        Position nextPos = direction.next(getPosition());
        game.getTile(getPosition()).remove(this);
        game.getTile(nextPos).add(this);
        setPosition(nextPos);
    }

}
