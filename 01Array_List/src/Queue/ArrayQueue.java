package Queue;

import java.util.Scanner;

/** 使用循环数组实现队列
 * Created by Chris on 2019/10/27.
 */
public class ArrayQueue {
    private int MAXSIZE; //实际上只能存放 (MAXSIZE - 1) 个元素,因为tail指向最后一个元素的下一个元素，占用了一个位置
    private int header; //指向第一个元素
    private int tail;   //指向最后一个元素的下一个元素s
    private int[] arr;

    public ArrayQueue(int MAXSIZE){
        this.MAXSIZE = MAXSIZE;
        this.header = 0;
        this.tail = 0;
        arr = new int[MAXSIZE];
    }

    /**
     * 添加元素
     */
    public void add(int element){
        if(isFull()){
            System.out.println("QUEUE IS FULL: WAIT A MOMENT");
            return;
        }
        arr[tail] = element;
        tail = (tail + 1) % MAXSIZE;
    }

    /**
     * 获取队列数据——出队列
     */
    public int get(){
        if(isEmpty()){
            throw new RuntimeException("QUEUE IS EMPTY");
        }
        int getValue = arr[header];
        header = (header + 1) % MAXSIZE;
        return getValue;
    }

    /**
     * 队列是否满
     */
    public boolean isFull(){
        return (tail + 1) % MAXSIZE == header;
    }


    /**
     * 队列是否为空
     */
    public boolean isEmpty(){
        return header == tail;
    }

    /**
     * 打印队列信息：
     * 头部元素、尾部元素、队列中元素个数、队列中的元素
     */
    public void printInfo(){
        System.out.println("=====START: QUEUE INFO =====");
        System.out.println("HEADER = " + arr[(header+1)%MAXSIZE] + '\n' + "TAIL = " + arr[(tail+1)%MAXSIZE]);
        System.out.println("NUMEL = " + (tail + MAXSIZE - header) % MAXSIZE);
        if(!isEmpty()){
            System.out.print("ELEMENT: ");
            for(int i = header; i < header + ((tail + MAXSIZE - header)%MAXSIZE); i++){
                System.out.print(arr[i%MAXSIZE]  + ",");
            }
            System.out.println();
        }
        System.out.println("=====END: QUEUE INFO =====");
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);  //实际上只能存放4个元素
        Scanner scanner = new Scanner(System.in);

        System.out.println("'a': add data\n" + "'f': get on data\n" + "'p': print queue information\n" + "'exit': close system");
        boolean loop = true;
        while (loop){
            System.out.print("SELECTION OPERATION: ");
            String command = scanner.next();

            switch (command){
                case "a":
                    System.out.print("INPUT NUMBER: ");
                    int element = scanner.nextInt();
                    arrayQueue.add(element);
                    break;
                case "f":
                    System.out.print("GET ONE DATA: ");
                    System.out.println(arrayQueue.get());
                    break;
                case "p":
                    System.out.println("PRINT QUEUE ELEMENT: ");
                    arrayQueue.printInfo();
                    break;
                case "exit":
                    loop = false;
                    scanner.close();
                    break;
                default:
                    System.out.println("INVALID...");
                    break;
            }
        }

    }
}
