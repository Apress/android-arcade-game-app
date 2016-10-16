package com.jfdimarzio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PBMainMenu extends Activity {
	 /** Called when the activity is first created. */
		final PBGameVars engine = new PBGameVars();
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        PBGameVars.context = getApplicationContext();
	       	        
	        /** Set menu button options */
	        ImageButton start = (ImageButton)findViewById(R.id.btnStart);
	        ImageButton exit = (ImageButton)findViewById(R.id.btnExit);
	        
	        start.getBackground().setAlpha(PBGameVars.MENU_BUTTON_ALPHA);
	        start.setHapticFeedbackEnabled(PBGameVars.HAPTIC_BUTTON_FEEDBACK);
	       
	        exit.getBackground().setAlpha(PBGameVars.MENU_BUTTON_ALPHA); 
	        exit.setHapticFeedbackEnabled(PBGameVars.HAPTIC_BUTTON_FEEDBACK);
	        
	        start.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					/** Start Game!!!! */
					Intent game = new Intent(getApplicationContext(),PBGame.class);
					PBMainMenu.this.startActivity(game);

				}
	        	
	        });
	        
	        exit.setOnClickListener(new OnClickListener(){ 
				@Override
				public void onClick(View v) {
					boolean clean = false;
					clean = engine.onExit(v);	
					if (clean)
					{
						int pid= android.os.Process.myPid();
						android.os.Process.killProcess(pid);
					}
				}
	        	});
	    }

}
