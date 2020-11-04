/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.sprite;

import fr.ubx.poo.engine.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Sprite {

    // Decor
    public static final Image IMG_STONE = loadImage("stone.png");
    public static final Image IMG_TREE = loadImage("tree.png");

    // Bonus
    public static final Image IMG_HEART = loadImage("heart.png");
    public static final Image IMG_KEY = loadImage("key.png");

    // Personage
    public static final Image[] IMG_PLAYERS = {
            loadImage("player_up.png"),
            loadImage("player_right.png"),
            loadImage("player_down.png"),
            loadImage("player_left.png")};

    // Status bar
    public static final Image[] IMG_NUMBER = {
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
    public static final Image IMG_BANNER_BOMB = loadImage("banner_bomb.png");
    public static final Image IMG_BANNER_RANGE = loadImage("banner_range.png");

    public static final int size = 40;
    private final Pane layer;
    private ImageView imageView;
    private Image image;

    public Sprite(Pane layer, Image image) {
        this.layer = layer;
        this.image = image;
    }

    private static Image loadImage(String file) {
        return new Image(Sprite.class.getResource("/images/" + file).toExternalForm());
    }

    public final void setImage(Image image) {
        if (this.image == null || this.image != image) {
            this.image = image;
        }
    }

    public abstract void updateImage();

    public abstract Position getPosition();

    public final void render() {
        if (imageView != null) {
            remove();
        }
        updateImage();
        imageView = new ImageView(this.image);
        imageView.setX(getPosition().getX() * size);
        imageView.setY(getPosition().getY() * size);
        layer.getChildren().add(imageView);
    }

    public final void remove() {
        layer.getChildren().remove(imageView);
        imageView = null;
    }
}
