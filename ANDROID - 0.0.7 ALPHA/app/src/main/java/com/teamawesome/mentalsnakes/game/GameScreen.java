package com.teamawesome.mentalsnakes.game;

import android.graphics.Typeface;

import com.teamawesome.mentalsnakes.framework.Graphics;
import com.teamawesome.mentalsnakes.framework.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 22/06/2015.
 */
public class GameScreen
{
    private Grid grid;
    private ArrayList<Menu> menus = new ArrayList<Menu>();
    private ArrayList<User> users = new ArrayList<User>();
    private int userCurrentPlayerNumber = 0;

    public GameScreen(Grid grid)
    {
        this.grid = grid;
    }

    public void restartGrid()
    {
        grid.restart();
    }

    public void recreateGrid(int gridDimensionToSet)
    {
        grid.recreate(gridDimensionToSet);
    }

    public void restartUsers(int numUsers)
    {
        users.clear();
        addUsers(numUsers);
    }

    public int getUsersSize()
    {
        return users.size();
    }

    public void addMenu(Menu menuToAdd)
    {
        menus.add(menuToAdd);
    }

    public void addUsers(int usersToAdd)
    {
        switch(usersToAdd)
        {
            case 5 :
                User user5 = new User(76, 175, 80);
                users.add(user5);
            case 4 :
                User user4 = new User(255, 160, 0);
                users.add(user4);
            case 3 :
                User user3 = new User(123, 31, 162);
                users.add(user3);
            case 2 :
                User user2 = new User(244, 67, 54);
                users.add(user2);
            case 1 :
                User user1 = new User(0, 151, 167);
                users.add(user1);
                break;

        }
    }

    public void refresh(List<Input.TouchEvent> touchevents, Graphics g, Typeface font, int strokeWidth, int red, int green, int blue)
    {
        grid.draw(g, red, green, blue);
        User userCurrent = getUserCurrent();
        for(int i = 0; i < menus.size(); i++)
        {
            Menu menuI = menus.get(i);
            menuI.drawButtons(g, font, strokeWidth, userCurrent.getRed(), userCurrent.getGreen(), userCurrent.getBlue());
            menuI.menuDoPressAction(touchevents, this, userCurrent, userCurrentPlayerNumber);
        }
    }

    public User getUserCurrent()
    {
        User userCurrent = users.get(userCurrentPlayerNumber);
        return userCurrent;
    }

    public boolean checkCrash()
    {
        boolean checkCrash = grid.getCrash();
        return checkCrash;
    }

    public void setGridDisplay(boolean display)
    {
        grid.setDisplay(display);
    }


    public void increaseUserPlayerNumber(int increase)
    {
        this.userCurrentPlayerNumber = userCurrentPlayerNumber + increase;
        if(userCurrentPlayerNumber == users.size())
        {
            userCurrentPlayerNumber = 0;
        }
    }

    public void gridMoveSnake(Direction directionToMove, User userCurrent)
    {
        grid.moveSnake(directionToMove, userCurrent);
    }

}
