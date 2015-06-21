package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;

import com.teamawesome.mentalsnakes.framework.Graphics;

import java.util.ArrayList;

/**
 * Created by Ben on 20/06/2015.
 */
public class Grid
{
    private int xPixel;
    private int yPixel;
    private int gridPixelSize;
    private int gridDimension;
    private int dotSize;
    private boolean display;
    private ArrayList<GridDot> griddots = new ArrayList<GridDot>();
    private ArrayList<GridLine> gridlines = new ArrayList<GridLine>();
    private Snake snake;

    public Grid (int x, int y, int gridPixelSize, int gridDimension, int dotSize)
    {
        this.xPixel = x;
        this.yPixel = y;
        this.gridPixelSize = gridPixelSize;
        this.gridDimension = gridDimension;
        this.dotSize = dotSize;
        this.display = false;
        initialize();
    }

    public void initialize()
    {
        createDots();
        createSnake();
    }

    public void createDots()
    {
        for(int i = 0; i < gridDimension * gridDimension; i++)
        {
            GridDot gridDotToAdd = new GridDot(i - (int)(i/gridDimension) * gridDimension, (int)(i/gridDimension));
            griddots.add(gridDotToAdd);
        }
    }

    public void createSnake()
    {
        int startCoordinate = (int)(((double)gridDimension + 1)/2) - 1;
        this.snake = new Snake(startCoordinate);
    }

    public void setDisplay(boolean set)
    {
        this.display = set;
    }

    public void draw(Graphics g)
    {
        if(display)
        {
            for(int i = 0; i < griddots.size(); i++)
            {
                GridDot gridDotI = griddots.get(i);
                double xDraw = ((xPixel) + ((1/(double)(gridDimension - 1)) * (double)gridPixelSize * gridDotI.getXCoordinate()));
                double yDraw = ((yPixel) + ((1/(double)(gridDimension - 1)) * (double)gridPixelSize * gridDotI.getYCoordinate()));
                g.drawRect((int)xDraw, (int)yDraw, dotSize, dotSize, Color.rgb(20, 20, 20));
            }
            for(int i = 0; i < gridlines.size(); i++)
            {
                GridLine gridLineI = gridlines.get(i);
                gridLineI.draw(g);
            }
            double xDraw = xPixel + ((1/(double)(gridDimension - 1)) * (double)snake.getXCoordinate()) * (double)gridPixelSize;
            double yDraw = yPixel + ((1/(double)(gridDimension - 1)) * (double)snake.getYCoordinate()) * (double)gridPixelSize;
            g.drawRect((int)xDraw, (int)yDraw, snake.getSize(), snake.getSize(), Color.rgb(0, 100, 90));
        }
    }

    public void moveSnake(Direction directionToMove)
    {
        snake.move(directionToMove, griddots, gridlines, gridDimension);
    }

    public void recreate(int newGridDimension)
    {
        griddots.clear();
        gridlines.clear();
        this.gridDimension = newGridDimension;
        initialize();
    }

}
