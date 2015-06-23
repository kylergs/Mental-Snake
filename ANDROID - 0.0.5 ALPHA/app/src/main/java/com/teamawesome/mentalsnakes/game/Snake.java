package com.teamawesome.mentalsnakes.game;

import com.teamawesome.mentalsnakes.framework.Graphics;

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

    public Snake(int startCoordinate)
    {
        this.xCoordinate = startCoordinate;
        this.yCoordinate = startCoordinate;
        this.size = 20;
        this.crash = false;
    }

    public void move(Direction directionToGo, ArrayList<GridDot> griddots, ArrayList<GridLine> gridlines, int gridDimension, User userCurrent)
    {
        switch (directionToGo)
        {
            case NORTH :
                moveStep(0, -1, griddots, gridlines, gridDimension, userCurrent);
                break;
            case EAST :
                moveStep(1, 0, griddots, gridlines, gridDimension, userCurrent);
                break;
            case SOUTH :
                moveStep(0, 1, griddots, gridlines, gridDimension, userCurrent);
                break;
            case WEST :
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
    }

    public boolean collideWall(int gridDimension)
    {
        boolean collide = false;
        if(xCoordinate > gridDimension
                || yCoordinate > gridDimension
                || xCoordinate < 0
                || yCoordinate < 0)
        {
            collide = true;
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

    public int getSize()
    {
        return this.size;
    }

}



