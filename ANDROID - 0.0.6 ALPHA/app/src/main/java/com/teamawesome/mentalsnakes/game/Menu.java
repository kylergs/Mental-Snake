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
    private double buttonWidth;
    private double buttonHeight;
    private Orientation orientation;
    private int numButtons;

    public Menu(int numButtons, int menuX, int menuY, int menuWidth, int menuHeight, int buttonWidth, int buttonHeight)
    {
        this.menuX = menuX;
        this.menuY = menuY;
        this.menuWidth = menuWidth;
        this.menuHeight = menuHeight;
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;
        this.orientation = orientation;
        this.numButtons = numButtons;
    }

    public void addButton(Button buttonI)
    {
        buttons.add(buttonI);
    }

    public void drawButtons(Graphics g, Typeface font, int strokeWidth, int red, int green, int blue) {
        for (int i = 0; i < buttons.size(); i++) {
            Button buttonI = buttons.get(i);
            int drawX = (int) (menuX + (double) (buttonI.getXCoordinate()) * buttonWidth);
            int drawY = (int) (menuY + (double) (buttonI.getYCoordinate()) * buttonHeight);
            g.drawRect(drawX, drawY, (int) buttonWidth, (int) buttonHeight, Color.rgb(red, green, blue));
            g.drawText(buttonI.getString(), font, drawX, drawY + (int) (6 * buttonHeight / 8), 3 * (int) (buttonHeight / 4), strokeWidth, Color.rgb(220, 220, 220));
        }
    }

    public void clearButtons()
    {
        buttons.clear();
    }

    public void menuDoPressAction(List<Input.TouchEvent> touchevents, GameScreen screen, User userCurrent, int userCurrentID)
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
                    buttonI.doAction(screen, userCurrent);
                }
            }
        }
    }
    private boolean touchButtonCheck(Input.TouchEvent touchEventJ, Button buttonI)
    {
        boolean touchbutton = false;
        double buttonIXLeft = menuX + (buttonWidth * (double)buttonI.getXCoordinate());
        double buttonIXRight = buttonIXLeft + buttonWidth;
        double buttonIYTop = menuY + (buttonHeight * (double)buttonI.getYCoordinate());
        double buttonIYBottom = buttonIYTop + buttonHeight;

        if(touchEventJ.x > buttonIXLeft
                && touchEventJ.x < buttonIXRight
                && touchEventJ.y > buttonIYTop
                && touchEventJ.y < buttonIYBottom)
        {
            touchbutton = true;
        }

        return touchbutton;
    }


}
