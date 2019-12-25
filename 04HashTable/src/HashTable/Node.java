package HashTable;

/**
 * 节点
 * Created by Chris on 2019/12/4.
 */
public class Node {
    public int id;
    public String name;
    public Node nextNode;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
