package BinarySearch;

import QuickSort.QuickSort;
import SystemParameter.RunTime;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * Created by Chris on 2019/11/19.
 */
public class BinarySearch {
    private int[] arr;
    private List<Integer> res = new ArrayList<Integer>();    //存放结果：检索的序号
    private int NUMELE;
    private int cycleIndex;     //循环次数

    public BinarySearch(int NUMELE){
        this.NUMELE = NUMELE;
        arr = new int[NUMELE];
    }

    public List<Integer> searche(int[] arr, int left, int right, int findVal){
        cycleIndex++;
        //如果满足：终止条件，left > right，退出递归
        if(left > right){
            return new ArrayList<Integer>();
        }

        int midIndex = (left + right) / 2;
        int midVal = arr[midIndex];
        //右递归查找
        if(findVal > midVal){
            return searche(arr,midIndex + 1,right,findVal);
        }
        //左递归查找
        else if(findVal < midVal){
            return searche(arr,left,midIndex - 1,findVal);
        }
        //找到相应的元素：继续搜索该元素左右两边的相同元素
        else{
            //左查找
            int tempIndex = midIndex - 1;
            while (true){
                if(tempIndex < 0 || arr[tempIndex] != findVal){
                    break;
                }
                res.add(tempIndex);
                tempIndex--;
                cycleIndex++;
            }
            //插入第一次找到的值得索引
            res.add(midIndex);
            //右查找
            tempIndex = midIndex + 1;
            while (true){
                if(tempIndex > arr.length || arr[tempIndex] != findVal){
                    break;
                }
                res.add(tempIndex);
                tempIndex++;
                cycleIndex++;
            }
            return res;
        }
    }

    public void randomElements(){
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * 100);
        }
    }

    public void display(){
        System.out.println("==========SORT RESULT===========");
        for(int i  = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("==========SEARCH RESULT===========");
        System.out.println(res);
        System.out.println("CYCLEINDEX = " + cycleIndex);
    }

    public static void main(String[] args) {
        int numEle = 1000;
        RunTime runTime = new RunTime();
        BinarySearch binarySearch = new BinarySearch(numEle);
        QuickSort quickSort = new QuickSort(numEle);

        //生成随机数据
        binarySearch.randomElements();

        //数据：进行排序——使用快速排序
        quickSort.sort(binarySearch.arr,0,binarySearch.arr.length-1);

        //数据查找
        runTime.startProgram();
        binarySearch.searche(binarySearch.arr,0,binarySearch.arr.length-1,24);
        runTime.endProgram();

        //打印数据
        binarySearch.display();
    }
}
