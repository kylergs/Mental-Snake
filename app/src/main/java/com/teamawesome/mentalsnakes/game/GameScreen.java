package com.teamawesome.mentalsnakes.game;

import android.graphics.Color;
import android.graphics.Typeface;

import com.teamawesome.mentalsnakes.framework.Graphics;
import com.teamawesome.mentalsnakes.framework.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 22/06/2015.
 * Is a game-screen object, containing all of the code concerning the game play.
 * Contains most of the code for the game, and is updated each loop of the main method.
 */
public class GameScreen {
    private Grid grid; //A grid of dots
    private ArrayList<Menu> menus = new ArrayList<Menu>(); //The arraylist of menus to be shown onscreen
    private ArrayList<User> users = new ArrayList<User>(); //The arraylist of all the users
    private int userCurrentPlayerNumber = 0; //The index of the current user who's turn it is
    private int userStartPlayerNumber = 0; //When altering the number of users, this integer is required to ensure the correct start user
    private Backdrop backdrop; //A backdrop object
    private ScoreBox scorebox; //A scorebox object contains the ability to draw the scores to the screen
    private Direction lastDirection; //A direction that simply contains HAL's last move, to be drawn to the screen.

    public GameScreen(Grid grid, Backdrop backdrop, ScoreBox scorebox)
    {
        this.grid = grid;
        this.backdrop = backdrop;
        this.scorebox = scorebox;
    }

    public void restartGrid() {
        grid.restart();
    }

    public void recreateGrid(int gridDimensionToSet)
    {
        grid.recreate(gridDimensionToSet);
    }

    public void restartUsers() {
        for (int i = 0; i < users.size(); i++) {
            User userI = users.get(i);
            userI.setCrash(false);
            if (userI instanceof HAL) {
                HAL hal = (HAL) userI;
                hal.clearMemory();
            }
        }
        boolean userStartFound = false;
        for (int i = 0; i < users.size(); i++) {
            if (userStartFound == false) {
                User userI = users.get(i);
                userStartPlayerNumber = i;
                userStartFound = userI.getPlaying();
            }
        }
        this.userCurrentPlayerNumber = userStartPlayerNumber;
    }

    public void restartMenus()
    {
        for(int i = 0; i < menus.size(); i++)
        {
            Menu moveMenu = menus.get(i);
            moveMenu.setMove(true);
        }
    }

    public int getUsersSize() {
        return users.size();
    }

    public void addMenu(Menu menuToAdd) {
        menus.add(menuToAdd);
    }

    /*
     * The users are created with the RGB values that are chosen by the developer
     */
    public void createUsers(int usersToAdd) {
        switch (usersToAdd) {
            case 7:
                HAL hal = new HAL();
                users.add(hal);
            case 6:
                User user6 = new User(239, 108, 0);
                users.add(0, user6);
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
                user2.setPlaying(true);
                users.add(0, user2);
            case 1:
                User user1 = new User(41, 128, 185);
                user1.setPlaying(true);
                users.add(0, user1);
                break;
        }
    }

    /*
     * Simply increments the index of the current user who's turn it is
     */
    public void togglePlayingUser(int userNum, boolean active)
    {
        User userToggle = users.get(userNum);
        userToggle.setPlaying(active);
    }

