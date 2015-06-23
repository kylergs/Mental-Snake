package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 21/06/2015.
 */
public interface IBehavior
{
    public void doAction(Grid grid);
    public Button getButton();
    public void setButton(Button button);
}
