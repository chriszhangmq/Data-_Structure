package Prim;

/**
 * 图
 * Created by Chris on 2019/12/19.
 */
public class Graph {
    //顶点数据
    private char[] vertexes;
    //顶点数量
    private int vertexNum;
    //邻接矩阵
    private int[][] adjacentMatrix;

    public Graph(int vertexNum){
        this.vertexes = new char[vertexNum];
        this.vertexNum = vertexNum;
        this.adjacentMatrix = new int[vertexNum][vertexNum];
    }

    public char getVertexes(int index) {
        return vertexes[index];
    }

    public void setVertexes(int index, char vertexes) {
        this.vertexes[index] = vertexes;
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public void setVertexNum(int vertexNum) {
        this.vertexNum = vertexNum;
    }

    public int[][] getAdjacentMatrix() {
        return adjacentMatrix;
    }
    public int getAdjacentMatrix(int row, int column) {
        return adjacentMatrix[row][column];
    }
    public void setAdjacentMatrix(int row, int column, int weight) {
        this.adjacentMatrix[row][column] = weight;
    }
}
