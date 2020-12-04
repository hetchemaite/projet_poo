/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;

import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.view.sprite.Sprite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class World {
    private final Map<Position, Decor> grid;
    //private final List<WorldEntity[][]> raw = new ArrayList<>();
    //private final List<Dimension> dimension = new ArrayList<>();
    //private final List<Map<Position, Decor>> grid = new ArrayList<>();
    private final WorldEntity[][] raw ;
    public final Dimension dimension;
    private boolean worldchanged;
    

    public World(WorldEntity[][] raw) {
        this.raw = raw;
        dimension = new Dimension(raw.length, raw[0].length);
        //raw.forEach(r->dimension.add ( new Dimension(r.length, r[0].length)));
        //raw.forEach(r->grid.add ( new WorldBuilder.built(r.length, r[0].length)));        
        grid = WorldBuilder.build(raw, dimension);
    }

    public Position findPlayer() throws PositionNotFoundException {
        for (int x = 0; x < dimension.width; x++) {
            for (int y = 0; y < dimension.height; y++) {
                if (raw[y][x] == WorldEntity.Player || raw[y][x] == WorldEntity.DoorPrevOpened) {
                    return new Position(x, y);
                }
            }
        }
        throw new PositionNotFoundException("Player");
    }
    
    public Position[] findMonsters(int i){
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
    }
    
    public int NbMonsters(){
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
    }

    public Decor get(Position position) {
        return grid.get(position);
    }

    public void set(Position position, Decor decor) {
        grid.put(position, decor);
    }

    public void clear(Position position) {
        grid.remove(position);
    }

    public void forEach(BiConsumer<Position, Decor> fn) {
        grid.forEach(fn);
    }

    public Collection<Decor> values() {
        return grid.values();
    }

    public boolean isInside(Position position) {
        return true; // to update
    }

    public boolean isEmpty(Position position) {
        return grid.get(position) == null;
    }

	public boolean isWorldchanged() {
		return worldchanged;
	}

	public void setWorldchanged(boolean worldchanged) {
		this.worldchanged = worldchanged;
	}
}
