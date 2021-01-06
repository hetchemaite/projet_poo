/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.engine;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.view.sprite.Sprite;
import fr.ubx.poo.view.sprite.SpriteBomb;
import fr.ubx.poo.view.sprite.SpriteFactory;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.PositionNotFoundException;
import fr.ubx.poo.model.go.character.Player;
import fr.ubx.poo.model.go.Bomb;
import fr.ubx.poo.model.go.character.Monster;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
//
import javafx.animation.AnimationTimer;
import java.util.Timer;
import java.util.TimerTask;

public final class GameEngine {

    private static AnimationTimer gameLoop;
    private final String windowTitle;
    private final Game game;
    private final Player player;
    private final List<Sprite> sprites = new ArrayList<>();
    private StatusBar statusBar;
    private Pane layer;
    private Input input;
    private Stage stage;
    private Sprite spritePlayer;
    private List<Monster> Monsters = new ArrayList<>();
    private final List<Sprite> spritesMonsters = new ArrayList<>();
    private List<Bomb> Bombs= new ArrayList<>();
    private List<Sprite> spritesBombs = new ArrayList<>();
    

    public GameEngine(final String windowTitle, Game game, final Stage stage) {
        this.windowTitle = windowTitle;
        this.game = game;
        this.player = game.getPlayer();
        this.Monsters=game.getMonsters();
        initialize(stage, game);
        buildAndSetGameLoop();
    }

    private void initialize(Stage stage, Game game) {
    	System.out.println("YOOOOOOOO");
        this.stage = stage;
        Group root = new Group();
        layer = new Pane();

        int height = game.getWorld().getDimension().height;
        int width = game.getWorld().getDimension().width;
        int sceneWidth = width * Sprite.size;
        int sceneHeight = height * Sprite.size;
        Scene scene = new Scene(root, sceneWidth, sceneHeight + StatusBar.height);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

        stage.setTitle(windowTitle);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        input = new Input(scene);
        root.getChildren().add(layer);
        statusBar = new StatusBar(root, sceneWidth, sceneHeight, game);
        // Create decor sprites
        try {
        	player.setPosition(game.getWorld().findPlayer());
        }catch(PositionNotFoundException e) {
        	
        }
        //
        Monsters.clear();
        Monsters.addAll(game.getWorld().findMonsters(game));
        game.getWorld().forEach( (pos,d) -> sprites.add(SpriteFactory.createDecor(layer, pos, d)));
        spritePlayer = SpriteFactory.createPlayer(layer, player);
        Monsters.forEach(m -> spritesMonsters.add(SpriteFactory.createMonster(layer, m)));
        //moveAutomatically();

    }

    protected final void buildAndSetGameLoop() {
        gameLoop = new AnimationTimer() {
            public void handle(long now) {
                // Check keyboard actions
                processInput(now);

                // Do actions
                update(now);

                // Graphic update
                render();
                statusBar.update(game);
            }
        };
    }

    private void processInput(long now) {
        if (input.isExit()) {
            gameLoop.stop();
            Platform.exit();
            System.exit(0);
        }
        if (input.isMoveDown()) {
            player.requestMove(Direction.S);
        }
        if (input.isMoveLeft()) {
            player.requestMove(Direction.W);
        }
        if (input.isMoveRight()) {
            player.requestMove(Direction.E);
        }
        if (input.isMoveUp()) {
            player.requestMove(Direction.N);
        }
        if( input.isKey()) {
        	player.OpenDoor();
        }
        if(input.isBomb()) {
        	Bomb b=player.putBomb(now);
        	if(b!=null) {
        		Bombs.add(b);
            	spritesBombs.add(SpriteFactory.createBomb(layer, b));
        	}
        	
        }
        input.clear();
    }

    private void showMessage(String msg, Color color) {
        Text waitingForKey = new Text(msg);
        waitingForKey.setTextAlignment(TextAlignment.CENTER);
        waitingForKey.setFont(new Font(60));
        waitingForKey.setFill(color);
        StackPane root = new StackPane();
        root.getChildren().add(waitingForKey);
        Scene scene = new Scene(root, 400, 200, Color.WHITE);
        stage.setTitle(windowTitle);
        stage.setScene(scene);
        input = new Input(scene);
        stage.show();
        new AnimationTimer() {
            public void handle(long now) {
                processInput(now);
            }
        }.start();
    }

    private void update(long now) {
    	if(game.isLevelChanged()) {
    		System.out.println("test");
    		game.setLevelChanged(false);
    		initialize(stage,game);
    	}
    	//for Eeach if state<=0 then timer.cancel et boom
    	Bombs.forEach(b -> System.out.println(b.getState()));
    	Bombs.forEach(b -> b.update());
    	Bombs.removeIf(b -> b.getState()<=0); 
    	
        player.update(now);
        Monsters.forEach(m -> m.update(now));

        if (player.isAlive() == false) {
            gameLoop.stop();
            showMessage("Perdu!", Color.RED);
        }
        if (player.isWinner()) {
            gameLoop.stop();
            showMessage("Gagn�", Color.BLUE);
        }
    }

    private void render() {
    	if(this.game.getWorld().isWorldchanged()) {
    		sprites.forEach(Sprite::remove);
    		sprites.clear();
    		game.getWorld().forEach( (pos,d) -> sprites.add(SpriteFactory.createDecor(layer, pos, d)));
    		game.getWorld().setWorldchanged(false);
    	}
        sprites.forEach(Sprite::render);
        spritesMonsters.forEach(Sprite::render);
        spritesBombs.forEach(Sprite::render);
        
        // last rendering to have player in the foreground
        spritePlayer.render();
       
    }

    public void start() {
        gameLoop.start();
    }

    
    /*private void moveAutomatically(){
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            public void run() {
            	
            }
        }, 2,1500);
    }*/
}
