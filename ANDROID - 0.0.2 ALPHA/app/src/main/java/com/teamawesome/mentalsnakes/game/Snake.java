package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 19/06/2015.
 */
public class Snake
{

    private boolean display;
    private int xCoordinate;
    private int yCoordinate;

    public Snake(int xStart, int yStart)
    {
        this.display = false;
        this.xCoordinate = xStart;
        this.yCoordinate = yStart;
    }

    public void move(EnumHolder.Direction directionToGo)
    {
        switch (directionToGo)
        {
            case NORTH :
                moveStep(0, -1);
                break;
            case EAST :
                moveStep(1, 0);
                break;
            case SOUTH :
                moveStep(0, 1);
                break;
            case WEST :
                moveStep(-1, 0);
                break;
        }
    }

    private void moveStep(int xMove, int yMove)
    {
        this.xCoordinate = this.xCoordinate - xMove;
        this.yCoordinate = this.yCoordinate - yMove;
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

}



