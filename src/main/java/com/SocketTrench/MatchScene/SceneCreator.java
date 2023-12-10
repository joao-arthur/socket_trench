package com.SocketTrench.MatchScene;

import java.util.List;
import java.util.LinkedList;

import com.SocketTrench.Engine.GameObject;

public final class SceneCreator {
    public static List<GameObject> createMatchPlayer1() {
        final var gameObjects = new LinkedList<GameObject>();
        gameObjects.add(new BackgroundGameObject());
        gameObjects.add(new Player1GameObject());
        gameObjects.add(new Player2GameObject());
        return gameObjects;
    }

    public static List<GameObject> createMatchPlayer2() {
        final var gameObjects = new LinkedList<GameObject>();
        gameObjects.add(new BackgroundGameObject());
        gameObjects.add(new Player1GameObject());
        gameObjects.add(new Player2GameObject());
        return gameObjects;
    }
}
