/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;

import java.util.Objects;

public class Position {
    public final int x;
    public final int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean inside(Dimension d) {
        return x >= 0 && x < d.width && y >= 0 && y < d.height;
    }
    
    public Direction[] GetDirection(Position pos) {
    	int xDistance=(pos.x-this.x);
    	int yDistance=(pos.y-this.y);
    	boolean xDistanceLonger=false;
    	Direction a=Direction.values()[0];
    	Direction b=Direction.values()[0];
    	Direction c=Direction.values()[0];
    	Direction d=Direction.values()[0];
    	if(Math.abs(xDistance)>Math.abs(yDistance)) {
    		xDistanceLonger=true;
    	}
    	if(xDistanceLonger) {
    		if(xDistance>0) {
        		a=Direction.E;
        		d=Direction.W; 
        	}
    		else{
        		a=Direction.W;
        		d=Direction.E;
        	}
    		if(yDistance>0) {
        		b=Direction.S;
        		c=Direction.N;
        	}
    		else {
        		b=Direction.N;
        		c=Direction.S;
        	}
    	}else {
    		if(yDistance>0) {
        		a=Direction.S;
        		d=Direction.N;
        	}
    		else {
        		a=Direction.N;
        		d=Direction.S;
        	}
    		if(xDistance>0) {
        		b=Direction.E;
        		c=Direction.W;
        	}
    		else {
        		b=Direction.W;
        		c=Direction.E;
        	}
        	
    	}
    	Direction[] OrdreDirection={a,b,c,d};
		return OrdreDirection;
    }
}
