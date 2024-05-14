package model;

import java.util.Arrays;
import java.util.Random;


public class GridNumber {
    private final int X_COUNT;
    private final int Y_COUNT;

    private int[][] numbers;

    static Random random = new Random();
    private boolean movedright;
    private boolean movedleft;
    private boolean movedup;
    private boolean moveddown;

    public boolean isMovedright() {
        movedright=false;
        for (int i =0; i < numbers.length; i++) {
            for(int j=0;j<numbers[i].length-1;j++){
                for (int k=j+1;k<numbers[i].length;k++){
                    if (numbers[i][k]==0){
                        movedright=true;
                        break;
                    }else if(numbers[i][j+1]==numbers[i][j]){
                        movedright=true;
                        break;
                    }
                }
            }
        }
        return movedright;
    }

    public boolean isMovedleft() {
        movedleft=false;
        for (int i =0; i < numbers.length; i++) {
            for(int j=numbers[i].length-1;j>0;j--){
                for (int k=j-1;k>=0;k--){
                    if (numbers[i][k]==0){
                        movedleft=true;
                        break;
                    }else if(numbers[i][j-1]==numbers[i][j]){
                        movedleft=true;
                        break;
                    }
                }
            }
        }
        return movedleft;
    }

    public boolean isMovedup() {
        movedup=false;
        for (int i =numbers.length-1; i>0; i--) {
            for(int j=0;j<numbers[i].length;j++){
                for (int k=i-1;k>=0;k--){
                    if (numbers[k][j]==0){
                        movedup=true;
                        break;
                    }else if(numbers[i-1][j]==numbers[i][j]){
                        movedup=true;
                        break;
                    }
                }
            }
        }
        return movedup;
    }

    public boolean isMoveddown() {
        moveddown=false;
        for (int i =0; i<numbers.length-1; i++) {
            for(int j=0;j<numbers[i].length;j++){
                for (int k=i+1;k<numbers.length;k++){
                    if (numbers[k][j]==0){
                        moveddown=true;
                        break;
                    }else if(numbers[i+1][j]==numbers[i][j]){
                        moveddown=true;
                        break;
                    }
                }
            }
        }
        return moveddown;
    }

    public GridNumber(int xCount, int yCount) {
        this.X_COUNT = xCount;
        this.Y_COUNT = yCount;
        this.numbers = new int[this.X_COUNT][this.Y_COUNT];
        this.initialNumbers();
    }

    public void initialNumbers() {
        createNumber(numbers);
        createNumber(numbers);
    }
    //todo: finish the method of four direction moving.
    public void moveRight() {
        for (int i =0; i < numbers.length; i++) {
            for(int j=numbers[i].length-2;j>=0;j--){
                if(numbers[i][j]!=0){
                    moveRight1(numbers,i,j);
                }
            }
        }
        createNumber(numbers);
    }

    private void moveRight1(int[][] numbers, int i, int j) {
        if(j==numbers[i].length-1){
            return;
        }
        if(numbers[i][j+1]==numbers[i][j]){
            numbers[i][j+1]=numbers[i][j+1]+numbers[i][j];
            numbers[i][j]=0;
            moveRight1(numbers,i,j+1);
        }else if(numbers[i][j+1]==0){
            numbers[i][j+1]=numbers[i][j];
            numbers[i][j]=0;
            moveRight1(numbers,i,j+1);
        }
    }
    public void moveLeft() {
        for (int i =0; i < numbers.length; i++) {
            for(int j=0;j<numbers[i].length;j++){
                if(numbers[i][j]!=0){
                    moveLeft1(numbers,i,j);
                }
            }
        }
        createNumber(numbers);
    }

    //用来实现移动的递归方法
    private void moveLeft1(int[][] numbers, int i, int j) {
        if(j==0){
            return;
        }
        if(numbers[i][j-1]==numbers[i][j]){
            numbers[i][j-1]=numbers[i][j-1]+numbers[i][j];
            numbers[i][j]=0;
            moveLeft1(numbers,i,j-1);
        }else if(numbers[i][j-1]==0){
            numbers[i][j-1]=numbers[i][j];
            numbers[i][j]=0;
            moveLeft1(numbers,i,j-1);
        }
    }
    public void moveUp() {
        for (int i =0; i < numbers.length; i++) {
            for(int j=0;j<numbers[i].length;j++){
                if(numbers[i][j]!=0){
                    moveUp1(numbers,i,j);
                }
            }
        }
        createNumber(numbers);
    }

    //用来实现移动的递归方法
    private void moveUp1(int[][] numbers, int i, int j) {
        if(i==0){
            return;
        }
        if(numbers[i-1][j]==numbers[i][j]){
            numbers[i-1][j]+=numbers[i][j];
            numbers[i][j]=0;
            moveUp1(numbers,i-1,j);
        }else if(numbers[i-1][j]==0){
            numbers[i-1][j]=numbers[i][j];
            numbers[i][j]=0;
            moveUp1(numbers,i-1,j);
        }
    }
    public void moveDown() {
        for (int i =numbers.length-1; i >=0; i--) {
            for(int j=0;j<numbers[i].length;j++){
                if(numbers[i][j]!=0){
                    moveDown1(numbers,i,j);
                }
            }
        }
        createNumber(numbers);
    }

    //用来实现移动的递归方法
    private void moveDown1(int[][] numbers, int i, int j) {
        if(i==numbers.length-1){
            return;
        }
        if(numbers[i+1][j]==numbers[i][j]){
            numbers[i+1][j]+=numbers[i][j];
            numbers[i][j]=0;
            moveDown1(numbers,i+1,j);
        }else if(numbers[i+1][j]==0){
            numbers[i+1][j]=numbers[i][j];
            numbers[i][j]=0;
            moveDown1(numbers,i+1,j);

        }
    }

    //随机产生一个新的
    public void createNumber(int numbers[][]){
        if(checkfull()==false){
            int i=random.nextInt(numbers.length);
            int j=random.nextInt(numbers[0].length);
            if(numbers[i][j]!=0){
                createNumber(numbers);
            }else{
                int randomNumber=(random.nextInt(2)==0)?2:4;
                this.numbers[i][j]=randomNumber;}
        }


    }

//    public void removeGrid(int i, int j){
//        numbers[i][j]=0;
//    }

    public boolean checkfull(){
        boolean a=true;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                if(numbers[i][j]==0){
                    a=false;
                    break;
                }
            }
        }
        for (int i = 0; i < numbers.length-1; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                if(numbers[i][j]==numbers[i+1][j]){
                    a=false;
                    break;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length-1; j++) {
                if(numbers[i][j]==numbers[i][j+1]){
                    a=false;
                    break;
                }
            }
        }
        return a;
    }







    public int getNumber(int i, int j) {
        return numbers[i][j];
    }

    public void printNumber() {
        for (int[] line : numbers) {
            System.out.println(Arrays.toString(line));
        }
    }
}
