package ThreadBinaryTree;

/**
 * 线索二叉树
 *  仅写了：中序线索化二叉树方法
 * Created by Chris on 2019/12/8.
 */
public class ThreadedBinaryTree {
    private Node root;
    private Node preNode=null;  //用于保存当前节点的前一个节点

    public void setRoot(Node root){
        this.root = root;
    }

    /**
     * 中序遍历线索二叉树
     * 和普通的中序遍历不同
     */
    public void inorderTraversal(){
        Node currentNode = root;
        while(currentNode != null){
            //1、先找到最底层的左子树节点
            while(currentNode.getLeftType() == 0){
                currentNode = currentNode.getLeftNode();
            }

            //2、打印当前节点
            System.out.println(currentNode);

            //3、若当前节点的右子树指向后继节点，则输出该后继节点
            while(currentNode.getLeftType() == 1){
                currentNode = currentNode.getRightNode();
                System.out.println(currentNode);
            }

            //4、获得后继节点的右子树节点
            currentNode = currentNode.getRightNode();
        }
    }

    /**
     * 中序线索化二叉树
     * 将二叉树进行中序线索化
     */
    public void inorderThreadedTree(Node node){
        if(node == null){
            return;
        }
        //1、线索化左子树
        //找到最底层左子树的叶节点
        inorderThreadedTree(node.getLeftNode());

        //1.1处理前驱节点
        if(node.getLeftNode() == null){
            node.setLeftNode(preNode);
            node.setLeftType(1);
        }

        //1.2处理后继节点
        if(preNode!=null && preNode.getRightNode() == null){
            //前驱节点的右子树节点指向当前节点
            preNode.setRightNode(node);
            preNode.setRightType(1);
        }
        //1.3处理每个节点后，保存当前节点为前驱节点
        preNode = node;

        //2、线索化右子树
        inorderThreadedTree(node.getRightNode());

    }


    public static void main(String[] args){
        ThreadedBinaryTree threadBinaryTree = new ThreadedBinaryTree();
        Node node1 = new Node(1,"chris1");
        Node node3 = new Node(3,"chris3");
        Node node6 = new Node(6,"chris6");
        Node node8 = new Node(8,"chris8");
        Node node10 = new Node(10,"chris10");
        Node node14 = new Node(14,"chris14");

        threadBinaryTree.setRoot(node1);
        node1.setLeftNode(node3);
        node1.setRightNode(node6);
        node3.setLeftNode(node8);
        node3.setRightNode(node10);
        node6.setLeftNode(node14);

        //中序线索化二叉树
        threadBinaryTree.inorderThreadedTree(node1);
        //中序遍历线索化二叉树
        threadBinaryTree.inorderTraversal();
    }

}
