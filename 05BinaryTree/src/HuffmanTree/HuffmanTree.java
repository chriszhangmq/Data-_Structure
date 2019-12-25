package HuffmanTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树：所有节点的带权路径长度（wpl）最小
 * 思路：
 *      1、用ArrayList来存放数组
 *      2、将ArrayList元素从小到大排序
 *      3、取前两个元素，构成一棵树，该树的父节点=两个子节点之和
 *      4、删除数组中的前两个元素（因为，已经用过了），将父节点的值放到ArrayList最后面，再对ArrayList进行排序
 *      5、重复1 - 4步骤，最后剩下一个元素时结束（这个元素为赫夫曼树的根节点）
 *
 *  例如：原数组 ：13,7,8,1,3,29,6 ，其赫夫曼树为：
 *              67
 *           -------
 *           29     38
 *                -------
 *               15      23
 *           -------     -------
 *           7      8   10      13
 *                     ------
 *                    4      6
 *                 -----
 *                 1    3
 * Created by Chris on 2019/12/9.
 */
public class HuffmanTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 前序遍历
     * @param node
     */
    public void preorderTraversal(Node node){
        System.out.println("======= Preorder Traversal =======");
        if(root == null){
            System.out.println("Tree is null");
        }
        else{
            root.preorderTraversal();
        }
    }

    /**
     * 构建赫夫曼树
     * @return：赫夫曼树的root节点
     */
    public Node createHuffmanTree(int[] arr){
        List<Node> arrayList = new ArrayList<Node>();
        //1、将数组存入ArrayList中
        for(int val : arr){
            arrayList.add(new Node(val));
        }

        //2、构建赫夫曼树
        while(arrayList.size() > 1){
            //3、从小大大排序
            Collections.sort(arrayList);

            //4、取前两个数，构建一棵树
            Node leftNode = arrayList.get(0);
            Node rightNode = arrayList.get(1);
            Node parentNode = new Node(leftNode.getValue() + rightNode.getValue());
            parentNode.setLeftNode(leftNode);
            parentNode.setRightNode(rightNode);

            //5、将arrayList前两个元素删除，并将parent添加回arrayList中，然后进行下一次循环
            arrayList.remove(leftNode);
            arrayList.remove(rightNode);
            arrayList.add(parentNode);
        }
        //这是一个节点，也就是赫夫曼树的root节点
        return arrayList.get(0);
    }

    public static void main(String[] args) {
        int[] arr = {13,7,8,1,3,29,6};
        HuffmanTree huffmanTree = new HuffmanTree();
        //
        Node root = huffmanTree.createHuffmanTree(arr);
        huffmanTree.setRoot(root);
        huffmanTree.preorderTraversal(root);
    }

}
