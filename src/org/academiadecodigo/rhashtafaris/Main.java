package org.academiadecodigo.rhashtafaris;

import org.academiadecodigo.rhashtafaris.brickdestroyer.Game;


public class Main {

    public static void main(String[] args) {

        Game game = new Game();

        game.init();

        try {
            game.play();
            System.out.println("GO");

        } catch (InterruptedException ex){
            System.out.println(ex);

        }



    }

}
