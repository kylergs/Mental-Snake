package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 22/06/2015.
 */
public class MoveButton extends Button
{
    private Direction direction;

    public MoveButton(int xToSet, int yToSet, Direction direction)
    {
        super(xToSet, yToSet, "" + direction);
        this.direction = direction;
    }

    @Override
    public void doAction(Grid grid, User userCurrent)
    {
        grid.moveSnake(direction, userCurrent);
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public Direction getDirection()
    {
        return this.direction;
    }

}
