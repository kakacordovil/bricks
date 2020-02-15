package org.academiadecodigo.rhashtafaris.pongtobreak;

import org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects.Ball;
import org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects.Brick;
import org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects.Table;
import org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects.Table2;
import org.academiadecodigo.rhashtafaris.pongtobreak.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhashtafaris.pongtobreak.graphicgrid.GridDirection;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.LinkedList;


public class PongGame2 implements KeyboardHandler {

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


    public PongGame2() {
        this.grid = new GraphicGrid(GRID_COLUMNS, GRID_ROWS);  // INICIALIZAR QUADRO DE JOGO
        this.table1 = new Table[TABLE_WIDTH]; // INICIALIZAR PADDLE
        this.table2 = new  Table2[TABLE2_WIDTH]; // **SECOND TABLE **
        this.bricksList = new LinkedList<>();  // INICIALIZAR LISTA DE BRICKS
    }


    public void init() {

        // inicialização da grid;
        this.grid.init();

        initTables();

//        // INICIALIZAR TODOS OS BRICKS POR: ROW -> BRICK -> CÉLULA + Adicionar Brick ao LinkedList;
//        for (int z = 0; z < BRICKS_QTITY_ROWS; z++) {
//
//            for (int i = 0; i < (GRID_COLUMNS / BRICKS_WIDTH); i++) {
//
//                bricks = new Brick[BRICKS_WIDTH];
//
//                for(int x = 0; x < BRICKS_WIDTH; x++){
//
//                    //bricks[x] = new Brick(grid.makeGridPosition(i * BRICKS_WIDTH + x, z));
//                }
//
//                //bricksList.add(bricks);
//            }
//        }
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

        int life = 3;

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
                    continue;
                }

                if (ball.getLogicPosition().getRow() == 0 ) {
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

//        int initialColTable2 = 0;
//        for(Table2 table: table2) {
//            table.getLogicPosition().setPos(17,10);
//        }
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
