package SelectSort;


import SystemParameter.RunTime;

/**
 * 选择排序：
 * 思想：(找到剩余元素中，最小的元素，插到有序数组的最后面)
 *       从第1个元素开始，找到最小的元素，和arr[0]交换；
 *       从第2个元素开始，找到最小的元素，和rr[1]交换；
 *       从第3个元素开始，找到最小的元素，和arr[2]交换；
 *       。。。
 *       从第n-2个元素开始，找到最小的元素，和arr[n-2]交换；
 *       一共需要找n-1次最小值
 * Created by Chris on 2019/11/16.
 */
public class SelectSort {
    private int[] arr;
    private final int NUMELE;
    private int cycleIndex;

    public SelectSort(int NUMELE){
        this.NUMELE = NUMELE;
        arr = new int[NUMELE];
    }

    public void sort(){
        int min;        //最小值
        int minIndex;   //最小值的索引值

        for(int  i = 0; i < arr.length; i++){
            min = arr[i];
            minIndex = i;
            for(int j = i + 1; j < arr.length; j++ ){   //找到第i个 -- 最后一个元素中，最小的元素
                cycleIndex++;
                if(min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if(minIndex != i){          //最小元素与第i个元素互换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    public void randomElements(){
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * NUMELE);
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
        SelectSort selectSort = new SelectSort(100000);
        selectSort.randomElements();

        runTime.startProgram();
        selectSort.sort();
        runTime.endProgram();

        selectSort.display();
    }

    public void test(){
        RunTime runTime = new RunTime();
        SelectSort selectSort = new SelectSort(100000);
        selectSort.randomElements();

        runTime.startProgram();
        selectSort.sort();
        runTime.endProgram();
    }
}
