package org.academiadecodigo.rhastafaris.brickdestroyer.gameobjects;

import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GridDirection;
import org.academiadecodigo.rhastafaris.brickdestroyer.logicgrid.LogicPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Table implements KeyboardHandler {

    private LogicPosition logicPosition;
    private GraphicGrid grid;
    private Color color;

    public Table (LogicPosition position) {
        this.logicPosition = position;
        this.logicPosition.setColor(Color.RED);

    }

    public void move(GridDirection direction){
        logicPosition.moveInDirection(direction, 3);
    }

    public LogicPosition getLogicPosition() {
        return this.logicPosition;
    }


    public void keyboardInit() {

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent rightPressed = new KeyboardEvent();
        rightPressed.setKey(KeyboardEvent.KEY_RIGHT);
        rightPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent leftPressed = new KeyboardEvent();
        leftPressed.setKey(KeyboardEvent.KEY_LEFT);
        leftPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(rightPressed);
        keyboard.addEventListener(leftPressed);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch(keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_RIGHT:
                this.move(GridDirection.RIGHT);
                break;
            case KeyboardEvent.KEY_LEFT:
                this.move(GridDirection.LEFT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

}
