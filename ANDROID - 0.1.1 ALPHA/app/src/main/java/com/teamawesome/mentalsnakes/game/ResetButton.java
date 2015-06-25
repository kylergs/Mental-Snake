package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 23/06/2015.
 */
public class ResetButton extends Button
{

    public ResetButton (int x, int y, int stringWidth)
    {
        super(x, y, "RESET", stringWidth);
        this.setDisplay(false);
    }

    @Override
    public void doAction(GameScreen screen, User currentUser)
    {
        if(this.getDisplay())
        {
            screen.restartGrid();
            screen.restartUsers(screen.getUsersSize());
            this.setDisplay(false);
        }
    }

}
