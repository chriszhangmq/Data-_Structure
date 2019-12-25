package HuffmanCode;

/**
 * Created by Chris on 2019/12/10.
 */
public class Node implements Comparable<Node>{

    private Node rightNode;
    private Node leftNode;
    private int weight;  //存放权值：该字符出现的频率
    Byte data;           //存放数据：对应的字符--ASCII码值

    /**
     * 前序遍历
     */
    public void preorderTraversal(){
        System.out.println(this);
        //左递归
        if(this.getLeftNode() != null){
            this.leftNode.preorderTraversal();
        }
        //右递归
        if(this.getRightNode() != null){
            this.rightNode.preorderTraversal();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", data=" + data +
                '}';
    }

    public Node(int weight) {
        this.weight = weight;
    }

    public Node(Byte data, int weight) {
        this.weight = weight;
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

}
