package util;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundEffectPlayer {

    public static void SlideSoundEffect() {
        try {
            // 获取音频文件的路径
            String musicPath = "C:\\Users\\15520\\Downloads\\2048SlideSoundEffect.wav";

            // 创建一个 File 对象，指向音频文件
            File audioFile = new File(musicPath);

            // 创建一个 AudioInputStream 对象
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            // 获取音频剪辑并打开它
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // 开始播放音频剪辑
            clip.start();

            // 等待音频播放完毕
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 调用 playM4a 方法播放音频
        SlideSoundEffect();
    }
}

