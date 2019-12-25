package ChessBoard;

import java.awt.Point;      //坐标点
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 马踏棋盘
 * 附加：使用贪心算法减少运算时间
 * Created by Chris on 2019/12/23.
 */
public class ChessBoard {
    private int rowNum;
    private int columnNum;
    private boolean isVisited[];
    private boolean isFinish;

    public ChessBoard(int rowNum, int columnNum) {
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.isVisited = new boolean[rowNum * columnNum];
    }

    /**
     * 获取当前节点的下一步可走所有路径
     * (最多只可能有8种下一步)
     * @param currentPoint
     * @return
     */
    public ArrayList<Point> nextPath(Point currentPoint){
        ArrayList<Point> pointArr = new ArrayList<Point>();
        Point nextPoint = new Point();
        //第1个位置
        if((nextPoint.x = currentPoint.x - 2) >= 0 && (nextPoint.y = currentPoint.y - 1) >= 0){
            pointArr.add(new Point(nextPoint));
        }
        //第2个位置
        if((nextPoint.x = currentPoint.x - 1) >= 0 && (nextPoint.y = currentPoint.y - 2) >= 0){
            pointArr.add(new Point(nextPoint));
        }
        //第3个位置
        if((nextPoint.x = currentPoint.x + 1) < columnNum && (nextPoint.y = currentPoint.y - 2) >= 0){
            pointArr.add(new Point(nextPoint));
        }
        //第4个位置
        if((nextPoint.x = currentPoint.x + 2) < columnNum && (nextPoint.y = currentPoint.y - 1) >= 0){
            pointArr.add(new Point(nextPoint));
        }
        //第5个位置
        if((nextPoint.x = currentPoint.x + 2) < columnNum && (nextPoint.y = currentPoint.y + 1) < rowNum){
            pointArr.add(new Point(nextPoint));
        }
        //第6个位置
        if((nextPoint.x = currentPoint.x + 1) < columnNum && (nextPoint.y = currentPoint.y + 2) < rowNum){
            pointArr.add(new Point(nextPoint));
        }
        //第7个位置
        if((nextPoint.x = currentPoint.x - 1) >= 0 && (nextPoint.y = currentPoint.y + 2) < rowNum){
            pointArr.add(new Point(nextPoint));
        }
        //第8个位置
        if((nextPoint.x = currentPoint.x - 2) >= 0 && (nextPoint.y = currentPoint.y + 1) < rowNum){
            pointArr.add(new Point(nextPoint));
        }

        return pointArr;
    }

    /**
     * 贪心算法应用到马踏棋盘
     * 原理：每次选择下一个步骤少的路径进行遍历，减少运算时间，优先获得路径少的解
     * @param pointArr
     */
    private void sort(ArrayList<Point> pointArr){
        //进行非递减排序：（存在重复元素）
        //eg：排序后的数组：1，2，2，2，3，4，5，5，6，6
        pointArr.sort(new Comparator<Point>(){
            @Override
            public int compare(Point o1, Point o2){
                int count1 = nextPath(o1).size();
                int count2 = nextPath(o2).size();
                if(count1 < count2){
                    return -1;
                }
                else if(count1 == count2){
                    return 0;
                }
                else{
                    return 1;
                }
            }
        });
    }

    /**
     * 马踏棋盘算法实现
     * @param chessBoard
     * @param rowIndex    : 对应point.y  ,即Y坐标
     * @param columnIndex : 对应point.x ，即X坐标
     * @param step
     */
    public void traversalChessBoard(int[][] chessBoard, int rowIndex, int columnIndex, int step){
        chessBoard[rowIndex][columnIndex] = step;
        isVisited[rowIndex * columnNum + columnIndex] = true;
        //获取下一步可以走的路线
        ArrayList<Point> nextPoint = nextPath(new Point(columnIndex, rowIndex));
        //使用贪心算法，减少运算时间
        sort(nextPoint);
        //使用回溯，获得所有的可能性
        while(!nextPoint.isEmpty()){
            //取出一个路径
            Point point = nextPoint.remove(0);
            //如果没被遍历过，则继续深入
            if(!isVisited[point.y * columnNum + point.x]){
                traversalChessBoard(chessBoard, point.y, point.x,step + 1);
            }
        }

        //深度优先搜索，遍历完一条路径后，发现这条路不能满足要求，恢复参数初始化状态
        if(step < rowNum * columnNum && !isFinish){
            chessBoard[rowIndex][columnIndex] = 0;
            isVisited[rowIndex * columnNum + columnIndex] = false;
        }
        //找到结果
        else{
            isFinish = true;
        }
    }

    public static void main(String[] args) {
        //棋盘大小 8*8
        int rowNum = 8;
        int columnNum = 8;
        int[][] chessBoardArr = new int[rowNum][columnNum];
        //起始位置
        int rowStartIndex = 4;
        int columnStartIndex = 4;

        System.out.println("====== TraversalBoard ======");
        ChessBoard chessBoard = new ChessBoard(rowNum, columnNum);

        long startTime = System.currentTimeMillis();
        chessBoard.traversalChessBoard(chessBoardArr, rowStartIndex, columnStartIndex, 1);
        long endTime = System.currentTimeMillis();

        for(int[] i : chessBoardArr){
            System.out.println(Arrays.toString(i));
        }

        System.out.println("Run time = " + (endTime - startTime) / 1000.0 + " (S)");
    }

}
