package org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects;

import org.academiadecodigo.rhashtafaris.pongtobreak.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhashtafaris.pongtobreak.graphicgrid.GridDirection;
import org.academiadecodigo.rhashtafaris.pongtobreak.logicgrid.LogicPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Table2 implements KeyboardHandler {

    private LogicPosition logicPosition;
    private GraphicGrid grid;
    private Color color;
    private int minimum;
    private int maximum;

    public Table2(LogicPosition position, int min, int max) {
        this.logicPosition = position;
        this.logicPosition.setColor(Color.RED);
        this.minimum = min;
        this.maximum = max;
    }

    public void move(GridDirection direction){
        logicPosition.moveTableInDirection(direction, 3, this.minimum, this.maximum);
    }

    public LogicPosition getLogicPosition() {
        return this.logicPosition;
    }


    public void keyboardInit() {

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent rightPressed = new KeyboardEvent();
        rightPressed.setKey(KeyboardEvent.KEY_A);
        rightPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent leftPressed = new KeyboardEvent();
        leftPressed.setKey(KeyboardEvent.KEY_D);
        leftPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(rightPressed);
        keyboard.addEventListener(leftPressed);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch(keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_D:
                this.move(GridDirection.RIGHT);
                break;
            case KeyboardEvent.KEY_A:
                this.move(GridDirection.LEFT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

}
