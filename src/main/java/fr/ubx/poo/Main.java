/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo;

import fr.ubx.poo.engine.GameEngine;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.sprite.Sprite;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.util.Properties;

public class Main extends Application {

    public static String getResource(String path, String file) {
        return Sprite.class.getResource("/" + path + "/" + file).toExternalForm();
    }

    @Override
    public void start(Stage stage) {
        String path = getClass().getResource("/sample").getFile();
        Game game = new Game(path);
        GameEngine engine = new GameEngine("UBomb", game, stage);
        engine.start();
    }

    public static void main(String[] args) {
        System.out.println(args);
        launch(args);
    }

}
