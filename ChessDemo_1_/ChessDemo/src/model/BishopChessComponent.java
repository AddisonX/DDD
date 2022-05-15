
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

    public class BishopChessComponent extends ChessComponent {
        private static Image KING_WHITE;
        private static Image KING_BLACK;
        private Image kingImage;

        public void loadResource() throws IOException {
            if (KING_WHITE == null) {
                KING_WHITE = ImageIO.read(new File("ChessDemo/images/bishop-white.png"));
            }

            if (KING_BLACK == null) {
                KING_BLACK = ImageIO.read(new File("ChessDemo/images/bishop-black.png"));
            }

        }

        private void initiateKingImage(ChessColor color) {
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

        public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
            super(chessboardPoint, location, color, listener, size);
            this.initiateKingImage(color);
        }

        public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
            ChessboardPoint source = getChessboardPoint();

                int sum = source.getX() + source.getY();
                int dif = source.getX() - source.getY();
                if (dif == destination.getX()-destination.getY()) {
                    for (int x = Math.min(source.getX(), destination.getX()) + 1;
                         x < Math.max(source.getX(), destination.getX()); x++) {
                        if (!(chessComponents[x][x-dif] instanceof EmptySlotComponent)) {
                            return false;
                        }
                    }
                } else if (sum == destination.getX() + destination.getY()) {
                    for (int x = Math.min(source.getX(), destination.getX()) + 1;
                         x < Math.max(source.getX(), destination.getX()); x++) {
                        if (!(chessComponents[x][sum-x] instanceof EmptySlotComponent)) {
                            return false;
                        }
                    }
                } else { // Not on the diagonal.
                    return false;
                }
                return true;

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


