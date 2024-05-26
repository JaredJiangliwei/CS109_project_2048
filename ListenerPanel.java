package view;

import util.SoundEffectPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * This class is to enable key events.
 *
 */
public abstract class ListenerPanel extends JPanel {
    public ListenerPanel() {
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        this.setFocusable(true);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT :
                    doMoveRight();
                    new Thread(SoundEffectPlayer::SlideSoundEffect).start();
                    break;
                case KeyEvent.VK_LEFT :
                    doMoveLeft();
                    new Thread(SoundEffectPlayer::SlideSoundEffect).start();
                    break;
                case KeyEvent.VK_UP :
                    doMoveUp();
                    new Thread(SoundEffectPlayer::SlideSoundEffect).start();
                    break;
                case KeyEvent.VK_DOWN :
                    doMoveDown();
                    new Thread(SoundEffectPlayer::SlideSoundEffect).start();
                    break;
                //todo: complete other move event
            }
        }
    }
//    protected void processMouseEvent(MouseEvent e) {
//        super.processMouseEvent(e);
//        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
//            switch (e.getButton()) {
//                case MouseEvent.BUTTON3 -> doMoveRight();
//                case MouseEvent.BUTTON1-> doMoveLeft();
//
//                //todo: complete other move event
//            }
//        }
//    }


    public abstract void doMoveRight();
    public abstract void doMoveLeft();
    public abstract void doMoveUp();
    public abstract void doMoveDown();

}

