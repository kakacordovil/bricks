package org.academiadecodigo.rhastafaris.brickdestroyer.gameobjects;

import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GridDirection;
import org.academiadecodigo.rhastafaris.brickdestroyer.logicgrid.LogicPosition;


public class Brick {

    private LogicPosition logicPosition;
    private GraphicGrid grid;

    public Brick(LogicPosition position) {
        this.logicPosition = position;
    }

    public LogicPosition getLogicPosition() {
        return this.logicPosition;
    }

    public void hide(){
        logicPosition.hide();
    }
}
