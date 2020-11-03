/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.sprite;

import fr.ubx.poo.go.GameObject;
import fr.ubx.poo.go.decor.Stone;
import fr.ubx.poo.go.decor.Tree;
import fr.ubx.poo.go.personage.Player;
import javafx.scene.layout.Pane;


public class SpriteFactory {


    public static Sprite create(Pane layer, GameObject go) {
        if (go instanceof Stone)
            return new Sprite(go, layer, SpriteImages.STONE);
        if (go instanceof Tree)
            return new Sprite(go, layer, SpriteImages.TREE);
        if (go instanceof Player) {
            return new SpritePlayer((Player) go, layer, SpriteImages.PLAYERS);
        }
        return null;
    }

}
