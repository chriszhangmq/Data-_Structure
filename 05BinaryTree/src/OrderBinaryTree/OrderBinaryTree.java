package OrderBinaryTree;

/**
 * 顺序二叉树：
 * 将数组使用前序、中序、后序的方式，遍历一维数组
 *
 * 左子树：2*index + 1
 * 右子树：2*index + 2
 * 父节点：（n-1）/2
 * (n：一维数组的索引，从0开始)
 *
 * Created by Chris on 2019/12/8.
 */
public class OrderBinaryTree {
    private int[] arr;

    public OrderBinaryTree(int[] arr){
        this.arr = arr;
    }

    /**
     * 前序遍历顺序二叉树（一维数组）
     * 调用时：传入0，即：传入数组的第一个元素，作为root节点
     */
    public void preorderTraversal(int index){
        if(arr.length == 0){
            System.out.println("Arr is null");
            return;
        }
        //输出：当前节点
        System.out.println(arr[index]);
        //左递归：判断是否还有左子树，（arr.length + 1）为该二叉树的节点个数
        if(2*index + 1 < arr.length){
            preorderTraversal(2*index + 1);
        }
        //右递归：判断是否还有右子树，
        if(2*index+2 < arr.length){
            preorderTraversal(2*index + 2);
        }
    }

    /**
     * 中序遍历
     * @param index
     */
    public void inorderTraversal(int index){
        if(arr.length == 0){
            System.out.println("Arr is null");
            return;
        }
        //左递归
        if(2*index + 1 < arr.length){
            inorderTraversal(2*index + 1);
        }
        //输出
        System.out.println(arr[index]);
        //右递归
        if(2*index + 2 < arr.length){
            inorderTraversal(2*index + 2);
        }
    }

    /**
     * 后序遍历
     * @param index
     */
    public void postorderTraversal(int index){
        if(arr.length == 0){
            System.out.println("Arr is null");
            return;
        }
        if(2*index + 1 < arr.length){
            postorderTraversal(2*index + 1);
        }
        if(2*index + 2 < arr.length){
            postorderTraversal(2*index + 2);
        }
        System.out.println(arr[index]);
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6,7};
        OrderBinaryTree orderBinaryTree = new OrderBinaryTree(arr);

        System.out.println("\n======= PreorderTraversal =======");
        orderBinaryTree.preorderTraversal(0);

        System.out.println("\n======= InorderTraversal =======");
        orderBinaryTree.inorderTraversal(0);

        System.out.println("\n======= PostorderTraversal =======");
        orderBinaryTree.postorderTraversal(0);
    }

}
