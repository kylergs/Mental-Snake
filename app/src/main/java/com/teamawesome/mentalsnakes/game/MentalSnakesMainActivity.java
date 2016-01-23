package com.teamawesome.mentalsnakes.game;

import com.teamawesome.mentalsnakes.framework.Game; //Framework for the game
import com.teamawesome.mentalsnakes.framework.Graphics; //Framework for drawing objects
import com.teamawesome.mentalsnakes.framework.Screen; //Framework for the screen size
import com.teamawesome.mentalsnakes.framework.Input.TouchEvent; //Framework for touch events arrayList

import android.graphics.Typeface; //The type face fonts are imported from the res folder

import java.util.List;
/*
 * The Main Code :)
 * Author - BenWinchester
 * */

public class MentalSnakesMainActivity extends Screen {

    private int SCREEN_WIDTH; //The width of the screen
    private int SCREEN_HEIGHT; //The height of the screen
    private Graphics g; //The graphics object, which handles drawing
    private String versionNumber = "v0.2.5 Cookie"; //Version number string (drawn to screen)
    private static Typeface font; //The typeface used in the game
    private static int FONT_SIZE = 400; //The fontsize for the background font
    private static int STROKE_WIDTH = 5; //The stroke SCREEN_WIDTH of the font used
    private GameScreen screen; //The screen object, which handles the entire gameplay
    private DialogueScreen dialoguescreen; //This screen appears at the start with instructions
    private int gridX = 40; //The position of the grid, relative to the top left corner of the screen
    private int gridY = 40;
    private int gameMode = 0; //A switch determines whether the user should be looking at a dialogue or the gameplay

    public MentalSnakesMainActivity(Game game)
    {    	
        super(game);
        this.SCREEN_WIDTH =  1280;
        this.SCREEN_HEIGHT = 1920;
        this.g = game.getGraphics();
        this.font = Typeface.createFromAsset(game.getAssets(), "fonts/roboto-light.ttf");
        screen = new GameScreen(new Grid (gridX, gridY, SCREEN_WIDTH - 2 * gridX, 5, 15, 30), new Backdrop (SCREEN_WIDTH, SCREEN_HEIGHT, 240, 240, 240), new ScoreBox(25, 3 * SCREEN_HEIGHT / 5));
        dialoguescreen = new DialogueScreen(new Dialogue(50, 50, SCREEN_WIDTH, SCREEN_HEIGHT), new Backdrop(SCREEN_WIDTH, SCREEN_HEIGHT, 240, 240, 240));
        addScreenDirectionMenu();
        addScreenUserMenu();
        addScreenGridSizeMenu();
        addScreenResetButton();
        adddialoguescreenMenu();
        setdialoguescreenDialogue();
        screen.createUsers(7);
    }

    /*
     * Each menu is initialised individually, with button objects being passed through.
     * The direction menu handles the movement of the snake
     */

    public void addScreenDirectionMenu()
    {
        Menu menuDirection = new Menu (4, 0, 2 * SCREEN_HEIGHT / 3, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);

        Button buttonNorth = new MoveButton (1, 0, Direction.UP, 5, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 12 );
        menuDirection.addButton(buttonNorth);
        Button buttonWest = new MoveButton (0, 1, Direction.LEFT, 6, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 12);
        menuDirection.addButton(buttonWest);
        Button buttonSouth = new MoveButton (1, 2, Direction.DOWN, 5, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 12);
        menuDirection.addButton(buttonSouth);
        Button buttonEast = new MoveButton (2, 1, Direction.RIGHT, 6, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 12);
        menuDirection.addButton(buttonEast);

        screen.addMenu(menuDirection);
    }

    /*
     * The user menu handles the selection for which users are currently playing, and which are inactive
     */

    public void addScreenUserMenu()
    {
        Menu menuUser = new Menu (4, 0, 15 * SCREEN_HEIGHT / 16, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 16);

        Button button1User = new UserButton (0, 0, 0, "B", 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16, 41, 128, 185);
        button1User.setActive(true);
        menuUser.addButton(button1User);
        Button button2Users = new UserButton (1, 0, 1, "R", 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16, 244, 67, 54);
        button2Users.setActive(true);
        menuUser.addButton(button2Users);
        Button button3Users = new UserButton (2, 0, 2, "P", 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16, 123, 31, 162);
        menuUser.addButton(button3Users);
        Button button4Users = new UserButton (3, 0, 3, "Y", 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16, 255, 160, 0);
        menuUser.addButton(button4Users);
        Button button5Users = new UserButton (4, 0, 4, "G", 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16, 76, 175, 80);
        menuUser.addButton(button5Users);
        Button button6Users = new UserButton (5, 0, 5, "O", 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16, 239, 108, 0);
        menuUser.addButton(button6Users);
        Button button7Users = new UserButton (6, 0, 6, "H", 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16, 40, 40, 40);
        menuUser.addButton(button7Users);


        Label userLabel = new Label ("Users");
        menuUser.setLabel(userLabel);

        screen.addMenu(menuUser);
    }

