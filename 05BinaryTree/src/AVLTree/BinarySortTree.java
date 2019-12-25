package AVLTree;

/**
 * ===== AVL树 =====
 * 本质上：是二叉排序树的改进版，只不过在Node类添加了左右旋转、双旋转、测量左右子树高度的方法
 *         每添加一个新节点时，必须进行一次左右旋转或双旋转，保证生成的树为AVL树
 *
 * 左节点值 < 父节点值 < 右节点值
 * 二叉排序树，用中序遍历之后，得到的数据就是从小到大排序
 *
 * 注意：在新建二叉排序树时，必须均匀的分布数据，否则会出现错误，因为这边只处理了特定条件下的删除情况
 * Created by Chris on 2019/12/12.
 */
public class BinarySortTree {
    private Node root;

    /**
     * 删除节点：
     * 有如下情况：
     * 1、删除叶节点
     * 2、删除只有一颗子树的节点
     * 3、删除有两颗子树的节点
     * @param value
     */
    public void delete(int value){
        if(root == null){
            return;
        }
        else{
            Node targetNode = search(value);
            if(targetNode == null){
                return;
            }
            else{
                //1、该树只有root节点
                if(root.getLeftNode() == null && root.getRightNode() == null){
                    root = null;
                    return;
                }
                //2、该树存在多个节点
                Node parentNode = searchParentNode(value);
                //3、若删除的是叶节点
                if(targetNode.getLeftNode() == null && targetNode.getRightNode() == null){
                    //删除的叶节点为父节点的左节点
                    if(parentNode.getLeftNode() != null && parentNode.getLeftNode().getValue() == value){
                        parentNode.setLeftNode(null);
                    }
                    else if(parentNode.getRightNode() != null && parentNode.getRightNode().getValue() == value){
                        parentNode.setRightNode(null);
                    }
                }
                //4、删除的是有两颗子树的节点： 将该节点下，右子树中最小的节点值赋给该节点，并删除这个最小节点
                else if(targetNode.getRightNode() != null && targetNode.getLeftNode() != null){
                    int minNodeValue = delMinRightNode(targetNode);
                    targetNode.setValue(minNodeValue);
                }
                //5、删除的是只有一颗子树的节点
                else{
                    //要删除的节点只有左子树
                    if(targetNode.getLeftNode() != null){
                        //要删除的节点不是root
                        if(parentNode != null){
                            //要删除的节点在其父节点的左侧
                            if(parentNode.getValue() == value){
                                parentNode.setLeftNode(targetNode.getLeftNode());
                            }
                            //要删除的节点在其父节点的右侧
                            else {
                                parentNode.setRightNode(targetNode.getLeftNode());
                            }
                        }
                        //要删除的节点是root
                        else{
                            root = targetNode.getLeftNode();
                        }
                    }
                    //要删除的节点只有右子树
                    else{
                        //要删除的节点不是root
                        if(parentNode != null){
                            //要删除的节点在其父节点的左侧
                            if(parentNode.getValue() == value){
                                parentNode.setLeftNode(targetNode.getRightNode());
                            }
                            //要删除的节点在其父节点的右侧
                            else {
                                parentNode.setLeftNode(targetNode.getRightNode());
                            }
                        }
                        //要删除的节点是root
                        else{
                            root = targetNode.getRightNode();
                        }
                    }
                }

            }
        }
    }

    /**
     * 查找右子树最小的节点，并返回最小的节点数值
     * （用于删除有两个子树的节点）
     * 循环查找该节点第一个右子树下的最底层的左子树叶节点（就是该节点右子树的最小值）
     * @param node
     * @return
     */
    public int delMinRightNode(Node node){
        //寻找的最小节点
        Node minRightNode = node.getRightNode();
        while (minRightNode.getLeftNode() != null){
            minRightNode = minRightNode.getLeftNode();
        }
        delete(minRightNode.getValue());
        return minRightNode.getValue();
    }

    /**
     * 查找节点
     * @param value
     * @return
     */
    public Node search(int value){
        if(root != null){
            return root.search(value);
        }
        else {
            return null;
        }
    }

    /**
     * 查找父节点
     * @param value
     * @return
     */
    public Node searchParentNode(int value){
        if(root != null){
            return root.searchParentNode(value);
        }
        else{
            return null;
        }
    }

    /**
     * 添加节点
     * @param node
     */
    public void add(Node node){
        if(root == null){
            root = node;
        }
        else{
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal(){
        System.out.println("\n======= Inorder Traversal =======");
        if(root != null){
            root.inorderTraversal();
        }
        else{
            System.out.println("Tree is null");
        }
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    /*****************************  测试函数  **********************************/

    public static void main(String[] args) {
        //1、测试左旋转
//        int[] arr = {4,3,6,5,7,8};
        //2、测试右旋转
//        int[] arr = {10,12,8,9,7,6};
        //3、测试双旋转
        int[] arr = {10,11,7,6,8,9};

        BinarySortTree binarySortTree = new BinarySortTree();

        //添加节点
        for(int i : arr){
            binarySortTree.add(new Node(i));
        }
        binarySortTree.inorderTraversal();

        //删除节点
//        binarySortTree.delete(1);
//        binarySortTree.delete(7);
//        binarySortTree.inorderTraversal();

        //显示树的高度
        System.out.println("The hight of the tree : " + binarySortTree.getRoot().hight() + '\n' +
                            "Left subtree hight : " + binarySortTree.getRoot().getLeftNode().hight() + '\n' +
                            "Right subtree hight: " + binarySortTree.getRoot().getRightNode().hight()
                );

    }
}
