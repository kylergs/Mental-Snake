package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 23/06/2015.
 */
public class ResetButton extends Button
{

    public ResetButton (int x, int y, int stringWidth)
    {
        super(x, y, "RESTART", stringWidth);
        this.setDisplay(false);
        this.setActive(true);
    }

    @Override
    public void doAction(GameScreen screen, Menu menuCurrent, User currentUser)
    {
        if(this.getDisplay())
        {
            screen.restartGrid();
            screen.restartUsers();
            this.setDisplay(false);
        }
    }

}
