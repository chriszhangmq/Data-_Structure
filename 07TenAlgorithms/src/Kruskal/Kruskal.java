package Kruskal;

import java.util.Arrays;

/**
 *
 * Kruskal算法：
 * 求解最短路径（最小生成树），和Prim算法不一样
 * 步骤：
 *      1、所有边，按照权重，从小到大排序
 *      2、选择一条没有和目前最小生成树构成回路的边，添加到最小生成树
 *      3、重复步骤2，直到添加完 所有顶点
 * Created by Chris on 2019/12/21.
 */
public class Kruskal {
    public int edgeNum;                                 //边的数量
    public int vertexNum;                               //顶点个数
    public char[] vertexes;                             //顶点数据
    public int[][] adjacentMatrix;                      //邻接矩阵
    public static final int INF = Integer.MAX_VALUE;    //权重无限大，表示两个顶点不相连

    public Kruskal(char[] vertexes, int[][] adjacentMatrix) {
        this.vertexNum = vertexes.length;
        //初始化顶点数据
        this.vertexes = new char[vertexNum];
        for(int i = 0; i < vertexNum; i++){
            this.vertexes[i] = vertexes[i];
        }
        //初始化邻接矩阵数据
        this.adjacentMatrix = new int[vertexNum][vertexNum];
        for(int row = 0; row < vertexNum; row++){
            for(int column = 0; column < vertexNum; column++){
                this.adjacentMatrix[row][column] = adjacentMatrix[row][column];
            }
        }
        //初始化边的条数
        for(int row = 0; row < vertexNum; row++) {
            for (int column = row + 1; column < vertexNum; column++) {
                if(this.adjacentMatrix[row][column] != INF){
                    edgeNum++;
                }
            }
        }
    }

    /**
     * 打印邻接矩阵
     */
    public void displayAdjacentMatrix(){
        System.out.println("====== Adjacent Matrix ======");
        for(int row = 0; row < vertexNum; row++){
            for(int column = 0; column < vertexNum; column++){
                System.out.printf("%12d",adjacentMatrix[row][column]);
            }
            System.out.println();
        }
    }

    /**
     * 将边按照从大到小的顺序排列
     * @param edges
     */
    private void sortEdges(EdgeData[] edges){
        EdgeData temp = null;
        for(int i = 0; i < vertexNum; i++){
            for(int j = 0; j < vertexNum; j++){
                if(edges[j].weight > edges[j+1].weight){
                    temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     * 获取顶点的索引值
     * @param c
     * @return
     */
    private int getPosotion(char c){
        for(int i = 0; i < vertexNum; i++){
            if(vertexes[i] == c){
                return i;
            }
        }
        //找不到
        return -1;
    }

    /**
     * 得到边的集合：
     * 使用邻接矩阵的数据，创建边的集合
     * @return
     */
    private EdgeData[] getEdges(){
        int index = 0;
        EdgeData[] edgeData = new EdgeData[edgeNum];

        for(int row = 0; row < vertexNum; row++){
            for(int column = row + 1; column < vertexNum; column++){
                if(adjacentMatrix[row][column] != INF){
                    edgeData[index++] = new EdgeData(vertexes[row], vertexes[column], adjacentMatrix[row][column]);
                }
            }
        }
        return edgeData;
    }

    /**
     * 返回给定顶点的末尾节点，
     * 作用：判断两个顶点的终点是否相同，决定是否将该边加入到最小生成树中
     * @param vertexIndex ：顶点的索引
     * @param ends：不为0的数，表示已经添加节点的末尾节点的索引， 元素的序号表示：该顶点的索引--- vertexIndex
     * @return
     */
    private int getEndIndex(int vertexIndex, int[] ends){
        //找到给定顶点的索引对应的末尾节点
        while(ends[vertexIndex] != 0){
            vertexIndex = ends[vertexIndex];
        }
        //没找到，就返回节点自身的索引
        return vertexIndex;
    }

    /**
     * 获得最小生成树
     */
    public void createMST(){
        System.out.println("====== MST ======");
        int index = 0;                  //最后结果数组的索引
        int[] ends = new int[edgeNum];  //保存现有的最小成生树的每个顶点的终点
        EdgeData[] MST = new EdgeData[edgeNum];// 保存最小生成树
        EdgeData[] edges = getEdges();  //获取所有边

//        System.out.println(Arrays.toString(edges));
        //1、现将所有边进行排序
        sortEdges(edges);

        //2、获取最小生成树，需判断该边是否和已有的顶点构成回路
        for(int i = 0; i < edgeNum; i++){
            //获取边的起点、终点（一条边，有两个顶点）
            int edgeVertex1 = getPosotion(edges[i].start);
            int edgeVertex2 = getPosotion(edges[i].end);

            //获取该顶点在最小生成树中的终点位置
            int edgeVertex1_end = getEndIndex(edgeVertex1, ends);
            int edgeVertex2_end = getEndIndex(edgeVertex2, ends);

            //判断，这条边是否和现MST，构成回路
            if(edgeVertex1_end != edgeVertex2_end){
                //标记现有最小生成树所有顶点对应的终点
                ends[edgeVertex1_end] = edgeVertex2_end;
                //将该边添加进MST中
                MST[index++] = edges[i];
            }
        }
        for(int i = 0; i < index; i++){
            System.out.println(MST[i]);
        }
    }

    /****************************************  测试函数  *****************************************/
    public static void main(String[] args) {
        //顶点
        char[] vertexes = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] adjacentMatrix = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
        };
        Kruskal kruskal = new Kruskal(vertexes, adjacentMatrix);

        kruskal.displayAdjacentMatrix();
        kruskal.createMST();
    }
}
