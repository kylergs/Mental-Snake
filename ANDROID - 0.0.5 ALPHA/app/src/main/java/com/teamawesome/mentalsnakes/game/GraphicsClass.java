package com.teamawesome.mentalsnakes.game;

import com.teamawesome.mentalsnakes.framework.Screen;
import com.teamawesome.mentalsnakes.framework.implementation.AndroidGame;

/* Name: MentalSnakesMainActivity.java
 * Date: 23/03/2013
 * Author: Ben Winchester
 * Purpose: main class that launches the loading class that launches the Loading class, which Launches Intro
 * which launches the Main Menu
 */

public class GraphicsClass extends AndroidGame {
	
	@Override
	public Screen getStartScreen() 
	{
		return new MentalSnakesMainActivity(this);
	}
}
