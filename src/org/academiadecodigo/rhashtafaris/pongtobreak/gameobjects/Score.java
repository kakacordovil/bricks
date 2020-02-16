package org.academiadecodigo.rhashtafaris.pongtobreak.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.awt.*;

public class Score {

    private Rectangle scorePoint;

    public Score(Rectangle scorePoint) {
        this.scorePoint = scorePoint;
    }

    public void showScore() {
        scorePoint.setColor(Color.WHITE);
        scorePoint.draw();
    }

    public void markPoint() {
        scorePoint.setColor(Color.WHITE);
        scorePoint.fill();
    }




}
