/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.sprite;

import fr.ubx.poo.engine.Position;
import fr.ubx.poo.entity.decor.Decor;
import fr.ubx.poo.entity.decor.Stone;
import fr.ubx.poo.entity.decor.Tree;
import fr.ubx.poo.entity.go.GameObject;
import fr.ubx.poo.entity.go.personage.Player;
import javafx.scene.layout.Pane;


public final class SpriteFactory {

    public static Sprite createDecor(Pane layer, Position position, Decor decor) {
        if (decor instanceof Stone)
            return new SpriteDecor(layer, Sprite.IMG_STONE, position);
        if (decor instanceof Tree)
            return new SpriteDecor(layer, Sprite.IMG_TREE, position);
        return null;
    }

    public static Sprite createPlayer(Pane layer, Player player) {
       return new SpritePlayer(layer, Sprite.IMG_PLAYERS, player);
    }

    private SpriteFactory() {
    }
}
