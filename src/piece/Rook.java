package piece;

import main.GamePanel;

public class Rook extends Piece{
    public Rook(int color, int col, int row) {
        super(col, row, color);
        if(color== GamePanel.WHITE){
            image=getImage("/piece/chess_piece_2_white_rook");
        }
        else{
            image=getImage("/piece/chess_piece_2_black_rook");
        }
    }
}
