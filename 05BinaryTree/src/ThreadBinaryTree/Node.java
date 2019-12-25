package ThreadBinaryTree;

/**
 * 二叉树节点：用于线索二叉树
 *
 * 注意：二叉树的遍历查找，都是先遍历叶节点（二叉树最底部的节点），然后再向上寻找符合条件的数据
 * Created by Chris on 2019/12/6.
 */
public class Node {
    private int id;
    private String name;
    private Node leftNode;
    private Node rightNode;

    private int leftType;   //0：当前节点没有指向前驱节点，1：当前节点指向前驱节点
    private int rightType;  //0：当前节点没有指向后继节点，1：当前节点指向后继节点

    /**
     * 前序遍历
     * 父节点 -> 左节点 -> 右节点
     */
    public void preorderTraversal(){
        //显示父节点
        System.out.println(this);
        //遍历左子树：左递归
        if(this.leftNode != null){
            this.leftNode.preorderTraversal();
        }
        //遍历右子树：右递归
        if(this.rightNode != null){
            this.rightNode.preorderTraversal();
        }
    }

    /**
     * 中序遍历
     * 左节点 -> 父节点 -> 右节点
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

    /**
     * 后序遍历
     * 左节点 -> 右节点 -> 父节点
     */
    public void postorderTraversal(){
        if(this.leftNode != null){
            this.leftNode.postorderTraversal();
        }
        if(this.rightNode != null){
            this.rightNode.postorderTraversal();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * 父节点 -> 左节点 -> 右节点
     */
    public Node preorderTraversalSearch(int id){
        //1、判断当前节点是不是查找的对象（相当于是检查父节点）
        if(this.id == id){
            return this;        //找到结果
        }

        //用于存储遍历得到的结果
        Node resNode = null;
        //2、不是，进行左遍历
        if(this.leftNode != null){
            resNode = this.leftNode.preorderTraversalSearch(id);
        }
        //检查左遍历结果：不为空时，说明找到了结果：该结果由下面的this返回得到
        if(resNode != null){
            return resNode;     //退出递归，返回结果到上一级
        }

        //3、不是，进行右遍历
        if(this.rightNode != null){
            resNode = this.rightNode.preorderTraversalSearch(id);
        }

        //完成左右遍历之后，无论有没有找到结果，都要返回resNode
        return resNode;
    }

    /**
     * 中序遍历查找
     * 左节点 -> 父节点 -> 右节点
     */
    public Node inorderTraversalSearch(int id){
        //用于存放结果
        Node resNode = null;

        //1、左遍历：需要判断当前节点的左子节点是否为空
        if(this.leftNode != null){
            resNode = this.leftNode.inorderTraversalSearch(id);
        }
        //检查左遍历的结果：不为空时，说明找到了结果：该结果由下面的this返回得到
        if(resNode != null){
            return resNode;     //退出递归，返回结果到上一级
        }

        //2、判断当前节点是不是查找的对象（相当于是检查父节点）
        if(this.id == id){
            return this;        //找到结果
        }

        //3、进行右遍历：当左遍历没找到结果时，进行右遍历
        if(this.rightNode != null){
            resNode = this.rightNode.inorderTraversalSearch(id);
        }

        //完成左右遍历之后，无论有没有找到结果，都要返回resNode
        return resNode;
    }

    /**
     * 后序遍历查找
     * 左节点 -> 右节点 -> 父节点
     */
    public Node postorderTraversalSearch(int id){
        //存放查找结果
        Node resNode = null;

        //1、左遍历：需要判断当前节点的左子节点是否为空
        if(this.leftNode != null){
            resNode = this.leftNode.postorderTraversalSearch(id);
        }
        //检查左遍历的结果：不为空时，说明找到了结果：该结果由下面的this返回得到
        if(resNode != null){
            return resNode;     //退出递归，返回结果到上一级
        }

        //2、右遍历：需要判断当前节点的右子节点是否为空
        if(this.rightNode != null){
            resNode = this.rightNode.postorderTraversalSearch(id);
        }
        //检查右遍历的结果：不为空时，说明找到了结果：该结果由下面的this返回得到
        if(resNode != null){
            return resNode;     //退出递归，返回结果到上一级
        }

        //3、判断当前节点是不是查找的对象（相当于是检查父节点）
        if(this.id == id){
            return this;
        }

        //完成左右遍历之后，无论有没有找到结果，都要返回resNode
        return resNode;
    }


    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
