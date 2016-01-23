package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 28/06/2015.
 * This button will restart the user scores s.t. a game can be reset.
 */
public class ResetScoreButton extends Button
{
    public ResetScoreButton(int x, int y, String string, int stringWidth, int buttonWidth, int buttonHeight)
    {
        super(x, y, string, stringWidth, buttonWidth, buttonHeight);
    }

    @Override
    public void doAction(GameScreen screen, Menu currentMenu, User userCurrent)
    {
        screen.restartUsers();
    }
}
