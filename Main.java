public class Main {
    public static void main(String[] args) {
        // 创建GameFrame的实例，并设置窗口大小
        GameLoginInterface gameLoginInterface = new GameLoginInterface();
        // 创建一个新的线程播放音乐，背景音乐独立于游戏逻辑的执行，会一直播放，直到程序终止
        new Thread(BGM::playBackgroundMusic).start();

        // 设置窗口为可见状态
        gameLoginInterface.display();
    }
}
