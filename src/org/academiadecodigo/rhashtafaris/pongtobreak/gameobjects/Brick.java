package org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects;

import org.academiadecodigo.rhashtafaris.pongtobreak.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhashtafaris.pongtobreak.logicgrid.LogicPosition;
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
