package Recursion;

import com.sun.rowset.internal.Row;

/**
 * 迷宫问题：使用递归求解 —— 回溯算法
 * 策略：下右上左
 * 数组地图中的数字的含义：1：墙壁
 *                         2：已经走过
 *                         3、此路不通
 * Created by Chris on 2019/11/14.
 */
public class Labyrinth {
    public int[][] map;
    private final int ROWNUM;
    private final int COLNUM;

    public Labyrinth(int ROWNUM, int COLNUM){
        this.ROWNUM = ROWNUM;
        this.COLNUM = COLNUM;
        map = new int[ROWNUM][COLNUM];
    }

    /**
     * 绘制迷宫
     */
    public void drawMap(){
        //首尾行置为1
        for(int i = 0; i < COLNUM; i++){
            map[0][i] = 1;
            map[ROWNUM - 1][i] = 1;
        }
        //首尾列置为1
        for(int i = 0; i< ROWNUM; i++){
            map[i][0] = 1;
            map[i][COLNUM - 1] = 1;
        }
        //中间填写值：当墙
        map[3][1] = 1;
        map[3][2] = 1;

        //显示迷宫
        displayMap();
    }

    /**
     * 显示迷宫
     */
    public void displayMap(){
        System.out.println("================ MAp =============");
        for(int i =0; i < ROWNUM; i++){
            for(int j = 0; j < COLNUM; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
    }

    /**
     * 寻找迷宫出口路线
     * 搜索策略:下右上左
     * @param map
     * @param rowNum
     * @param colNum
     * @return
     */
    public boolean getWay(int[][] map, int rowNum, int colNum){
        if(map[ROWNUM - 2][COLNUM - 2] == 2){   //终点位置：
            return true;
        }
        else {
            //这个点没走过
            if(map[rowNum][colNum] == 0){
                map[rowNum][colNum] = 2;
                //向下走
                if(getWay(map,rowNum + 1, colNum)){
                    return true;
                }
                //向右走
                else if(getWay(map,rowNum,colNum+1)){
                    return true;
                }
                //向上走
                else if(getWay(map,rowNum-1,colNum)){
                    return true;
                }
                //向左走
                else if(getWay(map,rowNum,colNum-1)){
                    return true;
                }
                else {
                    map[rowNum][colNum] = 3; //此路不通
                    return false;
                }
            }
            //这个点走过了：此路不通,map[rowNum][colNum] = 1、2、3
            else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Labyrinth labyrinth = new Labyrinth(8,7);
        labyrinth.drawMap();
        labyrinth.getWay(labyrinth.map,1,1);    //起点：（1，1）、终点：（6，5）
        labyrinth.displayMap();
    }
}
