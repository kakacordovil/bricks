package org.academiadecodigo.rhastafaris.brickdestroyer.gameobjects;

import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GridDirection;
import org.academiadecodigo.rhastafaris.brickdestroyer.logicgrid.LogicPosition;
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
