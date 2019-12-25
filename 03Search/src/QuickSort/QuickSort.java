package QuickSort;

import SystemParameter.RunTime;


/**
 * 快速排序
 * Created by Chris on 2019/11/17.
 */
public class QuickSort {
    private int[] arr;
    private final int NUMELE;
    private int cycleIndex;

    public QuickSort(int NUMELE){
        this.NUMELE = NUMELE;
        arr = new int[NUMELE];
    }

    public void sort(int[] arr, int left, int right){
        int l = left;   //当前数组最左边元素索引
        int r = right;  //当前数组最右边元素索引
        int privot = arr[(left + right) / 2];   //数组的中间元素，作为切分点
        int temp = 0;
        //while里面：进行一次privot左右两边的元素调整
        while(l < r){
            //1、找到一个arr，左边>privot 和 右边<privot的元素索引
            //左边>privot
            while (arr[l] < privot){
                l++;
            }
            //右边<privot
            while (arr[r] > privot){
                r--;
            }
            //l>=r，说明privot左右两边的元素满足：左边全部<privot，右边全部>privot
            if(l >= r){
                break;
            }
            //2、交换两个元素
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //3.1、交换后，如果是privot和arr[l]元素交换（说明，此时privot右边的元素全部大于privot）
            // 需要对右边索引r前移一位：保证privot这个参考点不动
            if(arr[l] == privot){
                r--;
            }
            //3.2、交换后，如果是privot和arr[r]元素交换（说明，此时privot左边的元素全部大于privot）；
            // 需要对右边索引l后移一位：保证privot这个参考点不动
            if(arr[r] == privot){
                l++;
            }
        }

        //4、若l = r，说明已经遍历完一次数组，需要将l：后移一位，r：前移一位
        //否则后面的递归调用时，会出现栈溢出。
        //后面的递归调用：不会对privot进行排序，只会对privot两边的元素进行排序
        if(l==r){
            l++;
            r--;
        }
        //5、进行递归：对privot两边的元素进行排序
        //对privot左边，进行递归
        if(left < r){
            sort(arr,left,r);
        }
        //对privot右边，进行递归
        if(right > l){
            sort(arr,l,right);
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
        QuickSort quickSort = new QuickSort(100000);
        quickSort.randomElements();
//        quickSort.arr = new int[]{1,2,3,5,4,6,7};

        runTime.startProgram();
        quickSort.sort(quickSort.arr,0,quickSort.arr.length-1);
        runTime.endProgram();

//        quickSort.display();
    }

    public void test(){
        RunTime runTime = new RunTime();
        QuickSort quickSort = new QuickSort(100000);
        quickSort.randomElements();

        runTime.startProgram();
        quickSort.sort(quickSort.arr,0,quickSort.arr.length-1);
        runTime.endProgram();
    }


}
