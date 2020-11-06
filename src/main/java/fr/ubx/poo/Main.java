/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo;

import fr.ubx.poo.engine.GameEngine;
import fr.ubx.poo.game.Game;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Properties;

public class Main extends Application {
    private final Properties properties = new Properties();

    @Override
    public void start(Stage stage) {
        String path = getClass().getResource("/world").getFile();
        System.out.println(path);
        Game game = new Game(path);
        GameEngine engine = new GameEngine("UBomb", game, stage);
        engine.start();
    }

    public static void main(String[] args) {
        System.out.println(args);
        launch(args);
    }

}
