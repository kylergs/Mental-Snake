package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;

import com.teamawesome.mentalsnakes.framework.Graphics;

import java.util.ArrayList;

/**
 * Created by Ben on 21/06/2015.
 */
public class Menu
{
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private int menuX;
    private int menuY;
    private int menuWidth;
    private int menuHeight;
    private Orientation orientation;

    public Menu (int numMoveButtons, int menuX, int menuY, int menuWidth, int menuHeight, Orientation orientation)
    {
        this.menuX = menuX;
        this.menuY = menuY;
        this.menuWidth = menuWidth;
        this.menuHeight = menuHeight;
        for(int i = 0; i < numMoveButtons; i++)
        {
            Button buttonI = new Button (new Move(), i, 0);
            buttons.add(buttonI);
        }
        this.orientation = orientation;
    }

    public void drawButtons(Graphics g, int red, int green, int blue)
    {
        switch(orientation)
        {
            case VERTICAL :
                drawButtonsVertical(g, red, green, blue);
                break;
            case HORIZONTAL :
                drawButtonsHorizontal(g, red, green, blue);
                break;
        }
    }


    private void drawButtonsVertical(Graphics g, int red, int green, int blue)
    {
        for(int i = 0; i < buttons.size(); i++)
        {
            Button buttonI = buttons.get(i);
            g.drawRect(menuX, menuY + (int)((double)buttonI.getYCoordinate() * ((double)menuHeight/(double)buttons.size())), menuWidth, menuHeight, Color.rgb(red, green, blue));
        }
    }

    private void drawButtonsHorizontal(Graphics g, int red, int green, int blue)
    {
        for(int i = 0; i < buttons.size(); i++)
        {
            Button buttonI = buttons.get(i);
            g.drawRect(menuX + (int)((double)buttonI.getXCoordinate() * ((double)menuWidth/(double)buttons.size())), menuY, menuWidth, menuHeight, Color.rgb(red, green, blue));
        }
    }


    public void clearButtons()
    {
        buttons.clear();
    }

}
