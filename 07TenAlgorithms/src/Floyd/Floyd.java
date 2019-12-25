package Floyd;

/**
 * 测试Floyd算法
 * Created by Chris on 2019/12/22.
 */
public class Floyd {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B','C','D','E','F','G'};
        final int N = 65536;
        int[][] adjacentMatrix = {  //写对角线的0，表示顶点本省到自身的距离为0
                {0,5,7,N,N,N,2},    //N：表示这两个顶点不直接连接
                {5,0,N,9,N,N,3},
                {7,N,0,N,8,N,N},
                {N,9,N,0,N,4,N},
                {N,N,8,N,0,5,4},
                {N,N,N,4,5,0,6},
                {2,3,N,N,4,6,0}
        };

        Graph graph = new Graph(vertex.length,adjacentMatrix,vertex);
        graph.floyd();
        graph.display();
    }

}
