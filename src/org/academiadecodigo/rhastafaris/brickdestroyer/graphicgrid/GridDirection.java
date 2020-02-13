package org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid;

public enum GridDirection {
    UP,
    DOWN,
    UP_LEFT,
    UP_RIGHT,
    DOWN_LEFT,
    DOWN_RIGHT,
    LEFT,
    RIGHT;

    /*public boolean isOpposite(GridDirection dir) {
        return dir.equals(oppositeDirectionBricks());
    }*/

    public GridDirection oppositeDirectionBricks() {

        GridDirection opposite = null;

        switch(this) {

            case UP:
                opposite = DOWN;
                break;
            case DOWN:
                opposite = UP;
                break;
            case LEFT:
                opposite = RIGHT;
                break;
            case RIGHT:
                opposite = LEFT;
                break;
            case UP_LEFT:
                opposite = DOWN_LEFT;
                break;
            case UP_RIGHT:
                opposite = DOWN_RIGHT;
                break;
            case DOWN_LEFT:
                opposite = UP_LEFT;
                break;
            case DOWN_RIGHT:
                opposite = UP_RIGHT;
        }

        return opposite;
    }

    // Opposite Direction Diagonal Bricks + Walls
    public GridDirection oppositeDirectionDiagonal() {

        GridDirection opposite = null;

        switch(this) {

            case UP_LEFT:
                opposite = UP_RIGHT;
                break;
            case UP_RIGHT:
                opposite = UP_LEFT;
                break;
            case DOWN_LEFT:
                opposite = DOWN_RIGHT;
                break;
            case DOWN_RIGHT:
                opposite = DOWN_LEFT;
        }

        return opposite;
    }

}
