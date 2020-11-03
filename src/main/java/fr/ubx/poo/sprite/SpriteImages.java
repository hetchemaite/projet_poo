/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.sprite;

import javafx.scene.image.Image;

public abstract class SpriteImages {
    // Decor images
    public static final Image BOX = loadImage("box.png");
    public static final Image DOOR_OPENED = loadImage("door_opened.png");
    public static final Image DOOR_CLOSED = loadImage("door_closed.png");
    public static final Image STONE = loadImage("stone.png");
    public static final Image TREE = loadImage("tree.png");
    public static final Image KEY = loadImage("key.png");

    // Bonus
    public static final Image BONUS_BOMB_NB_INC = loadImage("bonus_bomb_nb_inc.png");
    public static final Image BONUS_BOMB_NB_DEC = loadImage("bonus_bomb_nb_dec.png");
    public static final Image BONUS_BOMB_RANGE_INC = loadImage("bonus_bomb_range_inc.png");
    public static final Image BONUS_BOMB_RANGE_DEC = loadImage("bonus_bomb_range_dec.png");
    public static final Image HEART = loadImage("heart.png");

    // Personage
    public static final Image[] MONSTERS = {
            loadImage("monster_up.png"),
            loadImage("monster_right.png"),
            loadImage("monster_down.png"),
            loadImage("monster_left.png")};
    public static final Image[] PLAYERS = {
            loadImage("player_up.png"),
            loadImage("player_right.png"),
            loadImage("player_down.png"),
            loadImage("player_left.png")};
    public static final Image PRINCESS = loadImage("bomberwoman.png");

    // Bombs
    public static final Image[] BOMBS = {
            loadImage("bomb1.png"),
            loadImage("bomb2.png"),
            loadImage("bomb3.png"),
            loadImage("bomb4.png")};
    public static final Image EXPLOSION = loadImage("explosion.png");

    // Status bar
    public static final Image[] NUMBER = {
            loadImage("banner_0.jpg"),
            loadImage("banner_1.jpg"),
            loadImage("banner_2.jpg"),
            loadImage("banner_3.jpg"),
            loadImage("banner_4.jpg"),
            loadImage("banner_5.jpg"),
            loadImage("banner_6.jpg"),
            loadImage("banner_7.jpg"),
            loadImage("banner_8.jpg"),
            loadImage("banner_9.jpg")};

    public static final Image BANNER_BOMB = loadImage("banner_bomb.png");
    public static final Image BANNER_RANGE = loadImage("banner_range.png");

    private static Image loadImage(String file) {
        return new Image(SpriteImages.class.getResource("/images/" + file).toExternalForm());
    }

}
