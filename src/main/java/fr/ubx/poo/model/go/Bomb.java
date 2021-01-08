package fr.ubx.poo.model.go;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.decor.Box;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.decor.DoorNextOpened;
import fr.ubx.poo.model.decor.Explosion;
import fr.ubx.poo.model.go.character.Monster;

public class Bomb extends GameObject {
	int state;
	int range;
	Timer t;
	
	public Bomb(Game game, Position position, long now) {
		super(game, position);
		state=4;
		range=game.getPlayer().getRangebomb();
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
		Decor d=game.getWorld().get(this.getPosition());
		String obj="Decor";
		if(d!=null) {
			obj=d.toString();
			if(obj.equals("Explosion"))
				state=0;
		}
		if(state<=0){
			t.cancel();
			game.getPlayer().setBombs(game.getPlayer().getBombs()+1);
			explosion();
		}
		
	}
	
	
	public void explosion() {
		Position pos=this.getPosition();
		clear(pos);
		for(int i=1; i<=range; i++){
			if (clear(new Position(pos.x +i, pos.y)))
				break;
		}
		
		for(int i=1; i<=range; i++){
			if(clear(new Position(pos.x -i, pos.y)))
				break;
		}
		for(int i=1; i<=range; i++){
			if(clear(new Position(pos.x , pos.y +i)))
				break;
		}
		for(int i=1; i<=range; i++){
			if(clear(new Position(pos.x, pos.y -i)))
				break;
		}
		game.getWorld().setWorldchanged(true);
	}
	
	
	public boolean clear(Position pos){
		boolean stop=true;
		boolean explosion=false;
		if(!pos.inside(game.getWorld().getDimension())) 
			return stop;
		Decor d=game.getWorld().get(pos);
		if(d!=null) {
			String obj=d.toString();
			if(obj.equals("Heart") || obj.equals("BombNumberDec") || obj.equals("BombNumberInc") || obj.equals("BombRangeDec") || obj.equals("BombRangeInc")){
				game.getWorld().clear(pos);
				explosion=true;
				stop=false;
			}
			if(obj.equals("Box")){
				game.getWorld().clear(pos);
				explosion=true;
			}
		}
		if(explosion || d==null) {
			game.getWorld().set(pos, new Explosion());
			game.getPlayer().GetHit(pos);
			game.getMonsters().forEach(m -> m.GetHit(pos));
			
			TimerTask suprexplosion=new TimerTask() {
			    public void run() {
			    	game.getWorld().clear(pos);
			    	game.getWorld().setWorldchanged(true);
			    }
			};
			t = new Timer();
			t.schedule(suprexplosion,750);
			
		}
		if(d==null)
			stop=false;
		return stop;
	}
	
	public int getState() {
		return state;
	}
}
