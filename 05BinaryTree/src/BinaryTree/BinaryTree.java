package BinaryTree;

/**
 * 二叉树
 * 和哈希表类似：都是在节点中定义好增删改查的方法，然后在二叉树的类中调用该方法实现相应的增删改查的方法（封装）。
 *
 * 下图为本程序的二叉树形状（根据id来排序）
 *             1
 *          -----
 *         2     3
 *             -----
 *            4     5
 *
 * Created by Chris on 2019/12/6.
 */
public class BinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal(){
        System.out.println("\n======= Preorder Traversal =======");
        if(root != null){
            //调用节点的前序遍历方法（不是二叉树的递归函数，而是root节点的递归函数）
            root.preorderTraversal();
        }
        else{
            System.out.println("Tree is null");
        }
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal(){
        System.out.println("\n======= Inorder Traversal =======");
        if(root != null){
            //调用节点的中序遍历方法（不是二叉树的递归函数，而是root节点的递归函数）
            root.inorderTraversal();
        }
        else{
            System.out.println("Tree is null");
        }
    }

    /**
     * 后序遍历
     */
    public void postorderTraversal(){
        System.out.println("\n======= Postorder Traversal =======");
        if(root != null){
            root.postorderTraversal();
        }
        else{
            System.out.println("Tree is null");
        }
    }

    /**
     * 前序遍历查找
     * @param id
     * @return
     */
    public Node preorderTraversalSearch(int id){
        System.out.println("\n======= Preorder Traversal Search =======");
        Node resNode = null;
        if(root != null){
            resNode = root.preorderTraversalSearch(id);
        }
        else{
            System.out.println("Tree is null");
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     * @param id
     * @return
     */
    public Node inorderTraversalSearch(int id){
        System.out.println("\n======= Inorder Traversal Search =======");
        Node resNode = null;
        if(root != null){
            resNode = root.inorderTraversalSearch(id);
        }
        else{
            System.out.println("Tree is null");
        }
        return resNode;
    }

    /**
     * 后续遍历查找
     * @param id
     * @return
     */
    public Node postorderTraversalSearch(int id){
        System.out.println("\n======= Postorder Traversal Search =======");
        Node resNode = null;
        if(root != null){
            resNode = root.postorderTraversalSearch(id);
        }
        else{
            System.out.println("Tree is null");
        }
        return resNode;
    }


    public static void main(String[] args){
        BinaryTree binaryTree = new BinaryTree();

        //新建节点
        Node node1 = new Node(1,"chris1");
        Node node2 = new Node(2,"chris2");
        Node node3 = new Node(3,"chris3");
        Node node4 = new Node(4,"chris4");
        Node node5 = new Node(5,"chris5");

        //添加节点
        binaryTree.setRoot(node1);
        node1.setLeftNode(node2);
        node1.setRightNode(node3);
        node3.setLeftNode(node4);
        node3.setRightNode(node5);

        //前序遍历
        binaryTree.preorderTraversal();
        //中序遍历
        binaryTree.inorderTraversal();
        //后序遍历
        binaryTree.postorderTraversal();

        //前序遍历查找
        System.out.println(binaryTree.preorderTraversalSearch(5));
        //中序遍历查找
        System.out.println(binaryTree.inorderTraversalSearch(3));
        //后序遍历查找
        System.out.println(binaryTree.postorderTraversalSearch(3));

    }
}
