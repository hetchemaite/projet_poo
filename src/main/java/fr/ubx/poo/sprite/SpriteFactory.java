/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.sprite;

import fr.ubx.poo.engine.Position;
import fr.ubx.poo.entity.decor.Decor;
import fr.ubx.poo.entity.decor.Stone;
import fr.ubx.poo.entity.decor.Tree;
import fr.ubx.poo.entity.go.personage.Player;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public final class SpriteFactory {
    // Decor
    private static final Image IMG_STONE = loadImage("stone.png");
    private static final Image IMG_TREE = loadImage("tree.png");

    // Bonus
    private static final Image IMG_HEART = loadImage("heart.png");
    private static final Image IMG_KEY = loadImage("key.png");

    // Personage
    private static final Image[] IMG_PLAYERS = {
            loadImage("player_up.png"),
            loadImage("player_right.png"),
            loadImage("player_down.png"),
            loadImage("player_left.png")};


    private SpriteFactory() {
    }

    private static Image loadImage(String file) {
        return new Image(Sprite.class.getResource("/images/" + file).toExternalForm());
    }

    public static Sprite createDecor(Pane layer, Position position, Decor decor) {
        if (decor instanceof Stone)
            return new SpriteDecor(layer, IMG_STONE, position);
        if (decor instanceof Tree)
            return new SpriteDecor(layer, IMG_TREE, position);
        return null;
    }

    public static Sprite createPlayer(Pane layer, Player player) {
        return new SpritePlayer(layer, IMG_PLAYERS, player);
    }
}
