/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.engine;

import fr.ubx.poo.game.Game;
import fr.ubx.poo.sprite.Sprite;
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

    public StatusBar(Group root, int sceneWidth, int sceneHeight, Game game) {
        this.game = game;

        level.getStyleClass().add("level");
        level.getChildren().add(new ImageView(Sprite.IMG_NUMBER[gameLevel]));

        ds.setRadius(5.0);
        ds.setOffsetX(3.0);
        ds.setOffsetY(3.0);
        ds.setColor(Color.color(0.5f, 0.5f, 0.5f));


        HBox status = new HBox();
        status.getStyleClass().add("status");
        HBox life = statusGroup(Sprite.IMG_HEART, this.lifeValue);
        HBox bombs = statusGroup(Sprite.IMG_BANNER_BOMB, bombsValue);
        HBox range = statusGroup(Sprite.IMG_BANNER_RANGE, rangeValue);
        HBox key = statusGroup(Sprite.IMG_KEY, keyValue);
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
            level.getChildren().add(new ImageView(Sprite.IMG_NUMBER[n]));
        }
    }

    private HBox statusGroup(Image kind, Text number) {
        HBox group = new HBox();
        ImageView life = new ImageView(kind);
        group.setSpacing(4);
        number.setEffect(ds);
        number.setCache(true);
        number.setFill(Color.BLACK);
        number.getStyleClass().add("number");
        group.getChildren().addAll(life, number);
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
