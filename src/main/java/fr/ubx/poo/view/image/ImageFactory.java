/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.view.image;

import fr.ubx.poo.game.Direction;
import javafx.scene.image.Image;

import static fr.ubx.poo.view.image.ImageResource.*;

public final class ImageFactory {
    private final Image[] images;

    private final ImageResource[] directions = new ImageResource[]{
            // Direction { N, E, S, W }
            PLAYER_UP, PLAYER_RIGHT, PLAYER_DOWN, PLAYER_LEFT,
    };
    
    private final ImageResource[] directions_monster = new ImageResource[] {
    		MONSTER_UP, MONSTER_RIGHT, MONSTER_DOWN, MONSTER_LEFT,
    };
    
    private final ImageResource[] directions_Boss = new ImageResource[] {
    		BOSS_UP, BOSS_RIGHT, BOSS_DOWN, BOSS_LEFT,
    		BOSS2_UP, BOSS2_RIGHT, BOSS2_DOWN, BOSS2_LEFT,
    		BOSS3_UP, BOSS3_RIGHT, BOSS3_DOWN, BOSS3_LEFT, 
    };


    private final ImageResource[] digits = new ImageResource[]{
            DIGIT_0, DIGIT_1, DIGIT_2, DIGIT_3, DIGIT_4,
            DIGIT_5, DIGIT_6, DIGIT_7, DIGIT_8, DIGIT_9,
    };
    
    private final ImageResource[] bomb = new ImageResource[] {
    		BOMB1, BOMB2, BOMB3, BOMB4,
    };

    private ImageFactory() {
        images = new Image[ImageResource.values().length];
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     */
    public static ImageFactory getInstance() {
        return Holder.instance;
    }

    private Image loadImage(String file) {
        return new Image(getClass().getResource("/images/" + file).toExternalForm());
    }

    public void load() {
        for (ImageResource img : ImageResource.values()) {
            images[img.ordinal()] = loadImage(img.getFileName());
        }
    }

    public Image get(ImageResource img) {
        return images[img.ordinal()];
    }

    public Image getDigit(int i) {
        if (i < 0 || i > 9)
            throw new IllegalArgumentException();
        return get(digits[i]);
    }

    public Image getPlayer(Direction direction) {
        return get(directions[direction.ordinal()]);
    }
    
    public Image getMonster(Direction direction, int lives, boolean Boss) {
        if(Boss) {
        	return get(directions_Boss[direction.ordinal() + 4*(lives-1)]);
        }
    	return get(directions_monster[direction.ordinal()]);
    }
    
	public Image getBomb(int state) {
		return get(bomb[state]);
	}

    /**
     * Holder
     */
    private static class Holder {
        /**
         * Instance unique non préinitialisée
         */
        private final static ImageFactory instance = new ImageFactory();
    }



}
