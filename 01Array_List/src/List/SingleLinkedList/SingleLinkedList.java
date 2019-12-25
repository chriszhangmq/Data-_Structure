package List.SingleLinkedList;

import java.util.Stack;

/**
 * 单链表: id是不会改变的，作为主键
 * Created by Chris on 2019/11/3.
 */
public class SingleLinkedList {
    private SingleListNode header = new SingleListNode(0,"","");    //头部
    private SingleListNode rear = header;                                            //尾部
    private boolean nodeExit = false;       //查找的节点是否存在
    private boolean delNode = false;        //删除节点的方法是否被调用

    /**
     * 添加节点：按照id大小，进行排序添加
     */
    public void put(SingleListNode newNode){
        SingleListNode temp = header;
        SingleListNode getNode = getNode(newNode.id);

        //节点已经存在，不重复添加
        if(nodeExit){
            System.out.println("NODE: " + getNode.id + " EXITED!");
            nodeExit = false;
            return;
        }
        //找到插入节点
        if(getNode != null){
            SingleListNode temp01 = getNode.next;
            getNode.next = newNode;
            newNode.next = temp01;
        }
        //没找到插入节点，就放在链表的最后面
        else{
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;

        }
    }

    /**
     * 找到某个节点：依据id查找
     */
    public SingleListNode getNode(int id){
        SingleListNode temp = header;
        while (true){
            if(temp.next == null){  //是否遍历完整个链表
                break;
            }
            if(temp.next.id > id){  //找到节点：返回插入位置的前一个节点
                return temp;
            }
            if(temp.next.id == id){ //节点已经存在，不会重复添加
                if(delNode){        //返回删除的节点
                    nodeExit = true;
                    return temp;
                }
                else {              //返回插入的节点位置
                    nodeExit = true;
                    return temp.next;
                }
            }
            temp = temp.next;
        }
        return null;    //返回null，说明遍历了所有节点，没找到位置，应该放在最后面
    }

    /**
     * 修改某个节点：依据id查找
     */
    public void modifyNode(SingleListNode modifyNode){
        System.out.println("\n=============== MODIFY NODE =============");
        SingleListNode temp = header;
        if(temp == null){
            System.out.println("Linked List is null !");
            return;
        }

        SingleListNode getNode = getNode(modifyNode.id);
        if(getNode == null){
            System.out.println("NO FOUND NODE: " + modifyNode.id);
        }
        else if(nodeExit){
            System.out.println("FOUND NODE: ");
            System.out.println(getNode);
            getNode.name = modifyNode.name;
            getNode.nickname = modifyNode.nickname;
            System.out.println(getNode);
            nodeExit = false;
        }
    }

    /**
     * 删除某个节点:依据id查找
     */
    public void remove(int id){
        System.out.println("\n=============== REMOVE NODE =============");
        delNode = true;
        SingleListNode getNode = getNode(id);
        if(nodeExit){
            System.out.println("success remove node: \n" + getNode);
            getNode.next = getNode.next.next;
        }
        else {
            System.out.println("Node Not Exist !");
        }
        nodeExit = false;
        delNode = false;
    }

    /**
     * 反转链表
     * 思路：将原来的链表最前面的元素，放到反转后链表的最前面，最后将原链表的头部指向反转后的链表。
     */
    public void reverse(){
        SingleListNode next = null;
        SingleListNode current = header.next;
        SingleListNode reverseHeader = new SingleListNode(0,"","");

        System.out.println("\n============ REVERSE LINKED ==============");
        if(current == null){
            System.out.println("LINKED lIST IS NULL !");
            return;
        }
        while (true){
            if(current == null){
                break;
            }
            next = current.next;
            current.next = reverseHeader.next;
            reverseHeader.next = current;
            current = next;
        }
        header = reverseHeader;
    }

    /**
     * 反向输出链表：不改变原有链表的结构，使用栈的特性——先进后出
     */
    public void reversePrint(){
        System.out.println("\n========= REVERSR PRINT LINKED LIST =========");
        SingleListNode current = header.next;
        if(current == null){
            System.out.println("LINKED LIST IS NULL !");
            return;
        }
        Stack<SingleListNode> stack = new Stack<SingleListNode>();
        //放入数据
        while (current != null){
            stack.push(current);
            current = current.next;
        }
        //取出数据
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
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
        SingleListNode temp = header.next;
        while (true){
            System.out.println(temp);
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList sll = new SingleLinkedList();
        SingleListNode l1 = new SingleListNode(1,"chris","MQ");
        SingleListNode l2 = new SingleListNode(3,"vicky","HJ");
        SingleListNode l3 = new SingleListNode(2,"yj","YJ");
        SingleListNode l4 = new SingleListNode(4,"alice","HCH");
        SingleListNode l5 = new SingleListNode(4,"al","HCH");

        sll.put(l1);            //添加元素，依据id大小自动排序
        sll.put(l2);
        sll.put(l3);
        sll.put(l4);
        sll.put(l5);
        sll.modifyNode(l5);     //修改节点数据
        sll.remove(3);      //删除数据

        sll.printLinkedList();  //打印链表

//        sll.reverse();        //反转链表
        sll.reversePrint();
        sll.printLinkedList();
    }
}
