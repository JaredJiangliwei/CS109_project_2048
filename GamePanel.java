package project;


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
        if(checkfull()){
            System.out.println("game over");
        }
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                if(model.getNumber(i,j)==1024){
                    //创建恭喜到达1024成就框
                }
            }
        }
        repaint();
    }

    /**
     * Implement the abstract method declared in ListenerPanel.
     * Do move right.
     */
    @Override
    public void doMoveRight() {
        if(model.isMovedright()){
            System.out.println("Click VK_RIGHT");
            this.afterMove();
            this.model.moveRight();
            this.updateGridsNumber();
        }

    }
    public void doMoveLeft() {
        if(model.isMovedleft()){
            System.out.println("Click VK_LEFT");
            this.afterMove();
            this.model.moveLeft();
            this.updateGridsNumber();
        }

    }
    public void doMoveUp() {
        if(model.isMovedup()){
            System.out.println("Click VK_UP");
            this.afterMove();
            this.model.moveUp();
            this.updateGridsNumber();
        }

    }
    public void doMoveDown() {
        if(model.isMoveddown()){
            System.out.println("Click VK_DOWN");
            this.afterMove();
            this.model.moveDown();
            this.updateGridsNumber();
        }

    }

    public void afterMove() {
        this.steps++;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
        this.scoreLabel.setText(String.format("Score: %d", model.getScore()));
    }
//    public void doremoveGrid(int i,int j){
//        grids[i][j].setNumber(0);
//        this.model.removeGrid(i,j);
//    }

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
