package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;
import android.graphics.Typeface;

import com.teamawesome.mentalsnakes.framework.Graphics;

/**
 * Created by Ben on 01/10/2015.
 */
public class Label
{
    private boolean active;
    private String labelText;

    public Label(String labelText)
    {
        this.active = true;
        this.labelText = labelText;
    }

    public void draw(int x, int y, Graphics g, Typeface font, int fontSize, int strokeWidth, int red, int green, int blue, int faintGrey)
    {
        g.drawText(labelText, font, x, y, fontSize, strokeWidth, Color.rgb(red, green, blue));
    }

    public boolean getActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }
}
