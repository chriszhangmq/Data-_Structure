package Dijkstra;

/**
 * 测试函数
 * Created by Chris on 2019/12/22.
 */
public class Dijkstra {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B','C','D','E','F','G'};
        final int N = 65536;
        int[][] adjacentMatrix = {
                {N,5,7,N,N,N,2},
                {5,N,N,9,N,N,3},
                {7,N,N,N,8,N,N},
                {N,9,N,N,N,4,N},
                {N,N,8,N,N,5,4},
                {N,N,N,4,5,N,6},
                {2,3,N,N,4,6,N}
        };

        Graph graph = new Graph(vertex, adjacentMatrix);
        graph.displayGraph();

        //生成最短路径：起始顶点2
        graph.shortPath(0);
        graph.displayDijkstra();
    }

}
