package HeapSort;

import SystemParameter.RunTime;

/**
 * 堆排序
 * 思路：
 *      1、先将无序数列排成大顶堆（或小顶堆）
 *      2、在将排好序的堆顶元素和现数组最后一个元素交换，然后再对除最后一个元素外的数组进行堆调整
 *      3、将堆调整后的数组的堆顶元素，和数组倒数第二个元素进行交换，再进行除末尾两个元素外的数组进行堆调整
 *      4、.....以此循环，直到所有元素排完
 * Created by Chris on 2019/12/8.
 */
public class HeapSort {
    /**
     * 堆排序
     */
    public void sort(int[] arr){
        System.out.println("======= Heap Sort =======");
        int temp = 0;
        //1、将原始序列，先排成大顶堆（从倒数第二层的非叶子节点开始排）
        //即：自底向上排
        for(int i = arr.length/2; i >= 0; i--){
            adjustHeap(arr,i,arr.length);
        }

        //2、将堆顶元素（最大或最小值）与当前未排序的数组的末尾值进行互换，再进行堆调整
        //即：最大值放到最后，，并且不再参与下次的堆调整
        for(int i = arr.length-1; i > 0; i--){
            //交换元素
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //再次进行堆调整：将剩余元素中，最大值调整到堆顶
            adjustHeap(arr,0,i);
        }
    }

    /**
     * 调整堆：使其成为大顶堆
     * 进行局部调整：调整过后，该局部树的最大值在其顶点
     */
    public void adjustHeap(int[] arr, int index,int currentArrLength){
        //保存当前传入的元素：父节点
        int temp = arr[index];
        //开始进行堆调整：获得左子树
        for(int i = 2*index + 1; i < currentArrLength; i = 2*i+1){
            //若左子树线于右子树
            if((i+1) < currentArrLength && (arr[i] < arr[i+1])){
                //则将右子树和父节点比较
                i++;
            }

            //将左右子树中较大者和父节点比较
            if(arr[i] > temp){
                //若子树大于父节点，父节点=较大的子节点
                arr[index] = arr[i];
                //记录较大子节点的索引
                index = i;
            }
            else{
                break;
            }
        }
        //将原本的父节点放到交换的子节点中去：实现父节点和子节点互换（或者是父节点不改变）
        arr[index] = temp;
    }


    public static void main(String[] args) {
//        int[] arr = {4,6,8,5,9};
        int NUM = 800000;
        int[] arr = new int[NUM];
        RunTime runTime = new RunTime();
        HeapSort heapSort = new HeapSort();

        //使用随机数进行测试
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * NUM);
        }

        //排序：并统计时间
        runTime.startProgram();
        heapSort.sort(arr);
        runTime.endProgram();

//        for(int i: arr){
//            System.out.print(i + "-");
//        }
    }
}
