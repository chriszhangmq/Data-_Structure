package Stack.ArrayStack;

/**
 * 使用数组模拟栈
 * Created by Chris on 2019/11/8.
 */
public class ArrayStack {
    private final int MAXSIZE;    //栈的大小
    private int[] stack;    //使用数组模拟栈
    private int top = -1;   //栈顶

    public ArrayStack(int MAXSIZE) {
        this.MAXSIZE = MAXSIZE;
        stack = new int[MAXSIZE];
    }

    public boolean isFull(){
        return top == MAXSIZE - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int val){
        //栈是否满
        if(isFull()){
            System.out.println("STACK IS FULL !");
            return;
        }
        //未满
        top++;
        stack[top] = val;
    }

    public int pop(){
        //栈是否为空
        if(isEmpty()){
            throw new  RuntimeException("STACK IS EMPTY !");
        }
        //不为空
        int val = stack[top];
        top --;
        return val;
    }

    public void printStack(){
        System.out.println("\n========= PRINT STACK ==========");
        //栈是否为空
        if(isEmpty()){
            System.out.println("STACK IS EMPTY !");
            return;
        }
        int temp = top;
        System.out.println("STACK TOP");
        while (temp > -1){
            System.out.println(stack[temp]);
            temp--;
        }
        System.out.println("STACK BUTTOM");
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);

        stack.push(10);
        stack.push(5);
        stack.push(1);
        stack.push(14);
        stack.push(17);

        stack.printStack();

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

        stack.printStack();
    }
}
