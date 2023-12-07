package com.SocketTrench.Match;

import java.util.ArrayList;

import com.SocketTrench.GameObject.BackgroundGameObject;
import com.SocketTrench.GameObject.Player1GameObject;
import com.SocketTrench.GameObject.Player2GameObject;
import com.SocketTrench.GameObject.ShootGameObject;

public final class MatchModel {
    public BackgroundGameObject background;
    public Player1GameObject player1;
    public Player2GameObject player2;
    public ArrayList<ShootGameObject> player1Shoots;
    public ArrayList<ShootGameObject> player2Shoots;

    public MatchModel() {
        this.background = new BackgroundGameObject();
        this.player1 = new Player1GameObject();
        this.player2 = new Player2GameObject();
        this.player1Shoots = new ArrayList<ShootGameObject>();
        this.player2Shoots = new ArrayList<ShootGameObject>();
    }
}
