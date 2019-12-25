package List.DoubleLinkedList;

/**
 * 单链表的节点
 * Created by Chris on 2019/11/3.
 */
public class DoubleListNode {
    public int id;
    public String name;
    public String nickname;
    public DoubleListNode next;
    public DoubleListNode pre;

    public DoubleListNode(int id, String name, String nickname){
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "SingleListNode {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
