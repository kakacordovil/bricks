package org.academiadecodigo.rhashtafaris.pongtobreak;

import org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects.Ball;
import org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects.Brick;
import org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects.Table;
import org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects.Table2;

import java.util.LinkedList;

public class Collision {
    
    private Ball ball;
    private LinkedList<Brick[]> bricksList;
    private Table[] table;
    private Table2[] table2;

    
    public Collision(Ball ball, Table[] table, Table2[] table2, LinkedList<Brick[]> bricksList){
        this.ball = ball;
        this.table = table;
        this.table2 = table2;
        this.bricksList = bricksList;
    }
    
    
    public boolean isCrashedWalls() {
        for (int i = 0; i < 40; i++) {

            if (ball.getLogicPosition().getCol() == 0) { //reflex in right walll
                return true;

            } else if (ball.getLogicPosition().getCol() == PongGame.GRID_COLUMNS - 1) { //reflex in left wall
                return true;
            }
        }

        return false;
    }


    public boolean isTableCollision() {

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


    public boolean isTable2Collision() {

        for (int z = 0; z < table2.length; z++) {

            if (ball.getLogicPosition().getRow() == table2[z].getLogicPosition().getRow() + 1 &&
                    ball.getLogicPosition().getCol() == table2[z].getLogicPosition().getCol()) {
                return true;

            } else if (ball.getLogicPosition().getRow() == table2[0].getLogicPosition().getRow() + 1 &&
                    ball.getLogicPosition().getCol() == table2[0].getLogicPosition().getCol() - 1) {
                return true;

            } else if (ball.getLogicPosition().getRow() == table2[table2.length - 1].getLogicPosition().getRow() + 1 &&
                    ball.getLogicPosition().getCol() == table2[table2.length - 1].getLogicPosition().getCol() + 1)
            // verificar o valor -1 do getCol da linha 75
            {
                return true;
            }
        }
        return false;
    }


    public boolean isBallCollision() {

        for (int i = 0; i < bricksList.size(); i++) {


            for (int z = 0; z < bricksList.get(i).length; z++) {

                // Superior - Inferior Bricks Collision
                if ((ball.getLogicPosition().getRow() == bricksList.get(i)[z].getLogicPosition().getRow() - 1 ||
                        ball.getLogicPosition().getRow() == bricksList.get(i)[z].getLogicPosition().getRow() + 1) &&
                        ball.getLogicPosition().getCol() == bricksList.get(i)[z].getLogicPosition().getCol()) {
                    hideBricks(i);
                    return true;
                }
            }
        }

//        if (ball.getLogicPosition().getRow() == 0) { //reflex in celing
//            return true;
//        }

        return false;
    }


    public boolean isBallCollisionDiagonal() {

        for (int i = 0; i < bricksList.size(); i++) {


            for (int z = 0; z < bricksList.get(i).length; z++) {

                // Diagonal Bricks Collision
                if (ball.getLogicPosition().getNextRow() == bricksList.get(i)[z].getLogicPosition().getRow() &&
                        ball.getLogicPosition().getNextCol() == bricksList.get(i)[z].getLogicPosition().getCol()) {

                    hideBricks(i);
                    return true;
                }
            }
        }

        return false;
    }

    public void hideBricks(int index) {

        for (int i = 0; i < PongGame.BRICKS_WIDTH; i++) {

            bricksList.get(index)[i].hide();
        }

        bricksList.remove(bricksList.get(index));
    }
}