    /*
     * The update method.
     * It draws the backdrop and text to the screen, refreshes the menus, checks whether the user should crash.
     * It then draws the grid, user scores and whether the current user has crashed onto the screen.
     */
    public void refresh(List<Input.TouchEvent> touchevents, Graphics g, Typeface font, int screenWidth, int screenHeight, int fontSize, int strokeWidth, int red, int green, int blue, int faintGrey, String versionNumber) {
        backdrop.draw(g);
        g.drawText(versionNumber, font, 50, 50 + fontSize, fontSize, strokeWidth, Color.rgb(faintGrey, faintGrey, faintGrey));
        grid.draw(g, red, green, blue);
        User userCurrent = getUserCurrent();
        //We check whether our user is an AI. If they are, then their move logic is different.
        if(userCurrent instanceof HAL && !userCrash())
        {
            HAL hal = (HAL)userCurrent;
            try
            {
                hal.makeMove(this);
            }
            catch (CloneNotSupportedException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            refreshMenuButtons(touchevents, g, userCurrent, font, strokeWidth, faintGrey);
        }
        crashUser(userCurrent);
        drawUserCrash(g, font, 50, screenHeight / 3, fontSize / 2, strokeWidth);
        drawUserScores(g, font, fontSize / 2, strokeWidth, screenWidth);
        drawHALMove(g, font, fontSize / 2, strokeWidth, screenWidth, screenHeight);
    }

    /*
     * Required by HAL
     * Enables the game to know if a user has crashed, and will stop HAL from moving if so.
     */
    public boolean userCrash()
    {
        boolean userCrash = false;
        for(int i = 0; i < users.size(); i++)
        {
            User userI = users.get(i);
            if(userI.getCrash())
            {
                userCrash = true;
            }
        }
        return userCrash;
    }

    public void refreshMenuButtons(List<Input.TouchEvent> touchevents, Graphics g, User userCurrent, Typeface font, int strokeWidth, int faintGrey) {
        for (int i = 0; i < menus.size(); i++)
        {
            Menu menuI = menus.get(i);
            menuI.draw(g, font, strokeWidth, userCurrent.getRed(), userCurrent.getGreen(), userCurrent.getBlue(), faintGrey);
            menuI.menuDoPressAction(touchevents, this, menuI, userCurrent, userCurrentPlayerNumber);
        }
    }

    public void crashMenu()
    {
        for(int i = 0; i < menus.size(); i++)
        {
            Menu moveMenu = menus.get(i);
            moveMenu.setMove(false);
        }
    }

    public void crashUser(User userCurrent)
    {
        if(checkCrash())
        {
            userCurrent.crash();
            setGridDisplay(true);
            setGridSnakeCrash(false);
            crashMenu();
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
        int j = 0;
        for (int i = 0; i < users.size(); i++)
        {
            User userI = users.get(i);
            if(userI.getPlaying() == true)
            {
                scorebox.draw(g, font, fontSize, strokeWidth, (int)(j * (double)screenWidth / (double)getNumPlayingUsers()), userI.getScore(), userI.getRed(), userI.getGreen(), userI.getBlue());
                j++;
            }
        }
    }


    /*
     * Draws the previous move by users to the screen, s.t. HAL's moves can be shown,
     * and s.t. if the phone is being passed between players, then the players don't have to
     * tell eachother their most recent move if they so wish (though this is a secondary reason).
     * If HAL has not made a move, then this method is void.
     */
    public void drawHALMove(Graphics g, Typeface font, int fontSize, int strokeWidth, int screenWidth, int screenHeight)
    {
        User userCurrent = getUserHAL();
        try
        {
            g.drawText("" + lastDirection, font, 50, screenHeight / 2, fontSize, strokeWidth, Color.rgb(userCurrent.getRed(), userCurrent.getGreen(), userCurrent.getBlue()));
        }
        catch(NullPointerException e)
        {

        }
    }

    public User getUserCurrent()
    {
        User userCurrent = getUser(userCurrentPlayerNumber);
        return userCurrent;
    }

    /*
     * Required in order to return the RGB color of HAL to the last move drawing method.
     */
    public User getUserHAL()
    {
        User userCurrent = new User();
        for(int i = 0; i < users.size(); i++)
        {
            User userI = getUser(i);
            if(userI instanceof HAL)
            {
                userCurrent = userI;
            }
        }
        return userCurrent;
    }

    public User getUser(int i)
    {
        return users.get(i);
    }

    public boolean checkCrash()
    {
        boolean checkCrash = grid.getCrash();
        return checkCrash;
    }

    public void setLastDirection(Direction direction)
    {
        this.lastDirection = direction;
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
            userCurrentPlayerNumber = userStartPlayerNumber;
        }
        userPlayingLoop();
    }

    public void userPlayingLoop()
    {
        for(int i = userCurrentPlayerNumber; i < users.size(); i++)
        {
            User userCurrent = users.get(userCurrentPlayerNumber);
            if(!userCurrent.getPlaying())
            {
                userCurrentPlayerNumber++;
            }
            if(userCurrentPlayerNumber == users.size())
            {
                userCurrentPlayerNumber = userStartPlayerNumber;
            }
        }
    }

    public int getNumPlayingUsers()
    {
        int numPlayingUsers = 0;
        for(int i = 0; i < users.size(); i++)
        {
            User userI = users.get(i);
            if(userI.getPlaying())
            {
                numPlayingUsers ++;
            }
        }
        return numPlayingUsers;
    }

    public void gridMoveSnake(Direction directionToMove, User userCurrent)
    {
        grid.moveSnake(directionToMove, userCurrent);
    }

    public int getGridDimension()
    {
        return grid.getGridDimension();
    }

}
