package HuffmanTree;

/**
 * Created by Chris on 2019/12/9.
 */
public class Node implements Comparable<Node>{
    private int value;
    private Node leftNode;
    private Node rightNode;

    /**
     * 前序遍历
     */
    public void preorderTraversal(){
        System.out.println(this.value);
        if(this.getLeftNode() != null){
            this.leftNode.preorderTraversal();
        }
        if(this.getRightNode() != null){
            this.rightNode.preorderTraversal();
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    /**
     * 从小到大排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value + '\'' +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }
}
