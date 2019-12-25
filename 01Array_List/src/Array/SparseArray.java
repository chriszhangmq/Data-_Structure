package Array;

import java.io.IOException;
import java.util.Scanner;
import FileOpt.*;
import SystemParameter.*;

/**
 * Created by Chris on 2019/10/25.
 */
public class SparseArray {
    private static int[][] arr;         //原数组
    private static int[][] sparseArr;   //稀疏数组
    private static int rowNum;
    private static int colNum;
    private static int nonzeroNum;      //稀疏数组中，非零元素的个数

    public static void main(String[] args) throws IOException {
        FileOperation fileOperation = new FileOperation();
        SparseArray spaArr = new SparseArray(3,3);
        RunTime runtime = new RunTime();

        // 1、输入原始数组
        spaArr.inputArray();
        runtime.startProgram();//开始计算运行时间
        // 2、转换成稀疏数组
        spaArr.sparse();
        // 3、保存稀疏数组
        fileOperation.arrWrite2File(sparseArr,"./data/sparseArr.txt");
        // 4、读取稀疏数组
        sparseArr = fileOperation.ArrRead2File("./data/sparseArr.txt");
        // 5、还原稀疏数组
        spaArr.restoreSparse();

        runtime.endProgram();//结束计算运行时间-显示
    }

    public SparseArray(int rowNum, int colNum){
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.nonzeroNum = 0;
        arr = new int[rowNum][colNum];
    }

    // 输入数组
    public void inputArray( ){
        System.out.println("=====INPUT ARRAY ["  + rowNum + " * " + colNum + "]: =====");
        Scanner scanner = new Scanner(System.in);
        int element;
        for(int i = 0; i < rowNum; i ++)
            for(int j = 0; j < colNum ; j++){
                element = scanner.nextInt();
                arr[i][j] = element;
            }

    }
    // 创建稀疏数组
    public void sparse(){
        System.out.println("====GENERATE SPARSE ARRAY====");
        for(int i = 0; i < rowNum; i++)
            for(int j = 0; j < colNum; j++){
                if(arr[i][j] != 0)
                    nonzeroNum ++;
            }
        // 定义稀疏数组
        sparseArr = new int[nonzeroNum + 1][3];
        // 生成稀疏数组
        sparseArr[0][0] = rowNum;
        sparseArr[0][1] = colNum;
        sparseArr[0][2] = nonzeroNum;
        int countNonzero = 0;
        for(int i = 0; i < rowNum; i++)
            for(int j = 0; j < colNum; j++){
                if(arr[i][j] != 0){
                    countNonzero++;
                    sparseArr[countNonzero][0] = i;
                    sparseArr[countNonzero][1] = j;
                    sparseArr[countNonzero][2] = arr[i][j];
                }
            }
        printArr(sparseArr);
    }

    // 还原稀疏数组
    public int[][] restoreSparse(){
        System.out.println("===== RESTORE SPARSE ARRAY =====");
        int[][] restor = new int[rowNum][colNum];
        for(int row = 1; row < sparseArr.length; row++)
                restor[sparseArr[row][0]][sparseArr[row][1]] = sparseArr[row][2];
        printArr(restor);
        return restor;
    }

    // 输出数组：用于显示数组元素
    private void printArr(int[][] arr){
        for(int[] row : arr){
            for(int ele : row)
                System.out.print(ele + " ");
            System.out.println();
        }
    }
}
