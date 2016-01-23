package com.teamawesome.mentalsnakes.game;

import java.util.ArrayList;

/**
 * Created by Ben on 19/06/2015.
 */
public class Snake
{
    private int xCoordinate;
    private int yCoordinate;
    private int size;
    private boolean crash;

    public Snake(int startCoordinate, int size)
    {
        this.xCoordinate = startCoordinate;
        this.yCoordinate = startCoordinate;
        this.size = size;
        this.crash = false;
    }

    public Snake(Snake snake)
    {
        this(snake.xCoordinate, snake.size);
    }

    public void move(Direction directionToGo, ArrayList<GridDot> griddots, ArrayList<GridLine> gridlines, int gridDimension, User userCurrent)
    {
        switch (directionToGo)
        {
            case UP:
                moveStep(0, -1, griddots, gridlines, gridDimension, userCurrent);
                break;
            case RIGHT:
                moveStep(1, 0, griddots, gridlines, gridDimension, userCurrent);
                break;
            case DOWN:
                moveStep(0, 1, griddots, gridlines, gridDimension, userCurrent);
                break;
            case LEFT:
                moveStep(-1, 0, griddots, gridlines, gridDimension, userCurrent);
                break;
        }
    }

    private void moveStep(int xMove, int yMove, ArrayList<GridDot> griddots, ArrayList<GridLine> gridlines, int gridDimension, User userCurrent)
    {
        GridDot gridDotCurrent = griddots.get(xCoordinate + gridDimension * yCoordinate);
        this.xCoordinate = this.xCoordinate + xMove;
        this.yCoordinate = this.yCoordinate + yMove;
        if(!collideWall(gridDimension))
        {
            GridDot gridDotDestination = griddots.get(xCoordinate + gridDimension * yCoordinate);
            GridLine gridLineMove = new GridLine (gridDotCurrent.getXCoordinate(), gridDotCurrent.getYCoordinate(), gridDotDestination.getXCoordinate(), gridDotDestination.getYCoordinate());
            gridLineMove.setColor(userCurrent.getRed(), userCurrent.getGreen(), userCurrent.getBlue());
            gridlines.add(gridLineMove);
            if(!gridDotDestination.getConnected())
            {
                gridDotDestination.connect();
            }
            else
            {
                crashGridDot();
            }
        }
        else
        {
            this.xCoordinate = this.xCoordinate - xMove;
            this.yCoordinate = this.yCoordinate - yMove;
        }
    }

    public boolean collideWall(int gridDimension)
    {
        boolean collide = false;
        if(xCoordinate >= gridDimension
                || yCoordinate >= gridDimension
                || xCoordinate < 0
                || yCoordinate < 0)
        {
            collide = true;
            this.crash = true;
        }
        return collide;
    }

    public void crashGridDot()
    {
        this.crash = true;
    }

    public boolean getCrash()
    {
        return this.crash;
    }

    public void setCrash(boolean setCrash)
    {
        this.crash = setCrash;
    }

    public int getXCoordinate()
    {
        return this.xCoordinate;
    }

    public int getYCoordinate()
    {
        return this.yCoordinate;
    }

    public void setXCoordinate(int x)
    {
        this.xCoordinate = x;
    }

    public void setYCoordinate(int y)
    {
        this.yCoordinate = y;
    }

    public int getSize()
    {
        return this.size;
    }

}



