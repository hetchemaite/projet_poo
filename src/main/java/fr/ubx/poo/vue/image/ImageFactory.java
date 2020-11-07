/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.vue.image;

import fr.ubx.poo.game.Direction;
import javafx.scene.image.Image;

import static fr.ubx.poo.vue.image.ImageResource.*;


public final class ImageFactory {
    public static ImageFactory instance = new ImageFactory();
    private final Image[] images;
    private final Image[] digits;
    private final Image[] players;

    private ImageFactory() {
        images = new Image[ImageResource.values().length];
        digits = new Image[10];
        players = new Image[Direction.values().length];
    }

    private Image loadImage(String file) {
        return new Image(getClass().getResource("/images/" + file).toExternalForm());
    }

    public void load() {
        for (ImageResource img : ImageResource.values()) {
            images[img.ordinal()] = loadImage(img.getFileName());
        }
        int i = 0;
        for (ImageResource img : new ImageResource[]{DIGIT_0, DIGIT_1, DIGIT_2, DIGIT_3, DIGIT_4, DIGIT_5, DIGIT_6, DIGIT_7, DIGIT_8, DIGIT_9}) {
            digits[i++] = images[img.ordinal()];
        }
        players[Direction.N.ordinal()] = get(PLAYER_UP);
        players[Direction.E.ordinal()] = get(PLAYER_RIGHT);
        players[Direction.S.ordinal()] = get(PLAYER_DOWN);
        players[Direction.W.ordinal()] = get(PLAYER_LEFT);
    }

    public Image get(ImageResource img) {
        return images[img.ordinal()];
    }

    public Image getDigit(int i) {
        try {
            return digits[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Digit must be in range [0-9]");
            throw e;
        }
    }

    public Image getPlayer(Direction direction) {
        return players[direction.ordinal()];
    }

}
