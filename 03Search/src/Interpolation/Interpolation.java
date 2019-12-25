package Interpolation;

import QuickSort.QuickSort;
import SystemParameter.RunTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 插值查找：二分查找的改进版本，一次定位到查找的元素
 * Created by Chris on 2019/11/19.
 */
public class Interpolation {
    private int[] arr;
    private List<Integer> res = new ArrayList<>();
    private int NUMELE;

    public Interpolation(int NUMELE){
        this.NUMELE = NUMELE;
        arr = new int[NUMELE];
    }

    public List<Integer> search(int[] arr, int left, int right, int findVal){
        //提前结束查找
        if(left > right || findVal < arr[left] || findVal > arr[right]){
            return new ArrayList<Integer>();
        }

        //改进公式：定位查找的元素
        int midIndex = left + (findVal - arr[left]) * (right - left) / (arr[right] - arr[left]);
        int midVal = arr[midIndex];

        if(findVal > midVal){
            return search(arr,midIndex + 1,right,findVal);
        }
        else if(findVal < midVal){
            return search(arr,left,midIndex - 1,findVal);
        }
        else {
            //左查找
            int tempIndex = midIndex - 1;
            while (true){
                if(tempIndex < 0 || arr[tempIndex] != findVal){
                    break;
                }
                res.add(tempIndex);
                tempIndex--;
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
    }

    public static void main(String[] args) {
        int numEle = 1000;
        RunTime runTime = new RunTime();
        Interpolation interpolation = new Interpolation(numEle);
        QuickSort quickSort = new QuickSort(numEle);

        //生成随机数据
        interpolation.randomElements();

        //数据：进行排序——使用快速排序
        quickSort.sort(interpolation.arr,0,interpolation.arr.length-1);

        //数据查找
        runTime.startProgram();
        interpolation.search(interpolation.arr, 0,interpolation.arr.length - 1, 1);
        runTime.endProgram();

        //打印数据
        interpolation.display();
    }
}
