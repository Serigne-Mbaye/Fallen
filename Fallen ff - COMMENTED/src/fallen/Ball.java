package fallen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Ball {
    private static final int DIMS = 30;
    /*
     * costante
     *      private: visibili da fuori ma usabili solo dentro
     *      static:Invocabile indipendentemente dall'esistenza di oggetti istanza della classe
     *      final:oggetto, una volta istanziato, non può più essere modificato.
     */
    public static boolean boo = false;
    static int  x = 120; //get bounds
    static int y = 0;    //get bounds
    int gravity = 3;
    int xa = 0;
    private Game game;

    public Ball(Game game) {
        this.game= game;
        /*
         * This: riferimento da dentro un metodo di una classe agli attributi di quella classe
         */
    }

    void move() {
        int i = 0;
        if (x+xa>=0 && x+xa<=game.getWidth()-30)// movimeto palla dx & sx
        {
            x=x+xa;
        }
        boo=intersec();
        if (boo)        // palla interseca con Brick
        {
            y = y -1; // palla si muove con il rettangolo in su
        }
        else if (y+gravity < game.getHeight()-30)
        {
                y = y + gravity;
        }
        if (y < -29 || y > 329)
        {
            game.GameOver();
        }
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -2;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = 2;
    }
    /*
     *  The keyboard and mouse events are controled by the operative system.
     *  The AWT engine and in particular the AWT-Windows thread comunicate with the
     *  operative system and knows when an event occurs. When a new event comes
     *  along it is placed in the "Event queue" so that it is attended by the
     *  AWT-EventQueue thread in its turn.
     */



    public boolean intersec()
    {
        int i = 0;
        for (Brick b: game.bricks
             ) {
            if (b.getBounds().intersects(getbounds())) {
                   i++;
            }
        }
        return i > 0;
    }



    public void paint(Graphics2D g) {
        g.fillOval(x, y, 30, 30);
    }
    public static Rectangle getbounds()
    {
        return new Rectangle(x,y,DIMS, DIMS);
    }
}