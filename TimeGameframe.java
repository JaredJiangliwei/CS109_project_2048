package view;

import controller.GameController;
import util.ColorMap;
import util.SoundEffectPlayer;

import javax.swing.*;
import java.awt.*;

public class TimeGameframe extends JFrame {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    private  JButton downBtn;
    private GameController controller;
    private JButton restartBtn;
    private JButton saveBtn;
    private JButton loadBtn;

    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private GamePanel gamePanel;
    private JButton upBtn;
    private JButton leftBtn;
    private JButton rightBtn;
    private JButton propsBtn;
    private CountdownTimer timer;

    public Timer getTimer1() {
        return timer1;
    }

    public void setTimer1(Timer timer1) {
        this.timer1 = timer1;
    }

    private Timer timer1;//Jared: I try to give the evt into this class in the hope of stop the timer;


    public TimeGameframe(int width, int height,String username,  int c) {
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();//调用ColorMap类中的静态方法
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8),c);
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);//add gamepanel into the gameframe


        this.username=username;
        this.controller = new GameController(gamePanel, gamePanel.getModel(),username);
        this.restartBtn = createButton("Restart", new Point(500, 150), 110, 50);
        this.saveBtn= createButton("Save",new Point(500,220),110,50);
        this.loadBtn = createButton("Load", new Point(500, 290), 110, 50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 50), 180, 50);
        gamePanel.setStepLabel(stepLabel);
        this.scoreLabel=createLabel("",new Font("serif", Font.ITALIC, 22), new Point(480, 100), 180, 50);
        gamePanel.setScoreLabel(scoreLabel);
        this.timeLabel=createLabel("",new Font("serif", Font.ITALIC, 22), new Point(480, 25), 180, 50);
//        CountdownTimer time=new CountdownTimer(200);
//        this.timeLabel.setText(String.format("Time: %d",seconds));
//        timer = new CountdownTimer(3);
//
//        ActionListener taskPerformer = new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                timer.decrementSeconds();
//                timeLabel.setText("Remaining: " + timer.getSeconds() + "s");
//                if (timer.isFinished()) {
//                    JOptionPane.showMessageDialog(null, "Time's up!", "Countdown Finished", JOptionPane.INFORMATION_MESSAGE);
//                    controller.restartGame();
//                    gamePanel.setVisible(false);
//                    ((Timer) evt.getSource()).stop();
//                }
//            }
//        };
//
//        new Timer(1000, taskPerformer).start();
//




//        javax.swing.Timer swingTimer = new javax.swing.Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                timer.decrementSeconds(); // 减少秒数
//                timeLabel.setText("Remaining: " + timer.getSeconds() + "s"); // 更新标签文本
//                if (timer.isFinished()) {
//                    // 如果倒计时结束
//                    JOptionPane.showMessageDialog(null, "Time's up!" +
//                            "Your score is");
//                    controller.restartGame();
//                    gamePanel.setVisible(false);
//                    swingTimer.stop(); // 停止Timer
//                    timeLabel.setText("Time's up!"); // 显示结束信息
//                }

//
//            }
//        });

        // 启动Timer
//        swingTimer.start();



    //加小道具
        this.propsBtn = createButton("P", new Point(620, 450), 50, 50);
        this.propsBtn.addActionListener(e -> {
            gamePanel.doremoveGrid();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        //游戏界面里 重来 load 上下左右 的按钮
        this.restartBtn.addActionListener(e -> {
            timer1.stop();
            controller.restartGame();
            this.setVisible(false);
            gamePanel.requestFocusInWindow();//enable key listener

        });
        this.saveBtn.addActionListener(e->{
            System.out.println("savebutton clicked");
            controller.saveGame();
            gamePanel.requestFocusInWindow();
        });
        this.loadBtn.addActionListener(e -> {
            System.out.println("loadbutton clicked");
            controller.loadGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        //todo: add other button here
        this.upBtn = createButton("up", new Point(525, 360), 65, 30);
        this.upBtn.addActionListener(e -> {
            new Thread(SoundEffectPlayer::SlideSoundEffect).start();
            new Thread(SoundEffectPlayer::SlideSoundEffect).start();
            gamePanel.doMoveUp();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.leftBtn = createButton("left", new Point(480, 400), 65, 30);
        this.leftBtn.addActionListener(e -> {
            new Thread(SoundEffectPlayer::SlideSoundEffect).start();
            gamePanel.doMoveLeft();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.downBtn = createButton("down", new Point(525, 440), 67, 30);
        this.downBtn.addActionListener(e -> {
            new Thread(SoundEffectPlayer::SlideSoundEffect).start();
            gamePanel.doMoveDown();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.rightBtn = createButton("right", new Point(570, 400), 65, 30);
        this.rightBtn.addActionListener(e -> {
            new Thread(SoundEffectPlayer::SlideSoundEffect).start();
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

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(int t) {
        this.timeLabel.setText("Remaining: " + t + "s");
    }
}
