/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.sprite;

import fr.ubx.poo.go.personage.Player;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SpritePlayer extends Sprite {

    private final ColorAdjust effect = new ColorAdjust();
    protected final Image[] images;

    public SpritePlayer(Player player, Pane layer, Image[] images) {
        super(player, layer);
        this.images = images;
        updateImage();
    }

    @Override
    public void updateImage() {
        Player player = (Player) getGameObject();
        int code = player.getDirection().getCode();
        setImage(images[code]);
    }
}
