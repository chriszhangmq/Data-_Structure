package InsertSort;

import SystemParameter.RunTime;

/**
 * 插入排序：
 * 思想：（将数组分为有序、无序两部分，将无序数组中第一个数，插入到有序数组中）
 * 缺点：当无序数组最后一个元素较小时，需要运行的次数变多
 * Created by Chris on 2019/11/16.
 */
public class InsertSort {
    private int[] arr;
    private final int NUMELE;
    private int cycleIndex;

    public InsertSort(int NUMELE){
        this.NUMELE = NUMELE;
        arr = new int[NUMELE];
    }

    public void sort(){
        int insertVal;          //保存需要插入元素的值
        int insertIndex;        //有序数组，当前最后一个元素的索引

        for(int i = 1; i < arr.length; i++){
            insertVal = arr[i]; //保存需要插入元素的值
            insertIndex = i - 1;
            //遍历有序数组，寻找插入位置，找到位置后，需要将数组向后移动一个位置
            while (insertIndex >= 0 && (arr[insertIndex] > insertVal)){ //从小到大排序,有序数组是按照：从后向前遍历
                cycleIndex++;
                arr[insertIndex + 1] = arr[insertIndex];                //元素往后移动
                insertIndex--;
            }
            //将需要插入的元素，插入到有序数组中
            arr[insertIndex+1] = insertVal;
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
        InsertSort insertSort = new InsertSort(100000);
        insertSort.randomElements();
//        insertSort.arr = new int[]{0,200,54,7,-1,-41,15};
        runTime.startProgram();
        insertSort.sort();
        runTime.endProgram();

//        insertSort.display();
    }

    public void test(){
        RunTime runTime = new RunTime();
        InsertSort insertSort = new InsertSort(100000);
        insertSort.randomElements();
//        insertSort.arr = new int[]{0,200,54,7,-1,-41,15};
        runTime.startProgram();
        insertSort.sort();
        runTime.endProgram();
    }
}
