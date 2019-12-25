package Floyd;

import java.util.Arrays;

/**
 * 实现Floyd的主要算法
 * Created by Chris on 2019/12/22.
 */
public class Graph {
    private char[] vertex;
    private int[][] dis;
    private int[][] pre;

    public Graph(int vertexNum, int[][] matrix, char[] vertex){
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[vertexNum][vertexNum];

        //初始化前驱矩阵，存放的是前驱顶点的下标
        for(int i = 0; i < vertexNum; i++){
            Arrays.fill(pre[i], i);
        }
    }

    public void display(){
        char[] vertex = {'A','B','C','D','E','F','G'};
        for(int k = 0; k < dis.length; k++){
            for(int i = 0; i < dis.length; i++){
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            for(int i = 0; i < dis.length; i++){
                System.out.print("(" + vertex[k] + "->" + vertex[i] + ", MIN Path = " + dis[k][i] + "), ");
            }
            System.out.println();
            System.out.println();
        }
    }

    /**
     * 使用Floyd算法求解最短路径
     * 复杂度：n立方阶
     */
    public void floyd(){
        int len = 0;

        /****** 开始计算每个顶点，到其他剩余顶点的最短路径 ******/
        //中间顶点
        for(int mid = 0; mid < dis.length ; mid++){
            //起始顶点
            for(int start = 0; start < dis.length; start++){
                //末尾顶点
                for(int end = 0; end < dis.length; end++){
                    //起始顶点到中介顶点的距离 + 中间顶点到末尾顶点的距离
                    len = dis[start][mid] + dis[mid][end];
                    if(len < dis[start][end]){
                        //更新最短距离
                        dis[start][end] = len;
                        //更新前驱节点
                        pre[start][end] = pre[mid][end];
                    }
                }
            }
        }
    }

}
