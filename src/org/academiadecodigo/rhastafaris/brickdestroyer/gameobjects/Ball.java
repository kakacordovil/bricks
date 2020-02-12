package org.academiadecodigo.rhastafaris.brickdestroyer.gameobjects;

import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GraphicPosition;
import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GridDirection;
import org.academiadecodigo.rhastafaris.brickdestroyer.logicgrid.LogicPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class Ball {

    private LogicPosition logicPosition;
    // private GraphicGrid grid;
    // private GraphicPosition graphicPosition;
    private GridDirection direction;
    private Color color;

    public Ball(LogicPosition position){
        this.logicPosition = position;
        this.direction = GridDirection.DOWN;
        this.logicPosition.setColor(Color.BLUE);
    }

    public void move(GridDirection direction){
        logicPosition.moveInDirection(direction, 1);
    }

    /*public GridDirection changeDirection() {

        GridDirection newDirection = direction;

        newDirection = newDirection.oppositeDirectionBricks();

        return newDirection;
    }*/

    public LogicPosition getLogicPosition () {
        return this.logicPosition;
    }

    public GridDirection getDirection () {
        return this.direction;
    }

    public void setDirection(GridDirection direction){
        this.direction = direction;
    }

}
