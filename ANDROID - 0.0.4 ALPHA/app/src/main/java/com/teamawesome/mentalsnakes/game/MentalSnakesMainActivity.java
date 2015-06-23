package com.teamawesome.mentalsnakes.game;

import com.teamawesome.mentalsnakes.framework.Game; //Framework for the game
import com.teamawesome.mentalsnakes.framework.Graphics; //Framework for drawing objects
import com.teamawesome.mentalsnakes.framework.Input;
import com.teamawesome.mentalsnakes.framework.Screen; //Framework for the screen size
import com.teamawesome.mentalsnakes.framework.Input.TouchEvent; //Framework for touch events arrayList

import android.graphics.Color; //A standard list of colors is imported
import android.graphics.Typeface; //The type face fonts are imported from the res folder

import java.util.ArrayList;
import java.util.List;
/*
 * The Main Code :)
 * Author - BenWinchester
 * */

public class MentalSnakesMainActivity extends Screen {

    private int width;
    private int height;
    private Graphics g;
    private Backdrop backdrop;
    private String versionNumber = "0.0.4";
    private static Typeface font;
    private static int FONT_SIZE = 40;
    private static int STROKE_WIDTH = 5;
    private GameScreen screen;
    private int gridX = 40;
    private int gridY = 40;
    private ArrayList<User> users = new ArrayList<User>();

    public MentalSnakesMainActivity(Game game)
    {    	
        super(game);
        this.width =  640;
        this.height = 960;
        this.g = game.getGraphics();
        this.backdrop = new Backdrop (width, height, 240, 240, 240);
        this.font = Typeface.createFromAsset(game.getAssets(), "fonts/roboto-light.ttf");
        screen = new GameScreen(new Grid (gridX, gridY, width - 2 * gridX, 5, 10));
        addScreenDirectionMenu();
        addUsers(1);
    }

    public void addScreenDirectionMenu()
    {
        Menu menuDirection = new Menu (4, 0, 2 * height / 3, width / 3, height / 3, width / 3, height / 12, Orientation.VERTICAL);

        Button buttonNorth = new MoveButton (0, 0, Direction.NORTH);
        menuDirection.addButton(buttonNorth);
        Button buttonWest = new MoveButton (0, 1, Direction.WEST);
        menuDirection.addButton(buttonWest);
        Button buttonSouth = new MoveButton (0, 2, Direction.SOUTH);
        menuDirection.addButton(buttonSouth);
        Button buttonEast = new MoveButton (0, 3, Direction.EAST);
        menuDirection.addButton(buttonEast);

        screen.addMenu(menuDirection);
    }

    public void addUsers(int usersToAdd)
    {
        for(int i = 0; i < usersToAdd; i++)
        {
            User userToAdd = new User(255, 87,34);
            users.add(userToAdd);
        }
    }

	@Override 
    public void update(float deltaTime) 
    {
        backdrop.draw(g);
        g.drawText (versionNumber, font, 50, 50, FONT_SIZE, STROKE_WIDTH, Color.rgb(0, 0, 0));
        List<TouchEvent> touchevents = game.getInput().getTouchEvents(); //The arraylist is defined
        User userCurrent = users.get(0);
        screen.refresh(touchevents, g, font, STROKE_WIDTH, 0, 100, 90, userCurrent);
        screen.setGridDisplay(true);
        if(screen.checkCrash())
        {
            screen.setGridDisplay(true);
            g.drawText("CRASH", font, 100, 100, 50, STROKE_WIDTH, Color.rgb(0, 100, 90));
            userCurrent.crash();
        }
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
