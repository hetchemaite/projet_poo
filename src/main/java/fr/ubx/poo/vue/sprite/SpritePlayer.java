/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.vue.sprite;

import fr.ubx.poo.model.go.personage.Player;
import fr.ubx.poo.vue.image.ImageFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;

public class SpritePlayer extends SpriteGameObject {
    private final ColorAdjust effect = new ColorAdjust();

    public SpritePlayer(Pane layer, Player player) {
        super(layer, null, player);
        updateImage();
    }

    @Override
    public void updateImage() {
        Player player = (Player) go;
        setImage(ImageFactory.instance.getPlayer(player.getDirection()));
    }
}
