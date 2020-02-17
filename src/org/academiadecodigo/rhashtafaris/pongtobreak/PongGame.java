package org.academiadecodigo.rhashtafaris.pongtobreak;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects.*;
import org.academiadecodigo.rhashtafaris.pongtobreak.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhashtafaris.pongtobreak.graphicgrid.GridDirection;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;


public class PongGame implements KeyboardHandler {

    final static int GRID_COLUMNS = 40;
    final static int GRID_ROWS = 30;
    final static int BRICKS_WIDTH = 5;
    final static int BRICKS_QTITY_ROWS = 8;
    final static int TABLE_WIDTH = 7;
    final static int TABLE_ROW = 25;

    final static int TABLE2_WIDTH = 7;
    final static int TABLE2_ROW = 4;

    private int delay = 100; // NOT A CONSTANT - IT CHANGES WHILE GAME PLAYS
    private GraphicGrid grid;
    private Ball ball;
    //private Brick brick;
    private Table[] table1;
    private Table2[] table2;
    private Brick[] bricks;
    private boolean youMove;
    private LinkedList<Brick[]> bricksList;
    private boolean space = false;
    private Text scoreP1;
    private Text scoreP2;
    private Sound lostSound;
    private Sound gameSound;



    public PongGame() {
        this.grid = new GraphicGrid(GRID_COLUMNS, GRID_ROWS);  // INICIALIZAR QUADRO DE JOGO
        this.table1 = new Table[TABLE_WIDTH]; // INICIALIZAR PADDLE
        this.table2 = new  Table2[TABLE2_WIDTH]; // **SECOND TABLE **
        this.bricksList = new LinkedList<>();  // INICIALIZAR LISTA DE BRICKS
        lostSound = new Sound("/resources/333785__projectsu012__8-bit-failure-sound.wav");


    }


    public void init() {
        // inicialização da grid;
        this.grid.init();
        gameSound = new Sound("/resources/Rasputin.wav");
        gameSound.play(true);

        initTables();

    }

    private void initTables() {
        // inicializar Tijolos && Table
        int initialColTable = (GRID_COLUMNS / 2) - (int) Math.floor(TABLE_WIDTH / 2);

        for(int i = 0; i < this.table1.length; i++){

            // Make Grid Position Center + Minimum Column Limit + Maximum Column Limit
            table1[i] = new Table(grid.makeGridPosition(initialColTable + i, TABLE_ROW), i, GRID_COLUMNS - TABLE_WIDTH + i);
        }

        // inicializar Table2

        int initialColTable2 = (GRID_COLUMNS / 2) - (int) Math.floor(TABLE2_WIDTH / 2);

        for(int i = 0; i < this.table2.length; i++){

            // Make Grid Position Center + Minimum Column Limit + Maximum Column Limit
            table2[i] = new Table2(grid.makeGridPosition(initialColTable2 + i, TABLE2_ROW), i,
                    GRID_COLUMNS - TABLE2_WIDTH + i);
        }

    }


