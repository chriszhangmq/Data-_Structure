package Stack.Calculator.InfixCalculator;

/**
 * 使用链表模拟栈
 * Created by Chris on 2019/11/8.
 */
public class LinkedListStack {
    private SingleListNode header = new SingleListNode(0);
    private SingleListNode tail = header;
    private int size;
    private final int MAXSIZE;

    public LinkedListStack(int MAXSIZE){
        this.MAXSIZE = MAXSIZE;
    }

    public boolean isFull(){
        return size == MAXSIZE;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void push(int val){
        //栈是否满
        if(isFull()){
            System.out.println("STACK IS FULL !");
            return;
        }
        //未满
        SingleListNode newNode = new SingleListNode(val);
        tail.setNext(newNode);
        tail = newNode;
        size++;
    }

    public int pop(){
        //栈是否空
        if(isEmpty()){
            size = 0;
            throw new RuntimeException("STACK IS EMPTY !");
        }
        //不为空
        int val;
        if(tail != header){      //链表节点个数 > 1
            val = tail.getVal();
            tail = secondLast();
            size--;
            return val;
        }
        else {                   //链表节点个数 = 1
            size--;
            return tail.getVal();
        }
    }

    /**
     * 获取链表尾部倒数第二个节点
     * @return
     */
    public SingleListNode secondLast(){
        if(isEmpty()){
            throw new RuntimeException("STACK IS EMPTY !");
        }
        SingleListNode temp = header;
        while (true){
            if(temp.getNext() == tail){
                return temp;
            }
            temp = temp.getNext();
        }
    }

    public int peek(){
        return tail.getVal();
    }

    /**
     * 打印栈：
     * 实际上使用的时链表的逆序打印: 直接出栈，并不影响header节点，不影响原有链表的结构；
     * 只需要关注size，保存当前值即可，因为pop( )方法会改变size
     */
    public void printStack(){
        System.out.println("\n========= PRINT STAXK =========");
        int saveSize = size;

        System.out.println("STACK TOP");
        while (true){
            if(isEmpty()){
                break;
            }
            System.out.println(pop());
        }
        System.out.println("STACK BOTTOM");
        size = saveSize;

    }

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack(5);

        System.out.println("\n========= PUSH STACK =========");
        stack.push(2);
        stack.push(5);
        stack.push(0);
        stack.push(7);
        stack.push(10);
        stack.push(100);

        stack.printStack();

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

    }
}
