package com.teamawesome.mentalsnakes.game;

/**
 * Created by Ben on 23/06/2015.
 */
public class User
{
    private int red;
    private int green;
    private int blue;
    private int score;
    private boolean crash;

    public User (int red, int green, int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.crash = false;
    }

    public void setScore(int scoreToSet)
    {
        this.score = scoreToSet;
    }

    public int getScore()
    {
        return this.score;
    }

    public int getRed()
    {
        return this.red;
    }

    public int getGreen()
    {
        return this.green;
    }

    public int getBlue()
    {
        return this.blue;
    }

    public void setCrash(boolean crashToSet)
    {
        this.crash = crashToSet;
    }

    public void crash()
    {
        setScore(this.score + 1);
        setCrash(true);
    }

    public boolean getCrash()
    {
        return this.crash;
    }

}
