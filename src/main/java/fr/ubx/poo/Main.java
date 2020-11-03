/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo;

import fr.ubx.poo.engine.GameEngine;
import fr.ubx.poo.game.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Game game;

    @Override
    public void start(Stage stage) {
        game = new Game();
        GameEngine engine = new GameEngine("UBomb", game, stage);
        engine.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
