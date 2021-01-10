/*
 * Copyright (c) 2020. Laurent RÃ©veillÃ¨re
 */

package fr.ubx.poo.view.sprite;

import java.util.Timer;
import java.util.TimerTask;

import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.go.character.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Sprite {

    public static final int size = 40;
    private final Pane layer;
    private ImageView imageView;
    private Image image;

    public Sprite(Pane layer, Image image) {
        this.layer = layer;
        this.image = image;
    }

    public final void setImage(Image image) {
        if (this.image == null || this.image != image) {
            this.image = image;
        }
    }

    public abstract void updateImage();

    public abstract Position getPosition();

    public final void render() {
        if (imageView != null) {
            remove();
        }
        updateImage();
        imageView = new ImageView(this.image);
        imageView.setX(getPosition().x * size);
        imageView.setY(getPosition().y * size);
        //affichage effet visuel lors de l'invicibilité aprés avoir recu un coup
        if(this instanceof SpritePlayer) {
        	SpritePlayer sp=(SpritePlayer) this;
        	Player player=(Player )sp.go;
        	if(player.isInvicible() && !sp.getInvicibilityAnim()) {
        		Timer t = new Timer();
        		sp.inverseBrightness();
        		sp.setInvicibilityAnim(true);
        		TimerTask invicibility=new TimerTask() {
        			public void run() {
        				sp.inverseBrightness();
        				if(!player.isInvicible()) {
        					if(sp.effect().getBrightness()==0.75)
        						sp.inverseBrightness();
        					
        					sp.setInvicibilityAnim(false);
        					cancel();
                			
        				}
        			}
   			  	};
   			  	t.scheduleAtFixedRate(invicibility, 250, 250);
        	}
        	imageView.setEffect(sp.effect());
        }
        layer.getChildren().add(imageView);
    }

    public final void remove() {
        layer.getChildren().remove(imageView);
        imageView = null;
    }
}
