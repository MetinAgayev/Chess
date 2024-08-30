package piece;

import main.GamePanel;

public class Queen extends Piece{
    public Queen(int color, int col, int row) {
        super(col, row, color);
        if(color== GamePanel.WHITE){
            image=getImage("/piece/w-queen");
        }
        else{
            image=getImage("/piece/b-queen");
        }
    }
}
