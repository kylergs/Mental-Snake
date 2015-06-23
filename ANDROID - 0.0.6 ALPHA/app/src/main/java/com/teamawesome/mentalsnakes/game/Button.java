package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;

import com.teamawesome.mentalsnakes.framework.Graphics;
import com.teamawesome.mentalsnakes.framework.Input;

import java.util.List;

/**
 * Created by Ben on 19/06/2015.
 */
public class Button
{
    private int xCoordinate;
    private int yCoordinate;
    private String string;

    public Button (int xToSet, int yToSet, String string)
    {
        this.xCoordinate = xToSet;
        this.yCoordinate = yToSet;
        this.string = string;
    }

    public int getXCoordinate()
    {
        return this.xCoordinate;
    }

    public int getYCoordinate()
    {
        return this.yCoordinate;
    }

    public void setXCoordinate(int xCoordinateToSet)
    {
        this.xCoordinate = xCoordinateToSet;
    }

    public void setYCoordinate(int yCoordinateToSet)
    {
        this.yCoordinate = yCoordinateToSet;
    }

    public void doAction(GameScreen screen, User userCurrent)
    {

    }

    public String getString()
    {
        return this.string;
    }

    public void setString(String stringToSet)
    {
        this.string = stringToSet;
    }
}
