package ShellSort;

import SystemParameter.RunTime;
import jdk.nashorn.tools.Shell;

/**
 * 希尔排序：有两种（1）交换法 （2）位移法
 * Created by Chris on 2019/11/16.
 */
public class ShellSort {
    private int[] arr;
    private final int NUMELE;
    private int cycleIndex;

    public ShellSort(int NUMELE){
        this.NUMELE = NUMELE;
        arr = new int[NUMELE];
    }

    /**
     * 交换法排序
     * 思路：（1）步长变更
     *       （2）确定每个步长下的分组
     *       （3）对每个分组进行排序：按照元素大小，遍历、交换位置
     */
    public void sortExch(){
        int temp;   //暂时存储交换的元素

        //1、步长:stepSize，每次对半减小
        for(int stepSize = arr.length/2; stepSize > 0; stepSize /= 2){
            //2、不同步长下的分组：i表示每组第2个元素的索引，无论arr元素个数=奇、偶数，都可以完成所有数据的排序
            for(int i = stepSize; i < arr.length; i++){
                //3、对每个分组进行排序：每组元素之间的间隔为stepSize，从后往前遍历数组
                for(int j = i - stepSize; j >= 0; j -= stepSize){
                    if(arr[j] > arr[j+stepSize]){
                        temp = arr[j];
                        arr[j] = arr[j+stepSize];
                        arr[j+stepSize] = temp;
                    }
                }
            }
        }
    }

    /**
     * 位移法排序：
     * 本质：将交换法中的交换，改为插入排序
     */
    public void sortOffset(){
        int temp;
        int insertVal;      //保存需要插入元素的值
        int insertIndex;    //需要插入元素的索引
        //1、步长
        for(int stepSize = arr.length/2; stepSize > 0; stepSize /=2){
            //2、分组：从每一组的第2个元素开始，使用插入排序
            for(int i = stepSize; i < arr.length; i++){
                //3、插入排序
                insertVal = arr[i]; //保存需要插入元素的值
                insertIndex = i;    //需要插入元素的索引
                //若，当前要插入的值 < 有序数组最后一个元素，才进行元素后移
                if(arr[insertIndex] < arr[insertIndex - stepSize]){
                    while (insertIndex - stepSize >= 0 && (insertVal < arr[insertIndex - stepSize])){
                        arr[insertIndex] = arr[insertIndex - stepSize];
                        insertIndex -= stepSize;
                    }
                    //在有序数组最后面添加当前要插入的值
                    arr[insertIndex] = insertVal;
                }
                //当前要插入的值 >= 有序数组最后一个元素：不需要修改，因为 insertVal = arr[i],要插入的元素就在该位置
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
        System.out.println();
    }

    public static void main(String[] args) {
        RunTime runTime = new RunTime();
        ShellSort shellSort = new ShellSort(100000);


        /**
         * 交换法
         */
        System.out.println("\n=======交换法=====");
        shellSort.randomElements();
//        shellSort.display();
        runTime.startProgram();
        shellSort.sortExch();
        runTime.endProgram();
//        shellSort.display();

        /**
         * 位移法
         */
        System.out.println("\n=======位移法=====");
        shellSort.randomElements();
//        shellSort.display();
        runTime.startProgram();
        shellSort.sortOffset();
        runTime.endProgram();
        shellSort.display();
    }

    public void test(){
        RunTime runTime = new RunTime();
        ShellSort shellSort = new ShellSort(100000);
        /**
         * 位移法
         */
        shellSort.randomElements();
        runTime.startProgram();
        shellSort.sortOffset();
        runTime.endProgram();
    }
}
