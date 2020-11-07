/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo;

import fr.ubx.poo.engine.GameEngine;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.vue.image.ImageFactory;
import fr.ubx.poo.vue.sprite.Sprite;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ImageFactory.instance.load();
        String path = getClass().getResource("/sample").getFile();
        Game game = new Game(path);
        GameEngine engine = new GameEngine("UBomb", game, stage);
        engine.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
