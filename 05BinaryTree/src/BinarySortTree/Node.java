package BinarySortTree;

/**
 * Created by Chris on 2019/12/12.
 */
public class Node {
    private  int value;
    private Node leftNode;
    private Node rightNode;

    /**
     * 搜索节点
     * @param value
     * @return
     */
    public Node search(int value){
        //找到节点
        if(this.value == value){
            return this;
        }
        //没找到，继续左递归
        if(value < this.value){
            if(this.leftNode != null){
                return this.leftNode.search(value);
            }
            else{
                return null;
            }
        }
        //右递归
        else{
            if(this.rightNode != null){
                return this.rightNode.search(value);
            }
            else{
                return null;
            }
        }
    }

    /**
     * 搜索目标节点的父节点
     * @param value
     * @return
     */
    public Node searchParentNode(int value){
        //找到父节点
        if((this.leftNode != null && this.leftNode.value == value) || (this.rightNode != null) && this.rightNode.value == value){
            return this;
        }
        //没找到，左递归
        if(value < this.value && this.leftNode != null){
            return this.leftNode.searchParentNode(value);
        }
        //没找到，右递归
        else if(value >= this.value && this.rightNode != null){
            return this.rightNode.searchParentNode(value);
        }
        //最终都没找到
        else {
            return null;
        }
    }



    /**
     * 添加节点
     * @param node
     */
    public void add(Node node){
        if(node == null){
            return;
        }
        //添加的节点 < 当前节点
        if(node.value < this.value){
            //左节点不为空，继续左遍历
            if(this.leftNode != null){
                this.leftNode.add(node);
            }
            else {
                this.leftNode = node;
            }
        }
        //添加的节点 >= 当前节点
        else{
            //右节点不为空，继续右遍历
            if(this.rightNode != null){
                this.rightNode.add(node);
            }
            else{
                this.rightNode = node;
            }
        }
    }

    /**
     * 中序遍历：
     * 二叉排序树使用中序遍历，得到的数据就是从小到大排序
     */
    public void inorderTraversal(){
        if(this.leftNode != null){
            this.leftNode.inorderTraversal();
        }
        System.out.println(this);
        if(this.rightNode != null){
            this.rightNode.inorderTraversal();
        }
    }

    public Node(int value) {
        this.value = value;
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

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
