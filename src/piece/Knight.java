package piece;

import main.GamePanel;

public class Knight extends Piece{
    public Knight(int col, int row, int color) {
        super(col, row, color);
        if(color==GamePanel.WHITE){
            image=getImage("/piece/chess_piece_2_white_knight/");
        }
        else{
            image=getImage("/piece/chess_piece_2_black_knight/");
        }
    }
}
