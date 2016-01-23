package com.teamawesome.mentalsnakes.game;

import android.graphics.Typeface;

import com.teamawesome.mentalsnakes.framework.Graphics;
import com.teamawesome.mentalsnakes.framework.Input.TouchEvent;

import java.util.List;

/**
 * Created by Ben on 25/06/2015.
 * This class holds dialogue, and re-sizes to display a dialogue object for the User's info
 */
public class DialogueScreen
{
    private Backdrop backdrop; //A backdrop object for the screen
    private Dialogue dialogue; //The dialogue
    private Menu menu; //The menu containing actions the user can choose
    private int mode; //TODO Remove obsolete variables

    public DialogueScreen(Dialogue dialogue, Backdrop backdrop)
    {
        this.dialogue = dialogue;
        this.backdrop = backdrop;
        this.mode = 0;
    }

    public void setMenu(Menu menu)
    {
        this.menu = menu;
    }

    public void setDialogue(Dialogue dialogueToSet)
    {
        this.dialogue = dialogue;
    }

    public Dialogue getDialogue()
    {
        return this.dialogue;
    }

    public void dialogueAddString(String stringToAdd)
    {
        dialogue.addString(stringToAdd);
    }

    /*
     * This method calls the draw method.
     * It then refreshes the menu with the TouchEvents on the screen
     * If the user has chosen a global action on the screen, we alter the main activity accordingly.
     */
    public void refresh(MentalSnakesMainActivity main, List<TouchEvent> touchevents, Graphics g, Typeface font, int fontSize, int strokeWidth, int red, int green, int blue, int faintGrey)
    {
        draw(g, font, fontSize, strokeWidth, red, green, blue, faintGrey);
        menu.draw(g, font, strokeWidth, red, green, blue, faintGrey);
        menu.menuDoPressAction(touchevents, new GameScreen(new Grid(), backdrop, new ScoreBox()), menu, new User(red, green, blue), 0);
        IntroButton introButton = (IntroButton) menu.getButton(0);
        main.setMode(introButton.getMode());
    }

    /*
     * We draw the backdrop and menu.
     * TODO Move the menu drawing code to this method.
     */
    public void draw(Graphics g, Typeface font, int fontSize, int strokeWidth, int red, int green, int blue, int faintGrey)
    {
        backdrop.draw(g);
        dialogue.draw(g, font, fontSize, strokeWidth, red, green, blue, faintGrey);
    }

}
