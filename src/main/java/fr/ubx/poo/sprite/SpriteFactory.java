/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.sprite;

import fr.ubx.poo.engine.Position;
import fr.ubx.poo.entity.Entity;
import fr.ubx.poo.entity.decor.Stone;
import fr.ubx.poo.entity.decor.Tree;
import fr.ubx.poo.entity.go.personage.Player;
import javafx.scene.layout.Pane;


public final class SpriteFactory {

    public static Sprite create(Pane layer, Position position, Entity go) {
        if (go instanceof Stone)
            return new SpriteDecor(layer, Sprite.IMG_STONE, position);
        if (go instanceof Tree)
            return new SpriteDecor(layer, Sprite.IMG_TREE, position);
        if (go instanceof Player) {
            return new SpritePlayer(layer, Sprite.IMG_PLAYERS, (Player) go);
        }
        return null;
    }

    private SpriteFactory() {
    }
}
