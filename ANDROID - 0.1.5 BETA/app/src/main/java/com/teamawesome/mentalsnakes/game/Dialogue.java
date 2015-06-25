package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;
import android.graphics.Typeface;

import com.teamawesome.mentalsnakes.framework.Graphics;

import java.util.ArrayList;

/**
 * Created by Ben on 23/06/2015.
 */
public class Dialogue
{
    private int xBox;
    private int yBox;
    private int width;
    private int height;
    private ArrayList<String> strings = new ArrayList<String>();

    public Dialogue (int xBox, int yBox, int width, int height)
    {
        this.xBox = xBox;
        this.yBox = yBox;
        this.width = width;
        this.height = height;
    }

    public void addString(String stringToAdd)
    {
        strings.add(stringToAdd);
    }

    public void clearStrings()
    {
        strings.clear();
    }

    public ArrayList<String> getStrings()
    {
        return this.strings;
    }

    public void draw(Graphics g, Typeface font, int fontSize, int strokeWidth, int red, int green, int blue, int faintGrey)
    {
        int boxSize = fontSize * (strings.size() + 2);
        g.drawRect(xBox - 20, yBox - 20, width - (2 * (xBox - 20)), boxSize, Color.rgb(red, green, blue));
        g.drawRect(xBox, yBox, width - (2 * xBox), boxSize - 40, Color.rgb(faintGrey, faintGrey, faintGrey));
        for(int i = 0; i < strings.size(); i++)
        {
            String stringI = strings.get(i);
            g.drawText(stringI, font, xBox, yBox + ((i + 1) * fontSize), fontSize, strokeWidth, Color.rgb(red, green, blue));
        }
    }

}
