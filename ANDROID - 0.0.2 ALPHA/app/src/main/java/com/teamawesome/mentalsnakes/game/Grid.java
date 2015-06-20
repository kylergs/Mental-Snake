package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;

import com.teamawesome.mentalsnakes.framework.Graphics;

import java.util.ArrayList;

/**
 * Created by Ben on 20/06/2015.
 */
public class Grid
{
    private int xPixel;
    private int yPixel;
    private int gridPixelSize;
    private int gridDimension;
    private int dotSize;
    private boolean display;
    private ArrayList<GridDot> griddots = new ArrayList<GridDot>();
    private ArrayList<GridLine> gridlines = new ArrayList<GridLine>();

    public Grid (int x, int y, int gridPixelSize, int gridDimension, int dotSize)
    {
        this.xPixel = x;
        this.yPixel = y;
        this.gridPixelSize = gridPixelSize;
        this.gridDimension = gridDimension;
        this.dotSize = dotSize;
        this.display = false;
        createDots();
    }

    public void createDots()
    {
        for(int i = 0; i < gridDimension * gridDimension; i++)
        {
            GridDot gridDotToAdd = new GridDot(i - (int)(i/gridDimension) * gridDimension, (int)(i/gridDimension));
            griddots.add(gridDotToAdd);
        }
    }

    public void setDisplay(boolean set)
    {
        this.display = set;
    }

    public void draw(Graphics g)
    {
        if(display)
        {
            for(int i = 0; i < griddots.size(); i++)
            {
                GridDot gridDotI = griddots.get(i);
                int xICoordinate = gridDotI.getXCoordinate();
                int yICoordinate = gridDotI.getYCoordinate();
                double xDraw = ((xPixel) + ((1/(double)gridDimension) * (double)gridPixelSize * xICoordinate));
                double yDraw = ((yPixel) + ((1/(double)gridDimension) * (double)gridPixelSize * yICoordinate));
                g.drawRect((int)xDraw, (int)yDraw, dotSize, dotSize, Color.rgb(20, 20, 20));
            }
        }
    }

    public void recreate(int newGridDimension)
    {
        griddots.clear();
        gridlines.clear();
        this.gridDimension = newGridDimension;
        createDots();
    }

}
