package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 25/06/2015.
 */
public class IntroButton extends Button
{
    private int mode;

    public IntroButton (int xToSet, int yToSet, String string, int stringWidth)
    {
        super(xToSet, yToSet, string, stringWidth);
        this.mode = 0;
        this.setActive(true);
    }

    @Override
    public void doAction(GameScreen screen, Menu currentMenu, User userCurrent)
    {
        this.mode = 1;
    }

    public int getMode()
    {
        return this.mode;
    }

}
