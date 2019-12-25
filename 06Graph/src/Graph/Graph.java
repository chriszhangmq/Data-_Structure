package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图的实现：
 * 主要是：深度优先搜索、广度优先搜索的实现s
 * Created by Chris on 2019/12/15.
 */
public class Graph {
    //顶点集合
    private ArrayList<String> vertexList;
    //邻接矩阵，横纵坐标为对应的顶点： 1：两个顶点直连    0：两个顶点不直连
    private int[][] adjacentMatrix;
    //图的边数
    private int edgesNum;
    //存储对应的顶点是否被访问过
    private boolean[] isVisited;

    /***************************************  深度优先搜索 **************************************/

    /**
     * 深度优先搜索
     */
    public void dfs(){
        System.out.println("\n======= Depth First Search =======");
        //纵向遍历所有节点
        for(int i = 0; i < getVertexNum(); i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    /**
     * 单个顶点的纵向遍历
     * @param isVisited
     * @param index
     */
    public void dfs(boolean[] isVisited, int index){
        //输出当前节点
        System.out.print(getVertexByIndex(index) + "-->");

        //标记当前节点已被访问
        isVisited[index] = true;

        //纵向搜索：当前节点的第一个相邻节点的索引值
        int firstNeighbor = getFirstNeighbor(index);

        //判断该节点是否存在
        while (firstNeighbor != -1){
            //该第一个相邻节点没被访问过
            if(!isVisited[firstNeighbor]){
                //该节点继续进行纵向搜索： （体现深度优先搜索）
                dfs(isVisited, firstNeighbor);
            }
            //该节点被访问过：获取第一个相邻节点的下一个同层节点
            firstNeighbor = getNextNeighbor(index, firstNeighbor);
        }
    }
    /***************************************  广度优先搜索 **************************************/
    /**
     * 广度优先搜索
     */
    public void bfs(){
        System.out.println("\n======= breadth First Search =======");
        //横向遍历所有节点
        for(int i = 0; i < getVertexNum(); i++){
            if(!isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }

    /**
     * 单个节点的横向遍历 （输出同层节点）
     * @param isVisited
     * @param index
     */
    public void bfs(boolean[] isVisited, int index){
        //存放需要访问节点的顺序：即：访问这些节点下一层的节点
        LinkedList list = new LinkedList();
        //同层顶点的列表中的第一个节点
        int listFirstVertex;
        //纵向搜索：当前节点的第一个相邻节点的索引值
        int firstNeighbor;

        System.out.print(getVertexByIndex(index) + "-->");
        isVisited[index] = true;

        //将该节点添加进list，用于访问该节点的下一层节点
        list.addLast(index);

        //需要访问的节点队列是否为空？
        while(!list.isEmpty()){
            //获取要访问队列的第一个顶点
            listFirstVertex = (Integer)list.removeFirst();
            //获取该节点的第一个相邻节点 （纵向）
            firstNeighbor = getFirstNeighbor(listFirstVertex);

            //判断该节点是否存在？
            while (firstNeighbor != -1){
                //该节点没被访问过
                if(!isVisited[firstNeighbor]){
                    System.out.print(getVertexByIndex(firstNeighbor) + "-->");
                    isVisited[firstNeighbor] = true;
                    //将该节点的第一个相邻节点放入待访问节点的列表中 （纵向）
                    list.addLast(firstNeighbor);
                }
                //该节点被访问过：(体现广度优先搜索)
                firstNeighbor = getNextNeighbor(listFirstVertex,firstNeighbor);
            }
        }
    }

    /***************************************  通用方法 **************************************/
    /**
     * 获取顶点index  的第一个相邻节点的下标
     * 作用：用与于获取该顶点的第一个连接点——即：获取下一层的顶点  (纵向)
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index){
        for(int i = 0; i < vertexList.size(); i++){
            if(adjacentMatrix[index][i] > 0){
                return i;
            }
        }
        //不存在返回-1
        return -1;
    }

    /**
     * 获取当前顶点的下一个相邻顶点的坐标
     * 作用：用于遍历该节点同一层子树的所有顶点 （横向）
     * @param currentVertexIndex：当前顶点的索引
     * @param currentIndex：当前顶点所对应连接的顶点的索引
     * @return
     */
    public int getNextNeighbor(int currentVertexIndex, int currentIndex){
        for(int i = currentIndex + 1; i < vertexList.size(); i++){
            if(adjacentMatrix[currentVertexIndex][i] > 0){
                return i;
            }
        }
        //不存在返回-1
        return -1;
    }

    /**
     * 构造函数
     * @param vertexNum
     */
    public Graph(int vertexNum) {
        this.vertexList = new ArrayList<String>(vertexNum);
        this.adjacentMatrix = new int[vertexNum][vertexNum];
        this.isVisited = new boolean[vertexNum];
        this.edgesNum = 0;
    }

    /**
     * 显示邻接矩阵：也就是打印当前图的各个顶点的连接状态
     */
    public void printGraph(){
        for(int[] row : adjacentMatrix){
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * 返回顶点个数
     * @return
     */
    public int getVertexNum(){
        return vertexList.size();
    }

    /**
     * 通过索引获取顶点值
     * @param index
     * @return
     */
    public String getVertexByIndex(int index){
        return vertexList.get(index);
    }

    /**
     * 获取边的数量
     * @return
     */
    public int getEdgesNum(){
        return edgesNum;
    }

    /**
     * 获取邻接矩阵某个点的值
     * 即：权值
     * @param index1
     * @param index2
     * @return
     */
    public int getVertexWeight(int index1, int index2){
        return adjacentMatrix[index1][index2];
    }

    /**
     * 添加顶点
     * @param vertex
     */
    public void addVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边：
     * 即：确定顶点之间的关系，
     * 1：表示两个顶点连接
     * @param rowIndex ：横坐标
     * @param columnIndex：纵坐标
     */
    public void addEdge(int rowIndex, int columnIndex){
        adjacentMatrix[rowIndex][columnIndex] = 1;
        adjacentMatrix[columnIndex][rowIndex] = 1;
        edgesNum++;
    }

    /*****************************************  测试函数 ************************************************/

    public static void main(String[] args) {
        int vertexNum = 5;
        String[] vertexs = {"A","B","C","D","E"};
        Graph graph = new Graph(vertexNum);

        //添加顶点
        for(String vertex : vertexs){
            graph.addVertex(vertex);
        }

        //添加边：即：连接对应的顶点
        graph.addEdge(0,1);     //A - B
        graph.addEdge(0,2);     //A - C
//        graph.addEdge(1,2);     //B - C
        graph.addEdge(1,3);     //B - D
        graph.addEdge(1,4);     //B - E

        //打印邻接矩阵
        graph.printGraph();

        //深度优先搜索
//        graph.dfs();

        //广度优先搜索
        graph.bfs();

    }

}
