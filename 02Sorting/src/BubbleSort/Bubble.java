package BubbleSort;

import SystemParameter.RunTime;


/**
 * Created by Chris on 2019/11/16.
 */
public class Bubble {
    private int[] arr;
    private final int NUM;
    private int cycleIndex;
    private boolean flag;

    public Bubble(int NUM){
        this.NUM = NUM;
        arr = new int[NUM];
    }

    public void sort(){
        int temp;
        for(int i = 0; i < arr.length - 1; i ++){
            for(int j = 0; j < arr.length - 1 - i; j++){
                cycleIndex++;
                if(arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if(!flag){  //排序提前结束
                break;
            }
            else {
                flag = false;
            }
        }
    }

    public void randomElements(){
        for(int i = 0; i < NUM; i++){
            arr[i] = (int)(Math.random() * NUM); // 产生[ 0, NUM）的随机数
        }
    }

    public void display(){
        System.out.println("==========RESULT===========");
        System.out.println("CYCLE INDEX = " + cycleIndex);
        for(int i  = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        RunTime runTime = new RunTime();
        Bubble bubble = new Bubble(100000);
        bubble.randomElements();
//        bubble.arr = new int[]{1, -2, 2, 5, 9};

        runTime.startProgram();
        bubble.sort();
        runTime.endProgram();

//        bubble.display();
    }


    public void test(){
        RunTime runTime = new RunTime();
        Bubble bubble = new Bubble(100000);
        bubble.randomElements();
        runTime.startProgram();
        bubble.sort();
        runTime.endProgram();
    }
}
