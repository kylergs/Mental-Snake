package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;
import android.graphics.Typeface;

import com.teamawesome.mentalsnakes.framework.Graphics;
import com.teamawesome.mentalsnakes.framework.Input;

import java.util.List;

/**
 * Created by Ben on 19/06/2015.
 * This is a parent class of all the buttons which are used onscreen
 * It contains the basic set-up of methods
 */
public class Button
{
    private int xCoordinate; //The coordinates within the Menu object for the button
    private int yCoordinate;
    private double buttonWidth; //TODO Move the width and height into the containing Menu class
    private double buttonHeight;
    private String string; //The string to be displayed on the button
    private boolean display;
    private int stringWidth;
    private boolean active; //Whether the button has been selected (IE: Colored in)

    public Button (int xToSet, int yToSet, String string, int stringWidth, int buttonWidth, int buttonHeight)
    {
        this.xCoordinate = xToSet;
        this.yCoordinate = yToSet;
        this.string = string;
        this.display = true;
        this.stringWidth = stringWidth;
        this.active = false;
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;
    }

    /*
     * Contains the method for drawing the button
     * TODO Move this into the Menu class also
     */
    public void draw(Graphics g, Typeface font, int strokeWidth, int menuX, int menuY, int red, int green, int blue, int faintGrey)
    {
        int drawX = (int) (menuX + (xCoordinate * buttonWidth));;
        int drawY = (int) (menuY + (double)yCoordinate * buttonHeight);
        double stringLength = string.length();

        if(display && active)
        {
            g.drawRect(drawX, drawY, (int) buttonWidth, (int) buttonHeight, Color.rgb(red, green, blue));
            g.drawText(string, font, drawX + (int)(buttonWidth * (1 - (stringLength) / stringWidth ) / 2), drawY + (int) (6 * buttonHeight / 8), 3 * (int) (buttonHeight / 4), strokeWidth, Color.rgb(220, 220, 220));
        }
        else if(display && !active)
        {
            g.drawRect(drawX, drawY, (int) buttonWidth, (int) buttonHeight, Color.rgb(faintGrey, faintGrey, faintGrey));
            g.drawText(string, font, drawX + (int)(buttonWidth * (1 - (stringLength) / stringWidth ) / 2), drawY + (int) (6 * buttonHeight / 8), 3 * (int) (buttonHeight / 4), strokeWidth, Color.rgb(220, 220, 220));
        }
    }

    public int getStringWidth()
    {
        return this.stringWidth;
    }

    public int getXCoordinate()
    {
        return this.xCoordinate;
    }

    public int getYCoordinate()
    {
        return this.yCoordinate;
    }

    public double getWidth()
    {
        return this.buttonWidth;
    }

    public double getHeight()
    {
        return this.buttonHeight;
    }

    /*
     * This method simply sets up the container structure for all daughter button classes
     */
    public void doAction(GameScreen screen, Menu currentMenu, User userCurrent)
    {

    }

    public void setMove(boolean setMove) {
    }

    public String getString()
    {
        return this.string;
    }

    public void setString(String stringToSet)
    {
        this.string = stringToSet;
    }

    /*
     * Whether the button should be displayed (used mainly in the Reset button)
     */
    public void setDisplay(boolean displayToSet)
    {
        this.display = displayToSet;
    }

    public boolean getDisplay()
    {
        return this.display;
    }

    /*
     * Code concerning whether the button has been selected
     * (Used mainly in the User and Grid menus)
     */
    public void setActive(boolean active)
    {
        this.active = active;
    }

    public boolean getActive()
    {
        return this.active;
    }

    public void toggleActive()
    {
        active = !active;
    }
}
