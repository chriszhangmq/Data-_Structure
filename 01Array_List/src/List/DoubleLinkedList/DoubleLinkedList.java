package List.DoubleLinkedList;

/**
 * 双向链表：
 * 注意：删除头部、尾部节点的区别
 * Created by Chris on 2019/11/6.
 */
public class DoubleLinkedList {
    private DoubleListNode header = new DoubleListNode(0,"","");
    private DoubleListNode rear;
    private int size;
    private boolean nodeExit = false;

    /**
     * 添加节点：按照id大小，进行排序添加
     */
    public void put(DoubleListNode newNode){
        DoubleListNode getNode = getNode(newNode.id);

        //节点已经存在，不重复添加
        if(nodeExit){
            System.out.println("NODE: " + getNode.id + " EXITED!");
            nodeExit = false;
            return;
        }
        //找到插入节点
        if(getNode != null){
            DoubleListNode temp01 = getNode.next;
            getNode.next = newNode;
            newNode.pre = getNode;

            temp01.pre = newNode;
            newNode.next = temp01;
        }
        //没找到插入节点，就放在链表的最后面
        else{
            if(header.next == null){    //添加头一个节点
                header.next = newNode;
                newNode.pre = header;

                rear = newNode;
                return;
            }
            rear.next = newNode;        //添加其他节点
            newNode.pre = rear;

            rear = newNode;
        }
        size++;
    }

    /**
     * 找到某个节点：依据id查找
     */
    public DoubleListNode getNode(int id){
        DoubleListNode temp = header;
        while (true){
            if(temp.next == null){  //是否遍历完整个链表
                break;
            }
            if(temp.next.id > id){  //找到节点：返回插入位置的前一个节点
                return temp;
            }
            if(temp.next.id == id){ //节点已经存在，不会重复添加
                return temp.next;
            }
            temp = temp.next;
        }
        return null;    //返回null，说明遍历了所有节点，没找到位置，应该放在最后面
    }

    /**
     * 打印链表
     */
    public void printLinkedList(){
        System.out.println("\n=============== PRINT LINKED LIST =============");
        if(header.next == null){
            System.out.println("链表为空");
            return;
        }
        DoubleListNode temp = header.next;
        while (true){
            System.out.println(temp);
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList dll = new DoubleLinkedList();

        DoubleListNode l1 = new DoubleListNode(1,"chris","MQ");
        DoubleListNode l2 = new DoubleListNode(2,"yj","YJ");
        DoubleListNode l3 = new DoubleListNode(3,"vicky","HJ");
        DoubleListNode l4 = new DoubleListNode(4,"alice","HCH");

        dll.put(l1);    //添加的新节点，依据id大小自动排序
        dll.put(l3);
        dll.put(l2);
        dll.put(l4);

        dll.printLinkedList();
    }

}
