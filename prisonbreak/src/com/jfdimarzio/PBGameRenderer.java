package com.jfdimarzio;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class PBGameRenderer  implements Renderer{

	private PBBackground background = new PBBackground();
	private PBPlayer player1 = new PBPlayer();
	private PBBall ball = new PBBall();
	private PBTextures textureLoader;
	private int[] spriteSheets = new int[3];
	private int numberOfRows = 4;
	private PBWall wall;
	
	private long loopStart = 0;
	private long loopEnd = 0;
	private long loopRunTime = 0 ;
	
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		loopStart = System.currentTimeMillis();
		// TODO Auto-generated method stub
		try {
			if (loopRunTime < PBGameVars.GAME_THREAD_FPS_SLEEP){
				Thread.sleep(PBGameVars.GAME_THREAD_FPS_SLEEP - loopRunTime);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	
		
		drawBackground1(gl);

		movePlayer1(gl);
		drawBricks(gl);
		moveBall(gl);
		
		detectCollisions();
		
	    loopEnd = System.currentTimeMillis();
	    loopRunTime = ((loopEnd - loopStart));
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		gl.glViewport(0, 0, width,height);
		 
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		
		gl.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		// TODO Auto-generated method stub
		initializeBricks();
		textureLoader = new PBTextures(gl);
		spriteSheets = textureLoader.loadTexture(gl, PBGameVars.BRICK_SHEET, PBGameVars.context, 1); 
		spriteSheets = textureLoader.loadTexture(gl, PBGameVars.NUMBER_SHEET, PBGameVars.context, 2);
		spriteSheets = textureLoader.loadTexture(gl, PBGameVars.BALL_SHEET, PBGameVars.context, 3);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);

		background.loadTexture(gl,PBGameVars.BACKGROUND, PBGameVars.context);
		player1.loadTexture(gl,PBGameVars.PADDLE, PBGameVars.context);
	}
	
	private void drawBackground1(GL10 gl){
		
	    gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glPushMatrix();
	    gl.glScalef(1f, 1f, 1f);
	    //gl.glTranslatef(0f, 0f, 0f);
	   
	    background.draw(gl);
	    gl.glPopMatrix();
	    gl.glLoadIdentity();


	}
	
	private void initializeBricks(){
		wall = new PBWall(numberOfRows);
	}
	
	private void drawBricks(GL10 gl){
		for (int x = 0; x < wall.rows.length; x++)
		{
			for(int y = 0; y < wall.rows[x].bricks.length; y++)
			{
				if(!wall.rows[x].bricks[y].isDestroyed)
				{
								
					switch (wall.rows[x].bricks[y].brickType){
					case PBGameVars.BRICK_BLUE: 
						gl.glMatrixMode(GL10.GL_MODELVIEW);
						gl.glLoadIdentity();
						gl.glPushMatrix();
						gl.glScalef(.25f, .25f, 1f);
						gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
						
						gl.glMatrixMode(GL10.GL_TEXTURE);
						gl.glLoadIdentity();
						gl.glTranslatef(0.50f, 0.25f , 0.0f);
						
						
						wall.rows[x].bricks[y].draw(gl, spriteSheets);
						gl.glPopMatrix();
						gl.glLoadIdentity();
						break;
					case PBGameVars.BRICK_BROWN:
						gl.glMatrixMode(GL10.GL_MODELVIEW);
						gl.glLoadIdentity();
						gl.glPushMatrix();
						gl.glScalef(.25f, .25f, 1f);
						gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
						
						gl.glMatrixMode(GL10.GL_TEXTURE);
						gl.glLoadIdentity();
						gl.glTranslatef(0.0f, 0.50f , 0.0f);
						
						
						wall.rows[x].bricks[y].draw(gl, spriteSheets);
						gl.glPopMatrix();
						gl.glLoadIdentity();
						break;
					case PBGameVars.BRICK_DARK_GRAY:
						gl.glMatrixMode(GL10.GL_MODELVIEW);
						gl.glLoadIdentity();
						gl.glPushMatrix();
						gl.glScalef(.25f, .25f, 1f);
						gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
						
						gl.glMatrixMode(GL10.GL_TEXTURE);
						gl.glLoadIdentity();
						gl.glTranslatef(0.25f, 0.25f , 0.0f);
						
						
						wall.rows[x].bricks[y].draw(gl, spriteSheets);
						gl.glPopMatrix();
						gl.glLoadIdentity();
						break;
					case PBGameVars.BRICK_GREEN:
						gl.glMatrixMode(GL10.GL_MODELVIEW);
						gl.glLoadIdentity();
						gl.glPushMatrix();
						gl.glScalef(.25f, .25f, 1f);
						gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
						
						gl.glMatrixMode(GL10.GL_TEXTURE);
						gl.glLoadIdentity();
						gl.glTranslatef(0.0f, 0.25f , 0.0f);
						
						
						wall.rows[x].bricks[y].draw(gl, spriteSheets);
						gl.glPopMatrix();
						gl.glLoadIdentity();
						break;
					case PBGameVars.BRICK_LITE_GRAY:
						gl.glMatrixMode(GL10.GL_MODELVIEW);
						gl.glLoadIdentity();
						gl.glPushMatrix();
						gl.glScalef(.25f, .25f, 1f);
						gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
						
						gl.glMatrixMode(GL10.GL_TEXTURE);
						gl.glLoadIdentity();
						gl.glTranslatef(0.25f, 0.0f , 0.0f);
						
						
						wall.rows[x].bricks[y].draw(gl, spriteSheets);
						gl.glPopMatrix();
						gl.glLoadIdentity();
						break;
					case PBGameVars.BRICK_PURPLE:
						gl.glMatrixMode(GL10.GL_MODELVIEW);
						gl.glLoadIdentity();
						gl.glPushMatrix();
						gl.glScalef(.25f, .25f, 1f);
						gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
						
						gl.glMatrixMode(GL10.GL_TEXTURE);
						gl.glLoadIdentity();
						gl.glTranslatef(0.50f, 0.0f , 0.0f);
						
						
						wall.rows[x].bricks[y].draw(gl, spriteSheets);
						gl.glPopMatrix();
						gl.glLoadIdentity();
						break;
					case PBGameVars.BRICK_RED:
						gl.glMatrixMode(GL10.GL_MODELVIEW);
						gl.glLoadIdentity();
						gl.glPushMatrix();
						gl.glScalef(.25f, .25f, 1f);
						gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
						
						gl.glMatrixMode(GL10.GL_TEXTURE);
						gl.glLoadIdentity();
						gl.glTranslatef(0.0f, 0.0f , 0.0f);
						
						
						wall.rows[x].bricks[y].draw(gl, spriteSheets);
						gl.glPopMatrix();
						gl.glLoadIdentity();
						break;
					default:
						gl.glMatrixMode(GL10.GL_MODELVIEW);
						gl.glLoadIdentity();
						gl.glPushMatrix();
						gl.glScalef(.25f, .25f, 1f);
						gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
						
						gl.glMatrixMode(GL10.GL_TEXTURE);
						gl.glLoadIdentity();
						gl.glTranslatef(0.0f, 0.0f , 0.0f);
						
						
						wall.rows[x].bricks[y].draw(gl, spriteSheets);
						gl.glPopMatrix();
						gl.glLoadIdentity();
						break;
					}
					
				}
			}
		}
			
	}
	
	private void moveBall(GL10 gl){

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(.25f, .25f, 1f);	
		
		ball.posX += (float) ((PBGameVars.ballTargetX - ball.posX )/ (ball.posY / (PBGameVars.ballTargetY )));

		ball.posY -= PBGameVars.ballTargetY * 3;



		gl.glTranslatef(ball.posX, ball.posY, 0f);
		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f,0.0f, 0.0f); 	

		ball.draw(gl,spriteSheets);
		gl.glPopMatrix();
		gl.glLoadIdentity();

}
	
	private void movePlayer1(GL10 gl){

				gl.glMatrixMode(GL10.GL_MODELVIEW);
				gl.glLoadIdentity();
				gl.glPushMatrix();
				gl.glScalef(.25f, .25f, 1f);	
				
				if (PBGameVars.playerAction == PBGameVars.PLAYER_MOVE_LEFT_1 && PBGameVars.playerBankPosX > 0)
				{
					PBGameVars.playerBankPosX = PBGameVars.playerBankPosX - PBGameVars.PLAYER_MOVE_SPEED;
					
				}
				else if(PBGameVars.playerAction == PBGameVars.PLAYER_MOVE_RIGHT_1 &&  PBGameVars.playerBankPosX < 2.5)
				{
					PBGameVars.playerBankPosX = PBGameVars.playerBankPosX + PBGameVars.PLAYER_MOVE_SPEED;
				}

				gl.glTranslatef(PBGameVars.playerBankPosX, .5f, 0f);
				gl.glMatrixMode(GL10.GL_TEXTURE);
				gl.glLoadIdentity();
				gl.glTranslatef(0.0f,0.0f, 0.0f); 	

				player1.draw(gl);
				gl.glPopMatrix();
				gl.glLoadIdentity();

		}
	
	private void detectCollisions(){
		if(ball.posY <= 0){
			//gameover
		}
		
		for (int x = 0; x < wall.rows.length; x++)
		{
			for(int y = 0; y < wall.rows[x].bricks.length; y++)
			{
				if(!wall.rows[x].bricks[y].isDestroyed)
				{
					if (((ball.posY > wall.rows[x].bricks[y].posY - .25f) 
							&& (ball.posY < wall.rows[x].bricks[y].posY) 
							&& (ball.posX + .25f > wall.rows[x].bricks[y].posX)
							&& (ball.posX < wall.rows[x].bricks[y].posX + 1.50f)))
							{
						wall.rows[x].bricks[y].isDestroyed = true;
						
						PBGameVars.ballTargetY = PBGameVars.ballTargetY * -1f;
						if(PBGameVars.ballTargetX == -2f){
							PBGameVars.ballTargetX = 5f;
						}else{
							PBGameVars.ballTargetX = -2f;
						}
						
						}
						
						
					}
					
				}
			}
		
		if((ball.posY - .25f <= .5f) 
				&& (ball.posX + .25f > PBGameVars.playerBankPosX )
				&& (ball.posX < PBGameVars.playerBankPosX  + 1.50f)){
			PBGameVars.ballTargetY = PBGameVars.ballTargetY * -1f;
			if(PBGameVars.ballTargetX == -2f){
				PBGameVars.ballTargetX = 5f;
			}else{
				PBGameVars.ballTargetX = -2f;
			}
		}
		if(ball.posX < 0 || ball.posX + .25f > 3.75f)
		{
			PBGameVars.ballTargetX = PBGameVars.ballTargetX * -1f;
			
		}

	}	
}

