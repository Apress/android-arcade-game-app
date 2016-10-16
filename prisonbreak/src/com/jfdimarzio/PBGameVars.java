package com.jfdimarzio;

import android.content.Context;
import android.view.Display;
import android.view.View;

public class PBGameVars {
	public static Display display;
	public static Context context;
	public static final int GAME_THREAD_DELAY = 3000;
	public static final int MENU_BUTTON_ALPHA = 0;
	public static final boolean HAPTIC_BUTTON_FEEDBACK = true;
	public static final int GAME_THREAD_FPS_SLEEP = (1000/60);	
	public static final int BACKGROUND = R.drawable.bg1;
	public static float playerBankPosX = -.73f;
	public static int playerAction = 0;
	public static final int PLAYER_MOVE_LEFT_1 = 1;
	public static final int PLAYER_RELEASE = 3;
	public static final int PLAYER_MOVE_RIGHT_1 = 4;
	public static final float PLAYER_MOVE_SPEED = .2f;
	public static final int PADDLE = R.drawable.goldbrick;
	public static final int BRICK_BLUE = 1;
	public static final int BRICK_BROWN = 2;
	public static final int BRICK_DARK_GRAY = 3;
	public static final int BRICK_GREEN = 4;
	public static final int BRICK_LITE_GRAY = 5;
	public static final int BRICK_PURPLE = 6;
	public static final int BRICK_RED = 7;
	public static final int BRICK_SHEET = R.drawable.bricksheet;
	public static final int NUMBER_SHEET = R.drawable.numbersheet;
	public static final int BALL_SHEET = R.drawable.ballsheet;
	public static final float BALL_SPEED = 0.01f;
	public static final int BALL_MODE_NORMAL = 0;
	public static final int BALL_MODE_SPIKE = 1;
	public static float ballTargetY = 0.01f;
	public static float ballTargetX = -1.125f;
	public static final int NUMBER_OF_MODE_IMPACTS = 6;

	
	public boolean onExit(View v) {
        /*try
        {
        	Intent bgmusic = new Intent(context, SFMusic.class);
        	context.stopService(bgmusic);
        	musicThread.stop();*/

        	return true;
       /* }catch(Exception e){
        	return false;
        }*/
     		
	}
}
