package Dijkstra;

import java.util.Arrays;

/**
 * 图:
 * Dijkstra主要的生成算法
 * Created by Chris on 2019/12/22.
 */
public class Graph {
    private char[] vertex;                  //顶点数组
    private int[][] adjacentMatrix;         //邻接矩阵
    private VisitedVertex isVisitedVertex;  //已经访问的顶点的集合

    public Graph(char[] vertex, int[][] adjacentMatrix){
        this.vertex = vertex;
        this.adjacentMatrix = adjacentMatrix;
    }

    public void displayDijkstra(){
        isVisitedVertex.display();
    }

    public void displayGraph(){
        System.out.println("====== adjacent matrix ======");
        for(int[] link : adjacentMatrix){
            System.out.println(Arrays.toString(link));
        }
    }

    public void update(int index){
        int dist = 0;
        for(int i = 0; i < adjacentMatrix[index].length; i++){
            dist = isVisitedVertex.getDistance(index) + adjacentMatrix[index][i];
            //没被访问过的顶点，并且该路径最短，更新当前的最短路径
            if(!isVisitedVertex.in(i) && dist < isVisitedVertex.getDistance(i)){
                isVisitedVertex.updatePreVertex(i, index);
                isVisitedVertex.updateDistance(i,dist);
            }
        }
    }

    /**
     * 核心，获得最短路径
     * @param vertexIndex
     */
    public void shortPath(int vertexIndex){
        isVisitedVertex = new VisitedVertex(vertex.length, vertexIndex);
        update(vertexIndex);
        for(int j = 1; j < vertex.length; j++){
            vertexIndex = isVisitedVertex.updateArr();
            update(vertexIndex);
        }
    }
}
