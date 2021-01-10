/*
 * Copyright (c) 2020. Laurent R√©veill√®re
 */

package fr.ubx.poo.model.go.character;

import java.util.Timer;
import java.util.TimerTask;

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
    private int lives = 1;
    Boolean Boss=false;
    private boolean invicible;
    Timer t=new Timer();
	
    
    public Monster(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
        if(game.getLevel()==3) {
        	lives=3;
        	Boss=true;
        }
        
        //timer pour les mouvement des monstres
        int time=1500-(500*(game.getLevel()-1));
        if(time < 500)
        	time=500;
        t.scheduleAtFixedRate(new TimerTask() {
        	public void run() {
        		if(game.getLevel()==0 || game.getLevel()==1) { //dÈplacement alÈatoire
        			requestMove(Direction.random());
        		}else { // mouvement dirigÈ vers le player et seulement des mouvement possibles
        			//On reÁoit une liste des directions qui rapproche le + du player puis on vÈrifie si on peut faire ces mouvement
        			Direction[] ListBetterDirection=getPosition().GetDirection(game.getPlayer().getPosition());
        			int i=0;
        			while(i!=4 && !canMove(ListBetterDirection[i])){
        				i++;
        			}
        			if(i==4) {
        				requestMove(Direction.random());	
        			}else {
        				requestMove(ListBetterDirection[i]);        				
        			}
        			
        		}
            }
        }, 1000,time);
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
        if(!(nextPos.inside(game.getWorld().getDimension())))
        	return false;
        if(d!=null) {
        	String obj=d.toString();
        	 if(obj.equals("Box") || obj.equals("Stone") || obj.equals("Tree") || obj.equals("DoorNextClosed") || obj.equals("DoorNextOpened") || obj.equals("DoorPrevOpened") || obj.equals("Princess")) {
                 return false;
             }
        }
        for(Monster m: game.getMonsters()) {
        	if( m.getPosition().equals(nextPos))
        		return false;
        }
       
        return true;
    }

    public void doMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);
        game.getPlayer().GetHit(nextPos);
    }
    
    
    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
            }
        }
        moveRequested = false;
    }
    
    public void GetHit(Position pos) {
    	if(pos.equals(this.getPosition())) {
    		this.lives--;
    		if(lives==0)
        		Kill();
    		//invincibilitÈ temporaire
			setInvicible(true);
			TimerTask invincibility=new TimerTask() {
				public void run() {
				    setInvicible(false);
				}
			};
			Timer t = new Timer();
			t.schedule(invincibility,1500);
    	}
    }
    
   

	public void Kill2() {
		if(isBoss()) {
	    	this.alive=false;
	    	t.cancel();
		}
		else{
			Kill();
		}
    }
	public void Kill() {
    	if(isBoss()) {
    		// si c'est un boss il drop un clÈ dans 775 millisec
			TimerTask dropKey=new TimerTask() {
			    public void run() {
			    		game.getWorld().set(getPosition(), new Key());
			    		game.getWorld().setWorldchanged(true);
			    }
			};
			Timer d = new Timer();
			d.schedule(dropKey,775);
    	}
    	this.alive=false;
    	t.cancel();
	}
    public boolean isAlive() {
        return alive;
    }
    
    public void setLives(int lives) {
    	this.lives=lives;
    }
    public int getLives() {
    	return this.lives;
    }
    
    public boolean isBoss() {
    	return this.Boss;
    }

	public boolean isInvicible() {
		return this.invicible;
	}
    
    private void setInvicible(boolean b) {
    	this.invicible=b;
		
	}

}
