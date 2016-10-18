import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;
import java.awt.*;

public class Lab2 implements GLEventListener {

    private static final int WIDTH = 630;
    private GLU glu;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        glu = new GLU();
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();

        glu.gluOrtho2D(-WIDTH/2, WIDTH/2, -250, 250);

        gl.glBegin(GL.GL_POINTS);
        Line line = new Line(new Point(0, -159), new Point(258, 200));
        line.drawBresenham8Line(gl, WIDTH);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {

    }

    public static void main(String[] args) {
        Lab2 lab2 = new Lab2();

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas glCanvas = new GLCanvas(capabilities);

        glCanvas.addGLEventListener(lab2);
        glCanvas.setSize(WIDTH, 500);

//        creating frame
        final JFrame frame = new JFrame("Line");

        //adding canvas to frame
        frame.getContentPane().add(glCanvas);
        initViews(frame);
        frame.setSize(WIDTH, 570);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void initViews(JFrame frame) {
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton convertToCylindrical = new JButton("to Cylindrical");
        convertToCylindrical.setSize(100, 30);

        JLabel cartesianLabel = new JLabel("Cartesian: ");
        JLabel cylindricalLabel = new JLabel("Cylindrical: ");
        JTextField x = new JTextField(4);
        JTextField y = new JTextField(4);
        JTextField z = new JTextField(4);
        JTextField distance = new JTextField(4);
        JTextField phi = new JTextField(4);
        JTextField height = new JTextField(4);

        convertToCylindrical.addActionListener(e -> {
            Point3D point3D = new Point3D(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()),
                    Integer.parseInt(z.getText()));
            CylindricalPoint cylindricalPoint = Converter.convert(point3D);
            distance.setText(String.valueOf(cylindricalPoint.getRadiusDistance()));
            phi.setText(String.valueOf(cylindricalPoint.getPhi()));
            height.setText(String.valueOf(cylindricalPoint.getHeight()));
        });

        panel.add(cartesianLabel, BorderLayout.WEST);
        panel.add(x, BorderLayout.WEST);
        panel.add(y, BorderLayout.WEST);
        panel.add(z, BorderLayout.WEST);
        panel.add(convertToCylindrical, BorderLayout.WEST);
        panel.add(cylindricalLabel, BorderLayout.WEST);
        panel.add(distance, BorderLayout.WEST);
        panel.add(phi, BorderLayout.WEST);
        panel.add(height, BorderLayout.WEST);

        frame.add(panel, BorderLayout.SOUTH);
    }

}
