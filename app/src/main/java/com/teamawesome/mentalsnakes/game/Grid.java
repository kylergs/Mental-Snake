package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;

import com.teamawesome.mentalsnakes.framework.Graphics;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ben on 20/06/2015.
 */
public class Grid implements Cloneable
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
    private int snakeSize;

    public Grid (int x, int y, int gridPixelSize, int gridDimension, int dotSize, int snakeSize)
    {
        this.xPixel = x;
        this.yPixel = y;
        this.gridPixelSize = gridPixelSize;
        this.gridDimension = gridDimension;
        this.dotSize = dotSize;
        this.display = false;
        this.snakeSize = snakeSize;
        initialize();
    }

    public Grid ()
    {
        this(0, 0, 10, 1, 15, 30);
    }

    /*
     * A clone constructor was needed for HAL to be able to work
     */

    public Grid (Grid gridToClone)
    {
        this(gridToClone.getXPixel(), gridToClone.getYPixel(), gridToClone.getPixelSize(), gridToClone.getGridDimension(), gridToClone.getDotSize(), gridToClone.getSnakeSize());
    }

//    public void clone(Grid gridToClone) {
//        this.xPixel = gridToClone.getXPixel();
//        this.yPixel = gridToClone.getYPixel();
//        this.gridPixelSize = gridToClone.getPixelSize();
//        this.gridDimension = gridToClone.getGridDimension();
//        this.dotSize = gridToClone.getDotSize();
//        this.display = gridToClone.getDisplay();
//        this.griddots.clear();
//        ArrayList<GridDot> gridDotToClone = gridToClone.getGridDots();
//        for(int i = 0; i < gridDotToClone.size(); i++)
//        {
//            GridDot gridDotI = gridDotToClone.get(i);
//            this.griddots.add(gridDotI);
//        }
//        this.gridlines.clear();
//        ArrayList<GridLine> gridLineToClone = gridToClone.getGridLines();
//        for(int i = 0; i < gridLineToClone.size(); i++)
//        {
//            GridLine gridLineI = gridLineToClone.get(i);
//            this.gridlines.add(gridLineI);
//        }
//        this.snake = new Snake(gridToClone.getSnake());
//        this.snakeSize = gridToClone.getSnakeSize();
//    }

    public void initialize()
    {
        createDots();
        createSnake();
        this.setDisplay(false);
    }

    public void restart()
    {
        griddots.clear();
        gridlines.clear();
        initialize();
    }

    public void setGridDimension(int dimensionToSet)
    {
        this.gridDimension = dimensionToSet;
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
        GridDot gridDotStart = griddots.get(startCoordinate * (gridDimension + 1));
        gridDotStart.setConnect(true);
        this.snake = new Snake(startCoordinate, snakeSize);
    }

    public void setDisplay(boolean set)
    {
        this.display = set;
    }

    public void draw(Graphics g, int red, int green, int blue)
    {
        if(display)
        {
            for(int i = 0; i < griddots.size(); i++)
            {
                GridDot gridDotI = griddots.get(i);
                double xDraw = (((xPixel) + ((1/(double)(gridDimension - 1)) * (double)gridPixelSize * gridDotI.getXCoordinate())) - (double)dotSize/2);
                double yDraw = ((yPixel) + ((1/(double)(gridDimension - 1)) * (double)gridPixelSize * gridDotI.getYCoordinate())) - (double)dotSize/2;
                g.drawRect((int)xDraw, (int)yDraw, dotSize, dotSize, Color.rgb(20, 20, 20));
            }
            for(int i = 0; i < gridlines.size(); i++)
            {
                GridLine gridLineI = gridlines.get(i);
                double xDrawStart = xPixel + (gridLineI.getXStart() * ((double)gridPixelSize/(double)(gridDimension - 1)));
                double yDrawStart = yPixel + (gridLineI.getYStart() * ((double)gridPixelSize/(double)(gridDimension - 1)));
                double xDrawEnd = xPixel + (gridLineI.getXEnd() * ((double)gridPixelSize/(double)(gridDimension - 1)));
                double yDrawEnd = yPixel + (gridLineI.getYEnd() * ((double)gridPixelSize/(double)(gridDimension - 1)));
                g.drawLine((int)xDrawStart, (int)yDrawStart, (int)xDrawEnd, (int)yDrawEnd, Color.rgb(gridLineI.getRed(), gridLineI.getGreen(), gridLineI.getBlue()));
            }
            double xDraw = xPixel + ((1/(double)(gridDimension - 1)) * (double)snake.getXCoordinate()) * (double)gridPixelSize - (double)(snake.getSize())/2;
            double yDraw = yPixel + ((1/(double)(gridDimension - 1)) * (double)snake.getYCoordinate()) * (double)gridPixelSize - (double)(snake.getSize())/2;
            g.drawRect((int)xDraw, (int)yDraw, snake.getSize(), snake.getSize(), Color.rgb(red, green, blue));
        }
    }

    public void moveSnake(Direction directionToMove, User userCurrent)
    {
        if(!snake.collideWall(gridDimension))
        {
            snake.move(directionToMove, griddots, gridlines, gridDimension, userCurrent);
        }
        else
        {
            snake.setCrash(true);
        }
    }

    public void setSnakeCoordinate(int x, int y)
    {
        snake.setXCoordinate(x);
        snake.setYCoordinate(y);
    }

    public ArrayList<Integer> getSnakeCoordinate()
    {
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(snake.getXCoordinate());
        coordinates.add(snake.getYCoordinate());
        return coordinates;
    }

    public boolean getCrash()
    {
        return this.snake.getCrash();
    }

    public void setSnakeCrash(boolean setCrash)
    {
        this.snake.setCrash(setCrash);
    }

    public boolean getDisplay()
    {
        return this.display;
    }

    public ArrayList<GridDot> getGridDots()
    {
        return this.griddots;
    }

    public ArrayList<GridLine> getGridLines()
    {
        return this.gridlines;
    }

    public Snake getSnake()
    {
        return this.snake;
    }

    public int getXPixel()
    {
        return this.xPixel;
    }

    public int getYPixel()
    {
        return this.yPixel;
    }

    public int getPixelSize()
    {
        return this.gridPixelSize;
    }

    public int getDotSize()
    {
        return this.dotSize;
    }

    public int getSnakeSize()
    {
        return this.snakeSize;
    }

    public int getGridDimension()
    {
        return this.gridDimension;
    }

    public void recreate(int newGridDimension)
    {
        this.gridDimension = newGridDimension;
        restart();
        initialize();
    }

}
