package List.Josephu;

/**
 * 单链表的节点
 * Created by Chris on 2019/11/3.
 */
public class SingleListNode {
    private int id;
    private SingleListNode next;

    public SingleListNode(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public SingleListNode getNext() {
        return next;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNext(SingleListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "SingleListNode {" +
                "id=" + id +
                '}';
    }
}
