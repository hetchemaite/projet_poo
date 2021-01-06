
package fr.ubx.poo.view.sprite;

import fr.ubx.poo.model.go.Bomb;
import fr.ubx.poo.view.image.ImageFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;

public class SpriteBomb extends SpriteGameObject {
    private final ColorAdjust effect = new ColorAdjust();
    boolean dead=false;
    public SpriteBomb(Pane layer, Bomb Bomb) {
        super(layer, null, Bomb);
        updateImage();
    }

    @Override
    public void updateImage() {
        Bomb Bomb = (Bomb) go;
        if(!(Bomb.getState()<=0)) {
        	setImage(ImageFactory.getInstance().getBomb(Bomb.getState()-1));
        }else{
        	dead=true;
        }
        
    }
    
    public boolean isDead() {
    	return dead;
    }

}

