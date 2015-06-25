package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;
import android.graphics.Typeface;

import com.teamawesome.mentalsnakes.framework.Graphics;
import com.teamawesome.mentalsnakes.framework.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 22/06/2015.
 */
public class GameScreen {
    private Grid grid;
    private ArrayList<Menu> menus = new ArrayList<Menu>();
    private ArrayList<User> users = new ArrayList<User>();
    private int userCurrentPlayerNumber = 0;
    private Backdrop backdrop;
    private ScoreBox scorebox;

    public GameScreen(Grid grid, Backdrop backdrop, ScoreBox scorebox)
    {
        this.grid = grid;
        this.backdrop = backdrop;
        this.scorebox = scorebox;
    }

    public void restartGrid() {
        grid.restart();
    }

    public void recreateGrid(int gridDimensionToSet) {
        grid.recreate(gridDimensionToSet);
    }

    public void restartUsers()
    {
        this.userCurrentPlayerNumber = 0;
        for(int i = 0; i < users.size(); i++)
        {
            User userI = users.get(i);
            userI.setCrash(false);
        }
    }

    public void resetUsers(int numUsers)
    {
        users.clear();
        addUsers(numUsers);
        restartUsers();
    }

    public int getUsersSize() {
        return users.size();
    }

    public void addMenu(Menu menuToAdd) {
        menus.add(menuToAdd);
    }

    public void addUsers(int usersToAdd) {
        switch (usersToAdd) {
            case 5:
                User user5 = new User(76, 175, 80);
                users.add(0, user5);
            case 4:
                User user4 = new User(255, 160, 0);
                users.add(0, user4);
            case 3:
                User user3 = new User(123, 31, 162);
                users.add(0, user3);
            case 2:
                User user2 = new User(244, 67, 54);
                users.add(0, user2);
            case 1:
                User user1 = new User(0, 151, 167);
                users.add(0, user1);
                break;

        }
    }

    public void refresh(List<Input.TouchEvent> touchevents, Graphics g, Typeface font, int screenWidth, int fontSize, int strokeWidth, int red, int green, int blue, int faintGrey, String versionNumber)
    {
        backdrop.draw(g);
        g.drawText(versionNumber, font, 50, 50 + fontSize, fontSize, strokeWidth, Color.rgb(faintGrey, faintGrey, faintGrey));
        grid.draw(g, red, green, blue);
        User userCurrent = getUserCurrent();
        refreshMenuButtons(touchevents, g, userCurrent, font, strokeWidth, faintGrey);
        crashUser(userCurrent);
        drawUserCrash(g, font, 50, 530, fontSize / 2, strokeWidth);
        drawUserScores(g, font, fontSize / 2, strokeWidth, screenWidth);
    }

    public void refreshMenuButtons(List<Input.TouchEvent> touchevents, Graphics g, User userCurrent, Typeface font, int strokeWidth, int faintGrey) {
        for (int i = 0; i < menus.size(); i++) {
            Menu menuI = menus.get(i);
            menuI.drawButtons(g, font, strokeWidth, userCurrent.getRed(), userCurrent.getGreen(), userCurrent.getBlue(), faintGrey);
            menuI.menuDoPressAction(touchevents, this, menuI, userCurrent, userCurrentPlayerNumber);
        }
    }

    public void crashUser(User userCurrent)
    {
        if(checkCrash())
        {
            userCurrent.crash();
            setGridDisplay(true);
            setGridSnakeCrash(false);
        }
    }


    public void drawUserCrash(Graphics g, Typeface font, int x, int y, int fontSize, int strokeWidth)
    {
        for(int i = 0; i < users.size(); i++)
        {
            User userI = users.get(i);
            if(userI.getCrash())
            {
                g.drawText("CRASH", font, x, y, fontSize, strokeWidth, Color.rgb(userI.getRed(), userI.getGreen(), userI.getBlue()));
            }
        }
    }

    public void drawUserScores(Graphics g, Typeface font, int fontSize, int strokeWidth, int screenWidth)
    {
        for (int i = 0; i < users.size(); i++)
        {
            User userI = users.get(i);
            scorebox.draw(g, font, fontSize, strokeWidth, (int)(i * (double)screenWidth / (double)users.size()), userI.getScore(), userI.getRed(), userI.getGreen(), userI.getBlue());
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
        for(int i = 0; i < menus.size(); i++)
        {
            Menu menuReset = menus.get(i);
            menuReset.setResetButtonDisplay(display);
        }
    }

    public void setGridSnakeCrash(boolean crashToSet)
    {
        grid.setSnakeCrash(crashToSet);
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
