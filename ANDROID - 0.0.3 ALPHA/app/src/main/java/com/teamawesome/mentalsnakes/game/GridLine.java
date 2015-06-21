package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;

import com.teamawesome.mentalsnakes.framework.Graphics;

/**
 * Created by Ben on 19/06/2015.
 */
public class GridLine
{
    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;
    private int red;
    private int green;
    private int blue;

    public GridLine(int x1, int y1, int x2, int y2)
    {
        this.xStart = x1;
        this.yStart = y1;
        this.xEnd = x2;
        this.yEnd = y2;
    }

    public void setColor(int redToSet, int greenToSet, int blueToSet)
    {
        this.red = redToSet;
        this.green = greenToSet;
        this.blue = blueToSet;
    }

    public void draw(Graphics g)
    {
        g.drawLine(xStart, yStart, xEnd, yEnd, Color.rgb(red, green, blue));
    }
}
