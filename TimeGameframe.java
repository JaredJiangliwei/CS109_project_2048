

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

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

    //加小道具
        this.propsBtn = createButton("tool", new Point(600, 475), 60, 20);
        this.propsBtn.addActionListener(e -> {
            if(gamePanel.isDead()){
                JFrame diyFrame = new JFrame("想要敲除的块的位子");
                diyFrame.setSize(500, 200);
                diyFrame.setLayout(new GridLayout(3, 2));
                diyFrame.setLocationRelativeTo(null);
                diyFrame.setVisible(true);
                JTextField xField = new JTextField();
                JTextField yField = new JTextField();
                JButton loginButton = new JButton("确定");
                JButton loginBackButton = new JButton("返回");
                loginButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String x = xField.getText();
                        String y = yField.getText();
                        boolean isValid = false;
                        while (!isValid) {
                            try {
                                // 尝试将输入转换为整数
                                int xnumber = Integer.parseInt(x.trim());
                                int ynumber = Integer.parseInt(y.trim());
                                // 检查整数是否大于0
                                if (xnumber>=4||ynumber>=4) {
                                    JOptionPane.showMessageDialog(null, "输入数字过大，请重新输入。", "错误", JOptionPane.ERROR_MESSAGE);
                                    xField.setText("");
                                    yField.setText("");
                                    // 获取新的输入（在实际应用中，这一步可能通过再次显示对话框或等待用户输入来实现）
                                    // 由于这里是示例，我们假设用户下一次点击按钮时会输入新的值
                                    return; // 等待用户再次点击按钮
                                } else if (xnumber >= 0 && ynumber>=0) {
                                    isValid = true; // 输入是正整数
                                    diyFrame.setVisible(false);
                                    gamePanel.doremoveGrid(xnumber,ynumber);
                                }else {
                                    JOptionPane.showMessageDialog(null, "输入的不是自然数，请重新输入。", "错误", JOptionPane.ERROR_MESSAGE);
                                    // 清空文本字段以便用户重新输入
                                    xField.setText("");
                                    yField.setText("");
                                    // 获取新的输入（在实际应用中，这一步可能通过再次显示对话框或等待用户输入来实现）
                                    // 由于这里是示例，我们假设用户下一次点击按钮时会输入新的值
                                    return; // 等待用户再次点击按钮
                                }
                            } catch (NumberFormatException ex) {
                                // 输入的不是整数，提示用户重新输入
                                JOptionPane.showMessageDialog(null, "输入的不是正整数，请重新输入。", "错误", JOptionPane.ERROR_MESSAGE);
                                // 清空文本字段以便用户重新输入
                                xField.setText("");
                                yField.setText("");
                                // 获取新的输入（同上）
                                return; // 等待用户再次点击按钮
                            }
                        }
                    };
                });
                loginBackButton.addActionListener(a -> {
                    diyFrame.setVisible(false);
                });

                diyFrame.add(new JLabel("请输入x:"));
                diyFrame.add(new JLabel("请输入y:"));
                diyFrame.add(xField);
                diyFrame.add(yField);
                diyFrame.add(loginButton);
                diyFrame.add(loginBackButton);
//                gamePanel.doremoveGrid(1,2);
//                gamePanel.requestFocusInWindow();//enable key listener
            }else{
                JOptionPane.showMessageDialog(null, "游戏已结束，无法使用道具，请重新开始游戏", "错误", JOptionPane.ERROR_MESSAGE);
            }
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