    public void play() throws InterruptedException {


        int round = 5;
        int showRound = 0;
        int player1Wins = 0;
        int player2Wins = 0;



        String scoreRound;
        scoreRound = "Score: player2: " + player2Wins;
        scoreP2 = new Text(18, 12, scoreRound);
        scoreP2.setColor(Color.WHITE);
        scoreP2.draw();

        String scoreRoundP1;
        scoreRoundP1 = "Score: player1: " + player1Wins;
        scoreP1 = new Text(498, 440, scoreRoundP1);
        scoreP1.setColor(Color.WHITE);
        scoreP1.draw();



//        score = new Score(new Rectangle(4, 4, 8, 8));
//        score.showScore();

        // Inicializar KeyBoard Table
        for (int i = 0; i < TABLE_WIDTH; i++) {
            table1[i].keyboardInit();
        }

        for (int i = 0; i < TABLE2_WIDTH; i++) {
            table2[i].keyboardInit();
        }


        while (true) {

            youMove = true;
            this.ball = new Ball(grid.makeGridPosition(20, 20));
            Collision collision = new Collision(ball, table1, table2, bricksList);

            while (youMove) {

                ball.move(ball.getDirection());


                Thread.sleep(delay);


                if (collision.isBallCollision()) {
                    ball.setDirection(ball.getDirection().oppositeDirectionBricks());
                    continue;
                }


                if (collision.isBallCollisionDiagonal()) {
                    ball.setDirection(ball.getDirection().oppositeDirectionDiagonal());
                    continue;
                }


                if (collision.isTableCollision()) {

                    ball.setDirection(GridDirection.UP);

                    if (ball.getLogicPosition().getCol() < table1[table1.length / 2].getLogicPosition().getCol()) {

                        ball.setDirection(GridDirection.UP_LEFT);
                    }

                    if (ball.getLogicPosition().getCol() > table1[table1.length / 2].getLogicPosition().getCol()) {

                        ball.setDirection(GridDirection.UP_RIGHT);
                    }

                    if(delay >= 30){
                        delay -= 2;
                    }

                    continue;

                }


                if (collision.isTable2Collision()) {

                    ball.setDirection(GridDirection.DOWN);

                    if (ball.getLogicPosition().getCol() < table2[table2.length / 2].getLogicPosition().getCol()) {

                        ball.setDirection(GridDirection.DOWN_LEFT);
                    }

                    if (ball.getLogicPosition().getCol() > table2[table2.length / 2].getLogicPosition().getCol()) {

                        ball.setDirection(GridDirection.DOWN_RIGHT);
                    }

                    if(delay >= 30){
                        delay -= 2;
                    }

                    continue;

                }

                if (collision.isCrashedWalls()) {
                    ball.setDirection(ball.getDirection().oppositeDirectionDiagonal());
                    continue;
                }

                if (ball.getLogicPosition().getRow() == GRID_ROWS - 1) {
                    youMove = false;
                    ball.getLogicPosition().hide();
                    lostSound = new Sound("/resources/333785__projectsu012__8-bit-failure-sound.wav");++player2Wins;
                    lostSound.play(true);
                    continue;
                }

                if (ball.getLogicPosition().getRow() == 0 ) {
                    youMove = false;
                    ball.getLogicPosition().hide();
                    lostSound = new Sound("/resources/333785__projectsu012__8-bit-failure-sound.wav");
                    lostSound.play(true);
                    ++player1Wins;
                    continue;
                }

            }


            String scoreRound1P2 = "Score: player2: " + player2Wins ;
            scoreP2.setText(scoreRound1P2);
            String scoreRound1P1 = "Score: player1: " + player1Wins ;
            scoreP1.setText(scoreRound1P1);

            System.out.println("\033[35mROUND "+ ++showRound);
            System.out.println("Player 1: " + player1Wins);
            System.out.println("Player 2: " + player2Wins);

            if (--round == 0) {

                Sound winSound = new Sound("/resources/495005__0ev__win-video-game-sound.wav");
                Picture picwin2 = new Picture(121, 87,"/Users/codecadet/brickout/brick-destroyer/resources/ecra_de_vitoria_P2_jogo.jpg");
                picwin2.draw();
                winSound.play(true);

                Text textWins;
                String textvictory;
                textvictory = "WITH: " + player2Wins + " POINTS!";
                textWins= new Text(267, 340, textvictory);
                textWins.setColor(Color.WHITE);
                textWins.draw();
                gameSound.close();





                if (player1Wins > player2Wins) {

                    picwin2 = new Picture(121, 87,"/Users/codecadet/brickout/brick-destroyer/resources/ecra_de_vitoria_P1_jogo.jpg");
                    picwin2.draw();

                    String textvictory1 = "WITH: " + player1Wins + " POINTS!";
                    textWins.setText(textvictory1);
                    gameSound.close();
//                    while (!space) {
//                        Thread.sleep(100);
//                    }
//                    space = false;
//                    init();
//                    play();



                }




                while (!space) {
                    Thread.sleep(100);
                }
                space = false;
                init();
                play();

                break;
            }


            reStart();

        }
    }


    public boolean reStart() throws InterruptedException {

        keyboardInit();

        while (!space) {
            Thread.sleep(delay);
        }

//        table1[0].getLogicPosition().setPos(17, 25);
//        table1[1].getLogicPosition().setPos(18, 25);
//        table1[2].getLogicPosition().setPos(19,25);
//        table1[3].getLogicPosition().setPos(20, 25);
//        table1[4].getLogicPosition().setPos(21, 25);
//        table1[5].getLogicPosition().setPos(22,25);
//        table1[6].getLogicPosition().setPos(23, 25);
//        //initTables();
////
////        int initialColTable2 = (GRID_COLUMNS / 2) - (int) Math.floor(TABLE2_WIDTH / 2);
////
////        for(int i = 0; i < this.table2.length; i++){
////
////            // Make Grid Position Center + Minimum Column Limit + Maximum Column Limit
////            table2[i].setLogicPosition(grid.makeGridPosition(initialColTable2 + i, TABLE2_ROW));
////
////
//////                    new Table2(grid.makeGridPosition(initialColTable2 + i, TABLE2_ROW), i,
//////                    GRID_COLUMNS - TABLE2_WIDTH + i);}

        space = false;
        return true;
    }


    public void exit() {
    }


    public void keyboardInit() {

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent spacePressed = new KeyboardEvent();
        spacePressed.setKey(KeyboardEvent.KEY_SPACE);
        spacePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(spacePressed);


    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_SPACE:
                space = true;
                break;

            default:
                space = false;
                break;
        }
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
}
