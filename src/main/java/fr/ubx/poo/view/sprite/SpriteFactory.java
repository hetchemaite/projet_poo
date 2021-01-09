/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.view.sprite;

import static fr.ubx.poo.view.image.ImageResource.*;

import fr.ubx.poo.game.Position;
import fr.ubx.poo.model.decor.BombNumberDec;
import fr.ubx.poo.model.decor.BombNumberInc;
import fr.ubx.poo.model.decor.BombRangeDec;
import fr.ubx.poo.model.decor.BombRangeInc;
import fr.ubx.poo.model.decor.Box;
import fr.ubx.poo.model.decor.Decor;
import fr.ubx.poo.model.decor.DoorNextClosed;
import fr.ubx.poo.model.decor.DoorNextOpened;
import fr.ubx.poo.model.decor.DoorPrevOpened;
import fr.ubx.poo.model.decor.Explosion;
import fr.ubx.poo.model.decor.Heart;
import fr.ubx.poo.model.decor.Key;
import fr.ubx.poo.model.decor.Princess;
import fr.ubx.poo.model.decor.Stone;
import fr.ubx.poo.model.decor.Tree;
import fr.ubx.poo.model.go.Bomb;
import fr.ubx.poo.model.go.character.Monster;
import fr.ubx.poo.model.go.character.Player;
import fr.ubx.poo.view.image.ImageFactory;
import javafx.scene.layout.Pane;


public final class SpriteFactory {
    
    public static Sprite createDecor(Pane layer, Position position, Decor decor) {
        ImageFactory factory = ImageFactory.getInstance();
        if (decor!=null) {
            String obj = decor.toString();           
            if (obj.equals("Stone"))
                return new SpriteDecor(layer, factory.get(STONE), position);
            if (obj.equals("Tree"))
                return new SpriteDecor(layer, factory.get(TREE), position);
            if (obj.equals("Box"))
                return new SpriteDecor(layer, factory.get(BOX), position);
            if (obj.equals("Heart"))
                return new SpriteDecor(layer, factory.get(HEART), position);
            if (obj.equals("Key"))
                return new SpriteDecor(layer, factory.get(KEY), position);
            if (obj.equals("BombRangeInc"))
                return new SpriteDecor(layer, factory.get(BOMBRANGEINC), position);
            if (obj.equals("BombRangeDec"))
                return new SpriteDecor(layer, factory.get(BOMBRANGEDEC), position);
            if (obj.equals("BombNumberInc"))
                return new SpriteDecor(layer, factory.get(BOMBNUMBERINC), position);
            if (obj.equals("BombNumberDec"))
                return new SpriteDecor(layer, factory.get(BOMBNUMBERDEC), position);
            if (obj.equals("Princess"))
                return new SpriteDecor(layer, factory.get(PRINCESS), position);
            if (obj.equals("DoorNextClosed"))
                return new SpriteDecor(layer, factory.get(DOORNEXTCLOSED), position);
            if (obj.equals("DoorNextOpened"))
                return new SpriteDecor(layer, factory.get(DOORNEXTOPENED), position);
            if (obj.equals("DoorPrevOpened"))
                return new SpriteDecor(layer, factory.get(DOORPREVOPENED), position);
            if (obj.equals("Explosion"))
            	return new SpriteDecor(layer, factory.get(EXPLOSION), position);
            
        }
        return null;
    }

    public static Sprite createPlayer(Pane layer, Player player) {
        return new SpritePlayer(layer, player);
    }
    public static Sprite createMonster(Pane layer, Monster monster) {
    	return new SpriteMonster(layer, monster);
    }
    public static Sprite createBomb(Pane layer, Bomb bomb) {
    	return new SpriteBomb(layer,bomb);
    }
}
