package AVLTree;

/**
 * Created by Chris on 2019/12/12.
 */
public class Node {
    private  int value;
    private Node leftNode;
    private Node rightNode;

    /**
     * 右旋转
     */
    public void rightRotate(){
        //新建节点：其值为当前节点的值
        Node newNode = new Node(this.value);
        //新建节点的右子树 = 当前节点的右子树
        newNode.rightNode = this.rightNode;
        //新建节点的左子树 = 当前节点左子树的右子树
        newNode.leftNode = this.leftNode.rightNode;
        //当前节点的值 = 当前节点左子树的值
        this.value = this.leftNode.value;
        //当前节点的右子树 = 新建节点
        this.rightNode = newNode;
        //当前节点的左子树 = 当前节点左子树的左子树
        this.leftNode = this.leftNode.leftNode;
    }

    /**
     * 左旋转
     */
    public void leftRotate(){
        //新建节点：其值为当前节点的值
        Node newNode = new Node(this.value);
        //新建节点的左子树 = 当前节点的左子树
        newNode.leftNode = this.leftNode;
        //新建节点的右子树 = 当前节点右子树的左子树
        newNode.rightNode = this.rightNode.leftNode;
        //当前节点的值 = 当前节点右子树的值
        this.value = this.rightNode.value;
        //当前节点的右子树 = 当前节点的右子树的右子树
        this.rightNode = this.rightNode.rightNode;
        //当前节点的左子树 = 新建节点
        this.leftNode = newNode;
    }

    /**
     * 返回该节点的左子树高度
     * @return
     */
    public int leftTreeHight(){
        if(this.leftNode == null){
            return 0;
        }
        else{
            return this.leftNode.hight();
        }
    }

    /**
     * 返回该节点的右子树高度
     * @return
     */
    public int rightTreeHight(){
        if(this.rightNode == null){
            return 0;
        }
        else{
            return this.rightNode.hight();
        }
    }

    /**
     * 计算以该节点为根节点的树高度
     * @return
     */
    public int hight(){
        return Math.max(this.leftNode == null ? 0: this.leftNode.hight(),
                this.rightNode == null ? 0 : this.rightNode.hight()) + 1;
    }

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
     * 每添加一个节点都需要进行 左右旋转、或双旋转，以保证最终得到的二叉树是AVL树
     * @param node
     */
    public void add(Node node){
        if(node == null){
            return;
        }
        /**
         * 添加节点
         */
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

        /**
         * 添加完节点后，进行左右旋转、双旋转
         */
        //左旋转：该节点的右子树高度 > 该节点的左子树高度
        if(this.rightTreeHight() - this.leftTreeHight() > 1){
            //双旋转：该节点的右子树的左子树高度 > 该节点的右子树的右子树的高度
            if(this.rightNode != null && this.rightNode.leftTreeHight() > this.rightNode.rightTreeHight()){
                //该节点的右子树先进行右旋转
                this.rightNode.leftRotate();
                //再对该节点进行左旋转
                this.leftRotate();
            }
            //只进行，左旋转
            else{
                this.leftRotate();
            }
            //必须有这个，否则在添加一次节点 和旋转之后，就会继续执行其他递归函数的旋转操作
            return;
        }
        //右旋转：该节点的左子树高度 > 该节点的右子树高度
        if(this.leftTreeHight() - this.rightTreeHight() > 1){
            //双旋转：该节点的左子树的右子树高度 > 该节点的左子树的左子树高度
            if(this.leftNode != null && this.leftNode.rightTreeHight() > this.leftNode.leftTreeHight()){
                //该节点的左子树先进性左旋转
                this.leftNode.leftRotate();
                //再进行右旋转
                this.rightRotate();
            }
            //只进行，右旋转
            else{
                this.rightRotate();
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
