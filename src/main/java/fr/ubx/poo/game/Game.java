/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;


import fr.ubx.poo.model.go.character.Monster;
import fr.ubx.poo.model.go.character.Player;

public class Game {

    private final World world;
    private final Player player;
    private List<Monster> Monsters = new ArrayList<>();
    private final String worldPath;
    public int initPlayerLives;
    private String prefix;
    private int levels;
    private int Level=0;
    private boolean LevelChanged=false;
    
    public int getInitPlayerLives() {
        return initPlayerLives;
    }
    
    public Game(String worldPath) {    
        this.worldPath = worldPath;
        loadConfig(worldPath);
        world = new World(loadWorld(worldPath), levels);
        Position positionPlayer = null;
        Monsters=world.findMonsters(this);
        try {
            positionPlayer = world.findPlayer();
            player = new Player(this, positionPlayer);
        } catch (PositionNotFoundException e) {
            System.err.println("Position not found : " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }



    private void loadConfig(String path) {
        try (InputStream input = new FileInputStream(new File(path, "config.properties"))) {
            Properties prop = new Properties();            
            // load the configuration file
            prop.load(input);
        	prefix = prop.getProperty("prefix");
        	levels = Integer.parseInt(prop.getProperty("levels"));
            initPlayerLives = Integer.parseInt(prop.getProperty("lives", "3"));
        } catch (IOException ex) {
            System.err.println("Error loading configuration");
        }
    }

    private List<WorldEntity [][]> loadWorld(String path){
    	List<WorldEntity [][]> World=new ArrayList<>();
    	for(int i=1; i<levels+1; i++) {
    		World.add(loadLevel(path, i));
    	}
    	return World;
    }
    
    
    private WorldEntity [][] loadLevel(String path, int level) {
    	WorldEntity[][] newLevel = null;	    
    	try {
    		// load the level file
    		BufferedReader fd = new BufferedReader(new FileReader(path+"/" +prefix+level+".txt")); 
    	    String line;
    	    int j=0;
    	    fd.mark(1000);
    	    int y = (int) fd.lines().count();
    	    fd.reset();
    	    line = fd.readLine();
    	    fd.reset();
    	    int x = line.length();
    	    newLevel = new WorldEntity[y][x];
    	    
    		while ((line = fd.readLine()) != null) {
    			for (int i=0; i<x; i++) {    				
    				Optional<WorldEntity> e= WorldEntity.fromCode(line.charAt(i));
    				if(e.isPresent()) {
    					newLevel[j][i]=e.get();
    				}else {
    					//retourner erreur
    					//throw new IOExecption("soldat inconnue dans le bataillon");
    				}
    		    	System.out.print(newLevel[j][i]);
    			}
    			System.out.println();
    			j++;
    		}
    		fd.close();
        } catch (IOException ex) {
        	System.err.println(ex);
        	System.err.println("Error loading level");
        }
		return newLevel;   	
    	
    }
    
    
    
    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return this.player;
    }
    
    public List<Monster> getMonsters() {
        return this.Monsters;
    }

	public int getLevel() {
		return Level;
	}

	public void setLevel(int i) {
		Level=i;
		this.getWorld().setCurrent_lvl(i);
	}
		
	public void setLevelChanged(boolean bool) {
		this.LevelChanged=bool;
		
	}
	
	public boolean isLevelChanged() {
		return LevelChanged;
	}

}
