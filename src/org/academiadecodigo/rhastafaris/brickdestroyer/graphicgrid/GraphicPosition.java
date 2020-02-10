package org.academiadecodigo.rhastafaris.brickdestroyer.graphicgrid;

import org.academiadecodigo.rhastafaris.brickdestroyer.logicgrid.LogicPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


public class GraphicPosition extends LogicPosition {
    private Rectangle rectangle;
    private GraphicGrid graphicGrid;


    public GraphicPosition(int col, int row, GraphicGrid grid) {

        super(col, row, grid);
        this.graphicGrid = grid;

        this.rectangle = new Rectangle(grid.columnToX(col), grid.rowToY(row), grid.getCellSize(), grid.getCellSize());
        show();
    }

    @Override
    public void show() {

        this.rectangle.setColor(Color.RED);
        this.rectangle.fill();

    }

    @Override
    public void hide() {

        this.rectangle.delete();

    }

    /**
     * @see GridPosition#moveInDirection(GridDirection, int)
     */
    @Override
    public void moveInDirection(GridDirection direction, int distance) {
        int initialCol = super.getCol();
        int initialRow = super.getRow();

        super.moveInDirection(direction, distance);

        this.rectangle.translate(graphicGrid.getCellSize() * (super.getCol() - initialCol), graphicGrid.getCellSize() * (super.getRow() - initialRow));
    }
}
