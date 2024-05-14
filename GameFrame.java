package view;

import controller.GameController;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private  JButton downBtn;
    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;

    private JLabel stepLabel;
    private JLabel scoreLabel;
    private GamePanel gamePanel;
    private JButton upBtn;
    private JButton leftBtn;
    private JButton rightBtn;
    private JButton propsBtn;


    public GameFrame(int width, int height,int c) {
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8),c);
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);



        this.controller = new GameController(gamePanel, gamePanel.getModel());
        this.restartBtn = createButton("Restart", new Point(500, 150), 110, 50);
        this.loadBtn = createButton("Load", new Point(500, 220), 110, 50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 50), 180, 50);
        gamePanel.setStepLabel(stepLabel);
        this.scoreLabel=createLabel("",new Font("serif", Font.ITALIC, 22), new Point(480, 100), 180, 50);
        gamePanel.setScoreLabel(scoreLabel);


        //加小道具
//        this.propsBtn = createButton("P", new Point(630, 10), 50, 50);
//        this.propsBtn.addActionListener(e -> {
//            //GamePanel.doremoveGrid();
//            gamePanel.requestFocusInWindow();//enable key listener
//        });
        //游戏界面里 重来 load 上下左右 的按钮
        this.restartBtn.addActionListener(e -> {
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.loadBtn.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();//enable key listener
        });
        //todo: add other button here
        this.upBtn = createButton("up", new Point(525, 310), 65, 30);
        this.upBtn.addActionListener(e -> {
            gamePanel.doMoveUp();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.leftBtn = createButton("left", new Point(480, 350), 65, 30);
        this.leftBtn.addActionListener(e -> {
            gamePanel.doMoveLeft();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.downBtn = createButton("down", new Point(525, 390), 67, 30);
        this.downBtn.addActionListener(e -> {
            gamePanel.doMoveDown();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.rightBtn = createButton("right", new Point(570, 350), 65, 30);
        this.rightBtn.addActionListener(e -> {
            gamePanel.doMoveRight();
            gamePanel.requestFocusInWindow();//enable key listener
        });



        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    private JButton createButton(String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        this.add(button);
        return button;
    }

    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        this.add(label);
        return label;
    }

}
