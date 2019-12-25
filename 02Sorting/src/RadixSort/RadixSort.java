package RadixSort;

import SystemParameter.RunTime;

/**
 * 基数排序：（桶排序）
 * Created by Chris on 2019/11/18.
 */
public class RadixSort {
    private int[] arr;
    private final int NUMELE;
    private int cycleIndex;

    public RadixSort(int NUMELE){
        this.NUMELE = NUMELE;
        arr = new int[NUMELE];
    }

    /**
     * 思路：
     * 1、寻找数组中最大的元素-->获取位数digits
     * 2、for循环，将原数组按照个、十、百、千...的顺序遍历digits遍，放到桶中（每次遍历完成后，都从桶中取出结果，放回原数组）
     */
    public void sort(){
        int[][] bucketArr = new int[10][arr.length];    //桶数组
        int[] bucketArrNum = new int[10];               //记录每个桶中的元素个数

        //1、获取最大的元素-->得到长度
        int max = 0;
        for(int i = 0; i <arr.length; i++ ){
            if(max < arr[i])
                max = arr[i];
        }

        //2、进行桶排序：需要遍历 的顺序遍历digits遍 次数组
        int maxLenght = (max + "").length();    //最大元素的位数
        int digits = 0;                             //元素某个位：个、十、百、千...
        int arrIndex = 0;                           //原数组的索引
        for(int i = 0, unit = 1; i < maxLenght; i++, unit *= 10){
            //3、元素放入桶中：按照个、十、百、千...的顺序遍历 digits 遍
            for(int j = 0; j < arr.length; j++){
                digits = arr[j] /unit % 10;
                bucketArr[digits][bucketArrNum[digits]] = arr[j];   //元素放入对应的桶中
                bucketArrNum[digits]++;                             //记录对应桶中的元素个数
            }
            //4、遍历一次数组后：将元素放回原数组,等待下次遍历
            for(int j = 0; j < bucketArrNum.length; j++){
                if(bucketArrNum[j] != 0){
                    for(int x = 0; x < bucketArrNum[j]; x++){
                        arr[arrIndex++] = bucketArr[j][x];
                    }
                }
                bucketArrNum[j] = 0;                                //桶数组取完后，需要清零桶数组的计数
            }
            arrIndex=0;                                             //清零原数组的索引值
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
        RadixSort radixSort = new RadixSort(100000);
        radixSort.randomElements();

        runTime.startProgram();
        radixSort.sort();
        runTime.endProgram();

//        radixSort.display();
    }

    public void test(){
        RunTime runTime = new RunTime();
        RadixSort radixSort = new RadixSort(100000);
        radixSort.randomElements();

        runTime.startProgram();
        radixSort.sort();
        runTime.endProgram();
    }
}
