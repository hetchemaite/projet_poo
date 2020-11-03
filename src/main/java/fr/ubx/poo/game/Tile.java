package fr.ubx.poo.game;

import fr.ubx.poo.go.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final List<GameObject> gos = new ArrayList<>();

    public Tile(GameObject go) {
        if (go != null)
            gos.add(go);
    }

    public List<GameObject> getGameObjects() {
        return gos;
    }

    public void add(GameObject go) {
        if (go != null)
            gos.add(go);
    }

    public void remove(GameObject go) {
        gos.remove(go);
    }

    public boolean isEmpty() {
        return gos.isEmpty();
    }

}
