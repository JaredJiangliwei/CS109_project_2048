package project;



import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;

    private JLabel stepLabel;
    private GamePanel gamePanel;

    public GameFrame(int width, int height) {
        this.setTitle("2024 CS109 Project Demo");//set title of the frame
        this.setLayout(null);//no use,do not
        this.setSize(width, height);//set size
        ColorMap.InitialColorMap();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when we click the "close" button,the program ends
        setLocationRelativeTo(null);//make the frame at the center of the screen
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8));
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, gamePanel.getModel());
        this.restartBtn = createButton("Restart", new Point(500, 150), 110, 50);
        this.loadBtn = createButton("Load", new Point(500, 220), 110, 50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 50), 180, 50);
        gamePanel.setStepLabel(stepLabel);

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
