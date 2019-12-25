package List.Josephu;

/**
 * 约瑟夫环
 * Created by Chris on 2019/11/7.
 */
public class Josephu {
    private SingleListNode header = new SingleListNode(0);
    private int size;

    /**
     * 添加节点：一次性添加多少个节点
     * @param num
     */
    public void put(int num){
        System.out.println("========= PUT NODE =========");

        if(num < 1){
            System.out.println("ERROR：The number of Node shuould't < 1");
            return;
        }
        SingleListNode currentNode = null;
        for(int i = 1; i <= num; i++){
            SingleListNode newNode = new SingleListNode(i);
            if(i == 1){
                header = newNode;       //加入第一个节点时,构成环状
                header.setNext(header);
                currentNode = header;
            }
            else {
                currentNode.setNext(newNode);   //后面添加的节点，自动与头节点构成环状
                newNode.setNext(header);
                currentNode = newNode;
            }
            size++;
        }
    }

    /**
     * 节点出圈，获得出列的顺序
     * 思路：1、使用辅助节点assistNode：位于first后一个的节点，用于拼接出列后的链表
     *       2、每次移动countNum个节点，first所在位置为出列节点
     *       3、assistNode、first，同时移动每次移动 countNum - 1，因为自身所在位置为1
     */
    public void sequence(int startId, int countNum){
        System.out.println("\n=========== GET SEQUENCE ===========");
        if(startId > size || startId <= 0 || countNum >= size){
            System.out.println("ERROR; invalid parameters !");
            return;
        }

        SingleListNode assitNode = header;
        //找到辅助节点位置：位于header的后一个节点——链表尾部
        while (true){
            if(assitNode.getNext() == header){
                break;
            }
            assitNode = assitNode.getNext();
        }

        //从第startId节点开始出列：移动assistNode、header节点为位置
        while (startId > 1){
            header = header.getNext();
            assitNode = assitNode.getNext();
            startId--;
        }

        //获得出列的顺序
        while (true){
            if(assitNode == header){
                break;
            }
            for(int i = 0; i < countNum - 1; i++){
                header = header.getNext();
                assitNode = assitNode.getNext();
            }
            System.out.print(header.getId() + "->");
            header = header.getNext();
            assitNode.setNext(header);
        }
        System.out.println("\nTHE LAST NODE = " + header.getId());
    }

    /**
     * 打印链表
     */
    public void printList(){
        System.out.println("========== PRINT LINKED LIST ==========");
        if(size <= 0){
            System.out.println("ERROR: Linked List is null !");
            return;
        }
        SingleListNode currentNode = header;
        while (true){
            System.out.print(currentNode.getId() + "->");
            if(currentNode.getNext() == header){        //是否遍历完链表：依据，下一个节点为头节点
                break;
            }
            currentNode = currentNode.getNext();
        }
    }

    public static void main(String[] args) {
        Josephu josephu = new Josephu();
        josephu.put(25);
        josephu.printList();
        josephu.sequence(2,2);
    }
}
