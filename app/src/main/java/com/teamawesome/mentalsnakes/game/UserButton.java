package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;
import android.graphics.Typeface;

import com.teamawesome.mentalsnakes.framework.Graphics;

/**
 * Created by Ben on 23/06/2015.
 */
public class UserButton extends Button
{
    private int numUser;
    private int red;
    private int green;
    private int blue;

    public UserButton (int x, int y, int numUser, String userLabel, int stringWidth, int buttonWidth, int buttonHeight, int red, int green, int blue)
    {
        super(x, y, userLabel, stringWidth, buttonWidth, buttonHeight);
        this.numUser = numUser;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public void doAction(GameScreen screen, Menu currentMenu, User userCurrent)
    {
        this.toggleActive();
        screen.togglePlayingUser(numUser, getActive());
        screen.restartGrid();
    }

    @Override
    public void draw(Graphics g, Typeface font, int strokeWidth, int menuX, int menuY, int red, int green, int blue, int faintGrey)
    {
        int drawX = (int) (menuX + (this.getXCoordinate() * this.getWidth()));;
        int drawY = (int) (menuY + (double)this.getYCoordinate() * this.getWidth());
        double stringLength = this.getString().length();

        if(this.getDisplay() && this.getActive())
        {
            g.drawRect(drawX, drawY, (int) this.getWidth(), (int) this.getHeight(), Color.rgb(this.red, this.green, this.blue));
            g.drawText(this.getString(), font, drawX + (int)(this.getWidth() * (1 - (stringLength) / this.getStringWidth() ) / 2), drawY + (int) (6 * this.getHeight() / 8), 3 * (int) (this.getHeight() / 4), strokeWidth, Color.rgb(220, 220, 220));
        }
        else if(this.getDisplay() && !this.getActive())
        {
            g.drawRect(drawX, drawY, (int) this.getWidth(), (int) this.getHeight(), Color.rgb(faintGrey, faintGrey, faintGrey));
            g.drawText(this.getString(), font, drawX + (int)(this.getWidth() * (1 - (stringLength) / this.getStringWidth() ) / 2), drawY + (int) (6 * this.getHeight() / 8), 3 * (int) (this.getHeight() / 4), strokeWidth, Color.rgb(220, 220, 220));
        }
    }

}
