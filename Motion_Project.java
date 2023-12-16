import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Motion_Project {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Motion Background");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        MotionPanel motionPanel = new MotionPanel();
        frame.add(motionPanel);
        frame.setVisible(true);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                motionPanel.moveCircles();
                motionPanel.repaint();
            }
        });
        timer.start();
    }
}

class MotionPanel extends JPanel {
    private static final int NUM_CIRCLES = 50;
    private int[] x, y;
    private int[] xSpeed, ySpeed;

    public MotionPanel() {
        x = new int[NUM_CIRCLES];
        y = new int[NUM_CIRCLES];
        xSpeed = new int[NUM_CIRCLES];
        ySpeed = new int[NUM_CIRCLES];

        // Initialize circle positions and speeds
        for (int i = 0; i < NUM_CIRCLES; i++) {
            x[i] = (int) (Math.random() * getWidth());
            y[i] = (int) (Math.random() * getHeight());
            xSpeed[i] = (int) (Math.random() * 5) + 1;
            ySpeed[i] = (int) (Math.random() * 5) + 1;
        }
    }

    public void moveCircles() {
        // Move circles based on their speeds
        for (int i = 0; i < NUM_CIRCLES; i++) {
            x[i] += xSpeed[i];
            y[i] += ySpeed[i];

            // Wrap around when circles move out of the panel
            x[i] = (x[i] + getWidth()) % getWidth();
            y[i] = (y[i] + getHeight()) % getHeight();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw circles at their current positions
        for (int i = 0; i < NUM_CIRCLES; i++) {
            g.setColor(Color.BLUE);
            g.fillOval(x[i], y[i], 20, 20);
        }
    }
}
