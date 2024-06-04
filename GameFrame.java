

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GameFrame extends JFrame {
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
    private GamePanel gamePanel;
    private JButton upBtn;
    private JButton leftBtn;
    private JButton rightBtn;
    private JButton propsBtn;

    private boolean isAIOn=false;

    private Timer AiTimer;


    public GameFrame(int width, int height,String username,  int c) {
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


        //加小道具
        this.propsBtn = createButton("P", new Point(620, 450), 50, 50);
        this.propsBtn.addActionListener(e -> {
            if(isAIOn==false){
                isAIOn=true;
            }
            else if(isAIOn==true){
                isAIOn=false;
            }
            int delay = 100; // 毫秒
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if(isAIOn==false){
                        ((javax.swing.Timer) evt.getSource()).stop(); // 停止Timer
                    }else {
                        AiJudge ai = new AiJudge(gamePanel.getModel().getX_COUNT(),gamePanel.getModel().getY_COUNT());
                        ai.setNumbers(gamePanel.getModel().getNumbers());
                        ai.moveRight();
                        ai.setNumbers(gamePanel.getModel().getNumbers());
                        ai.moveDown();
                        ai.setNumbers(gamePanel.getModel().getNumbers());
                        ai.moveLeft();
                        ai.setNumbers(gamePanel.getModel().getNumbers());
                        ai.moveLeft();
                        ai.setNumbers(gamePanel.getModel().getNumbers());
                        ai.setMax();
                        for (int[] line : ai.getNumbers()) {
                            System.out.println(Arrays.toString(line));
                        }
                        System.out.println(ai.getMax());
                        System.out.println(ai.getScorechangeright());
                        System.out.println(ai.getScorechangeleft());
                        System.out.println(ai.getScorechangeup());
                        if (ai.getMax() == 0) {
                            if (ai.isMoveddown()) {
                                gamePanel.doMoveDown();
                            } else if (ai.isMovedleft()) {
                                gamePanel.doMoveLeft();
                                System.out.println("0l");
                            } else if (ai.isMovedright()) {
                                gamePanel.doMoveRight();
                            } else {
                                gamePanel.doMoveUp();
                            }
                        } else if (ai.getMax() == ai.getScorechangedown() && ai.isMoveddown()) {
                            System.out.println(ai.getScorechangedown());
                            System.out.println(ai.getMax());
                            System.out.println("down");
                            gamePanel.doMoveDown();
                        } else if (ai.getMax() == ai.getScorechangeright() && ai.isMovedright()) {
                            System.out.println(ai.getMax());
                            System.out.println("Right");
                            gamePanel.doMoveRight();
                        } else if (ai.getMax() == ai.getScorechangeleft() && ai.isMovedleft()) {
                            gamePanel.doMoveLeft();
                        } else if (ai.getMax() == ai.getScorechangeup() && ai.isMovedup()) {
                            gamePanel.doMoveUp();
                        }

                        // 如果需要继续循环，则不需要做任何事情，因为Timer会继续触发事件
                        if (gamePanel.checkfull()) { // 假设我们想要循环10次后停止
                            System.out.println("stop");
                            ((javax.swing.Timer) evt.getSource()).stop(); // 停止Timer
                        }
                    }

                }
            };
            // 创建一个javax.swing.Timer实例，并启动它
            javax.swing.Timer timer = new javax.swing.Timer(delay, taskPerformer);
            AiTimer=timer;
            timer.start();


//            gamePanel.doremoveGrid();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        //游戏界面里 重来 load 上下左右 的按钮
        this.restartBtn.addActionListener(e -> {
            try{
                AiTimer.stop();
            }catch (NullPointerException event){

            }
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
}