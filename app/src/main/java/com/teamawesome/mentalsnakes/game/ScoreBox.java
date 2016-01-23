package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;
import android.graphics.Typeface;

import com.teamawesome.mentalsnakes.framework.Graphics;

/**
 * Created by Ben on 25/06/2015.
 */
public class ScoreBox
{
    private int x;
    private int y;

    public ScoreBox (int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public ScoreBox()
    {
        this(0, 0);
    }

    public void draw(Graphics g, Typeface font, int fontSize, int strokeWidth, int deltaX, int userScore, int red, int green, int blue)
    {
        g.drawText("" + userScore, font, x + deltaX, y, fontSize, strokeWidth, Color.rgb(red, green, blue));
    }

}
