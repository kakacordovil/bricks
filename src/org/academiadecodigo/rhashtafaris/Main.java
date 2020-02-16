package org.academiadecodigo.rhashtafaris;

import org.academiadecodigo.rhashtafaris.brickdestroyer.Game;
import org.academiadecodigo.rhashtafaris.pongtobreak.PongGame;


public class Main {

    public static void main(String[] args) {

        //Game game = new Game();
        PongGame game = new PongGame();

        game.init();

        try {
            game.play();
            System.out.println("GO FUCK YOURSELF");

        } catch (InterruptedException ex){
            System.out.println(ex);

        }



    }

}
