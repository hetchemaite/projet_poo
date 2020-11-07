package fr.ubx.poo.vue.sprite;

import fr.ubx.poo.engine.Position;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class SpriteDecor extends Sprite {
    private Position position;

    public SpriteDecor(Pane layer, Image image, Position position) {
        super(layer, image);
        this.position = position;
    }

    @Override
    public void updateImage() {

    }

    @Override
    public Position getPosition() {
        return position;
    }
}
