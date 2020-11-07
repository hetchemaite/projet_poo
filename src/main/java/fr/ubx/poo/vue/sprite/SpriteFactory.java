/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.vue.sprite;

import fr.ubx.poo.engine.Position;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.decor.Stone;
import fr.ubx.poo.model.decor.Tree;
import fr.ubx.poo.model.go.personage.Player;
import fr.ubx.poo.vue.image.ImageFactory;
import static fr.ubx.poo.vue.image.ImageResource.*;
import javafx.scene.layout.Pane;


public final class SpriteFactory {

    public static Sprite createDecor(Pane layer, Position position, Decor decor) {
        ImageFactory factory = ImageFactory.instance;
        if (decor instanceof Stone)
            return new SpriteDecor(layer, factory.get(STONE), position);
        if (decor instanceof Tree)
            return new SpriteDecor(layer, factory.get(TREE), position);
        return null;
    }

    public static Sprite createPlayer(Pane layer, Player player) {
        return new SpritePlayer(layer, player);
    }
}
