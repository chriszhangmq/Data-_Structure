package Prim;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 最小生成树
 * 本质：实际上就是利用图论，找到最短路径
 * Created by Chris on 2019/12/19.
 */
public class Prim {
    /**
     * 生成图
     * @param graph
     * @param vertexNum：顶点数
     * @param data：顶点数据
     * @param adjacentMatrix：邻接矩阵
     */
    public void createGraph(Graph graph, int vertexNum, char[] data, int[][] adjacentMatrix){
        for(int i = 0; i < vertexNum; i++){
            graph.setVertexes(i, data[i]);
            for(int j = 0; j < vertexNum; j++){
                graph.setAdjacentMatrix(i, j, adjacentMatrix[i][j]);
            }
        }

    }

    public void displayAdjacentMatrix(Graph graph){
        System.out.println("====== DISPLAY ADJACENTMATRIX ======");
        int j = 0;
        System.out.print("    " + '\t');
        for(int i = 0; i < graph.getVertexNum(); i++){
            System.out.print(graph.getVertexes(i) + "" + '\t');
        }
        System.out.println();
        for(int[] i : graph.getAdjacentMatrix()){
            System.out.print(graph.getVertexes(j++) + "" + '\t');
            System.out.println(Arrays.toString(i));
        }
    }

    /**
     * 获取最小生成树
     * @param graph：传入原有图，用于构建MST
     * @param startVertex：起始顶点
     * @return
     */
    public HashMap<Integer, HashSet<Character>> MST(Graph graph, int startVertex){
        System.out.println("====== MST ======");
        //标记被访问过的顶点
        int[] isVisited = new int[graph.getVertexNum()];
        isVisited[startVertex] = 1;   //初始时，该起点被访问过

        //记录最终结果
        HashMap<Integer, HashSet<Character>> res = new HashMap<Integer, HashSet<Character>>();

        //记录两个顶点的索引
        int preIndex = -1;   //已被访问的顶点索引
        int postIndex = -1;  //没被访问的顶点索引

        //初始化最短路径数值
        int minWeight = 100; //选择邻接矩阵中最大的数据---表示这两个顶点不相连

        //开始寻找最短路径
        for(int i = 1; i < graph.getVertexNum(); i++){          //寻找到 graph.getVertexNum() -1 条路径，才算完成最短路径规划
            for(int x = 0; x < graph.getVertexNum() ; x++){     //遍历已被访问的顶点
                for(int y = 0; y < graph.getVertexNum(); y++){  //遍历未被访问的顶点
                    //找到一个已被访问顶点的未被访问过的邻接节点，且满足权重最小(路径最短)
                    if(isVisited[x] == 1 && isVisited[y] == 0 && graph.getAdjacentMatrix(x, y) < minWeight){
                        preIndex = x;
                        postIndex = y;
                    }
                }
            }
            System.out.println(graph.getVertexes(preIndex)+ "--" + graph.getVertexes(postIndex) + "--"
                    + graph.getAdjacentMatrix(preIndex,postIndex));

            //目前：存储的结果少了一个，因为HashMap不能存在两个相同的键值
            //存储最短路径的了两个顶点、权重   : （因为HashSet不能添加重复元素，因此采用新建对象来存储这些相同的元素）
            HashSet<Character> twoVertex = new HashSet<Character>();
            twoVertex.add(graph.getVertexes(preIndex));
            twoVertex.add(graph.getVertexes(postIndex));
            res.put(graph.getAdjacentMatrix(preIndex,postIndex), twoVertex);

            //标记该节点已被访问
            isVisited[postIndex] = 1;
            minWeight = 100;
        }
        return res;
    }

    public static void main(String[] args){
        //顶点数
        int vertexNum = 7;
        //顶点
        char[] vertexes = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] adjacentMatrix = new int[][]{
                {100,5,7,100,100,100,2},    //100---表示这两个顶点不相连
                {5,100,100,9,100,100,3},
                {7,100,100,100,8,100,100},
                {100,9,100,100,100,4,100},
                {100,100,8,100,100,5,4},
                {100,100,100,4,5,100,6},
                {2,3,100,100,4,6,100}
        };
        Prim prim = new Prim();
        Graph graph = new Graph(vertexNum);

        //创建图
        prim.createGraph(graph,vertexNum,vertexes,adjacentMatrix);

        //打印生成的图
        prim.displayAdjacentMatrix(graph);

        //获取最短路径
        HashMap<Integer, HashSet<Character>> res = prim.MST(graph,5);
        for (HashMap.Entry<Integer, HashSet<Character>> entry : res.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
