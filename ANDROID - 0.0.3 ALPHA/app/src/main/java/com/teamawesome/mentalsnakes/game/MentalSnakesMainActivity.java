package com.teamawesome.mentalsnakes.game;

import com.teamawesome.mentalsnakes.framework.Game; //Framework for the game
import com.teamawesome.mentalsnakes.framework.Graphics; //Framework for drawing objects
import com.teamawesome.mentalsnakes.framework.Graphics.PixmapFormat; //Framework for drawing the pixmap images
import com.teamawesome.mentalsnakes.framework.Input.TouchEvent; //Framework for touch events arrayList
import com.teamawesome.mentalsnakes.framework.Pixmap; //Framework for interprating images
import com.teamawesome.mentalsnakes.framework.Screen; //Framework for the screen size

import java.util.ArrayList; //Imports the ArrayList framework
import java.util.List; //Imports the list framework
import java.util.Random; //Imports the framework for generating random variables

import android.graphics.Color; //A standard list of colors is imported
import android.graphics.Typeface; //The type face fonts are imported from the res folder
/*
 * The Main Code :)
 * Author - BenWinchester
 * */

public class MentalSnakesMainActivity extends Screen {

    private int width;
    private int height;
    private Graphics g;
    private Backdrop backdrop;
    private String versionNumber = "0.0.3";
    private static Typeface font;
    private static int FONT_SIZE = 40;
    private static int STROKE_WIDTH = 5;
    private Grid grid;
    private int gridX = 40;
    private int gridY = 40;


    public MentalSnakesMainActivity(Game game)
    {    	
        super(game);
        this.width =  640;
        this.height = 960;
        this.g = game.getGraphics();
        this.backdrop = new Backdrop (width, height, 240, 240, 240);
        this.font = Typeface.createFromAsset(game.getAssets(), "fonts/roboto-light.ttf");
        grid = new Grid (gridX, gridY, width - 2 * gridX, 5, 10);
    }

	@Override 
    public void update(float deltaTime) 
    {
        backdrop.draw(g);
        g.drawText (versionNumber, font, 50, 50, FONT_SIZE, STROKE_WIDTH, Color.rgb(0, 0, 0));
        grid.setDisplay(true);
        grid.draw(g);
        Menu menu = new Menu(4, width / 3, 3 * height / 4, width / 3, height / 8, Orientation.VERTICAL);
        menu.drawButtons(g, 0, 100, 90);
    }
    
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void present(float deltaTime) {

    }
}
