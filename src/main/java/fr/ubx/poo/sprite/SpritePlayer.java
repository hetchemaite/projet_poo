/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.sprite;

import fr.ubx.poo.entity.go.personage.Player;
import fr.ubx.poo.engine.Direction;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Map;

public class SpritePlayer extends SpriteGameObject {

    private final ColorAdjust effect = new ColorAdjust();
    protected final ImageDirection images;

    public SpritePlayer(Pane layer, ImageDirection images, Player player) {
        super(layer, null, player);
        this.images = images;
        updateImage();
    }

    @Override
    public void updateImage() {
        Player player = (Player) go;
        setImage(images.get(player.getDirection()));
    }
}
