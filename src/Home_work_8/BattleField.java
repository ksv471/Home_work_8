package Home_work_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleField extends JPanel {
    private GameWindow gameWindow;

    private int size;
    private int winline;

    private boolean isInit;

    private int cellWidth;
    private int cellHeight;

    public BattleField(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(Color.ORANGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int cellX = e.getX() / cellWidth;
                int cellY = e.getY() / cellHeight;


                if(!Logic.gameFinished) {
                    Logic.humanTurn(cellX,cellY);
                }
            }
        });
    }

    void startNewGame(int size, int winline) {
        this.size = size;
        this.winline = winline;

        isInit = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isInit){
            return;
        }

        cellWidth = getWidth()/size;
        cellHeight = getHeight()/size;

        g.setColor(Color.GRAY);
        ((Graphics2D)g).setStroke(new BasicStroke(2f));

        for (int i = 0; i < size; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, getWidth(), y);
        }

        for (int i = 0; i < size; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, getHeight());
        }

//        g.setColor(Color.RED);
//        ((Graphics2D)g).setStroke(new BasicStroke(5f));
//        g.drawLine(100, 100, 300, 400);
    }
}
