package com.teamawesome.mentalsnakes.game;

import android.graphics.Typeface;

import com.teamawesome.mentalsnakes.framework.Graphics;
import com.teamawesome.mentalsnakes.framework.Input;
import com.teamawesome.mentalsnakes.framework.Input.TouchEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 25/06/2015.
 */
public class IntroScreen
{
    private Backdrop backdrop;
    private Dialogue dialogue;
    private Menu menu;
    private int mode;

    public IntroScreen (Dialogue dialogue, Backdrop backdrop)
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

    public void refresh(MentalSnakesMainActivity main, List<TouchEvent> touchevents, Graphics g, Typeface font, int fontSize, int strokeWidth, int red, int green, int blue, int faintGrey)
    {
        draw(g, font, fontSize, strokeWidth, red, green, blue, faintGrey);
        menu.drawButtons(g, font, strokeWidth, red, green, blue, faintGrey);
        menu.menuDoPressAction(touchevents, new GameScreen(new Grid(), backdrop, new ScoreBox()), menu, new User(red, green, blue), 0);
        IntroButton introButton = (IntroButton) menu.getButton(0);
        main.setMode(introButton.getMode());
    }


    public void draw(Graphics g, Typeface font, int fontSize, int strokeWidth, int red, int green, int blue, int faintGrey)
    {
        backdrop.draw(g);
        dialogue.draw(g, font, fontSize, strokeWidth, red, green, blue, faintGrey);
    }

}
