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

    public GameScreen(Grid grid)
    {
        this.grid = grid;
    }

    public void setGrid(Grid grid)
    {
        this.grid = grid;
    }

    public void restartGrid(int x, int y, int gridPixelSize, int gridDimension, int dotSize)
    {
        Grid grid = new Grid (x, y, gridPixelSize, gridDimension, dotSize);
        this.grid = grid;
    }

    public void addMenu(Menu menuToAdd)
    {
        menus.add(menuToAdd);
    }

    public void clearMenus()
    {
        menus.clear();
    }

    public void refresh(List<Input.TouchEvent> touchevents, Graphics g, Typeface font, int strokeWidth, int red, int green, int blue, User userCurrent)
    {
        grid.draw(g, red, green, blue);
        for(int i = 0; i < menus.size(); i++)
        {
            Menu menuI = menus.get(i);
            menuI.drawButtons(g, font, strokeWidth, red, green, blue);
            menuI.menuDoPressAction(touchevents, grid, userCurrent);
        }
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

}
