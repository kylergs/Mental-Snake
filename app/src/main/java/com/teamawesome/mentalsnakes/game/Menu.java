package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;
import android.graphics.Typeface;

import com.teamawesome.mentalsnakes.framework.Graphics;
import com.teamawesome.mentalsnakes.framework.Input;

import java.util.ArrayList;
import java.util.List;

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
    private int numButtons;
    private Label label;

    public Menu(int numButtons, int menuX, int menuY, int menuWidth, int menuHeight)
    {
        this.menuX = menuX;
        this.menuY = menuY;
        this.menuWidth = menuWidth;
        this.menuHeight = menuHeight;
        this.numButtons = numButtons;
    }

    public void addButton(Button buttonI)
    {
        buttons.add(buttonI);
    }

    public void setLabel(Label labelToSet)
    {
        this.label = labelToSet;
    }

    public void draw(Graphics g, Typeface font, int strokeWidth, int red, int green, int blue, int faintGrey)
    {
        drawButtons(g, font, strokeWidth, red, green, blue, faintGrey);
        try
        {
            if(label.getActive())
            {
                drawLabel(g, font, strokeWidth, red, green, blue, faintGrey);
            }
        }
        catch(NullPointerException e)
        {

        }
    }

    public void drawButtons(Graphics g, Typeface font, int strokeWidth, int red, int green, int blue, int faintGrey)
    {
        for (int i = 0; i < buttons.size(); i++)
        {
            Button buttonI = buttons.get(i);
            buttonI.draw(g, font, strokeWidth, menuX, menuY, red, green, blue, faintGrey);
        }
    }

    public void drawLabel(Graphics g, Typeface font, int strokeWidth, int red, int green, int blue, int faintGrey)
    {
        label.draw(menuX, menuY - menuHeight / 6, g, font, menuHeight, strokeWidth, red, green, blue, faintGrey);
    }

    public void clearButtons()
    {
        buttons.clear();
    }

    public void menuDoPressAction(List<Input.TouchEvent> touchevents, GameScreen screen, Menu currentMenu, User userCurrent, int userCurrentID)
    {
        for(int i = 0; i < buttons.size(); i++)
        {
            Button buttonI = buttons.get(i);
            for(int j = 0; j < touchevents.size(); j++)
            {
                Input.TouchEvent touchEventJ = touchevents.get(j);
                if(touchButtonCheck(touchEventJ, buttonI)
                        && touchEventJ.type == touchEventJ.TOUCH_UP)
                {
                    buttonI.doAction(screen, currentMenu, userCurrent);
                }
            }
        }
    }

    private boolean touchButtonCheck(Input.TouchEvent touchEventJ, Button buttonI)
    {
        boolean touchbutton = false;
        double buttonIXLeft = menuX + (buttonI.getWidth() * (double)buttonI.getXCoordinate());
        double buttonIXRight = buttonIXLeft + buttonI.getWidth();
        double buttonIYTop = menuY + (buttonI.getHeight() * (double)buttonI.getYCoordinate());
        double buttonIYBottom = buttonIYTop + buttonI.getHeight();

        if(touchEventJ.x > buttonIXLeft
                && touchEventJ.x < buttonIXRight
                && touchEventJ.y > buttonIYTop
                && touchEventJ.y < buttonIYBottom)
        {
            touchbutton = true;
        }

        return touchbutton;
    }

    public Button getButton(int i)
    {
        return this.buttons.get(i);
    }

    public void setResetButtonDisplay(boolean display)
    {
        for(int i = 0; i < buttons.size(); i++)
        {
            Button buttonI = buttons.get(i);
            if(buttonI instanceof ResetButton)
            {
                buttonI.setDisplay(display);
            }
        }
    }

    public void setButtonsActive(boolean setActive)
    {
        for(int i = 0; i < buttons.size(); i++)
        {
            Button buttonI = buttons.get(i);
            buttonI.setActive(setActive);
        }
    }


    public void setMove(boolean setMove)
    {
        for(int i = 0; i < buttons.size(); i++)
        {
            Button buttonI = buttons.get(i);
            buttonI.setMove(setMove);
        }
    }
}
