import com.jogamp.opengl.GL2;

public class Line {

    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public void drawBresenham8Line(GL2 gl, int width) {
        gl.glBegin(GL2.GL_LINE_LOOP);
        int x0 = start.getX();
        int y0 = start.getY();
        int x1 = end.getX();
        int y1 = end.getY();

        int dx = x1 - x0;
        int dy = y1 - y0;

        int d = 2 * dy - dx;

        int x = x0;
        int y = y0;

        if (dy > dx) {
            for (int i = 0; i < width; i++) {
                if (d > 0) {
                    x = x + 1;
                    d = d + 2 * (dx - dy);
                } else {
                    d = d + 2 * dx;
                }
                gl.glVertex2i(x, y + i);
            }
        } else {
            for (int i = 0; i < width; i++) {
                if (d > 0) {
                    y = y + 1;
                    d = d + 2 * (dy - dx);
                } else {
                    d = d + 2 * dy;
                }
                gl.glVertex2i(x + i, y);
            }
        }
        gl.glBegin(GL2.GL_LINE_LOOP);
    }

}
