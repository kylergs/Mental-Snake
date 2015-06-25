package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 23/06/2015.
 */
public class UserButton extends Button
{
    private int numUsers;

    public UserButton (int x, int y, int numUsers, int stringWidth)
    {
        super(x, y, "" + numUsers, stringWidth);
        this.numUsers = numUsers;
    }

    @Override
    public void doAction(GameScreen screen, Menu currentMenu, User userCurrent)
    {
        screen.restartGrid();
        screen.restartUsers();
        screen.resetUsers(numUsers);
        currentMenu.setButtonsActive(false);
        this.setActive(true);
    }

}
