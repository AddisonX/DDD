
package model;

import controller.ClickController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import view.ChessboardPoint;

public class PawnChessComponent extends ChessComponent {
    private static Image KING_WHITE;
    private static Image KING_BLACK;
    private Image kingImage;
//    public int first;

    public void loadResource() throws IOException {
        if (KING_WHITE == null) {
            KING_WHITE = ImageIO.read(new File("ChessDemo/images/pawn-white.png"));
        }

        if (KING_BLACK == null) {
            KING_BLACK = ImageIO.read(new File("ChessDemo/images/pawn-black.png"));
        }

    }

    private void initiatePawnImage(ChessColor color) {
        try {
            this.loadResource();
            if (color == ChessColor.WHITE) {
                this.kingImage = KING_WHITE;
            } else if (color == ChessColor.BLACK) {
                this.kingImage = KING_BLACK;
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        this.initiatePawnImage(color);
    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if(getChessColor() == ChessColor.WHITE){
            if(source.getX()==1&&
                     source.getY()==destination.getY()
                    && destination.getX()>source.getX()
                    && destination.getX()-source.getX()<=2
                    && chessComponents[destination.getX()][destination.getY()].getChessColor() == ChessColor.NONE
                    && chessComponents[source.getX()+1][source.getY()].getChessColor() == ChessColor.NONE){
                return true;
            }else if (source.getY()==destination.getY()
                    && destination.getX()>source.getX()
                    && destination.getX()-source.getX()<=1
                    && chessComponents[destination.getX()][destination.getY()].getChessColor() == ChessColor.NONE){
                return true;
            }else return chessComponents[destination.getX()][destination.getY()].getChessColor() == ChessColor.BLACK
                    && destination.getX() > source.getX()
                    && 2 == (source.getX() - destination.getX()) * (source.getX() - destination.getX())
                    + (source.getY() - destination.getY()) * (source.getY() - destination.getY());
        }else if(getChessColor() == ChessColor.BLACK){ /////////////////////////////////////
            if(source.getX()==6&&
                    source.getY()==destination.getY()
                    && destination.getX()<source.getX()
                    && destination.getX()-source.getX()>=-2
                    && chessComponents[destination.getX()][destination.getY()].getChessColor() == ChessColor.NONE
                    && chessComponents[source.getX()-1][source.getY()].getChessColor() == ChessColor.NONE){
                return true;
            }else if (source.getY()==destination.getY()
                    && destination.getX()<source.getX()
                    && destination.getX()-source.getX()>=-1
                    && chessComponents[destination.getX()][destination.getY()].getChessColor() == ChessColor.NONE){
                return true;
            }else return chessComponents[destination.getX()][destination.getY()].getChessColor() == ChessColor.WHITE
                    && destination.getX() < source.getX()
                    && 2 == (source.getX() - destination.getX()) * (source.getX() - destination.getX())
                    + (source.getY() - destination.getY()) * (source.getY() - destination.getY());
        }
        return false;
    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.kingImage, 0, 0, this.getWidth(), this.getHeight(), this);
        g.setColor(Color.BLACK);
        if (this.isSelected()) {
            g.setColor(Color.RED);
            g.drawOval(0, 0, this.getWidth(), this.getHeight());
        }

    }
}


