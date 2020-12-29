/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go.character;


import java.util.List;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.decor.*;
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
    private int bombs = 1;
    private int keys = 0;
    private int rangebomb = 1;
    private boolean winner;

    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
        this.lives = game.getInitPlayerLives();
    }

    public void setLives(int lives) {
    	this.lives = lives;
    }
    
    public int getLives() {
        return lives;
    }
    
    public boolean isWinner() {
        return winner;
    }

    public boolean isAlive() {
        return alive;
    }

	public int getBombs() {
		return bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}

	public int getKeys() {
		return keys;
	}

	public void setKeys(int keys) {
		this.keys = keys;
	}

	public int getRangebomb() {
		return rangebomb;
	}

	public void setRangebomb(int rangebomb) {
		this.rangebomb = rangebomb;
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
    	if( !(nextPos.inside(game.getWorld().dimension.get(game.getLevel()))) || d instanceof Stone || d instanceof Tree) {
    		return false;
    	}else {
    		if(d instanceof Box) {
    			Position nextpos2= direction.nextPosition(nextPos);
    			return ((nextpos2.inside(game.getWorld().dimension.get(game.getLevel()))) && game.getWorld().isEmpty(nextpos2));
    		}
    	}
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
        if(d instanceof Heart) {
        	game.getWorld().clear(nextPos);
        	game.getWorld().setWorldchanged(true);
        	setLives(lives+1);
        }
        if(d instanceof Key) {
        	game.getWorld().clear(nextPos);
        	game.getWorld().setWorldchanged(true);
        	setKeys(keys+1);
        }
        if(d instanceof BombNumberDec) {
        	if (getBombs()>0) {
        		game.getWorld().clear(nextPos);
            	game.getWorld().setWorldchanged(true);
            	setBombs(bombs-1);
        	}        	
        }
        if(d instanceof BombNumberInc) {
        	game.getWorld().clear(nextPos);
        	game.getWorld().setWorldchanged(true);
        	setBombs(bombs+1);
        }
        if(d instanceof BombRangeDec) {
        	if (getRangebomb()>1) {
        		game.getWorld().clear(nextPos);
            	game.getWorld().setWorldchanged(true);
            	setRangebomb(rangebomb-1);        		
        	}        	
        }
        if(d instanceof BombRangeInc) {
        	game.getWorld().clear(nextPos);
        	game.getWorld().setWorldchanged(true);
        	setRangebomb(rangebomb+1);
        }
        if(d instanceof Princess) {
        	this.winner=true;
        }
        if(d instanceof DoorNextClosed) {
        	if (this.keys>0) {
        		game.getWorld().clear(nextPos);            	
            	game.getWorld().set(nextPos, new DoorNextOpened());
            	game.getWorld().setWorldchanged(true);
            	this.keys--;
            	game.setlevelchanged(true);
            	game.setLevel(game.getLevel()+1);
        	}  
        }
        if(d instanceof DoorNextOpened) {
        	game.setlevelchanged(true);
        	game.setLevel(game.getLevel()+1);
        }
        if(d instanceof DoorPrevOpened) {
        	game.setLevel(game.getLevel()-1);
        }
        
        
        List<Monster> monsters=game.getMonsters();
        monsters.forEach(m -> MoveOnMonster(m.getPosition()));
        //Monster[]  monster = game.getMonsters();
        /*int nbMonster = game.getnbMonsters();
        for (int i=0; i<nbMonster; i++) {
    		if (monster[i].getPosition().equals(this.getPosition()) ) {    			
        		this.lives--;
        		if (lives==0) {
        			this.alive=false;
        		}        		
        	}
        }*/
        
    }
    
    public void MoveOnMonster(Position pos) {
    	if(pos.equals(this.getPosition())) {
    		this.lives--;
    		if(lives==0)
    			this.alive=false;
        }
    }

    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }

    

}
