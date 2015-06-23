package com.teamawesome.mentalsnakes.game;

import com.teamawesome.mentalsnakes.framework.Game; //Framework for the game
import com.teamawesome.mentalsnakes.framework.Graphics; //Framework for drawing objects
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
    private String versionNumber = "0.0.7";
    private static Typeface font;
    private static int FONT_SIZE = 40;
    private static int STROKE_WIDTH = 5;
    private GameScreen screen;
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
        screen = new GameScreen(new Grid (gridX, gridY, width - 2 * gridX, 5, 10));
        addScreenDirectionMenu();
        addScreenUserMenu();
        addScreenGridSizeMenu();
        screen.addUsers(2);
    }

    public void addScreenDirectionMenu()
    {
        Menu menuDirection = new Menu (4, 0, 2 * height / 3, width / 3, height / 3, width / 3, height / 12);

        Button buttonNorth = new MoveButton (1, 0, Direction.NORTH);
        menuDirection.addButton(buttonNorth);
        Button buttonWest = new MoveButton (0, 1, Direction.WEST);
        menuDirection.addButton(buttonWest);
        Button buttonSouth = new MoveButton (1, 2, Direction.SOUTH);
        menuDirection.addButton(buttonSouth);
        Button buttonEast = new MoveButton (2, 1, Direction.EAST);
        menuDirection.addButton(buttonEast);

        screen.addMenu(menuDirection);
    }

    public void addScreenUserMenu()
    {
        Menu menuUser = new Menu (4, 0, 15 * height / 16, width / 3, height / 16, width / 12, height / 16);

        Button button2Users = new UserButton (0, 0, 2);
        menuUser.addButton(button2Users);
        Button button3Users = new UserButton (1, 0, 3);
        menuUser.addButton(button3Users);
        Button button4Users = new UserButton (2, 0, 4);
        menuUser.addButton(button4Users);
        Button button5Users = new UserButton (3, 0, 5);
        menuUser.addButton(button5Users);

        screen.addMenu(menuUser);
    }

    public void addScreenGridSizeMenu()
    {
        Menu menuUser = new Menu (4, 2 * width / 3, 15 * height / 16, width / 3, height / 16, width / 12, height / 16);

        Button button5Grid = new GridSizeButton (0, 0, 5);
        menuUser.addButton(button5Grid);
        Button button7Grid = new GridSizeButton (1, 0, 7);
        menuUser.addButton(button7Grid);
        Button button9Grid = new GridSizeButton (2, 0, 9);
        menuUser.addButton(button9Grid);
        Button button11Grid = new GridSizeButton (3, 0, 11);
        menuUser.addButton(button11Grid);

        screen.addMenu(menuUser);
    }

	@Override 
    public void update(float deltaTime) 
    {
        backdrop.draw(g);
        g.drawText (versionNumber, font, 50, 50, FONT_SIZE, STROKE_WIDTH, Color.rgb(0, 0, 0));
        List<TouchEvent> touchevents = game.getInput().getTouchEvents(); //The arraylist is defined
        screen.refresh(touchevents, g, font, STROKE_WIDTH, 0, 100, 90);
        if(screen.checkCrash())
        {
            screen.setGridDisplay(true);
            User userCurrent = screen.getUserCurrent();
            g.drawText("CRASH", font, 100, 100, 50, STROKE_WIDTH, Color.rgb(userCurrent.getRed(), userCurrent.getGreen(), userCurrent.getBlue()));
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
