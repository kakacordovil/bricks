package org.academiadecodigo.rhashtafaris.pongtobreak;



public class App {

    public static void main(String[] args) {

        //PongGame game = new PongGame();
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
