package Dijkstra;

import java.util.Arrays;

/**
 * 已被访问顶点的集合
 * 功能：广度优先搜索的思想，遍历所有顶点，获取最短路径
 * Created by Chris on 2019/12/22.
 */
public class VisitedVertex {
    public int[] isVisitedVertex;   //记录被访问的顶点：1，被访问过    0，没被访问
    public int[] preVisitedVertex;  //记录当前顶点，所对应的前一个顶点
    public int[] distance;          //保存起始顶点到各个顶点的最短距离

    /**
     *
     * @param vertexNum  ：顶点个数
     * @param vertexIndex：顶起始点索引
     */
    public VisitedVertex(int vertexNum, int vertexIndex){
        this.isVisitedVertex = new int[vertexNum];
        this.preVisitedVertex = new int[vertexNum];
        this.distance = new int[vertexNum];
        Arrays.fill(distance,65535);
        this.preVisitedVertex[vertexIndex] = 1; //第一个顶点被访问过
        this.distance[vertexIndex] = 0;
    }

    /**
     * 判断该顶点是否被访问过
     * @param vertexIndex
     * @return
     */
    public boolean in(int vertexIndex){
        return isVisitedVertex[vertexIndex] == 1;
    }

    /**
     * 更新当前最短路径
     * @param vertexIndex
     * @param minDistance
     */
    public void updateDistance(int vertexIndex, int minDistance){
        distance[vertexIndex] = minDistance;
    }

    /**
     * 更新vertexIndex顶点的前驱顶点为 preVertexIndex
     * @param vertexIndex  :需要更新前驱顶点的顶点
     * @param preVertexIndex：对应的前驱顶点
     */
    public void updatePreVertex(int vertexIndex, int preVertexIndex){
        preVisitedVertex[vertexIndex] = preVertexIndex;
    }

    /**
     * 获取出发顶点 --> vertexIndex顶点的距离
     * @param vertexIndex
     * @return
     */
    public int getDistance(int vertexIndex){
        return distance[vertexIndex];
    }

    /**
     * 选择路径最短的顶点
     * @return
     */
    public int updateArr(){
        int min = 65535;
        int index = 0;
        for(int i = 0; i < isVisitedVertex.length; i++){
            if(isVisitedVertex[i] == 0 && distance[i] < min){
                min = distance[i];
                index = i;
            }
        }
        isVisitedVertex[index] = 1;
        return index;
    }

    public void display(){
        System.out.println("====== Result ======");
        for(int i : isVisitedVertex){
            System.out.print(i + " ");
        }
        System.out.println();

        for(int i : preVisitedVertex){
            System.out.print(i + " ");
        }
        System.out.println();

        for(int i : distance){
            System.out.print(i + " ");
        }
        System.out.println();

        char[] vertex = {'A', 'B','C','D','E','F','G'};
        int count = 0;
        for(int i : distance){
            if(i != 65535) {
                System.out.print(vertex[count] + "(" + i + ")" + " -- ");
            }
            else{
                System.out.print("N");
            }
            count++;
        }
        System.out.println();
    }
}
