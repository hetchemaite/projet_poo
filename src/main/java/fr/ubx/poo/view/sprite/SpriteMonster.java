
package fr.ubx.poo.view.sprite;

import fr.ubx.poo.model.go.character.Monster;
import fr.ubx.poo.view.image.ImageFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;

public class SpriteMonster extends SpriteGameObject {
    private final ColorAdjust effect = new ColorAdjust();
    private boolean invicibilityAnim=false;
    public SpriteMonster(Pane layer, Monster monster) {
        super(layer, null, monster);
        updateImage();
    }

    @Override
    public void updateImage() {
        Monster monster = (Monster) go;
        setImage(ImageFactory.getInstance().getMonster(monster.getDirection(), monster.getLives(), monster.isBoss()));
    }
    
    public ColorAdjust effect() {
    	return this.effect;
    }
    
    public boolean getInvicibilityAnim() {
    	return this.invicibilityAnim;
    }
    
    public void setInvicibilityAnim(boolean bool) {
    	this.invicibilityAnim=bool;
    }
    
    
    public void inverseBrightness() {
    	if(effect().getBrightness()==0.75) {
    		effect.setBrightness(0);
    	}else {
    		effect.setBrightness(0.75);
    	}
    }
}

