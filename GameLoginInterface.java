//请给我用java编写一个类，在这个类的功能是新建一个窗口，窗口中有三个按钮，
// 分别是游客登录，账号登录，注册账号按钮，用户点击注册按钮时会显示另一个窗口，
// 这个窗口是用于用户注册账号的，有返回和创建两个按钮，当按下返回时会调到最初的窗口，
// 用户可以输入用户名和密码创建账号，当用户名或密码为空时不能创造账号，且用户名不能重复，
//用户输入的账号和密码会保存在一个名为users.txt中的文本文档中
// 在最初的窗口中点击账号登录时会跳转到登录窗口，用户输入账号与密码登录，若成功则显示登录成功。
// （这是一个2048游戏的登录界面）


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GameLoginInterface {
    //one interface is composed of 3 interfaces
    private JFrame mainFrame;//this is the main interface
    private JFrame registerFrame;//this is register interface
    private JFrame loginFrame;//this is login interface
    private JFrame modeFrame;

    private JTextField usernameField;//a component that allows users to input text
    private JPasswordField passwordField;//a component that allows users to input text while not displaying the text

    private Map<String, String> users = new HashMap<>();//键值对，第一个string是用户名，第二个string是密码
    private String userFile = "users.txt";
    private String username;
    private CountdownTimer timer;
    private boolean isDead;

    public GameLoginInterface() {//constructor
        loadUsers();
        createMainFrame();
        createRegisterFrame();
        createLoginFrame();
    }

    //loadUsers is used to get the users' information,get the hashmap named users
    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {//构建了一个bufferedreader类，这个类的实例br可以读取文档信息
            String line;
            while ((line = br.readLine()) != null) {//readline()方法，读取一整行的数据，若读到头了会返回null
                String[] parts = line.split(",");//Based on the format,read the users name and password
                if (parts.length >= 2) {
                    String username = parts[0];
                    String password = parts[1];
                    users.put(username, password);//向名为users的hashmap中添加键对
                }
            }
        } catch (IOException e) {
            e.printStackTrace();//暂且不太用理这一行，这是关于报错的，若找不到文件，会进入这里，输出报错信息
        }
    }

    private void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(userFile))) {//bw is a instance of the class named BufferedWriter,this is used to write things in a file
            for (Map.Entry<String, String> entry : users.entrySet()) {//遍历每一个键对
                bw.write(entry.getKey() + "," + entry.getValue());//write in file
                bw.newLine();//换行
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createMainFrame() {
        mainFrame = new JFrame("2048游戏登录界面");
        mainFrame.setSize(400, 450);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());//// 设置 BorderLayout(a kind of layout method)
        mainFrame.setLocationRelativeTo(null);//make the window at the center of the screen
        // 加载图片
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File("D:\\Program Files\\CS109_Final_Project_2048Game\\2048游戏界面.png   "));
        } catch (IOException e) {
            e.printStackTrace();
        }
         //创建北部面板用于放置图片
        JPanel northPanel = new JPanel();
        northPanel.setBackground(new Color(255, 255, 128));
        try{
            Image scaledImage = originalImage.getScaledInstance(330, 330, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage); // 替换为图片的实际路径
            JLabel imageLabel = new JLabel(imageIcon);
            northPanel.add(imageLabel);
        }catch (NullPointerException e){
            System.out.println("can not load the image");
        }


        JPanel whole = new JPanel();
        whole.setBackground(new Color(255, 255, 128));
        mainFrame.add(whole);

        // 创建南部面板用于放置按钮
        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(255, 255, 128));
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 设置按钮间的水平和垂直间距


        JButton visitorButton = new JButton("游客登录");
        JButton accountLoginButton = new JButton("账号登录");
        JButton registerButton = new JButton("注册账号");

        visitorButton.setPreferredSize(new Dimension(100, 60));
        accountLoginButton.setPreferredSize(new Dimension(100, 60));
        registerButton.setPreferredSize(new Dimension(100, 60));

        southPanel.add(visitorButton);
        southPanel.add(accountLoginButton);
        southPanel.add(registerButton);

        // 将面板添加到 BorderLayout 的对应区域
        mainFrame.add(northPanel, BorderLayout.NORTH);
        mainFrame.add(southPanel, BorderLayout.SOUTH);

        visitorButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame, "欢迎您，游客！");
            //GameFrame gameFrame = new GameFrame(670,530);
            //gameFrame.setVisible(true);
            modeChoose();
            mainFrame.setVisible(false);
        });

        accountLoginButton.addActionListener(e -> {
            mainFrame.setVisible(false);
            loginFrame.setVisible(true);
        });

        registerButton.addActionListener(e -> {
            mainFrame.setVisible(false);
            registerFrame.setVisible(true);
        });

    }

    private void createRegisterFrame() {
        registerFrame = new JFrame("注册账号");
        registerFrame.setSize(500, 200);
        registerFrame.setLayout(new GridLayout(3, 2));
        registerFrame.setLocationRelativeTo(null);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton createButton = new JButton("创建");
        JButton backButton = new JButton("返回");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(registerFrame, "用户名或密码不能为空！");
                } else if (users.containsKey(username)) {
                    JOptionPane.showMessageDialog(registerFrame, "用户名已存在！");
                } else {
                    users.put(username, password);
                    saveUsers(); // 保存用户信息到文件
                    JOptionPane.showMessageDialog(registerFrame, "注册成功！");
                }
            }
        });
        backButton.addActionListener(e -> {
            registerFrame.setVisible(false);
            mainFrame.setVisible(true);
        });

        registerFrame.add(new JLabel("用户名:"));
        registerFrame.add(usernameField);
        registerFrame.add(new JLabel("密码:"));
        registerFrame.add(passwordField);
        registerFrame.add(createButton);
        registerFrame.add(backButton);
    }

    private void createLoginFrame() {
        loginFrame = new JFrame("账号登录");
        loginFrame.setSize(500, 200);
        loginFrame.setLayout(new GridLayout(3, 2));
        loginFrame.setLocationRelativeTo(null);

        JTextField loginUsernameField = new JTextField();
        JPasswordField loginPasswordField = new JPasswordField();
        JButton loginButton = new JButton("登录");
        JButton loginBackButton = new JButton("返回");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameinputed = loginUsernameField.getText();
                String password = new String(loginPasswordField.getPassword());

                if (users.containsKey(usernameinputed) && users.get(usernameinputed).equals(password)) {
                    username = usernameinputed;
                    JOptionPane.showMessageDialog(loginFrame, "登录成功！");
                    modeChoose();
//                    GameFrame gameFrame = new GameFrame(670,530,4);
//                    gameFrame.setUsername(username);
//                    gameFrame.setVisible(true);
                    mainFrame.setVisible(false);
                    loginFrame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "用户名或密码错误！");
                }
            }
        });

        loginBackButton.addActionListener(e -> {
            loginFrame.setVisible(false);
            mainFrame.setVisible(true);
        });

        loginFrame.add(new JLabel("用户名:"));
        loginFrame.add(loginUsernameField);
        loginFrame.add(new JLabel("密码:"));
        loginFrame.add(loginPasswordField);
        loginFrame.add(loginButton);
        loginFrame.add(loginBackButton);
    }

    public void display() {
        mainFrame.setVisible(true);
        registerFrame.setVisible(false);
        loginFrame.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameLoginInterface interfaceExample = new GameLoginInterface();
                interfaceExample.display();
            }
        });
    }

    public void modeChoose() {

        modeFrame = new JFrame("模式选择");
        modeFrame.setSize(400, 200);
        modeFrame.setLocationRelativeTo(null);
        modeFrame.setBackground(new Color(255, 255, 128));
        modeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // 创建主面板并设置布局为BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 创建顶部标签并设置文本和居中
        JLabel titleLabel = new JLabel("模式选择");
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // 水平居中
        Font font = new Font("Serif", Font.PLAIN, 30); // Serif是字体名称，Font.PLAIN是样式（普通），16是字体大小
        titleLabel.setFont(font);
        // 如果需要垂直居中，可以将标签添加到一个新的JPanel中，并设置该面板的BorderLayout

        // 将顶部标签添加到主面板的NORTH位置
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // 创建竖直方向的面板来放置按钮
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30)); // 竖直布局
//        buttonPanel.setBackground(new Color(255, 255, 128));

        // 创建两个按钮并添加到竖直面板中
        JButton button1 = new JButton("计时模式");
        JButton button2 = new JButton("自定义模式");
        JButton button3 = new JButton("经典模式");
        button1.setSize(500,300);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        // 添加竖直面板到主面板的CENTER位置（或根据需要选择其他位置）
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        modeFrame.getContentPane().add(mainPanel);
        modeFrame.setVisible(true);


        button1.addActionListener(e -> {//timing mode

//            GameFrame gameFrame = new GameFrame(670, 530, username, 4);

//            gameFrame.setVisible(true);
            TimeGameframe timeGameframe=new TimeGameframe(670, 530, username, 4);
            timeGameframe.setVisible(true);
            mainFrame.setVisible(false);
            modeFrame.setVisible(false);

            timer = new CountdownTimer(100);

            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    timer.decrementSeconds();
                    timeGameframe.setTimeLabel(timer.getSeconds());
                    if (timer.isFinished()) {
                        JOptionPane.showMessageDialog(null, "Time's up!", "Countdown Finished", JOptionPane.INFORMATION_MESSAGE);
                        timeGameframe.setVisible(false);
                        mainFrame.setVisible(true);
                        ((Timer) evt.getSource()).stop();
                    }
                }
            };

            Timer timer1 = new Timer(1000, taskPerformer);
            timer1.start();

            timeGameframe.setTimer1(timer1);
            timeGameframe.getGamePanel().setTime2(timer1);

        });
        button2.addActionListener(e -> {
            JFrame diyFrame = new JFrame("自定义模式");
            diyFrame.setSize(500, 200);
            diyFrame.setLayout(new GridLayout(2, 2));
            diyFrame.setLocationRelativeTo(null);
            diyFrame.setVisible(true);

            JTextField diyField = new JTextField();
            JButton loginButton = new JButton("确定");
            JButton loginBackButton = new JButton("返回");
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String diy = diyField.getText();
                    boolean isValid = false;

                    while (!isValid) {
                        try {
                            // 尝试将输入转换为整数
                            int number = Integer.parseInt(diy.trim());

                            // 检查整数是否大于0
                            if (number>13) {
                                JOptionPane.showMessageDialog(null, "输入数字过大，请重新输入。", "错误", JOptionPane.ERROR_MESSAGE);
                            } else if (number > 1) {
                                isValid = true; // 输入是正整数
                                diyFrame.setVisible(false);
                                GameFrame gameFrame = new GameFrame(670, 530, username, number);
                                gameFrame.setVisible(true);
                                mainFrame.setVisible(false);
                                modeFrame.setVisible(false);
                            }else {
                                JOptionPane.showMessageDialog(null, "输入的不是正整数，请重新输入。", "错误", JOptionPane.ERROR_MESSAGE);
                                // 清空文本字段以便用户重新输入
                                diyField.setText("");
                                // 获取新的输入（在实际应用中，这一步可能通过再次显示对话框或等待用户输入来实现）
                                // 由于这里是示例，我们假设用户下一次点击按钮时会输入新的值
                                return; // 等待用户再次点击按钮
                            }
                        } catch (NumberFormatException ex) {
                            // 输入的不是整数，提示用户重新输入
                            JOptionPane.showMessageDialog(null, "输入的不是正整数，请重新输入。", "错误", JOptionPane.ERROR_MESSAGE);
                            // 清空文本字段以便用户重新输入
                            diyField.setText("");
                            // 获取新的输入（同上）
                            return; // 等待用户再次点击按钮
                        }
                    }
                };
            });
            loginBackButton.addActionListener(a -> {
                diyFrame.setVisible(false);
                modeFrame.setVisible(true);
            });

            diyFrame.add(new JLabel("请输入一个正整数:"));
            diyFrame.add(diyField);
            diyFrame.add(loginButton);
            diyFrame.add(loginBackButton);

        });
        button3.addActionListener(e -> {

            GameFrame gameFrame = new GameFrame(670, 530, username, 4);

            gameFrame.setVisible(true);

        });
    }
}