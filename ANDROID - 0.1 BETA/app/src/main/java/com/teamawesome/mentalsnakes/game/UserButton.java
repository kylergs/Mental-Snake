package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 23/06/2015.
 */
public class UserButton extends Button
{
    private int numUsers;

    public UserButton (int x, int y, int numUsers)
    {
        super(x, y, "" + numUsers);
        this.numUsers = numUsers;
    }

    @Override
    public void doAction(GameScreen screen, User currentUser)
    {
        screen.restartGrid();
        screen.restartUsers(numUsers);
    }
}
