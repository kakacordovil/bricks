package org.academiadecodigo.rhashtafaris.brickdestroyer.gameobjects;

import org.academiadecodigo.rhashtafaris.brickdestroyer.graphicgrid.GridDirection;
import org.academiadecodigo.rhashtafaris.brickdestroyer.logicgrid.LogicPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class Ball {

    private LogicPosition logicPosition;
    private GridDirection direction;

    public Ball(LogicPosition position){
        this.logicPosition = position;
        this.direction = GridDirection.DOWN;
        this.logicPosition.setColor(Color.BLUE);
    }

    public void move(GridDirection direction){
        logicPosition.moveInDirection(direction, 1);
    }

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
