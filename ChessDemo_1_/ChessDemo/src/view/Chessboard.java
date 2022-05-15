//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import controller.ClickController;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import javax.swing.JComponent;

import model.*;

public class Chessboard extends JComponent {
    private static final int CHESSBOARD_SIZE = 8;
    private final ChessComponent[][] chessComponents = new ChessComponent[8][8];
    private ChessColor currentColor;
    private final ClickController clickController;
    private final int CHESS_SIZE;

    public Chessboard(int width, int height) {
        this.currentColor = ChessColor.WHITE;
        this.clickController = new ClickController(this);
        this.setLayout((LayoutManager)null);
        this.setSize(width, height);
        this.CHESS_SIZE = width / 8;
        System.out.printf("chessboard size = %d, chess size = %d\n", width, this.CHESS_SIZE);
        this.initiateEmptyChessboard();
        this.initRookOnBoard(0, 0, ChessColor.WHITE);
        this.initRookOnBoard(0, 7, ChessColor.WHITE);
        this.initRookOnBoard(7, 0, ChessColor.BLACK);
        this.initRookOnBoard(7, 7, ChessColor.BLACK);


        this.initKingOnBoard(0, 4, ChessColor.WHITE);
        this.initKingOnBoard(7, 4, ChessColor.BLACK);

        this.initKnightOnBoard(0, 6, ChessColor.WHITE);
        this.initKnightOnBoard(0, 1, ChessColor.WHITE);
        this.initKnightOnBoard(7, 6, ChessColor.BLACK);
        this.initKnightOnBoard(7, 1, ChessColor.BLACK);

        this.initQueenOnBoard(0,3,ChessColor.WHITE);
        this.initQueenOnBoard(7,3,ChessColor.BLACK);

        this.initBishopOnBoard(0,2,ChessColor.WHITE);
        this.initBishopOnBoard(0,5,ChessColor.WHITE);
        this.initBishopOnBoard(7,2,ChessColor.BLACK);
        this.initBishopOnBoard(7,5,ChessColor.BLACK);

        for (int i = 0; i <8 ; i++) {
            this.initPawnOnBoard(1,i,ChessColor.WHITE);
        }
        for (int i = 0; i < 8; i++) {
            this.initPawnOnBoard(6,i,ChessColor.BLACK);

        }
    }

    public ChessComponent[][] getChessComponents() {
        return this.chessComponents;
    }

    public ChessColor getCurrentColor() {
        return this.currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX();
        int col = chessComponent.getChessboardPoint().getY();
        if (this.chessComponents[row][col] != null) {
            this.remove(this.chessComponents[row][col]);
        }

        this.add(this.chessComponents[row][col] = chessComponent);
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        if (!(chess2 instanceof EmptySlotComponent)) {
            this.remove((Component)chess2);
            this.add((Component)(chess2 = new EmptySlotComponent(((ChessComponent)chess2).getChessboardPoint(), ((ChessComponent)chess2).getLocation(), this.clickController, this.CHESS_SIZE)));
        }

        chess1.swapLocation((ChessComponent)chess2);
        int row1 = chess1.getChessboardPoint().getX();
        int col1 = chess1.getChessboardPoint().getY();
        this.chessComponents[row1][col1] = chess1;
        int row2 = ((ChessComponent)chess2).getChessboardPoint().getX();
        int col2 = ((ChessComponent)chess2).getChessboardPoint().getY();
        this.chessComponents[row2][col2] = (ChessComponent)chess2;
        chess1.repaint();
        ((ChessComponent)chess2).repaint();
    }

    public void initiateEmptyChessboard() {
        for(int i = 0; i < this.chessComponents.length; ++i) {
            for(int j = 0; j < this.chessComponents[i].length; ++j) {
                this.putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), this.calculatePoint(i, j), this.clickController, this.CHESS_SIZE));
            }
        }

    }

    public void swapColor() {
        this.currentColor = this.currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
    }

    private void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), this.calculatePoint(row, col), color, this.clickController, this.CHESS_SIZE);
        chessComponent.setVisible(true);
        this.putChessOnBoard(chessComponent);
    }

    private void initKingOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row, col), this.calculatePoint(row, col), color, this.clickController, this.CHESS_SIZE);
        chessComponent.setVisible(true);
        this.putChessOnBoard(chessComponent);
    }

    private void initKnightOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row, col), this.calculatePoint(row, col), color, this.clickController, this.CHESS_SIZE);
        chessComponent.setVisible(true);
        this.putChessOnBoard(chessComponent);
    }

    private void initQueenOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row, col), this.calculatePoint(row, col), color, this.clickController, this.CHESS_SIZE);
        chessComponent.setVisible(true);
        this.putChessOnBoard(chessComponent);
    }

    private void initBishopOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row, col), this.calculatePoint(row, col), color, this.clickController, this.CHESS_SIZE);
        chessComponent.setVisible(true);
        this.putChessOnBoard(chessComponent);
    }

    private void initPawnOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row, col), this.calculatePoint(row, col), color, this.clickController, this.CHESS_SIZE);
        chessComponent.setVisible(true);
        this.putChessOnBoard(chessComponent);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private Point calculatePoint(int row, int col) {
        return new Point(col * this.CHESS_SIZE, row * this.CHESS_SIZE);
    }

    public void loadGame(List<String> chessData) {
        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        chessData.forEach(var10001::println);
    }
}
