/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.engine;

import fr.ubx.poo.game.Game;
import fr.ubx.poo.sprite.Sprite;
import fr.ubx.poo.sprite.SpriteFactory;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StatusBar {
    public static final int statusBarHeight = 55;
    HBox statusBar = new HBox();
    Text lifeValue = new Text();
    Text bombsValue = new Text();
    Text rangeValue = new Text();
    Text keyValue = new Text();
    HBox level = new HBox();
    int gameLevel = 1;

    private final Game game;
    private final DropShadow ds = new DropShadow();

    private static final Image[] IMG_NUMBER = {
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


    private static final Image IMG_BANNER_BOMB = loadImage("banner_bomb.png");
    private static final Image IMG_BANNER_RANGE = loadImage("banner_range.png");
    private static final Image IMG_HEART = loadImage("heart.png");
    private static final Image IMG_KEY = loadImage("key.png");

    private static Image loadImage(String file) {
        return new Image(Sprite.class.getResource("/images/" + file).toExternalForm());
    }


    public StatusBar(Group root, int sceneWidth, int sceneHeight, Game game) {
        // Status bar
        this.game = game;

        level.getStyleClass().add("level");
        level.getChildren().add(new ImageView(IMG_NUMBER[gameLevel]));

        ds.setRadius(5.0);
        ds.setOffsetX(3.0);
        ds.setOffsetY(3.0);
        ds.setColor(Color.color(0.5f, 0.5f, 0.5f));


        HBox status = new HBox();
        status.getStyleClass().add("status");
        HBox life = statusGroup(IMG_HEART, this.lifeValue);
        HBox bombs = statusGroup(IMG_BANNER_BOMB, bombsValue);
        HBox range = statusGroup(IMG_BANNER_RANGE, rangeValue);
        HBox key = statusGroup(IMG_KEY, keyValue);
        status.setSpacing(40.0);
        status.getChildren().addAll(life, bombs, range, key);

        statusBar.getChildren().addAll(level, status);
        statusBar.getStyleClass().add("statusBar");
        statusBar.relocate(0, sceneHeight);
        statusBar.setPrefSize(sceneWidth, statusBarHeight);
        root.getChildren().add(statusBar);
    }

    private void updateLevel(int n) {
        if (n != gameLevel) {
            level.getChildren().clear();
            level.getChildren().add(new ImageView(IMG_NUMBER[n]));
        }
    }

    private HBox statusGroup(Image kind, Text number) {
        HBox group = new HBox();
        ImageView img = new ImageView(kind);
        group.setSpacing(4);
        number.setEffect(ds);
        number.setCache(true);
        number.setFill(Color.BLACK);
        number.getStyleClass().add("number");
        group.getChildren().addAll(img, number);
        return group;
    }

    public void update(Game game) {
        updateLevel(1);
        lifeValue.setText(String.valueOf(game.getPlayer().getLives()));
        rangeValue.setText("?");
        bombsValue.setText("?");
        keyValue.setText("?");
    }

}
