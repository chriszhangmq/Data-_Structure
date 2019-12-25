package HuffmanCode;

import java.util.*;

/**
 * 赫夫曼编码：实现无损压缩
 *
 *
 * Created by Chris on 2019/12/10.
 */
public class HuffmanEncode {
    private Node root;
    //存放赫夫曼编码的结果：每个字符 + 对应的赫夫曼编码
    private Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
    /**
     * 设定赫夫曼树的根节点：需要创建赫夫曼树之后才能使用该函数，进而使用前序遍历
     * @param root
     */
    public void setRoot(Node root){
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal(){
        System.out.println("\n======= Preorder Traversal =======");
        if(root != null){
            root.preorderTraversal();
        }
        else{
            System.out.println("Tree is null");
        }
    }

    /**
     * 接收字节数据，存入到 Node 中
     * @param bytes
     * @return
     */
    public List<Node> getNodes(byte[] bytes){
        ArrayList<Node> nodes = new ArrayList<Node>();
        Map<Byte, Integer> counts = new HashMap<>();

        //遍历bytes，统计内每个byte出现的次数
        for(byte b : bytes){
            Integer count = counts.get(b);
            //该byte第一次出现
            if(count == null){
                counts.put(b,1);
            }
            else{
                counts.put(b,count+1);
            }
        }

        //将键值对，转成Node对象，存到Node的集合中，存入节点的数据为：字符的ascii值，字符出现的频率
        for(Map.Entry<Byte, Integer> entry: counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    /**
     * 创建赫夫曼树
     * @param bytes
     * @return
     */
    public Node createHuffmanTree(byte[] bytes){
        List<Node> arrayList = new ArrayList<>();

        //1、将数组放入Arraylist中
        arrayList = getNodes(bytes);
        //2、创建赫夫曼树
        while(arrayList.size() > 1){
            //3、将ArrayList中的元素排序：从小到大
            Collections.sort(arrayList);

            //4、选取最小的两个创建二叉树
            Node leftNode = arrayList.get(0);
            Node rightNode = arrayList.get(1);
            Node parentNode = new Node(leftNode.getWeight() + rightNode.getWeight());
            parentNode.setLeftNode(leftNode);
            parentNode.setRightNode(rightNode);

            //5、删除已经使用的两个最小节点，并添加新建的父节点
            arrayList.remove(leftNode);
            arrayList.remove(rightNode);
            arrayList.add(parentNode);
        }
        return arrayList.get(0);
    }

    /**
     * 获得字符对应的赫夫曼编码
     * @param root
     */
    public Map<Byte,String>  getCode(Node root){
        if(root == null){
            System.out.println("Tree is null");
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        getCode(root,"0",stringBuilder);
        getCode(root,"1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 找到传入节点的所有叶子节点的赫夫曼编码，并存入huffmanCodes中
     *
     * @param node
     * @param code：0，左子节点   1：右子节点
     * @param stringBuilder：用于拼接节点
     */
    public void getCode(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node != null){
            //非叶子节点
            if(node.getLeftNode() != null || node.getRightNode() != null){
                //继续递归：直到找到叶子节点
                //左递归
                getCode(node.getLeftNode(),"0",stringBuilder2);
                //右递归
                getCode(node.getRightNode(),"1",stringBuilder2);
            }
            //获取对应字符(node.getData())的哈希编码(stringBuilder2)
            else{
                huffmanCodes.put(node.getData(),stringBuilder2.toString());
            }
        }
    }


    /**
     * 将字符串转成对应的赫夫曼编码
     * 只需要传入需要转换的字符串即可
     * @param str
     * @return
     */
    public StringBuilder getStringHuffmanCode(String str){
        System.out.println("\n====== Create string's huffmanCode =======");
        byte[] str2bytes = str.getBytes();
        StringBuilder res = new StringBuilder();
        //1、创建赫夫曼树
        Node root = createHuffmanTree(str2bytes);

        //2、设置赫夫曼树的root
        setRoot(root);

        //3、获得每个字符对应的赫夫曼编码
        Map<Byte,String> getHuffmanCode = getCode(root);

        //4、根据键来查询 对应字符的赫夫曼编码，再进行组装，得到该字符串对应的赫夫曼编码
        for(byte b : str2bytes){
            res.append(huffmanCodes.get(b));
        }
        return res;
    }

    /*******************************  以下函数均为测试函数 *******************************/

    /**
     * 打印赫夫曼编码的结果
     * @param hashMap
     */
    public void printHuffmanCode(Map<Byte,String> hashMap){
        System.out.println("\n======= pritn huffmanCode =======");
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " -- " + val);
        }
    }

    public static void main(String[] args) {

        String str = "i like like like java do like a java";
        //将字符串换换成ascii
        byte[] str2byte = str.getBytes();

        HuffmanEncode huffmanEncode = new HuffmanEncode();

//        //1、创建赫夫曼树
//        Node root = huffmanEncode.createHuffmanTree(str2byte);
//
//        //2、设置赫夫曼树的root
//        huffmanEncode.setRoot(root);
//
//        //前序遍历赫夫曼树
////        huffmanEncode.preorderTraversal();
//
//        //3、获得每个字符对应的赫夫曼编码
//        Map<Byte,String> getHuffmanCode = huffmanEncode.getCode(root);
//        huffmanEncode.printHuffmanCode(getHuffmanCode);

        //4、获取字符串的赫夫曼编码：只需要传入需要转换的字符串即可
        StringBuilder res = huffmanEncode.getStringHuffmanCode(str);
        System.out.println(res);
    }
}
