package Kruskal;

/**
 * 存储边的类
 * Created by Chris on 2019/12/21.
 */
public class EdgeData {
    public char start;      //边的起点
    public char end;        //边的终点
    public int weight;      //边的权重

    public EdgeData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EdgeData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
