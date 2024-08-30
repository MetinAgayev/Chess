package piece;

import main.GamePanel;

public class King extends Piece {
    public King(int color, int col, int row) {
        super(col, row, color);
        if (color == GamePanel.WHITE) {
            image = getImage("/piece/w-king");
        } else {
            image = getImage("/piece/b-king");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {

        if (isWithinBoard(targetCol, targetRow) == true) {
            if (Math.abs(targetCol-preCol) + Math.abs(targetRow - preRow) == 1 ||
                    Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isWithinBoard(int targetCol, int targetRow) {
        if (targetRow >= 0 && targetRow <= 7 && targetCol >= 0 && targetRow <= 7) {
            return true;
        }
        return false;
    }
}
