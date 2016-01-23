package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;

import com.teamawesome.mentalsnakes.framework.Graphics;
import com.teamawesome.mentalsnakes.framework.Pixmap;

/**
 * Created by Ben on 19/06/2015.
 * This class simply contains the ability to set the backdrop color behind the game
 */
public class Backdrop
{
    private int width; //Width of the screen
    private int height; //Height of the screen
    private int red; //Color values for the backdrop
    private int green;
    private int blue;
    private static Pixmap image; //Should an image be used, then it can be added here
    //TODO Add Pixmap background ability

    public Backdrop (int w, int h, int r, int g, int b)
    {
        this.width = w;
        this.height = h;
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    /*
     * The method is pre-set up for catching an exception for there being no background image
     */
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

    public void setPixmap(Pixmap imageToSet)
    {
        this.image = imageToSet;
    }

}
