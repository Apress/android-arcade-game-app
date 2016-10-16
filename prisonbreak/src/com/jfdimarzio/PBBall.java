package com.jfdimarzio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class PBBall {
	public float posY = 0f;
	public float posX = 0f;
	public float posT = 0f;
	
	public int ballMode = 0;
	
	private Random randomPos = new Random();
	private int damage = 0;
	
	
   private FloatBuffer vertexBuffer;
   private FloatBuffer textureBuffer;
   private ByteBuffer indexBuffer;
   
   private float vertices[] = {
                   0.0f, 0.0f, 0.0f, 
                   0.25f, 0.0f, 0.0f,  
                   0.25f, 0.25f, 0.0f,  
                   0.0f, 0.25f, 0.0f,
                                 };
   
    private float texture[] = {          
                   0.0f, 0.0f,
                   0.50f, 0.0f,
                   0.50f, 0.50f,
                   0.0f, 0.50f, 
                                  };
        
    private byte indices[] = {
                   0,1,2, 
                   0,2,3, 
                                  };
   

   public PBBall() {
	   posY = (randomPos.nextFloat() + 1f) * (float)(-1.75 - -1.6);
	   posX = randomPos.nextFloat() * .75f;
	   
      ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
      byteBuf.order(ByteOrder.nativeOrder());
      vertexBuffer = byteBuf.asFloatBuffer();
      vertexBuffer.put(vertices);
      vertexBuffer.position(0);

      byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
      byteBuf.order(ByteOrder.nativeOrder());
      textureBuffer = byteBuf.asFloatBuffer();
      textureBuffer.put(texture);
      textureBuffer.position(0);

      indexBuffer = ByteBuffer.allocateDirect(indices.length);
      indexBuffer.put(indices);
      indexBuffer.position(0);
   }
   
   public void applyDamage(){
	   if(ballMode == PBGameVars.BALL_MODE_SPIKE){
		   damage++;
		   if(damage == PBGameVars.NUMBER_OF_MODE_IMPACTS)
		   {
			   ballMode = PBGameVars.BALL_MODE_NORMAL;
		   }
		   
	   }

   }

   public void draw(GL10 gl, int[] spriteSheet) {
	   gl.glBindTexture(GL10.GL_TEXTURE_2D, spriteSheet[2]);
      
	   gl.glFrontFace(GL10.GL_CCW);
	   gl.glEnable(GL10.GL_CULL_FACE);
	   gl.glCullFace(GL10.GL_BACK);
           
	   gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	   gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

	   gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
	   gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
      
	   gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);      
      
	   gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	   gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	   gl.glDisable(GL10.GL_CULL_FACE);
   }

}

