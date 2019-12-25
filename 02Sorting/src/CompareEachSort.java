import BubbleSort.Bubble;
import InsertSort.InsertSort;
import MergeSort.MergeSort;
import QuickSort.QuickSort;
import RadixSort.RadixSort;
import SelectSort.SelectSort;
import ShellSort.ShellSort;

/**
 * 比较各个排序算法的排序时间：
 * Created by Chris on 2019/11/18.
 */
public class CompareEachSort {

    public static void main(String[] args) {
        int NUMELE = 100000;

        Bubble bubble = new Bubble(NUMELE);
        InsertSort insertSort = new InsertSort(NUMELE);
        MergeSort mergeSort = new MergeSort(NUMELE);
        QuickSort quickSort = new QuickSort(NUMELE);
        RadixSort radixSort = new RadixSort(NUMELE);
        SelectSort selectSort = new SelectSort(NUMELE);
        ShellSort shellSort = new ShellSort(NUMELE);

        System.out.println("BUBBLE SORT: ");
        bubble.test();
        System.out.println("INSERT SORT: ");
        insertSort.test();
        System.out.println("MERGE SORT: ");
        mergeSort.test();
        System.out.println("QUICK SORT: ");
        quickSort.test();
        System.out.println("RADIX SORT: ");
        radixSort.test();
        System.out.println("SELECT SORT: ");
        selectSort.test();
        System.out.println("SHELL SORT: ");
        shellSort.test();
    }
}
