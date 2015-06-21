package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;

import com.teamawesome.mentalsnakes.framework.Graphics;

/**
 * Created by Ben on 19/06/2015.
 */
public class Button
{
    private IBehavior behavior;
    private int xCoordinate;
    private int yCoordinate;

    public Button (IBehavior behavior, int xToSet, int yToSet)
    {
        this.behavior = behavior;
        this.xCoordinate = xToSet;
        this.yCoordinate = yToSet;
    }

    public void setBehavior(IBehavior behaviorToSet)
    {
        this.behavior = behaviorToSet;
        behavior.setButton(this);
    }

    public int getXCoordinate()
    {
        return this.xCoordinate;
    }

    public int getYCoordinate()
    {
        return this.yCoordinate;
    }

}
