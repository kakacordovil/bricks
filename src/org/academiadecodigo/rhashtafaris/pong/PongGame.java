package org.academiadecodigo.rhashtafaris.pong;

import org.academiadecodigo.rhashtafaris.pong.Collision;
import org.academiadecodigo.rhashtafaris.pong.gameobjects.Ball;
import org.academiadecodigo.rhashtafaris.pong.gameobjects.Brick;
import org.academiadecodigo.rhashtafaris.pong.gameobjects.Table;
import org.academiadecodigo.rhashtafaris.pong.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhashtafaris.pong.graphicgrid.GridDirection;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.LinkedList;


public class PongGame implements KeyboardHandler {

    final static int GRID_COLUMNS = 40;
    final static int GRID_ROWS = 30;
    final static int BRICKS_WIDTH = 5;
    final static int BRICKS_QTITY_ROWS = 8;
    final static int TABLE_WIDTH = 9;
    final static int TABLE_ROW = 25;
    private int delay = 100; // NOT A CONSTANT - IT CHANGES WHILE GAME PLAYS
    private GraphicGrid grid;
    private Ball ball;
    //private Brick brick;
    private Table[] table;
    private Brick[] bricks;
    private boolean youMove;
    private LinkedList<Brick[]> bricksList;
    private boolean space = false;
    //private Score score = new Score();





    public PongGame() {
        this.grid = new GraphicGrid(GRID_COLUMNS, GRID_ROWS);  // INICIALIZAR QUADRO DE JOGO
        this.table = new Table[TABLE_WIDTH]; // INICIALIZAR PADDLE
        this.bricksList = new LinkedList<>();  // INICIALIZAR LISTA DE BRICKS
    }


    public void init() {

        // inicialização da grid;
        this.grid.init();


        // inicializar Tijolos && Table
        int initialColTable = (GRID_COLUMNS / 2) - (int) Math.floor(TABLE_WIDTH / 2);

        for(int i = 0; i < this.table.length; i++){

            // Make Grid Position Center + Minimum Column Limit + Maximum Column Limit
            table[i] = new Table(grid.makeGridPosition(initialColTable + i, TABLE_ROW), i, GRID_COLUMNS - TABLE_WIDTH + i);
        }


        // INICIALIZAR TODOS OS BRICKS POR: ROW -> BRICK -> CÉLULA + Adicionar Brick ao LinkedList;
        for (int z = 0; z < BRICKS_QTITY_ROWS; z++) {

            for (int i = 0; i < (GRID_COLUMNS / BRICKS_WIDTH); i++) {

                bricks = new Brick[BRICKS_WIDTH];

                for(int x = 0; x < BRICKS_WIDTH; x++){

                    bricks[x] = new Brick(grid.makeGridPosition(i * BRICKS_WIDTH + x, z));
                }

                bricksList.add(bricks);
            }
        }
    }


    public void play() throws InterruptedException {

        int life = 3;

        // Inicializar KeyBoard Table
        for (int i = 0; i < TABLE_WIDTH; i++) {
            table[i].keyboardInit();
        }

        while (true) {

            youMove = true;
            this.ball = new Ball(grid.makeGridPosition(20, 20));
            Collision collision = new Collision(ball, table, bricksList);

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

                    if (ball.getLogicPosition().getCol() < table[table.length / 2].getLogicPosition().getCol()) {

                        ball.setDirection(GridDirection.UP_LEFT);
                    }

                    if (ball.getLogicPosition().getCol() > table[table.length / 2].getLogicPosition().getCol()) {

                        ball.setDirection(GridDirection.UP_RIGHT);
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
                    continue;
                }

            }


            if (--life == 0) {
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
