package model;

import java.util.Arrays;
import java.util.Random;

public class GridNumber {
    private final int X_COUNT;
    private final int Y_COUNT;

    private int[][] numbers;

    static Random random = new Random();

    public GridNumber(int xCount, int yCount) {
        this.X_COUNT = xCount;
        this.Y_COUNT = yCount;
        this.numbers = new int[this.X_COUNT][this.Y_COUNT];
        this.initialNumbers();
    }

    public void initialNumbers() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                //todo: update generate numbers method
                numbers[i][j] = random.nextInt(2) == 0 ? 4 : 0;
            }
        }
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
    }
//Amazing Algorithm!Genious ideas!
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




    public int getNumber(int i, int j) {
        return numbers[i][j];
    }

    public void printNumber() {
        for (int[] line : numbers) {
            System.out.println(Arrays.toString(line));
        }
    }
}

