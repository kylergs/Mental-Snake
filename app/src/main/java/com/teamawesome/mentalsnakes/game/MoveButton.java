package com.teamawesome.mentalsnakes.game;

import com.teamawesome.mentalsnakes.framework.Screen;

/**
 * Created by Ben on 22/06/2015.
 */
public class MoveButton extends Button
{
    private Direction direction;

    public MoveButton(int xToSet, int yToSet, Direction direction, int stringWidth, int buttonWidth, int buttonHeight)
    {
        super(xToSet, yToSet, "" + direction, stringWidth, buttonWidth, buttonHeight);
        this.direction = direction;
        this.setActive(true);
    }

    @Override
    public void doAction(GameScreen screen, Menu menuCurrent, User userCurrent)
    {
        if(getActive() && screen.getNumPlayingUsers() != 0)
        {
            screen.gridMoveSnake(direction, userCurrent);
            screen.increaseUserPlayerNumber(1);
        }
        halRemember(screen, direction);
    }

    /*
     * HAL is given the information about the moves by the other users
     */
    public void halRemember(GameScreen screen, Direction direction)
    {
        for(int i = 0; i < screen.getUsersSize(); i++)
        {
            User userI = screen.getUser(i);
            if(userI instanceof HAL)
            {
                HAL hal = (HAL)userI;
                hal.remember(direction);
            }
        }
    }

    @Override
    public void setMove(boolean setMove)
    {
        setActive(setMove);
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
