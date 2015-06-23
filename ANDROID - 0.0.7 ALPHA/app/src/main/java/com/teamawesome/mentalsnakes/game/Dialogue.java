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
    private ArrayList<String> strings = new ArrayList<String>();

    public Dialogue (int xBox, int yBox)
    {
        this.xBox = xBox;
        this.yBox = yBox;
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

    public void drawStrings(Graphics g, Typeface font, int fontSize, int strokeWidth, int red, int green, int blue, int screenWidth, int screenHeight)
    {
        int boxSize = fontSize * strings.size();
        g.drawRect(xBox - 20, yBox - 20, screenWidth - xBox + 20, screenHeight + 20 - yBox, Color.rgb(red, green, blue));
        g.drawRect(xBox, yBox, screenWidth - xBox, screenHeight - yBox, Color.rgb(220, 220, 220));
        for(int i = 0; i < strings.size(); i++)
        {
            String stringI = strings.get(i);
            g.drawText(stringI, font, xBox, yBox + (i * fontSize), fontSize, strokeWidth, Color.rgb(red, green, blue));
        }
    }

}
