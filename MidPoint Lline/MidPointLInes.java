/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author sabab
 */
public class MidPointLines  implements GLEventListener {
    /**
     * Interface to the GLU library.
     */
    private GLU glu;

    /**
     * Take care of initialization here.
     * @param gld
     */
    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    /**
     * Take care of drawing here.
     * @param drawable
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        /*
         * put your code here
         */
       //points should be in the same zone
       DrawMPL(gl,10,10,60,50);
        DrawMPL(gl,10,-10,60,-50);
        DrawMPL(gl,-30,-10,-100,-40);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //do nothing
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //do nothing
    }
    
    int dx, dy, b;
    private void DrawMPL(GL2 gl, int x1, int y1, int x2, int y2) {
       //write your own code
     int zone = findZone(x1, y1, x2, y2);
     x1 = Math.abs(x1);
        x2 = Math.abs(x2);
        y1 = Math.abs(y1);
        y2 = Math.abs(y2);
        if(zone == 1 || zone == 2 || zone == 5 || zone == 6){
            int tempy1 = y1;
            y1 = x1;
            x1 = tempy1;

            int tempy2 = y2;
            y2 = x2;
            x2 = tempy2;
        }

     dx=x2-x1;
     dy=y2-y1;
     int d=(2*dy)-dx;
     int de=2*dy;
     int dne=2*(dy-dx);
     int y=y1;
     int x=x1;
     gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(x,y);
     while(x<x2)
     {
      if(d<0)
      {x++;d+=de;}
      else
      {x++;y++;d+=dne;}
      //gl.glBegin(GL2.GL_LINES);
           gl.glVertex2d(x,y);
     }
      // gl.glPointSize(1.0f);
       //gl.glColor3d(1, 0, 0);
                    
       //gl.glBegin(GL2.GL_LINES);       
       
       
      //gl.glVertex2d(x1, y1);           
     // gl.glVertex2d(x2, y2);
     if(zone == 0){
            gl.glVertex2i(x, y);
        } else if (zone == 1) {
            gl.glVertex2i(y, x);
        } else if (zone == 2) {
            gl.glVertex2i(-y, x);
        } else if (zone == 3) {
            gl.glVertex2i(-x, y);
        } else if (zone == 4) {
            gl.glVertex2i(-x, -y);
        } else if (zone == 5) {
            gl.glVertex2i(-y, -x);
        } else if (zone == 6) {
            gl.glVertex2i(y, -x);
        } else if (zone == 7) {
            gl.glVertex2i(x, -y);
        }

       gl.glEnd();
    }
    
    //y=mx+b
    private int func(int x, float y){
        return (int)(dy*x - y*dx + b*dx);
    }
    
    
    int findZone(int x1, int y1, int x2, int y2) {
     int dx = x2 - x1, dy = y2 - y1;
        int zone = -1;

        if(dx > 0 && dy > 0){
            if (Math.abs(dx) >= Math.abs(dy))
                zone = 0;
            else
                zone = 1;
        } else if(dx < 0 && dy > 0){
            if (Math.abs(dy) >= Math.abs(dx))
                zone = 2;
            else
                zone = 3;
        } else if(dx < 0 && dy < 0){
            if (Math.abs(dx) >= Math.abs(dy))
                zone = 4;
            else
                zone = 5;
        } else {
            if (Math.abs(dy) >= Math.abs(dx))
                zone = 6;
            else
                zone = 7;
        }

        return zone;
    }
    
    int convertX(int x, int y, int zone){
        int convertedX=0;       
        return convertedX;
    }
    
    int convertY(int x, int y, int zone){
       int convertedY=0;        
       return convertedY;
    }
    
    
    public void dispose(GLAutoDrawable arg0) {
        //do nothing
    }
}

