package com.teamawesome.mentalsnakes.game;

/**
* Created by Ben on 21/06/2015.
*/
public class Move implements IBehavior
{
    private Button button;
    private Direction direction;

    public void doAction(Grid grid)
    {
        grid.moveSnake(direction);
    }

    public Button getButton()
    {
         return button;
    }

    public void setButton(Button button)
    {
        this.button = button;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

}
