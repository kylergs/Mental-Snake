package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 23/06/2015.
 */
public class GridSizeButton extends Button
{
    private int gridSize;

    public GridSizeButton (int x, int y, int gridSize, int stringWidth, int buttonWidth, int buttonHeight)
    {
        super(x, y, "" + gridSize, stringWidth, buttonWidth, buttonHeight);
        this.gridSize = gridSize;
    }

    @Override
    public void doAction(GameScreen screen, Menu currentMenu, User userCurrent)
    {
        screen.recreateGrid(gridSize);
        screen.restartUsers();
        currentMenu.setButtonsActive(false);
        this.setActive(true);
    }

}
