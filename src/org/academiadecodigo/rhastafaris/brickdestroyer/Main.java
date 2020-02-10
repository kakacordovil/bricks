package org.academiadecodigo.rhastafaris.brickdestroyer;

import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GraphicGrid;


public class Main {

    public static void main(String[] args) {

        Game game = new Game();

        game.init();

        try {
            game.play();
            System.out.println("GO FUCK YOURSELF");

        } catch (InterruptedException ex){
            System.out.println(ex);

        }



    }

}
