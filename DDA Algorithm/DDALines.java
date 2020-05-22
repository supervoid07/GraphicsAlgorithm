/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author nazmus.sabab
 */
public class DDALines implements GLEventListener {
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
        DDA(gl, 10, 20, 100, 40);
        DDA(gl, 100, 40, 10, 20);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //do nothing
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //do nothing
    }

    private void DDA(GL2 gl, int x1, int y1, int x2, int y2) {
        //write your own code

        // ASSIGNMENT STARTS HERE

        gl.glPointSize(1.0f);
        gl.glColor3d(1, 0, 0);
        gl.glBegin(GL2.GL_POINTS);

        gl.glVertex2d(x1, y1);
        gl.glVertex2d(x2, y2);

        float len;
        float dX = Math.abs(x2-x1);
        float dY = Math.abs(y2-y1);

        if(dX >= dY){
            len = dX;
        }else{
            len = dY;
        }
        dX = Math.abs(x2-x1)/len;
        dY = Math.abs(y2-y1)/len;

        float x = x1 + (dX);
        float y = y1 + (dY);

        for(int i=0; i<=len; i++){
            gl.glVertex2d(x, y);
            x += dX;
            y += dY;
        }

        // ASSIGNMENT ENDS HERE
    }

    public void dispose(GLAutoDrawable arg0) {
        //do nothing
    }
}
