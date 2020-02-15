package org.academiadecodigo.rhashtafaris.brickdestroyer.gameobjects;

import org.academiadecodigo.rhashtafaris.brickdestroyer.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhashtafaris.brickdestroyer.logicgrid.LogicPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;


public class Brick {

    private LogicPosition logicPosition;

    public Brick(LogicPosition position) {
        this.logicPosition = position;
        this.logicPosition.setColor(Color.GREEN);
    }

    public LogicPosition getLogicPosition() {
        return this.logicPosition;
    }

    public void hide(){
        logicPosition.hide();
    }
}
