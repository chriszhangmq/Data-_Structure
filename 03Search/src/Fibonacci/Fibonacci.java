package Fibonacci;

import java.util.Arrays;

/**
 * 斐波那契查找：无法理解（之后再来看）
 * Created by Chris on 2019/11/20.
 */
public class Fibonacci {

    /**
     * 获取fibonacci数列
     * @param num
     * @return
     */
    public int[] getFibonaciiArr(int num){
        int[] fibo = new int[num];
        fibo[0] = 1;
        fibo[1] = 1;
        for(int i = 2; i < num; i++){
            fibo[i] = fibo[i-1] + fibo[i-2];
        }
        return fibo;
    }

    public int search(int[] arr, int findVal){
        int high = arr.length - 1;
        int low = 0;
        int fiboIndex = 0;  //斐波那契数列分割值索引
        int mid = 0;
        int[] fiboArr = getFibonaciiArr(20);

        //获取斐波那契分割数的下标
        while (high > fiboArr[fiboIndex] - 1){
            fiboIndex++;
        }

        //fiboArr[fiboIndex]可能大于原来的数组，将原来的数组扩充，
        int[] arrTemp = Arrays.copyOf(arr,fiboArr[fiboIndex]);
        for(int i = high + 1; i < arrTemp.length; i++){
            arrTemp[i] = arr[high];
        }

        //查找数据
        while (low <= high){
            mid = low + fiboArr[fiboIndex - 1] - 1;
            if(findVal < arrTemp[mid]){         //查找的数在数组的左边
                high = mid - 1;
                fiboIndex--;
            }
            else if(findVal > arrTemp[mid]){    //查找的数在数组的右边
                low = mid + 1;
                fiboIndex -= 2;
            }
            else{       //找到元素
                if(mid <= high){
                    return mid;
                }
                else {
                    return high;
                }
            }
        }
        return -1;  //没找到元素
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        int[] arr = new int[]{1,2,51,84,63,214};

        int res = fibonacci.search(arr,214);

        System.out.println("res = " + res);
    }
}
