package Stack.Calculator.InfixCalculator;

/**
 * Created by Chris on 2019/11/8.
 */
public class SingleListNode {
    private int val;
    private SingleListNode next;

    public SingleListNode(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public SingleListNode getNext() {
        return next;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setNext(SingleListNode next) {
        this.next = next;
    }
}
