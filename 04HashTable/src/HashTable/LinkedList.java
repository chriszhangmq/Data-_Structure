package HashTable;

/**
 * 链表
 * Created by Chris on 2019/12/4.
 */
public class LinkedList {
    public Node header=null;
    public int size;

    /**
     * 增加
     * @param newNode
     */
    public void add(Node newNode){
        //第一次添加元素
        if(header == null){
            header = newNode;
            size++;
            return;
        }
        //不是第一次添加：遍历链表到结尾
        Node temp = header;
        while (true){
            if(temp.nextNode == null){
                break;
            }
            temp = temp.nextNode;
        }
        temp.nextNode = newNode;
        size++;
    }

    /**
     * 删除
     * @param id
     */
    public void remove(int id){
        //链表为空
        if(header == null){
            System.out.println("REMOVE ERROR: Linked Lits is null ! ");
            return;
        }

        //第一个就是查找的对象
        if(header.id == id){
            header = header.nextNode;
            return;
        }

        Node temp = header.nextNode;    //当前遍历所在的节点
        Node tempPre = header;          //当前遍历所在的节点的前一个
        //第一个不是查找的对象
        while (true){
            //找到
            if(temp.id == id){
                tempPre.nextNode = temp.nextNode;
                size--;
                break;
            }
            //没找到
            if(temp == null){
                System.out.println("REMOVE ERROR: Linked List not exit id = " + id);
                break;
            }
            tempPre = temp;
            temp = temp.nextNode;
        }
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public Node find(int id){
        if(header == null){
            System.out.println("FIND ERROR: Linked List is null !");
            return null;
        }
        Node temp = header;
        while (true){
            //找到
            if(temp.id == id){
                break;
            }
            //遍历完，没找到
            if(temp == null){
                temp = null;
                break;
            }
            temp = temp.nextNode;
        }
        return temp;
    }

    /**
     * 打印
     */
    public void print(){
        if(header == null){
            System.out.println("Linked List is null !");
            return;
        }
        Node temp = header;
        while (true){
            System.out.print(" --> " + temp.id + ", " + temp.name);
            if(temp.nextNode == null){
                break;
            }
            temp = temp.nextNode;
        }
        System.out.println();
    }

}
