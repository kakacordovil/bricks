package org.academiadecodigo.rhastafaris.brickdestroyer;


import org.academiadecodigo.rhastafaris.brickdestroyer.gameobjects.Ball;
import org.academiadecodigo.rhastafaris.brickdestroyer.gameobjects.Brick;
import org.academiadecodigo.rhastafaris.brickdestroyer.gameobjects.Table;
import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GridDirection;
import org.academiadecodigo.rhastafaris.brickdestroyer.logicgrid.LinkedList;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Game implements KeyboardHandler {

    private static int GRID_COLUMNS = 39;
    private static int GRID_ROWS = 30;
    private static int DELAY = 200;
    private GraphicGrid grid;
    private Ball ball;
    private Brick brick;
    private Table[] table;
    private Brick[] bricks;
    private boolean youMove;
    private LinkedList bricksList;
    private LinkedList brickRows;
    private boolean space = false;


    public Game() {
        this.grid = new GraphicGrid(GRID_COLUMNS, GRID_ROWS);

        this.bricksList = new LinkedList();
        keyboardInit();

    }

    public void init() {

        this.grid.init(); // inicialização da grid;

        // inicializar Tijolos && Table
        Table t1 = new Table(grid.makeGridPosition(18, 25));
        Table t2 = new Table(grid.makeGridPosition(19, 25));
        Table t3 = new Table(grid.makeGridPosition(20, 25));
        Table t4 = new Table(grid.makeGridPosition(21, 25));
        Table t5 = new Table(grid.makeGridPosition(22, 25));

        this.table = new Table[]{t1, t2, t3, t4, t5};


        for (int i = 0; i < (GRID_COLUMNS / 3); i++) {
            bricks = new Brick[3];
            bricks[0] = new Brick(grid.makeGridPosition(i * 3, 10));
            bricks[1] = new Brick(grid.makeGridPosition((i * 3) + 1, 10));
            bricks[2] = new Brick(grid.makeGridPosition((i * 3) + 2, 10));

            bricksList.addArray(bricks);
        }
    }


    public void play() throws InterruptedException {

        tableMove();
        int life = 3;

        for (int i = 0; i < life; i++) {

            youMove = true;
            this.ball = new Ball(grid.makeGridPosition(20, 20));

            while (youMove) {

                ball.move(ball.getDirection());

                Thread.sleep(DELAY);

                if (isCrashedBricks()) {
                    ball.setDirection(ball.getDirection().oppositeDirection());
                }

                if (isCrashedWalls()) {
                    ball.setDirection(ball.getDirection().oppositeDirectionWall());
                }

                if (ball.getLogicPosition().getRow() == GRID_ROWS - 1) {
                    youMove = false;
                    ball.getLogicPosition().hide();
                }
            }

            if (i == (life - 1)) {
                break;
            }

            if (newGame()) {
                continue;
            }
        }

    }

    public boolean newGame() {

        int i = 0;

        while (!space) {
            System.out.println(i++);
        }

        space = false;
        return true;

    }

    private boolean isCrashedWalls() {
        for (int i = 0; i < bricksList.size(); i++) {

            if (ball.getLogicPosition().getCol() == 0) { //reflex in right walll
                return true;

            } else if (ball.getLogicPosition().getCol() == GRID_COLUMNS - 1) { //reflex in left wall
                return true;
            }
        }

        return false;
    }

    private boolean isCrashedBricks() {
        for (int i = 0; i < bricksList.size(); i++) {

            for (int z = 0; z < table.length; z++) {

                if (ball.getLogicPosition().getRow() == table[z].getLogicPosition().getRow() - 1 &&
                        ball.getLogicPosition().getCol() == table[z].getLogicPosition().getCol()) {

                    return true;
                }
            }

            /*if (ball.getLogicPosition().equal(table[0].getLogicPosition())) {  //reflex in table
                return true;
            } else if (ball.getLogicPosition().equal(table[1].getLogicPosition())) {
                return true;
            } else if (ball.getLogicPosition().equal(table[2].getLogicPosition())) {
                return true;
            } else if (ball.getLogicPosition().equal(table[3].getLogicPosition())) {
                return true;
            } else if (ball.getLogicPosition().equal(table[4].getLogicPosition())) {
                return true;
            } else */

            if (ball.getLogicPosition().getRow() == 0) { //reflex in celing
                return true;

            } else if (ball.getLogicPosition().equal(bricksList.getArray(i)[0].getLogicPosition())) {  //reflex in brick
                bricksList.getArray(i)[0].hide();
                bricksList.getArray(i)[1].hide();
                bricksList.getArray(i)[2].hide();
                bricksList.remove(bricksList.getArray(i));
                return true;

            } else if (ball.getLogicPosition().equal(bricksList.getArray(i)[1].getLogicPosition())) {
                bricksList.getArray(i)[0].hide();
                bricksList.getArray(i)[1].hide();
                bricksList.getArray(i)[2].hide();
                bricksList.remove(bricksList.getArray(i));
                return true;

            } else if (ball.getLogicPosition().equal(bricksList.getArray(i)[2].getLogicPosition())) {
                bricksList.getArray(i)[0].hide();
                bricksList.getArray(i)[1].hide();
                bricksList.getArray(i)[2].hide();
                bricksList.remove(bricksList.getArray(i));
                return true;
            }
        }
        return false;
    }


    public void tableMove() throws InterruptedException {

        table[0].keyboardInit();
        table[1].keyboardInit();
        table[2].keyboardInit();
        table[3].keyboardInit();
        table[4].keyboardInit();
    }


    public void ballMove() throws InterruptedException {

        ball.move(ball.getDirection().oppositeDirection());
        Thread.sleep(DELAY);
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
