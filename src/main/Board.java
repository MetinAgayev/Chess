package main;

import java.awt.*;

public class Board {
    static final int MAX_COL = 8;
    static final int MAX_ROW = 8;
    public static final int SQUARE_SIZE = 100;
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE / 2;

    public void draw(Graphics2D graphics2D) {
        int color=0;
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                if(color==0){
                    graphics2D.setColor(new Color(210,165,125));
                    color=1;
                }
                else {
                    graphics2D.setColor(new Color(175,115,70));
                    color=0;
                }
                graphics2D.fillRect(col*SQUARE_SIZE,row*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE);
            }
            if(color==0){
                color=1;
            }
            else{
                color=0;
            }
        }
    }
}
