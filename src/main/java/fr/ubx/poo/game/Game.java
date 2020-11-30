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
import java.util.Properties;

import fr.ubx.poo.model.go.character.Monster;
import fr.ubx.poo.model.go.character.Player;

public class Game {

    private final World world;
    private final Player player;
    private Monster[] Monsters;
    private int nbMonsters;
    private final String worldPath;
    public int initPlayerLives;
    private String prefix;
    private int level=2;

    public Game(String worldPath) {
        
        this.worldPath = worldPath;
        loadConfig(worldPath);
        world = new World(loadWorld(worldPath));

        Position positionPlayer = null;
        nbMonsters=world.NbMonsters();
        Position[] MonstersPos=world.findMonsters(nbMonsters);
        Monsters=new Monster[nbMonsters];
        for(int i=0; i<nbMonsters; i++) {
        	Monsters[i]=new Monster(this, MonstersPos[i]);
        }
        try {
            positionPlayer = world.findPlayer();
            player = new Player(this, positionPlayer);
        } catch (PositionNotFoundException e) {
            System.err.println("Position not found : " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public int getInitPlayerLives() {
        return initPlayerLives;
    }

    private void loadConfig(String path) {
        try (InputStream input = new FileInputStream(new File(path, "config.properties"))) {
            Properties prop = new Properties();            
            // load the configuration file
            prop.load(input);
        	prefix = prop.getProperty("prefix");
            initPlayerLives = Integer.parseInt(prop.getProperty("lives", "3"));
        } catch (IOException ex) {
            System.err.println("Error loading configuration");
        }
    }

    private WorldEntity [][] loadWorld(String path) {
    	WorldEntity[][] newworld = null;	    
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
    	    newworld = new WorldEntity[y][x];
    	    
    		while ((line = fd.readLine()) != null) {
    			for (int i=0; i<x; i++) {    				
    				if (line.charAt(i)=='T') newworld[j][i]=WorldEntity.Tree;
    				if (line.charAt(i)=='_') newworld[j][i]=WorldEntity.Empty;
    				if (line.charAt(i)=='S') newworld[j][i]=WorldEntity.Stone;
    				if (line.charAt(i)=='+') newworld[j][i]=WorldEntity.BombNumberInc;
    				if (line.charAt(i)=='-') newworld[j][i]=WorldEntity.BombNumberDec;
    				if (line.charAt(i)=='>') newworld[j][i]=WorldEntity.BombRangeInc;
    				if (line.charAt(i)=='<') newworld[j][i]=WorldEntity.BombRangeDec;
    				if (line.charAt(i)=='B') newworld[j][i]=WorldEntity.Box;
    				if (line.charAt(i)=='M') newworld[j][i]=WorldEntity.Monster;
    				if (line.charAt(i)=='n') newworld[j][i]=WorldEntity.DoorNextClosed;
    				if (line.charAt(i)=='N') newworld[j][i]=WorldEntity.DoorNextOpened;
    				if (line.charAt(i)=='V') newworld[j][i]=WorldEntity.DoorPrevOpened;
    				if (line.charAt(i)=='P') newworld[j][i]=WorldEntity.Player;
    				if (line.charAt(i)=='W') newworld[j][i]=WorldEntity.Princess;
    				if (line.charAt(i)=='K') newworld[j][i]=WorldEntity.Key;
    				if (line.charAt(i)=='H') newworld[j][i]=WorldEntity.Heart;
    				
    		    	System.out.print(newworld[j][i]);
    			}
    			System.out.println();
    			j++;
    		}
    		fd.close();
        } catch (IOException ex) {
        	System.err.println(ex);
        	System.err.println("Error loading world");
        }
		return newworld;   	
    	
    }
    
    
    
    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return this.player;
    }
    public Monster[] getMonsters() {
        return this.Monsters;
    }
    public int getnbMonsters() {
        return this.nbMonsters;
    }

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
