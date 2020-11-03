/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.sprite;

import fr.ubx.poo.go.GameObject;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Sprite {

    public static final int size = 40;
    private final Pane layer;
    private final GameObject go;
    private ImageView imageView = null;
    private Image image;
    private Effect effect;

    public Sprite(GameObject go, Pane layer, Image image, Effect effect) {
        this.go = go;
        this.layer = layer;
        setImage(image, effect);
    }

    public Sprite(GameObject go, Pane layer, Image image) {
        this(go, layer, image, null);
    }

    public Sprite(GameObject go, Pane layer) {
        this(go, layer, null, null);
    }

    public GameObject getGameObject() {
        return go;
    }

    public final void setImage(Image image, Effect effect) {
        if (this.image == null || (this.image != image || this.effect != effect)) {
            this.image = image;
            this.effect = effect;
        }
    }

    public final void setImage(Image image) {
        setImage(image, null);
    }

    public void updateImage() {
    }

    public final void render() {
        if (imageView != null) {
            remove();
        }
        updateImage();
        imageView = new ImageView(this.image);
        imageView.setEffect(effect);
        imageView.setX(go.getPosition().getX() * size);
        imageView.setY(go.getPosition().getY() * size);
        layer.getChildren().add(imageView);
    }

    public final void remove() {
        layer.getChildren().remove(imageView);
        imageView = null;
    }
}
