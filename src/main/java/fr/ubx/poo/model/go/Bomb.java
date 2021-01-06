package fr.ubx.poo.model.go;

import java.util.Timer;
import java.util.TimerTask;

import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;

public class Bomb extends GameObject {
	int state;
	int range;
	Timer t;
	
	public Bomb(Game game, Position position, long now) {
		super(game, position);
		state=4;
		TimerTask explose=new TimerTask() {
		    public void run() {
		    	state--;
		    }
		};
		t = new Timer();
		t.scheduleAtFixedRate(explose,1000,1000);
		range=game.getPlayer().getRangebomb();
	}

	
	public void update() {
		if(state<=0) {
			t.cancel();
			game.getPlayer().setBombs(game.getPlayer().getBombs()+1);
			
		}
	}
	
	public int getState() {
		return state;
	}
}
