package Home_work_8;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    static final int WINDOW_POS_X = GameWindow.WINDOW_POS_X+50;
    static final int WINDOW_POS_Y = GameWindow.WINDOW_POS_Y+50;
    static final int WINDOW_WIDTH = 400;
    static final int WINDOW_HEIGHT = 300;

    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;

    private GameWindow gameWindow;

    private JSlider slFieldSize;
    private JSlider slWinLine;
    private JButton btnNewGame;



    public SettingWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Крестики нолики настройки");

        setLayout(new GridLayout(5,1));
        add(new Label("Field size:"));
        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slFieldSize.setMajorTickSpacing(1);
        slFieldSize.setPaintLabels(true);
        slFieldSize.setPaintTicks(true);
        add(slFieldSize);

        add(new Label("Win line:"));
        slWinLine = new JSlider(MIN_FIELD_SIZE, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
        slWinLine.setMajorTickSpacing(1);
        slWinLine.setPaintLabels(true);
        slWinLine.setPaintTicks(true);
        add(slWinLine);

        slFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                slWinLine.setMaximum(slFieldSize.getValue());
            }
        });

        btnNewGame = new JButton("Start new Game");
        add(btnNewGame);

        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = slFieldSize.getValue();
                int winline = slWinLine.getValue();

                Logic.SIZE = size;
                Logic.DOT_WIN = winline;
                Logic.initMap();
                Logic.gameFinished = false;

                gameWindow.startNewGame(size, winline);
                setVisible(false);
            }
        });

        setVisible(false);
    }
}
