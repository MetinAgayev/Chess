package main;

import piece.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;
    boolean canMove;
    boolean validSquare;
    public static ArrayList<Piece> pieces = new ArrayList();
    public static ArrayList<Piece> simPieces = new ArrayList();
    Piece activeP;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(pieces, simPieces);
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setPieces() {
        //White Pieces
        pieces.add(new Pawn(WHITE, 0, 6));
        pieces.add(new Pawn(WHITE, 1, 6));
        pieces.add(new Pawn(WHITE, 2, 6));
        pieces.add(new Pawn(WHITE, 3, 6));
        pieces.add(new Pawn(WHITE, 4, 6));
        pieces.add(new Pawn(WHITE, 5, 6));
        pieces.add(new Pawn(WHITE, 6, 6));
        pieces.add(new Pawn(WHITE, 7, 6));
        pieces.add(new Rook(WHITE, 0, 7));
        pieces.add(new Rook(WHITE, 7, 7));
        pieces.add(new Knight(WHITE, 1, 7));
        pieces.add(new Knight(WHITE, 6, 7));
        pieces.add(new Bishop(WHITE, 2, 7));
        pieces.add(new Bishop(WHITE, 5, 7));
        pieces.add(new Queen(WHITE, 3, 7));
        pieces.add(new King(WHITE, 4, 4));

        //Black pieces
        pieces.add(new Pawn(BLACK, 0, 1));
        pieces.add(new Pawn(BLACK, 1, 1));
        pieces.add(new Pawn(BLACK, 2, 1));
        pieces.add(new Pawn(BLACK, 3, 1));
        pieces.add(new Pawn(BLACK, 4, 1));
        pieces.add(new Pawn(BLACK, 5, 1));
        pieces.add(new Pawn(BLACK, 6, 1));
        pieces.add(new Pawn(BLACK, 7, 1));
        pieces.add(new Rook(BLACK, 0, 0));
        pieces.add(new Rook(BLACK, 7, 0));
        pieces.add(new Knight(BLACK, 1, 0));
        pieces.add(new Knight(BLACK, 6, 0));
        pieces.add(new Bishop(BLACK, 2, 0));
        pieces.add(new Bishop(BLACK, 5, 0));
        pieces.add(new Queen(BLACK, 3, 0));
        pieces.add(new King(BLACK, 4, 0));

    }

    public void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target) {
        target.clear();
        for (int i = 0; i < source.size(); i++) {
            target.add(source.get(i));

        }
    }

    public void update() {
        if (mouse.pressed) {
            if (activeP == null) {
                for (Piece piece : simPieces) {
                    if (piece.color == currentColor && piece.col == mouse.x / Board.SQUARE_SIZE &&
                            piece.row == mouse.y / Board.SQUARE_SIZE) {
                        activeP = piece;
                    }
                }
            } else {
                simulate();
            }
        }
        if (mouse.pressed == false) {
            if (activeP != null) {
                if (validSquare) {
                    activeP.updatePosition();
                } else {
                    activeP.resetPosition();
                    activeP = null;
                }
                activeP = null;
            }
        }
    }

    public void simulate() {
        canMove = false;
        validSquare = false;
        activeP.x = mouse.x - Board.SQUARE_SIZE;
        activeP.y = mouse.y - Board.SQUARE_SIZE;
        activeP.col = activeP.getCol(activeP.x);
        activeP.row = activeP.getRow(activeP.y);

        if (activeP.canMove(activeP.col, activeP.row)) {
            canMove = true;
            validSquare = true;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        board.draw(graphics2D);
        for (Piece piece : simPieces) {
            piece.draw(graphics2D);
        }

        if (activeP != null) {
            if (canMove) {
                graphics2D.setColor(Color.white);
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                graphics2D.fillRect(activeP.col * Board.SQUARE_SIZE, activeP.row * Board.SQUARE_SIZE, Board.SQUARE_SIZE,
                        Board.SQUARE_SIZE);
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            activeP.draw(graphics2D);
        }
    }

    @Override
    public void run() {
        double drawInternal = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInternal;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
}
