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
    private String versionNumber = "0.0.1";
    private static Typeface font;
    private static int FONT_SIZE = 40;
    private static int STROKE_WIDTH = 5;


    public MentalSnakesMainActivity(Game game)
    {    	
        super(game);
        this.width =  640;
        this.height = 960;
        this.g = game.getGraphics();
        this.backdrop = new Backdrop (width, height, 240, 240, 240);
        this.font = Typeface.createFromAsset(game.getAssets(), "fonts/roboto-light.ttf");
    }

	@Override 
    public void update(float deltaTime) 
    {
        backdrop.draw(g);
        g.drawText (versionNumber, font, 50, 50, FONT_SIZE, STROKE_WIDTH, Color.rgb(0, 0, 0));
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
