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
    //private final Map<Position, Decor> grid;
    //
    private final List<WorldEntity[][]> raw = new ArrayList<>();
    public final List<Dimension> dimension = new ArrayList<>();
    private final List<Map<Position, Decor>> grid = new ArrayList<>();
    //
    //private final WorldEntity[][] raw ;
    //public final Dimension dimension;
    private boolean worldchanged;
    private int current_lvl=0;
    
    //world(list<worldentity> listEntity,int nblvl)
    
    public World(List<WorldEntity[][]> raw, int nblvl) {
        //dimension = new Dimension(raw.length, raw[0].length);
        for(WorldEntity[][] r : raw){
        	this.raw.add(r);
        	Dimension dim=new Dimension(r.length, r[0].length);
        	dimension.add (new Dimension(r.length, r[0].length));
        	grid.add (  WorldBuilder.build(r, dim));
         }
        //raw.forEach(r->dimension.add ( new Dimension(r.length, r[0].length)));
        //raw.forEach(r->grid.add (  WorldBuilder.build(r, )));        
        //grid = WorldBuilder.build(raw, dimension);
    }

    public World(WorldEntity[][] mapentities) {
		// TODO Auto-generated constructor stub
	}

	public Position findPlayer() throws PositionNotFoundException {
    	for (int x = 0; x < dimension.get(current_lvl).width; x++) {
            for (int y = 0; y < dimension.get(current_lvl).height; y++) {
                if (raw.get(current_lvl)[y][x] == WorldEntity.Player || raw.get(current_lvl)[y][x] == WorldEntity.DoorPrevOpened) {
                    return new Position(x, y);
                }
            }
        }
        throw new PositionNotFoundException("Player");
    }
    
    /*public Position[] findMonsters(int i){
    	Position[] MonstersPos= new Position[i];
    	int j=0;
        for (int x = 0; x < dimension.width; x++) {
            for (int y = 0; y < dimension.height; y++) {
                if (raw[y][x] == WorldEntity.Monster) {
                    MonstersPos[j]=new Position(x,y);
                    j++;
                }
            }
        }
        return MonstersPos;
    }*/
    
    //version liste
	public List<Monster> findMonsters(Game game) {
		List<Monster> Monsters = new ArrayList<>();
        for (int x = 0; x < dimension.get(current_lvl).width; x++) {
            for (int y = 0; y < dimension.get(current_lvl).height; y++) {
                if (raw.get(current_lvl)[y][x] == WorldEntity.Monster) {
                    //;
                    Monsters.add(new Monster(game, new Position(x,y)));
                }
            }
        }
        return Monsters;
	}
	
    
    /*public int NbMonsters(){
    	int i=0;
        for (int x = 0; x < dimension.width; x++) {
            for (int y = 0; y < dimension.height; y++) {
                if (raw[y][x] == WorldEntity.Monster) {
                    i++;
                    System.out.println("Pouet\n");
                }
            }
        }
        return i;
    }*/

    public Decor get(Position position) {
        return grid.get(current_lvl).get(position);
    }

    public void set(Position position, Decor decor) {
        grid.get(current_lvl).put(position, decor);
    }

    public void clear(Position position) {
        grid.get(current_lvl).remove(position);
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


}
