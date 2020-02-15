package org.academiadecodigo.rhashtafaris.pong.gameobjects;

import org.academiadecodigo.rhashtafaris.pong.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhashtafaris.pong.logicgrid.LogicPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;


public class Brick {

    private LogicPosition logicPosition;
    private GraphicGrid grid;
    private Color color;

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
