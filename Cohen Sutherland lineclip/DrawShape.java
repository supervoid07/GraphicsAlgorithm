/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cohenlineclip;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



public class DrawShape implements GLEventListener {
    private GLU glu;

    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-500, -500, 500, 500);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-500, 500, -500, 500);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        int x_max = 300;
        int x_min = -300;
        int y_max = 210;
        int y_min = -200;



        Mid_Point_Line_Draw lineDraw = new Mid_Point_Line_Draw();

        lineDraw.drawTheLine(gl, x_min, y_min, x_min, y_max);
        lineDraw.drawTheLine(gl, x_min, y_min, x_max, y_min);
        lineDraw.drawTheLine(gl, x_min, y_max, x_max, y_max);
        lineDraw.drawTheLine(gl, x_max, y_min, x_max, y_max);

        LineClip lc = new LineClip();
        lc.cohen_sutherland(gl,400,0,0,300);
        lc.cohen_sutherland(gl,0,300,-400,0);
        lc.cohen_sutherland(gl,-400,0,0,-300);
        lc.cohen_sutherland(gl,0,-300,400,0);






    }


    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //do nothing
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //do nothing
    }

    public void dispose(GLAutoDrawable arg0) {
        //do nothing
    }


    

}

