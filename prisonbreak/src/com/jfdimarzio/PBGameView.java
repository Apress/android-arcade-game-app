package com.jfdimarzio;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class PBGameView extends GLSurfaceView {
	private PBGameRenderer renderer;
	
	public PBGameView(Context context) {
		super(context);
		
		renderer = new PBGameRenderer();
		
		this.setRenderer(renderer);
		

	}
	

}

