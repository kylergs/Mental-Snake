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
    private boolean display;
    private int stringWidth;
    private boolean active;

    public Button (int xToSet, int yToSet, String string, int stringWidth)
    {
        this.xCoordinate = xToSet;
        this.yCoordinate = yToSet;
        this.string = string;
        this.display = true;
        this.stringWidth = stringWidth;
        this.active = false;
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

    public int getStringWidth()
    {
        return this.stringWidth;
    }

    public void doAction(GameScreen screen, Menu currentMenu, User userCurrent)
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

    public void setDisplay(boolean displayToSet)
    {
        this.display = displayToSet;
    }

    public boolean getDisplay()
    {
        return this.display;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public boolean getActive()
    {
        return this.active;
    }
}
