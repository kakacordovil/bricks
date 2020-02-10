package org.academiadecodigo.rhastafaris.brickdestroyer.logicgrid;

import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GraphicGrid;
import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GraphicPosition;
import org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid.GridDirection;

public abstract class LogicPosition implements Positionable {
    private int col;
    private int row;

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
        // this.color = GridColor.NOCOLOR;
    }

    public boolean equal (LogicPosition position) {

        return this.col == position.getCol() && this.row == position.getRow();
    }

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

    public void moveInDirection(GridDirection direction, int distance) {

        switch (direction) {

            case UP:
                moveUp(distance);
                break;
            case DOWN:
                moveDown(distance);
                break;
            case LEFT:
                moveLeft(distance);
                break;
            case RIGHT:
                moveRight(distance);
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

    /**
     * Moves the position up
     *
     * @param dist the number of positions to move
     */
    private void moveUp(int dist) {

        int maxRowsUp = dist < getRow() ? dist : getRow();
        setPos(getCol(), getRow() - maxRowsUp);

    }

    /**
     * Moves the position down
     *
     * @param dist the number of positions to move
     */
    private void moveDown(int dist) {

        int maxRowsDown = dist > getGrid().getRows() - (getRow() + 1) ? getGrid().getRows() - (getRow() + 1) : dist;
        setPos(getCol(), getRow() + maxRowsDown);

    }

    /**
     * Moves the position left
     *
     * @param dist the number of positions to move
     */
    private void moveLeft(int dist) {

        int maxRowsLeft = dist < getCol() ? dist : getCol();
        setPos(getCol() - maxRowsLeft, getRow());

    }

    /**
     * Moves the position right
     *
     * @param dist the number of positions to move
     */
    private void moveRight(int dist) {
        int maxRowsRight = dist > getGrid().getCols() - (getCol() + 1) ? getGrid().getCols() - (getCol() + 1) : dist;
        setPos(getCol() + maxRowsRight, getRow());
    }

    private void moveUpLeft(int dist) {
        int maxRowsUp = dist < getRow() ? dist : getRow();
        setPos(getCol(), getRow() - maxRowsUp);
        int maxRowsLeft = dist < getCol() ? dist : getCol();
        setPos(getCol() - maxRowsLeft, getRow());
    }

    private void moveUpRight(int dist) {
        int maxRowsUp = dist < getRow() ? dist : getRow();
        setPos(getCol(), getRow() - maxRowsUp);
        int maxRowsRight = dist > getGrid().getCols() - (getCol() + 1) ? getGrid().getCols() - (getCol() + 1) : dist;
        setPos(getCol() + maxRowsRight, getRow());
    }

    private void moveDownLeft(int dist) {
        int maxRowsDown = dist > getGrid().getRows() - (getRow() + 1) ? getGrid().getRows() - (getRow() + 1) : dist;
        setPos(getCol(), getRow() + maxRowsDown);
        int maxRowsLeft = dist < getCol() ? dist : getCol();
        setPos(getCol() - maxRowsLeft, getRow());
    }

    private void moveDownRight(int dist) {
        int maxRowsDown = dist > getGrid().getRows() - (getRow() + 1) ? getGrid().getRows() - (getRow() + 1) : dist;
        setPos(getCol(), getRow() + maxRowsDown);
        int maxRowsRight = dist > getGrid().getCols() - (getCol() + 1) ? getGrid().getCols() - (getCol() + 1) : dist;
        setPos(getCol() + maxRowsRight, getRow());
    }
}
