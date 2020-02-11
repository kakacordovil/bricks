package org.academiadecodigo.rhastafaris.brickdestroyer;


import org.academiadecodigo.rhashtafaris.linkedlist.LinkedList;
import org.academiadecodigo.rhastafaris.brickdestroyer.gameobjects.Ball;
import org.academiadecodigo.rhastafaris.brickdestroyer.gameobjects.Brick;
import org.academiadecodigo.rhastafaris.brickdestroyer.gameobjects.Table;
import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GraphicGrid;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Game implements KeyboardHandler {

    private static int GRID_COLUMNS = 40;
    private static int GRID_ROWS = 30;
    private static int DELAY = 100;
    private GraphicGrid grid;
    private Ball ball;
    //private Brick brick;
    private Table[] table;
    private Brick[] bricks;
    private boolean youMove;
    private LinkedList<Brick[]> bricksList;
    private boolean space = false;


    public Game() {
        this.grid = new GraphicGrid(GRID_COLUMNS, GRID_ROWS);
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

        this.bricksList = new LinkedList<>();


        for (int z = 0; z < 8; z++) {
            for (int i = 0; i < (GRID_COLUMNS / 5); i++) {
                bricks = new Brick[5];
                bricks[0] = new Brick(grid.makeGridPosition(i * 5, z));
                bricks[1] = new Brick(grid.makeGridPosition((i * 5) + 1, z));
                bricks[2] = new Brick(grid.makeGridPosition((i * 5) + 2, z));
                bricks[3] = new Brick(grid.makeGridPosition((i * 5) + 3, z));
                bricks[4] = new Brick(grid.makeGridPosition((i * 5) + 4, z));

                bricksList.add(bricks);
            }
        }
    }


    public void play() throws InterruptedException {

        tableMove();
        int life = 3;

        while (true) {

            youMove = true;
            this.ball = new Ball(grid.makeGridPosition(20, 20));

            while (youMove) {

                ball.move(ball.getDirection());

                Thread.sleep(DELAY);

                if (isBallCollision()) {
                    ball.setDirection(ball.getDirection().oppositeDirectionBricks());
                }

                // int index = isTableCollision();

                if (isTableCollision()) {

                    if (ball.getLogicPosition().getCol() < table[2].getLogicPosition().getCol()){
                        ball.setDirection(ball.getDirection().oppositeDirectionTableLeft());

                    }

                    if (ball.getLogicPosition().getCol() > table[2].getLogicPosition().getCol()){
                        ball.setDirection(ball.getDirection().oppositeDirectionTableRight());

                    }

                    ball.setDirection(ball.getDirection().oppositeDirectionBricks());

                }


                if (isCrashedWalls()) {
                    ball.setDirection(ball.getDirection().oppositeDirectionWall());
                }

                if (ball.getLogicPosition().getRow() == GRID_ROWS - 1) {
                    youMove = false;
                    ball.getLogicPosition().hide();
                }
            }

            if (--life == 0) {
                break;
            }

            newGame();

        }

    }

    public boolean newGame() throws InterruptedException {

        while (!space) {
            Thread.sleep(DELAY);
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

    private boolean isTableCollision() {
        for (int z = 0; z < table.length; z++) {

            if (ball.getLogicPosition().getRow() == table[z].getLogicPosition().getRow() - 1 &&
                    ball.getLogicPosition().getCol() == table[z].getLogicPosition().getCol()) {
                return true;

            } else if (ball.getLogicPosition().getRow() == table[0].getLogicPosition().getRow() - 1 &&
                    ball.getLogicPosition().getCol() == table[0].getLogicPosition().getCol() - 1) {
                return true;

            } else if (ball.getLogicPosition().getRow() == table[table.length - 1].getLogicPosition().getRow() - 1 &&
                    ball.getLogicPosition().getCol() == table[table.length - 1].getLogicPosition().getCol() + 1) {
                return true;
            }
        }
        return false;
    }

    private boolean isBallCollision() {

        for (int i = 0; i < bricksList.size(); i++) {


            for (int z = 0; z < bricksList.get(i).length; z++) {

                if ((ball.getLogicPosition().getRow() == bricksList.get(i)[z].getLogicPosition().getRow() + 1 ||
                        ball.getLogicPosition().getRow() == bricksList.get(i)[z].getLogicPosition().getRow() - 1) &&
                        ball.getLogicPosition().getCol() == bricksList.get(i)[z].getLogicPosition().getCol()) {
                    hideBricks(i);
                    return true;

                } else if (ball.getLogicPosition().getRow() == bricksList.get(i)[z].getLogicPosition().getRow() &&
                        (ball.getLogicPosition().getCol() == bricksList.get(i)[0].getLogicPosition().getCol() - 1 ||
                                ball.getLogicPosition().getCol() == bricksList.get(i)[bricksList.get(i).length - 1].getLogicPosition().getCol() + 1)) {
                    hideBricks(i);
                    return true;

                } else if (ball.getLogicPosition().getRow() == bricksList.get(i)[0].getLogicPosition().getRow() - 1 &&
                        ball.getLogicPosition().getCol() == bricksList.get(i)[0].getLogicPosition().getCol() - 1 ||

                        ball.getLogicPosition().getRow() == bricksList.get(i)[bricksList.get(i).length - 1].getLogicPosition().getRow() - 1 &&
                                ball.getLogicPosition().getCol() == bricksList.get(i)[bricksList.get(i).length - 1].getLogicPosition().getCol() + 1 ||

                        ball.getLogicPosition().getRow() == bricksList.get(i)[0].getLogicPosition().getRow() + 1 &&
                                ball.getLogicPosition().getCol() == bricksList.get(i)[0].getLogicPosition().getCol() - 1 ||

                        ball.getLogicPosition().getRow() == bricksList.get(i)[bricksList.get(i).length - 1].getLogicPosition().getRow() + 1 &&
                                ball.getLogicPosition().getCol() == bricksList.get(i)[bricksList.get(i).length - 1].getLogicPosition().getCol() + 1) {

                    hideBricks(i);
                    return true;
                }
            }

            if (ball.getLogicPosition().getRow() == 0) { //reflex in celing
                return true;
            }
        }

        return false;

    }

    public void hideBricks(int index) {

        for (int i = 0; i < bricks.length; i++) {

            bricksList.get(index)[i].hide();
        }

        bricksList.remove(bricksList.get(index));
    }


    public void tableMove() throws InterruptedException {

        for (int i = 0; i < table.length; i++) {
            table[i].keyboardInit();
        }
    }


    public void ballMove() throws InterruptedException {

        ball.move(ball.getDirection().oppositeDirectionBricks());
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
