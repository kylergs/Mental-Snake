package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 23/06/2015.
 */
public class GridSizeButton extends Button
{
    private int gridSize;

    public GridSizeButton (int x, int y, int gridSize, int stringWidth)
    {
        super(x, y, "" + gridSize, stringWidth);
        this.gridSize = gridSize;
    }

    @Override
    public void doAction(GameScreen screen, Menu currentMenu, User userCurrent)
    {
        screen.recreateGrid(gridSize);
        screen.restartUsers(screen.getUsersSize());
        currentMenu.setButtonsActive(false);
        this.setActive(true);
    }

}
