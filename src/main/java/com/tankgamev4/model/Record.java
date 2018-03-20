package com.tankgamev4.model;

import sun.reflect.annotation.ExceptionProxy;

import java.io.*;
import java.util.Vector;

public class Record{

    //Enemy Tanks
    private static int enNum=0;
    //My Life
    private static int myLife=1;

    //Player Score
    private static int playerScore=0;

//    private boolean isNewGame = false;

    private static FileWriter fw;
    private static BufferedWriter bw;
    private static FileReader fr;
    private static BufferedReader br;
    private Vector<EnemyTank> etv = new Vector<EnemyTank>();
    static Vector<Node> nodes = new Vector<Node>();

    public static int getEnNum() {
        return enNum;
    }

    public static void setEnNum(int enNum) {
        Record.enNum = enNum;
    }

    public static int getMyLife() {
        return myLife;
    }

    public static void setMyLife(int myLife) {
        Record.myLife = myLife;
    }

    public static void reduceEnemyTank(){
        enNum--;
    }

    public static void reducePlayerTank(){
        myLife--;
    }

    public static void addPlayerSocre(){
        playerScore++;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void setPlayerScore(int playerScore) {
        Record.playerScore = playerScore;
    }

    public Vector<EnemyTank> getEtv() {
        return etv;
    }

    public void setEtv(Vector<EnemyTank> etv) {
        this.etv = etv;
    }



    //Record Games
    public void saveGames(){
        File file;
        try{
            file = new File("src/main/java/com/tankgamev4/save/save.txt");
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            bw.write(playerScore + "\r\n");
            for (int i = 0; i < etv.size(); i++) {
                EnemyTank et = etv.get(i);
                if(et.isLive){
                    String record = et.x+","+ et.y + "," + et.direct;
                    bw.write(record + "\r\n");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                bw.close();
                fw.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static Vector<Node> loadGames(){
        File file;

        try{
            file = new File("src/main/java/com/tankgamev4/save/save.txt");
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String st = br.readLine();
            Record.playerScore = Integer.parseInt(st);

            while((st=br.readLine())!=null){
                String res[] = st.split(",");
                Node node = new Node(Integer.parseInt(res[0]),Integer.parseInt(res[1]),Integer.parseInt(res[2]));
                nodes.add(node);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                br.close();
                fr.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return nodes;
    }

}
