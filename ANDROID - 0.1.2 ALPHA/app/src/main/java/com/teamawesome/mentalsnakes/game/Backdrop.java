package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;

import com.teamawesome.mentalsnakes.framework.Graphics;
import com.teamawesome.mentalsnakes.framework.Pixmap;

/**
 * Created by Ben on 19/06/2015.
 */
public class Backdrop
{
    private int width;
    private int height;
    private int red;
    private int green;
    private int blue;
    private static Pixmap image;

    public Backdrop (int w, int h, int r, int g, int b)
    {
        this.width = w;
        this.height = h;
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public void draw (Graphics g)
    {
        try
        {
            g.drawPixmap(this.image, 0, 0);
        }
        catch(NullPointerException e)
        {
            g.drawRect (0, 0, width + 1, height + 1, Color.rgb(red, green, blue));
        }
    }

}
