import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main implements GLEventListener {

    private GLU glu = new GLU();

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        drawTriangle(gl);
        drawCircle(gl);

        gl.glFlush();
    }

    private void drawCircle(GL2 gl) {
        gl.glTranslatef(0.0f, 1.0f, -2.0f);
        gl.glBegin(GL2.GL_LINE_LOOP);
        int points = 100;
        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / 100;
            gl.glVertex2f((float) Math.cos(angle), (float) Math.sin(angle));
        }
        gl.glEnd();
    }

    private void drawTriangle(GL2 gl) {
        gl.glTranslatef(-2f, -1.5f, -7.0f);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f(1.5f, 2.0f, 0.0f);
        gl.glVertex3f(1.5f, -1.0f, 0.0f);
        gl.glVertex3f(2.5f, -1.0f, 0.0f);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        if (height <= 0)
            height = 1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.f, h, 1, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public static void main(String[] args) {

        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(glProfile);
        GLCanvas glCanvas = new GLCanvas(capabilities);

        Main main = new Main();
        glCanvas.addGLEventListener(main);
        glCanvas.setSize(100, 100);

        FPSAnimator fpsAnimator = new FPSAnimator(glCanvas, 300, true);
        JFrame frame = new JFrame("The first lab");
        frame.getContentPane().add(glCanvas);

        frame.setSize(300, 300);
        frame.setBackground(Color.BLUE);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (fpsAnimator.isStarted())
                    fpsAnimator.stop();
                System.exit(0);
            }
        });

        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(0, 0));
        frame.add(jPanel, BorderLayout.SOUTH);
    }
}
