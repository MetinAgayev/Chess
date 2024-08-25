package piece;

import main.GamePanel;

public class Bishop extends Piece{
    public Bishop(int color, int col, int row) {
        super(col, row, color);
        if(color== GamePanel.WHITE){
            image=getImage("/piece/chess_piece_2_white_bishop");
        }
        else{
            image=getImage("/piece/chess_piece_2_black_bishop");
        }
    }
}
