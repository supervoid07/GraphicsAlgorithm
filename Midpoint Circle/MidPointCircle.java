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
 * @author sabab
 */
public class MidPointCircle implements GLEventListener {
    /**
     * Interface to the GLU library.
     */
    private GLU glu;

    /**
     * Take care of initialization here.
     *
     * @param gld
     */
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

    /**
     * Take care of drawing here.
     *
     * @param drawable
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        DrawCircle(gl, 50, 50, 400);
        
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

    private void DrawCircle(GL2 gl, int xC, int yC, int radius) {
        //write your own code
        gl.glPointSize(10.0f);
        gl.glColor3d(1, 2, 1);
        gl.glBegin(GL2.GL_POINTS);

        gl.glVertex2d(xC, yC);

        int x = radius, y = 0, d = -4 * radius + 5;
        draw8SymmetricCurves(gl, xC, yC, x, y);
        while (y <= x) {
            if (d < 0) {
                d = d + ((2 * y + 3) * 4);
                y++;
            } else {
                d += ((-2 * x + 2 * y + 5) * 4);
                x--;
                y++;
            }
            draw8SymmetricCurves(gl, xC, yC, x, y);
        }
        gl.glEnd();
    }

    public void draw8SymmetricCurves(GL2 gl, int xC, int yC, int x, int y) {
        gl.glBegin(GL2.GL_POINTS);

        gl.glVertex2d(xC + x, yC + y);
        gl.glVertex2d(xC + y, yC + x);

        gl.glVertex2d(xC - x, yC + y);
        gl.glVertex2d(xC - y, yC + x);

        gl.glVertex2d(xC - x, yC - y);
        gl.glVertex2d(xC - y, yC - x);

        gl.glVertex2d(xC + x, yC - y);
        gl.glVertex2d(xC + y, yC - x);
    }
}

