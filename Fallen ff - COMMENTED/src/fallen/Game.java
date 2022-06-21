package fallen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Game extends JPanel {
    static ArrayList<Brick> bricks = new ArrayList<>();
    /*
     *  ArrayList in Java is used to store dynamically sized collection of
     *  elements. Contrary to Arrays that are fixed in size, an ArrayList grows
     *  its size automatically when new elements are added to it.
     */
    static Random rand = new Random();
    static int points = 0, millis = 9;

    Ball ball = new Ball(this);

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ball.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                ball.keyPressed(e);
            }
        });
        setFocusable(true); // attiva la monirotaazione dei tasti
    }

    private void move() {
        ball.move();
        int i = 0;
        for (Brick b : bricks){
            b.move();

        }
    }
    private void builder(int i)
    {
        int hole, dimBuco = 70;
        for (int j = 0;j < i;j++ )
        {
            hole= rand.nextInt(249)+1;
            bricks.add(new Brick(0, 420, hole));
            bricks.add(new Brick(hole+dimBuco, 420, this.getWidth()-hole-dimBuco));
            points++;
            System.out.println(points);
            if (points%10==0)
            {
                millis--;
            }
        }
        for (int m = 0; m < bricks.size(); m++) {
            if (bricks.get(i).canc)
            {
                bricks.remove(i);
            }
        }

    }
    public void GameOver()
    {
        JOptionPane.showMessageDialog(this,"Points: "+points,"Game Over",JOptionPane.PLAIN_MESSAGE);
        System.exit(ABORT);

    }

    @Override
    public void paint(Graphics g) {
        int i=0;
        super.paint(g);
        /*
         * The precedent draw won't be "canceled" continuos line
         */
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        for (Brick b : bricks){
            b.paint(g2d);
            if (b.y == 300)
            {
                i++;
            }
        }
        i = i/2;
        builder(i);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Fall down");
        Game game = new Game();
        frame.add(game);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         *  This engine communicates with the operative system both to paint in the
         *  screen as to receive information from the keyboard and from the
         *  mouse.
         */
        final int dimBuco = 70;
        int buco;
        buco= 80;
        bricks.add(new Brick(0, game.getHeight()-20, buco));
        bricks.add(new Brick(buco+dimBuco, game.getHeight()-20, game.getWidth()-buco-dimBuco));
        while (true) {
            game.move();
            game.repaint();
            /*
             *  call repaint(), which forces de AWT engine to call the paint
             *  method to paint again the canvas.
             */
            Thread.sleep(millis);
        }
    }
}