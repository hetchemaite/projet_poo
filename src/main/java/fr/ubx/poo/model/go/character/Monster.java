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
import fr.ubx.poo.model.go.GameObject;
import fr.ubx.poo.game.Game;

public class Monster extends GameObject implements Movable {

    private boolean alive = true;
    Direction direction;
    private boolean moveRequested = false;
    Timer t=new Timer();

    public Monster(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
        t.scheduleAtFixedRate(new TimerTask() {
        	public void run() {
        		if(game.getLevel()==0) {
        			requestMove(Direction.random());
        		}else {
        			
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
        }, 1000,1500-(500*(game.getLevel()-1)));
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
    	if(pos.equals(this.getPosition()))
    		Kill();
    }
    
    public void Kill() {
    	this.alive=false;
    	t.cancel();
    }
    public boolean isAlive() {
        return alive;
    }
    
    

}