    /*
     * This menu handles the grid size, which can be chosen by the user.
     * It has 4 options for size: 5x5, 7x7, 9x9 & 11x11
     */

    public void addScreenGridSizeMenu()
    {
        Menu menuUser = new Menu (4, 7 * SCREEN_WIDTH / 12, 15 * SCREEN_HEIGHT / 16, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 16);

        Button button5Grid = new GridSizeButton (0, 0, 5, 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16);
        button5Grid.setActive(true);
        menuUser.addButton(button5Grid);
        Button button7Grid = new GridSizeButton (1, 0, 7, 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16);
        menuUser.addButton(button7Grid);
        Button button9Grid = new GridSizeButton (2, 0, 9, 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16);
        menuUser.addButton(button9Grid);
        Button button11Grid = new GridSizeButton (3, 0, 11, 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16);
        menuUser.addButton(button11Grid);
        Button button13Grid = new GridSizeButton (4, 0, 13, 2, SCREEN_WIDTH / 12, SCREEN_HEIGHT / 16);
        menuUser.addButton(button13Grid);

        Label gridLabel = new Label ("          Size");
        menuUser.setLabel(gridLabel);

        screen.addMenu(menuUser);
    }

    /*
     * The reset button is the only button in the reset menu.
     * It enables the game to be restarted after a player has crashed.
     */

    public void addScreenResetButton()
    {
//        int numButtons, int menuX, int menuY, int menuWidth, int menuHeight
        Menu menuReset = new Menu(1, 0, (int)((double)(11 * SCREEN_HEIGHT/ 16)), SCREEN_WIDTH / 3, SCREEN_HEIGHT / 16);
//                                   int x, int y, int stringWidth, int buttonWidth, int buttonHeight
        Button resetButton = new ResetButton(0, 0, 7, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 16);
        menuReset.addButton(resetButton);

        screen.addMenu(menuReset);
    }

    /*
     * This menu has again, one button.
     * This button is used to simply reurn a boolean value, when the user has read the dialogue screen.
     * The dialogue screen at the start with gameplay instructions is an example of this.
     */

    public void adddialoguescreenMenu()
    {
        Menu introMenu = new Menu(1, SCREEN_WIDTH / 3, 10 * SCREEN_HEIGHT / 12, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 12);
        Button introButton = new IntroButton(0, 0, "OK", 6, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 12);
        introMenu.addButton(introButton);
        dialoguescreen.setMenu(introMenu);
    }

    /*
     * This is not the most elegant piece of code.
     * Because of the nature of the textbox, individual lines of text must eb passed in separately.
     * The textbox then resizes itself accordingly, so that the screen contains a sensibly sized box.
     */

    public void setdialoguescreenDialogue()
    {
        Dialogue dialogue = dialoguescreen.getDialogue();
        dialogue.addString("MENTAL SNAKES");
        dialogue.addString("Take it in turns to move");
        dialogue.addString("a snake around the screen.");
        dialogue.addString("Use the buttons to control");
        dialogue.addString("the snake's movement.");
        dialogue.addString("Everyone has their own colour.");
        dialogue.addString("Don't crash into the walls,");
        dialogue.addString("or hit yourself");
        dialogue.addString("And try to remember where");
        dialogue.addString("you've been.");
        dialogue.addString("");
        dialogue.addString("Have Fun! :D");
        dialoguescreen.setDialogue(dialogue);
    }

    /*
     * This method allows the gamemode to be set
     */

    public void setMode(int modeToSet)
    {
        this.gameMode = modeToSet;
    }

    /*
     * This switch is the only method accessed once gameplay begins.
     * It determines the gamemode, and then updates that screen with the touchEvents accordingly.
     */

	@Override 
    public void update(float deltaTime) {
        List<TouchEvent> touchevents = game.getInput().getTouchEvents(); //The arraylist is defined
        switch(gameMode)
        {
            case 0 :
                dialoguescreen.refresh(this, touchevents, g, font, FONT_SIZE / 5, STROKE_WIDTH, 0, 151, 167, 230);
                break;
            case 1 :
                screen.refresh(touchevents, g, font, SCREEN_WIDTH, SCREEN_HEIGHT, FONT_SIZE, STROKE_WIDTH, 0, 100, 90, 180, versionNumber);
                break;
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
