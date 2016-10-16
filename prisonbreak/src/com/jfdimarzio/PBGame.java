package com.jfdimarzio;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;


public class PBGame extends Activity {
	final PBGameVars gameEngine = new PBGameVars();
	private PBGameView gameView;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new PBGameView(this);
        setContentView(gameView);
    }
    @Override
    protected void onResume() {
       super.onResume();
       gameView.onResume();
    }

    @Override
    protected void onPause() {
       super.onPause();
       gameView.onPause();
    }

   	@Override
   	public boolean onTouchEvent(MotionEvent event) {
   		//
   		float x = event.getX();
        float y = event.getY();
        int height = PBGameVars.display.getHeight() / 4;
        int playableArea = PBGameVars.display.getHeight() - height;
        if (y > playableArea){
        	switch (event.getAction()){
        	case MotionEvent.ACTION_DOWN:
        		if(x < PBGameVars.display.getWidth() / 2){
        			PBGameVars.playerAction = PBGameVars.PLAYER_MOVE_LEFT_1;
        		}else{
        			PBGameVars.playerAction = PBGameVars.PLAYER_MOVE_RIGHT_1;
        		}
        		break;
        	case MotionEvent.ACTION_UP:
        		PBGameVars.playerAction = PBGameVars.PLAYER_RELEASE;
        		break;
        	}
        	
        }

		return false;
    } 
}
