package piece;

import main.GamePanel;

public class Knight extends Piece{
    public Knight(int color, int col, int row) {
        super(col, row, color);
        if(color==GamePanel.WHITE){
            image=getImage("/piece/w-knight");
        }
        else{
            image=getImage("/piece/b-knight");
        }
    }
}
