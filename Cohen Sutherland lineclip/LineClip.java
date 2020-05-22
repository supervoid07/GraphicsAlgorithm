package Cohenlineclip;

import javax.media.opengl.GL2;

public class LineClip {
    private final int TOP = 8, BOTTOM = 4, LEFT = 1, RIGHT = 2;
    public void cohen_sutherland(GL2 gl, int x0, int y0, int x1, int y1) {

        int x_max = 300;
        int x_min = -300;
        int y_max = 210;
        int y_min = -200;

        Mid_Point_Line_Draw lineDraw = new Mid_Point_Line_Draw();

        int code0, code1, x = 0, y = 0;
        code0 = getCode(x0, y0);
        code1 = getCode(x1, y1);
        while (true) {
            if ((code0 | code1) == 0) //----completely accepted --- line will be colored green
            {
                gl.glColor3f(0, 1, 0);
                lineDraw.drawTheLine(gl, x0, y0, x1, y1);
                gl.glColor3f(1, 1, 1);
                break;
            } else if ((code0 & code1) != 0) //----completely rejected --- line will be colored red
            {
                gl.glColor3f(1, 0, 0);
                lineDraw.drawTheLine(gl, x0, y0, x1, y1);
                gl.glColor3f(1, 1, 1);
                break;
            } else //partially accepted
            {
                gl.glColor3f(1, 0, 0);
                lineDraw.drawTheLine(gl, x0, y0, x1, y1);
                gl.glColor3f(1, 1, 1);
                int code = (code0 != 0) ? code0 : code1;

                if ((code & TOP) != 0) {
                    y = y_max;
                    x = x0 + ((x1 - x0) * (y - y0) / (y1 - y0));
                } else if ((code & BOTTOM) != 0) {
                    y = y_min;
                    x = x0 + ((x1 - x0) * (y - y0) / (y1 - y0));
                } else if ((code & LEFT) != 0) {
                    x = x_min;
                    y = y0 + ((y1 - y0) * (x - x0) / (x1 - x0));
                } else if ((code & RIGHT) != 0) {
                    x = x_max;
                    y = y0 + ((y1 - y0) * (x - x0) / (x1 - x0));
                }
                if (code == code0) {
                    x0 = x;
                    y0 = y;
                    code0 = getCode(x0, y0);
                } else {
                    x1 = x;
                    y1 = y;
                    code1 = getCode(x1, y1);
                }
            }
        }
    }

    private int getCode(int x, int y ) {
        int x_max = 300;
        int x_min = -300;
        int y_max = 210;
        int y_min = -200;

        int code = 0;
        if (x > x_max)
            code += RIGHT;
        if (y > y_max)
            code += TOP;
        if (x < x_min)
            code += LEFT;
        if (y < y_min)
            code += BOTTOM;

        return code;
    }
}
