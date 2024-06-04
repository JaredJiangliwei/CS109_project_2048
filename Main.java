public class Main {
    public static void main(String[] args) {
        // 创建GameFrame的实例
        GameLoginInterface gameLoginInterface = new GameLoginInterface();
        new Thread(SoundEffectPlayer::playBackgroundMusic).start();

        // 设置窗口为可见状态
        gameLoginInterface.display();
    }
}