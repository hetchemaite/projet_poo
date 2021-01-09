/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;

import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.go.character.Monster;
import fr.ubx.poo.view.sprite.Sprite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class World {
	
    private final List<WorldEntity[][]> raw = new ArrayList<>();
    public final List<Dimension> dimension = new ArrayList<>();
    private final List<Map<Position, Decor>> grid = new ArrayList<>();
    private boolean worldchanged;
    private int current_lvl=0;
    private boolean PrevLevel=false;
    private boolean NextLevel=false;
    
    
    public World(List<WorldEntity[][]> raw, int nblvl) {
        for(WorldEntity[][] r : raw){
        	this.raw.add(r);
        	Dimension dim=new Dimension(r.length, r[0].length);
        	dimension.add (new Dimension(r.length, r[0].length));
        	grid.add (  WorldBuilder.build(r, dim));
         }
    }

    public World(WorldEntity[][] mapentities) {
		// TODO Auto-generated constructor stub
	}

	public Position findPlayer() throws PositionNotFoundException {
    	Position pos=null;
		if(isPrevLevel()) {
			pos=PositionPlayer(WorldEntity.DoorNextClosed);
		}else {
			if(isNextLevel()) {
				pos=PositionPlayer(WorldEntity.DoorPrevOpened);
			}else {
				pos=PositionPlayer(WorldEntity.Player);
			}
		}
		setPrevLevel(false);
		setNextLevel(false);
		if(pos==null)
			throw new PositionNotFoundException("Player");
		return pos;
    }
    
	public Position PositionPlayer(WorldEntity e) {
		for (int x = 0; x < getDimension().width; x++) {
            for (int y = 0; y < getDimension().height; y++) {
            		if(raw.get(current_lvl)[y][x] == e ) {
            			System.out.println(x+y);
            			return new Position(x,y);
            	}
            }
        }
		return null;
	}
	
    
    //version liste
	public List<Monster> findMonsters(Game game) {
		List<Monster> Monsters = new ArrayList<>();
        for (int x = 0; x <  getDimension().width; x++) {
            for (int y = 0; y <  getDimension().height; y++) {
                if (raw.get(current_lvl)[y][x] == WorldEntity.Monster) {
                    //;
                    Monsters.add(new Monster(game, new Position(x,y)));
                }
            }
        }
        return Monsters;
	}
	
    

    public Decor get(Position position) {
        return grid.get(current_lvl).get(position);
    }
    
    public Decor get(Position position, int level) {
        return grid.get(level).get(position);
    }

    public void set(Position position, Decor decor) {
        grid.get(current_lvl).put(position, decor);
    }
    
	public void set(Position position, Decor decor, int level) {
		 grid.get(level).put(position, decor);
	}

    public void clear(Position position) {
        grid.get(current_lvl).remove(position);
    }
    
	public void clear(Position position, int level) {
		 grid.get(level).remove(position);
	}

    public void forEach(BiConsumer<Position, Decor> fn) {
        grid.get(current_lvl).forEach(fn);
    }

    public Collection<Decor> values() {
        return grid.get(current_lvl).values();
    }

    public boolean isInside(Position position) {
        return true; // to update
    }

    public boolean isEmpty(Position position) {
        return grid.get(current_lvl).get(position) == null;
    }

	public boolean isWorldchanged() {
		return worldchanged;
	}

	public void setWorldchanged(boolean worldchanged) {
		this.worldchanged = worldchanged;
	}

	public int getCurrent_lvl() {
		return current_lvl;
	}

	public void setCurrent_lvl(int current_lvl) {
		this.current_lvl = current_lvl;
	}

	public Dimension getDimension() {
		return dimension.get(getCurrent_lvl());
	}
	
	public void setPrevLevel(boolean bool) {
		this.PrevLevel=bool;
	}
	
	public boolean isPrevLevel() {
		return this.PrevLevel;
	}
	
	public void setNextLevel(boolean bool) {
		this.NextLevel=bool;
	}
	public boolean isNextLevel() {
		return this.NextLevel;
	}
	




	
}
