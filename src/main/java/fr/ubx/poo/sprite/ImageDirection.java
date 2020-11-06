package fr.ubx.poo.sprite;

import fr.ubx.poo.engine.Direction;
import java.util.Map;
import java.util.HashMap;
import javafx.scene.image.Image;


public class ImageDirection {
    private final Map<Direction, Image> images = new HashMap<>();

    public ImageDirection(Image imageNorth, Image imageEast, Image imageSouth, Image imageWest) {
        images.put(Direction.N, imageNorth);
        images.put(Direction.E, imageEast);
        images.put(Direction.S, imageSouth);
        images.put(Direction.W, imageWest);
    }

    public Image get(Direction direction) {
        return images.get(direction);
    }
}

