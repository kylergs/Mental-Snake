package com.teamawesome.mentalsnakes.game;

import com.teamawesome.mentalsnakes.framework.Graphics;

import java.util.ArrayList;

/**
 * Created by Ben on 19/06/2015.
 */
public class Snake
{

    private boolean display;
    private int xCoordinate;
    private int yCoordinate;
    private int size;

    public Snake(int startCoordinate)
    {
        this.display = false;
        this.xCoordinate = startCoordinate;
        this.yCoordinate = startCoordinate;
        this.size = 20;
    }

    public void move(Direction directionToGo, ArrayList<GridDot> griddots, ArrayList<GridLine> gridlines, int gridDimension)
    {
        switch (directionToGo)
        {
            case NORTH :
                moveStep(0, -1, griddots, gridlines, gridDimension);
                break;
            case EAST :
                moveStep(1, 0, griddots, gridlines, gridDimension);
                break;
            case SOUTH :
                moveStep(0, 1, griddots, gridlines, gridDimension);
                break;
            case WEST :
                moveStep(-1, 0, griddots, gridlines, gridDimension);
                break;
        }
    }

    private void moveStep(int xMove, int yMove, ArrayList<GridDot> griddots, ArrayList<GridLine> gridlines, int gridDimension)
    {
        GridDot gridDotCurrent = griddots.get(xCoordinate + gridDimension * yCoordinate);
        this.xCoordinate = this.xCoordinate - xMove;
        this.yCoordinate = this.yCoordinate - yMove;
        GridDot gridDotDestination = griddots.get(xCoordinate + gridDimension * yCoordinate);
        gridDotDestination.connect();
        GridLine gridLineMove = new GridLine (gridDotCurrent.getXCoordinate(), gridDotCurrent.getYCoordinate(), gridDotDestination.getXCoordinate(), gridDotDestination.getYCoordinate());
        gridlines.add(gridLineMove);
    }

    private boolean collideWall(int gridSize)
    {
        boolean collide = false;
        if(xCoordinate > gridSize
                || yCoordinate > gridSize
                || xCoordinate < 0
                || yCoordinate < 0)
        {
            collide = true;
        }
        return collide;
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



