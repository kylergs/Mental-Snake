package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 19/06/2015.
 */
public class GridDot
{
    private int xCoordinate;
    private int yCoordinate;
    private boolean connected;

    public GridDot(int x, int y)
    {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.connected = false;
    }

    public boolean isConnected()
    {
        return connected;
    }

    public void SetConnect(boolean set)
    {
        this.connected = set;
    }

    public void Connect()
    {
        SetConnect(true);
    }

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate() {
        return this.yCoordinate;
    }
}
