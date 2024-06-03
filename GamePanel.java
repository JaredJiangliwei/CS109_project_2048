

import java.util.Random;
import javax.swing.*;
import java.awt.*;


public class GamePanel extends ListenerPanel {
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count ;
    private GridComponent[][] grids;

    private GridNumber model;

    public JLabel getStepLabel() {
        return stepLabel;
    }

    private JLabel stepLabel;

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    private JLabel scoreLabel;

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    private int steps;
    private final int GRID_SIZE;

    public void setTime2(Timer time1) {
        this.time1 = time1;
    }

    private Timer time1;

    private boolean has1024MadeBefore;

    private boolean has2048MadeBefore;

    public GamePanel(int size,int count) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.setSize(size, size);
        this.GRID_SIZE = size / count;
        this.grids = new GridComponent[count][count];
        this.model = new GridNumber(count, count);
        initialGame();

    }

    public GridNumber getModel() {
        return model;
    }

    public void initialGame() {
        this.steps = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j] = new GridComponent(i, j, model.getNumber(i, j), this.GRID_SIZE);
                grids[i][j].setLocation(j * GRID_SIZE, i * GRID_SIZE);
                this.add(grids[i][j]);
            }
        }
        model.printNumber();//check the 4*4 numbers in game
        this.repaint();
    }

    public void updateGridsNumber() {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j].setNumber(model.getNumber(i, j));
            }
        }
        repaint();
        if(checkfull()){
            try{time1.stop();}catch (NullPointerException e){

            }
            new Thread(SoundEffectPlayer::GameOverSoundEffect).start();
            JOptionPane.showMessageDialog(null, "Game Over！");
            System.out.println("game over");
        }

        if(!has1024MadeBefore){
            for (int i = 0; i < grids.length; i++) {
                for (int j = 0; j < grids[i].length; j++) {
                    if(model.getNumber(i,j)==1024&&has1024MadeBefore==false){
                        JOptionPane.showMessageDialog(null, "恭喜合成1024");
                        has1024MadeBefore = true;
                    }
                }
            }
        } else if (!has2048MadeBefore) {
            for (int i = 0; i < grids.length; i++) {
                for (int j = 0; j < grids[i].length; j++) {
                    if (model.getNumber(i,j)==2048&&has2048MadeBefore==false) {
                        new Thread(SoundEffectPlayer::VictorySoundEffect).start();
                        JOptionPane.showMessageDialog(null, "恭喜合成2048");
                        has2048MadeBefore = true;
                    }
                }
            }
        }

    }


    @Override
    public void doMoveRight() {
        if(model.isMovedright()){
            System.out.println("Click VK_RIGHT");
            this.model.moveRight();
            this.afterMove();
            this.updateGridsNumber();
        }

    }
    public void doMoveLeft() {
        if(model.isMovedleft()){
            System.out.println("Click VK_LEFT");
            this.model.moveLeft();
            this.afterMove();
            this.updateGridsNumber();
        }

    }
    public void doMoveUp() {
        if(model.isMovedup()){
            System.out.println("Click VK_UP");
            this.model.moveUp();
            this.afterMove();
            this.updateGridsNumber();
        }

    }
    public void doMoveDown() {
        if(model.isMoveddown()){
            System.out.println("Click VK_DOWN");
            this.model.moveDown();
            this.afterMove();
            this.updateGridsNumber();
        }
    }

    public void afterMove() {
        this.steps++;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
        this.scoreLabel.setText(String.format("Score: %d", model.getScore()));
    }
    public void doremoveGrid(){
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j <grids[0].length ; j++) {
                this.model.removeGrid(i,j);
            }
        }
        if(checkfull()==false){
            Random random=new Random();
            int i=random.nextInt(model.getNumbers().length);
            int j=random.nextInt(model.getNumbers()[0].length);
            if(model.getNumbers()[i][j]!=0){
                model.createNumber(model.getNumbers());
            }else{
                int randomNumber=(random.nextInt(2)==0)?2:4;
                this.model.getNumbers()[i][j]=randomNumber;}
        }
        this.updateGridsNumber();
    }

    public void setScoreLabel(JLabel scoreLabel){
        this.scoreLabel=scoreLabel;
    }
    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }

    public boolean checkfull(){
        boolean a=true;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                if(grids[i][j].getNumber()==0){
                    a=false;
                    break;
                }else if(grids[i][j].getNumber()==grids[i][j].getNumber());
            }
        }
        for (int i = 0; i < grids.length-1; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                if(grids[i][j].getNumber()==grids[i+1][j].getNumber()){
                    a=false;
                    break;
                }
            }
        }
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length-1; j++) {
                if(grids[i][j].getNumber()==grids[i][j+1].getNumber()){
                    a=false;
                    break;
                }
            }
        }
        return a;
    }


}
