package org.academiadecodigo.rhashtafaris.pongtobreak.graphicgrid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class GraphicGrid {

    public static final int PADDING = 10;
    public static final int CELL_SIZE = 15;
    private int rows;
    private int cols;
    private Rectangle gameBoard;

    public GraphicGrid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }


    public void init() {

        this.gameBoard = new Rectangle(PADDING, PADDING, getWidth(), getHeight());
        this.gameBoard.setColor(Color.BLACK);
        this.gameBoard.draw();
        this.gameBoard.fill();
    }


    public int getCols() {
        return this.cols;
    }


    public int getRows() {
        return this.rows;
    }


    public int getWidth() {
        return getCellSize() * getCols();
    }


    public int getHeight() {
        return getCellSize() * getRows();
    }

    /*
    public int getX() {
        //System.out.println(gameBoard.getX());
        return gameBoard.getX();
    }


    public int getY() {
        //System.out.println(gameBoard.getY());

        return gameBoard.getY();
    }

    */
    public int getCellSize() {
        return CELL_SIZE;
    }


    /**
     * @see Grid#makeGridPosition(int, int)
     *
     */

    public GraphicPosition makeGridPosition(int col, int row) {

        return new GraphicPosition(col, row, this);
    }


    /**
     * Auxiliary method to compute the y value that corresponds to a specific row
     *
     * @param row index
     * @return y pixel value
     */
    public int rowToY(int row) {
        return row * getCellSize() + PADDING;
    }

    /**
     * Auxiliary method to compute the x value that corresponds to a specific column
     *
     * @param column index
     * @return x pixel value
     */
    public int columnToX(int column) {
        return column * getCellSize() + PADDING;
    }

}
