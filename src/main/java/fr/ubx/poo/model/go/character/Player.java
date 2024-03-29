/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go.character;


import java.util.Timer;
import java.util.TimerTask;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.decor.*;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.Bomb;
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
    private boolean invincible=false;

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
    public boolean isInvicible() {
    	return invincible;
    }
    public void setInvicible(boolean bool) {
    	invincible=bool;
    }

    @Override
    public boolean canMove(Direction direction) {
    	Position nextPos = direction.nextPosition(getPosition());
    	
    	Decor d=game.getWorld().get(nextPos);
    	if(!nextPos.inside(game.getWorld().getDimension()))
    		return false;
    	if(d!=null) {
    		String obj=d.toString();
        	if(obj.equals("Stone") || obj.equals("Tree") || obj.equals("DoorNextClosed")){
        		return false;
        	}else {
        		if(obj.equals("Box")) {
        			Position nextpos2= direction.nextPosition(nextPos);
        			
        			for(Monster  m : game.getMonsters()) {
        				if(m.getPosition().equals(nextpos2)) {
        					return false;
        				}
        			}
        			return ((nextpos2.inside(game.getWorld().getDimension())) && game.getWorld().isEmpty(nextpos2));
        		}
        	}
    	}
		return true;       
    }
    
    public void clear(Position nextPos) {
    	game.getWorld().clear(nextPos);
    	game.getWorld().setWorldchanged(true);
    }
    

    public void doMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);
        Decor d=game.getWorld().get(nextPos);
        if(d!=null) {
        	String obj=d.toString();
	        if( obj.equals("Box")) {
	        	clear(nextPos);
	        	game.getWorld().set(direction.nextPosition(nextPos), new Box());
	        	game.getWorld().setWorldchanged(true);
	        }  
	        if(obj.equals("Heart")) {
	        	clear(nextPos);
	        	setLives(lives+1);
	        }
	        if(obj.equals("Key")) {
	        	clear(nextPos);
	        	setKeys(keys+1);
	        }
	        if(obj.equals("BombNumberDec")) {
	        	if (getBombs()>1) {
	        		clear(nextPos);
	            	setBombs(bombs-1);
	        	}        	
	        }
	        if(obj.equals("BombNumberInc")) {
	        	clear(nextPos);
	        	setBombs(bombs+1);
	        }
	        if(obj.equals("BombRangeDec")) {
	        	if (getRangebomb()>1) {
	        		clear(nextPos);
	            	setRangebomb(rangebomb-1);        		
	        	}        	
	        }
	        if(obj.equals("BombRangeInc")) {
	        	clear(nextPos);
	        	setRangebomb(rangebomb+1);
	        }
	        if(obj.equals("Princess")) {
	        	this.winner=true;
	        }
	        if(obj.equals("DoorNextOpened")) {
	        	game.setLevelChanged(true);
	        	game.getWorld().setNextLevel(true);
	        	game.setLevel(game.getLevel()+1);
	        	
	        }
	        if(obj.equals("DoorPrevOpened")) {
	        	game.setLevelChanged(true);
	        	game.getWorld().setPrevLevel(true);
	        	game.setLevel(game.getLevel()-1);
	        	
	        }
        }
        isOnMonster();
    }
    
   public void OpenDoor() {
	   Position nextPos = direction.nextPosition(getPosition());
       Decor d=game.getWorld().get(nextPos);
	   if(d!=null) {
		   String obj=d.toString();
		   if(obj.equals("DoorNextClosed")) {
	        	if (this.keys>0) {
	        		clear(nextPos);          	
	            	game.getWorld().set(nextPos, new DoorNextOpened());
	            	game.getWorld().setWorldchanged(true);
	            	this.keys--;
	        	}
		   }
	   }
   }
   public void isOnMonster() {
	  for(Monster m : game.getMonsters()) {
		  if(GetHit(m.getPosition())) {
			  break;
		  }
	  }
   }
   
   public boolean GetHit(Position pos) {
    	if(pos.equals(this.getPosition()) && !isInvicible()) {
    		this.lives--;
    		if(lives==0)
    			this.alive=false;
    		//invincibilit� temporaire
			setInvicible(true);
			TimerTask invincibility=new TimerTask() {
				public void run() {
				    setInvicible(false);
				}
			};
			Timer t = new Timer();
			t.schedule(invincibility,1500);
			
    		return true;
        }
    	return false;
    }
    
    public Bomb putBomb(long now) {
    	if(getBombs()>0) {
    		setBombs(getBombs()-1);
    		return new Bomb(game,game.getPlayer().getPosition(), now);
    	}else {
    		return null;
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
