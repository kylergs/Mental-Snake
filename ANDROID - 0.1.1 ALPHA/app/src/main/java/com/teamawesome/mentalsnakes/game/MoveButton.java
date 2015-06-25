package com.teamawesome.mentalsnakes.game;

import com.teamawesome.mentalsnakes.framework.Screen;

/**
 * Created by Ben on 22/06/2015.
 */
public class MoveButton extends Button
{
    private Direction direction;

    public MoveButton(int xToSet, int yToSet, Direction direction, int stringWidth)
    {
        super(xToSet, yToSet, "" + direction, stringWidth);
        this.direction = direction;
    }

    @Override
    public void doAction(GameScreen screen, User userCurrent)
    {
        screen.gridMoveSnake(direction, userCurrent);
        screen.increaseUserPlayerNumber(1);
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
