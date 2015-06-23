package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 23/06/2015.
 */
public class GridSizeButton extends Button
{
    private int gridSize;

    public GridSizeButton (int x, int y, int gridSize)
    {
        super(x, y, "" + gridSize);
        this.gridSize = gridSize;
    }

    @Override
    public void doAction(GameScreen screen, User currentUser)
    {
        screen.recreateGrid(gridSize);
        screen.restartUsers(screen.getUsersSize());
    }

}
