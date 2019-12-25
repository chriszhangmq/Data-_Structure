package HashTable;

/**
 * 哈希表实现
 * 组成：
 *      1、节点Node
 *      2、链表LinkedList：由节点构成，并且在链表中写出：链表元素的增删改查方法
 *      3、构建hashTable：由数组和链表构成，并且写出：哈希表的增删改查方法
 *
 * 用户所有的操作：只操作哈希表，再由哈希表自己去操作对应的链表
 * Created by Chris on 2019/12/4.
 */
public class HashTable {
    private int size;           //哈希表的大小
    private LinkedList[] linkedListsArray;

    public HashTable(int size) {
        this.size = size;
        //哈希表初始化
        this.linkedListsArray = new LinkedList[size];
        /**
         * 此处必须为每个元素对应的链表进行初始化
         */
        for(int i = 0; i < size; i++){
            linkedListsArray[i] = new LinkedList();
        }
    }

    /**
     * 添加
     * @param newNode
     */
    public void add(Node newNode){
        int hashCode = hashCode(newNode.id);
        linkedListsArray[hashCode].add(newNode);
    }

    /**
     * 删除
     * @param id
     */
    public void remove(int id){
        int hashCode = hashCode(id);
        linkedListsArray[hashCode].remove(id);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public Node find(int id){
        int hashCode = hashCode(id);
        Node resNode = linkedListsArray[hashCode].find(id);
        if(resNode == null){
            System.out.println("NOT FOUND id = " + id);
            return null;
        }
        return resNode;
    }

    /**
     * 计算哈希码
     * @param id
     * @return
     */
    public int hashCode(int id){
        return id % size;
    }

    /**
     * 显示哈希表所有元素
     */
    public void print(){
        for(int i = 0; i < size; i++){
            System.out.print(i  + "th :");
            linkedListsArray[i].print();
        }
    }

    public static void main(String[] args) {
        HashTable hashtable = new HashTable(7);
        Node node1 = new Node(1,"chris1");
        Node node2 = new Node(2,"chris2");
        Node node3 = new Node(3,"chris3");
        Node node4 = new Node(4,"chris4");
        Node node8 = new Node(8,"chris4");

        //1、增加
        System.out.println("\n==========  ADD  ==========");
        hashtable.add(node1);
        hashtable.add(node2);
        hashtable.add(node3);
        hashtable.add(node4);
        hashtable.add(node8);
        hashtable.print();

        //2、查找
        System.out.println("\n==========  FIND  ==========");
        Node resNode = hashtable.find(1);
        System.out.println(resNode);

        //3、删除
        System.out.println("\n==========  REMOVE  ==========");
        hashtable.remove(2);
        hashtable.print();
    }
}
