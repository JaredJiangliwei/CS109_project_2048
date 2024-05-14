package project;

import javax.swing.*;
import java.io.*;

/**
 * This class is used for interactive with JButton in GameFrame.
 */
public class GameController {
    private GamePanel view;
    private GridNumber model;
    private String usersname;


    public GameController(GamePanel view, GridNumber model,String usersname) {
        this.view = view;
        this.model = model;
        this.usersname=usersname;
    }
    public void restartGame() {
        System.out.println("Do restart game here");
    }

    public void saveGame(){
        //if username is null,i.e. user do not log in,the function is not available
        if(usersname==null){
            System.out.println("you have not logged in");
            JOptionPane.showMessageDialog(null,"您未登录");
            return;
        }
        //to create a file named "users's name.txt" and store the data
        String directoryPath = "D:\\Program Files\\2048";//the place we store the data
        File directory = new File(directoryPath);//create a folder
        String fileextension = ".txt";//后缀名
        File file = new File(directory,usersname+fileextension);//create the file for specific user
        //if the folder does not exist,create the folder
        if(!directory.exists()){
            directory.mkdirs();//create folder
            System.out.println("folder created");
        }
        //if the file is not created,create it
        try{
            if(!file.exists()){
                file.createNewFile();
                System.out.println("the file successfully created,the file name is "+file.getName());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        //store the data into the file created
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()))){
            int[][] record = model.getNumbers();
            int size = model.getX_COUNT();
            int step= view.getSteps();
            int score= model.getScore();
            //the syntax I choose:
            // first line records the size of the grid
            //second line is step;
            //third line is score
            //next few lines records the two-dimension-array
            writer.write(Integer.toString(size));
            writer.newLine();
            writer.write(Integer.toString(step));
            writer.newLine();
            writer.write(Integer.toString(score));
            writer.newLine();
            for(int[] row : record){
                for(int value : row){
                    writer.write(value+" ");
                }
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null,"saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(){
        if(usersname==null){
            System.out.println("you have not logged in");
            JOptionPane.showMessageDialog(null,"您未登录");
            return;
        }
        int size = model.getX_COUNT();
        try(BufferedReader reader = new BufferedReader(new FileReader("D:\\Program Files\\2048\\"+usersname+".txt"))){
            //the content to be read
            String line;
            line = reader.readLine();
            if(line==null){
                System.out.println("用户尚未保存过数据");
                JOptionPane.showMessageDialog(null,"You have not saved before");
                return;
            }
            int sizesaved = Integer.parseInt(line);
            if(sizesaved!=size){
                System.out.println("用户尚未保存过此类长宽数组的数据");
                JOptionPane.showMessageDialog(null,"You have not saved this mode before");
                return;
            }
            line= reader.readLine();
            int stepsaved = Integer.parseInt(line);
            line = reader.readLine();
            int score = Integer.parseInt(line);
            int [][] records = new int[sizesaved][sizesaved];
            int row=0;
            while((line = reader.readLine())!=null){
                String[] values = line.split(" ");
                for(int col =0;col< values.length;col++){
                    records[row][col]=Integer.parseInt(values[col]);
                }
                row++;
            }
            model.setScore(score);
            view.getScoreLabel().setText(String.format("Score: %d", model.getScore()));
            view.setSteps(stepsaved);
            view.getStepLabel().setText(String.format("Step: %d", stepsaved));
            model.setNumbers(records);
            view.updateGridsNumber();
        } catch (FileNotFoundException e){
            System.out.println("用户尚未保存过数据");
            JOptionPane.showMessageDialog(null,"You have not saved before");
            return;
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    //todo: add other methods such as loadGame, saveGame...
    public static void main(String[] args) {
        GameController test = new GameController(null,null,"1231");
        test.saveGame();
    }

}
