/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go.character;


import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.decor.Box;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.decor.Stone;
import fr.ubx.poo.model.decor.Tree;
import fr.ubx.poo.model.go.GameObject;
import fr.ubx.poo.game.Game;

public class Player extends GameObject implements Movable {

    private boolean alive = true;
    Direction direction;
    private boolean moveRequested = false;
    private int lives = 1;
    private boolean winner;

    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
        this.lives = game.getInitPlayerLives();
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

    @Override
    public boolean canMove(Direction direction) {
    	Position nextPos = direction.nextPosition(getPosition());
    	
    	Decor d=game.getWorld().get(nextPos);
    	if( !(nextPos.inside(game.getWorld().dimension)) || d instanceof Stone || d instanceof Tree) {
    		return false;
    	}else {
    		if(d instanceof Box) {
    			Position nextpos2= direction.nextPosition(nextPos);
    			return ((nextpos2.inside(game.getWorld().dimension)) && game.getWorld().isEmpty(nextpos2));
    		}
    	}
    	/*if(d  instanceof Monster) {
    		this.lives-=1;
    	}*/
    	//grid.get(position);
        return true;
        
    }

    public void doMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);
        Decor d=game.getWorld().get(nextPos);
        if(d instanceof Box) {
        	game.getWorld().clear(nextPos);
        	game.getWorld().set(direction.nextPosition(nextPos), new Box());
        	game.getWorld().setWorldchanged(true);
        }
        if(nextPos==get)
        
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
