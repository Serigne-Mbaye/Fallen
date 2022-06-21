package fallen;

import java.awt.*;


public class Brick {
    int width = 0;
    private static final int HEIGHT = 10;
    int x = 0;
    int y = 0;
    int ya = -1; // da verificare
    private Game game;
    public boolean canc = false;


    public Brick( int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public void move() {
        y = y + ya;
        if (y < -20)
            canc = true;
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, y, width, HEIGHT);
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, width, HEIGHT);
    }

    public int getTopY()
    {
        return y;
    }


}
