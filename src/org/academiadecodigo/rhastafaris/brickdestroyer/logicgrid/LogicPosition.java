package org.academiadecodigo.rhastafaris.brickdestroyer.logicgrid;

import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GridDirection;
import org.academiadecodigo.simplegraphics.graphics.Color;

public abstract class LogicPosition implements Positionable {
    private int col;
    private int row;
    private int nextCol;
    private int nextRow;
    private Color color;

    // private GridColor color;
    private GraphicGrid grid;

    /**
     * Construct a new grid position at a specific column and row
     *
     * @param col   the column of the grid position
     * @param row   the row of the grid position
     * @param grid  the grid in which the position will be displayed
     */
    public LogicPosition(int col, int row, GraphicGrid grid) {
        this.col = col;
        this.row = row;
        this.grid = grid;
        this.nextCol = 0;
        this.nextRow = 0;
        this.color = Color.ORANGE;
    }

    public void setNextPos(int col, int row){
        this.nextCol = col;
        this.nextRow = row;
    }

    public int getNextCol() {
        return nextCol;
    }

    public int getNextRow() {
        return nextRow;
    }

    /*public boolean equals(LogicPosition position) {

        return this.col == position.getCol() && this.row == position.getRow();
    }*/

    public GraphicGrid getGrid() {
        return grid;
    }

    public void setPos(int col, int row) {
        this.col = col;
        this.row = row;
        show();
    }

    @Override
    public abstract void show();

    @Override
    public abstract void hide();


    public int getCol() {
        return col;
    }


    public int getRow() {
        return row;
    }


    public void setColor(Color color) {
        this.color = color;
        show();
    }

    public void moveInDirection(GridDirection direction, int distance) {

        switch (direction) {

            case UP:
                moveUp(distance);
                break;
            case DOWN:
                moveDown(distance);
                break;
            case UP_LEFT:
                moveUpLeft(distance);
                break;
            case UP_RIGHT:
                moveUpRight(distance);
                break;
            case DOWN_LEFT:
                moveDownLeft(distance);
                break;
            case DOWN_RIGHT:
                moveDownRight(distance);
                break;
        }

    }

    public void moveTableInDirection(GridDirection direction, int distance, int min, int max) {

        switch (direction) {

            case LEFT:
                moveLeft(distance, min);
                break;
            case RIGHT:
                moveRight(distance, max);
                break;
        }

    }

    /**
     * Moves the position up
     *
     * @param dist the number of positions to move
     */
    private void moveUp(int dist) {
        // current move
        int maxRowsUp = dist < getRow() ? dist : getRow();
        setPos(getCol(), getRow() - maxRowsUp);

        // next move
        maxRowsUp = dist < getRow() ? dist : getRow();
        setNextPos(getCol(), getRow() - maxRowsUp);

    }

    /**
     * Moves the position down
     *
     * @param dist the number of positions to move
     */
    private void moveDown(int dist) {
        // current move
        int maxRowsDown = dist > getGrid().getRows() - (getRow() + 1) ? getGrid().getRows() - (getRow() + 1) : dist;
        setPos(getCol(), getRow() + maxRowsDown);

        // next move
        maxRowsDown = dist > getGrid().getRows() - (getRow() + 1) ? getGrid().getRows() - (getRow() + 1) : dist;
        setNextPos(getCol(), getRow() + maxRowsDown);

    }

    /**
     * Moves the position left
     *
     * @param dist the number of positions to move
     */
    private void moveLeft(int dist, int min) {
        // current move
        if(getCol() <= min + dist){
            setPos(min, getRow());
            return;
        }
        int maxRowsLeft = dist < getCol() ? dist : getCol();
        setPos(getCol() - maxRowsLeft, getRow());

    }

    /**
     * Moves the position right
     *
     * @param dist the number of positions to move
     */
    private void moveRight(int dist, int max) {
        // current move
        if(getCol() >= max - dist){
            setPos(max, getRow());
            return;
        }
        int maxRowsRight = dist > getGrid().getCols() - (getCol() + 1) ? getGrid().getCols() - (getCol() + 1) : dist;
        setPos(getCol() + maxRowsRight, getRow());

    }


    private void moveUpLeft(int dist) {
        // current move
        int maxRowsUp = dist < getRow() ? dist : getRow();
        int maxRowsLeft = dist < getCol() ? dist : getCol();
        setPos(getCol() - maxRowsLeft, getRow() - maxRowsUp);

        // next move
        maxRowsUp = dist < getRow() ? dist : getRow();
        maxRowsLeft = dist < getCol() ? dist : getCol();
        setNextPos(getCol() - maxRowsLeft, getRow() - maxRowsUp);
    }


    private void moveUpRight(int dist) {
        // current move
        int maxRowsUp = dist < getRow() ? dist : getRow();
        int maxRowsRight = dist > getGrid().getCols() - (getCol() + 1) ? getGrid().getCols() - (getCol() + 1) : dist;
        setPos(getCol() + maxRowsRight, getRow() - maxRowsUp);

        // next move
        maxRowsUp = dist < getRow() ? dist : getRow();
        maxRowsRight = dist > getGrid().getCols() - (getCol() + 1) ? getGrid().getCols() - (getCol() + 1) : dist;
        setNextPos(getCol() + maxRowsRight, getRow() - maxRowsUp);
    }


    private void moveDownLeft(int dist) {
        // current move
        int maxRowsDown = dist > getGrid().getRows() - (getRow() + 1) ? getGrid().getRows() - (getRow() + 1) : dist;
        int maxRowsLeft = dist < getCol() ? dist : getCol();
        setPos(getCol() - maxRowsLeft, getRow() + maxRowsDown);

        // next move
        maxRowsDown = dist > getGrid().getRows() - (getRow() + 1) ? getGrid().getRows() - (getRow() + 1) : dist;
        maxRowsLeft = dist < getCol() ? dist : getCol();
        setNextPos(getCol() - maxRowsLeft, getRow() + maxRowsDown);
    }


    private void moveDownRight(int dist) {
        // current move
        int maxRowsDown = dist > getGrid().getRows() - (getRow() + 1) ? getGrid().getRows() - (getRow() + 1) : dist;
        int maxRowsRight = dist > getGrid().getCols() - (getCol() + 1) ? getGrid().getCols() - (getCol() + 1) : dist;
        setPos(getCol() + maxRowsRight, getRow() + maxRowsDown);

        // next move
        maxRowsDown = dist > getGrid().getRows() - (getRow() + 1) ? getGrid().getRows() - (getRow() + 1) : dist;
        maxRowsRight = dist > getGrid().getCols() - (getCol() + 1) ? getGrid().getCols() - (getCol() + 1) : dist;
        setNextPos(getCol() + maxRowsRight, getRow() + maxRowsDown);
    }
}
