package MergeSort;

import SystemParameter.RunTime;

import java.util.Arrays;

/**
 * 归并排序：自顶向下的归并排序，分左、右边进行排序
 * 思想：采用分治的思想，先将原数组arr，分成几份，再分别进行组合
 * Created by Chris on 2019/11/17.
 */
public class MergeSort {
    private int[] arr;
    private int[] temp;
    private final int NUMELE;
    private int cycleIndex;

    public MergeSort(int NUMELE){
        this.NUMELE = NUMELE;
        arr = new int[NUMELE];
        temp = new int[NUMELE];
    }

    /**
     * 归并排序：分数组 + 合并数组
     * 思路：两两合并 --> 四四合并 --> 八八合并 --> ...
     * @param arr
     * @param left
     * @param right
     */
    public void sort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            //左边数组分割：递归
            sort(arr,left,mid,temp);
            //右边数组分割：递归
            sort(arr,mid + 1,right,temp);
            //合并数组：在完成上述的递归分割之后，才开始合并数组
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并数组
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    private void merge(int[] arr, int left, int mid ,int right,int[] temp){
        int leftArrIndex = left;       //左边数组的初始索引
        int rightArrIndex = mid + 1;   //右边数组的初始索引
        int tempIndex = 0;              //temp[]的初始索引

        //1、先合将原数组左右两边能够合并的元素合并起来，放到temp[]中
        while (leftArrIndex <= mid && rightArrIndex <= right){
            //左、右两边从头开始，挑选较小的元素，放到temp[]中
            if(arr[leftArrIndex] <= arr[rightArrIndex]){
                temp[tempIndex] = arr[leftArrIndex];
                leftArrIndex++;
                tempIndex++;
            }
            else {
                temp[tempIndex] = arr[rightArrIndex];
                rightArrIndex++;
                tempIndex++;
            }
        }

        //2、再将左边或右边数组中剩余的元素合并起来，放到temp[]中
        while (leftArrIndex <= mid){
            temp[tempIndex] = arr[leftArrIndex];
            leftArrIndex++;
            tempIndex++;
        }
        while (rightArrIndex <= right){
            temp[tempIndex] = arr[rightArrIndex];
            rightArrIndex++;
            tempIndex++;
        }

        //3、将temp[]中的结果，填充回原数组arr[]
        //第一次合并：tempLeft = 0，righ=1 ； tempLeft = 2，righ=3 ；tempLeft = n-2，righ=n-1 ；
        //......
        //最后一次合并：tempLeft = 0，righ=n-1 ；
        tempIndex = 0;
        int tempLeft = left;        //存储要放到原数组的起始位置
        while (tempLeft <= right){
            arr[tempLeft] = temp[tempIndex];
            tempLeft++;
            tempIndex++;
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
        System.out.println();
    }

    public static void main(String[] args) {
        RunTime runTime = new RunTime();
        MergeSort mergeSort = new MergeSort(100000);
        mergeSort.randomElements();
//        System.out.println(Arrays.toString(mergeSort.arr));

        runTime.startProgram();
        mergeSort.sort(mergeSort.arr,0,mergeSort.arr.length - 1, mergeSort.temp);
        runTime.endProgram();

        mergeSort.display();
    }

    public void test(){
        RunTime runTime = new RunTime();
        MergeSort mergeSort = new MergeSort(100000);
        mergeSort.randomElements();
        runTime.startProgram();
        mergeSort.sort(mergeSort.arr,0,mergeSort.arr.length - 1, mergeSort.temp);
        runTime.endProgram();

    }
}
