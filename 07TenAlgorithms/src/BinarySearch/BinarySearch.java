package BinarySearch;

/**
 * 二分查找
 * （非递归形式）
 * 二分查找的数组必须是已经排好序的
 * Created by Chris on 2019/12/16.
 */
public class BinarySearch {
    public int search(int[] arr, int findData){
        int left = 0;
        int right = arr.length - 1;
        int mid = (left + right) / 2;

        while(left <= right){
            if(arr[mid] > findData){
                right = mid - 1;
            }
            else if(arr[mid] < findData){
                left = mid + 1;
            }
            else if(arr[mid] == findData){
                return mid;
            }
            mid = (left + right) / 2;
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] arr = {1,2,5,8,9,10,15,22,50};

        System.out.println(binarySearch.search(arr, 10));
    }

}
