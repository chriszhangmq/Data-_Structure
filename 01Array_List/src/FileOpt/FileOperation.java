package FileOpt;

import java.io.*;

/**
 * Created by Chris on 2019/10/25.
 */
public class FileOperation {
    public int ArrRowNum;
    public int ArrcolNum;
    public static void main(String[] args) {

    }

    /**
     * 二维数组写入txt
     * @param arr：二维数组
     * @param path：文件路径
     * @throws IOException
     * PS：数据之间使用 \t 分割
     */
    public void arrWrite2File(int[][] arr, String path) throws IOException {
        File file = new File(path);
        FileWriter writer = new FileWriter(file);
        System.out.println("===== WRITE DATA =====");
        for(int rowNum = 0; rowNum < arr.length; rowNum++){
            for(int colNum = 0; colNum < arr[0].length; colNum++){
                writer.write(arr[rowNum][colNum] + "\t");
            }
            writer.write("\r\n");
        }
        writer.close();
    }

    /**
     * 读取txt中的二维数组，  PS：数据之间使用 \t 分割
     * @param path：文件路径
     * @return
     * @throws IOException
     * PS：数组一定要是按照一定的规范输入，才能够读取
     */
    public int[][] ArrRead2File(String path) throws IOException {
        int[][] res ;
        int fileLinage = 0;
        int rowNum = 0;
        String lineData;
        File file = new File(path);
        BufferedReader bufreader = new BufferedReader(new FileReader(file));
        System.out.println("===== READ DATA =====");
        // 读取文件中，数据的行数
        while ((lineData = bufreader.readLine()) != null){
            String[] temp = lineData.split("\t");
            fileLinage++;
            // 记录数组的列数
            ArrcolNum = temp.length;
        }
        // 记录数组的行数
        ArrRowNum = fileLinage;
        bufreader.close();

        // 初始化数组
        res = new int[ArrRowNum][ArrcolNum];

        // 读取文件数据到数组
        bufreader = new BufferedReader(new FileReader(file));
        while ((lineData = bufreader.readLine()) != null){
            String[] temp = lineData.split("\t");
            int colNum = 0;
            for(String str : temp){
                res[rowNum][colNum++] =  Integer.parseInt(str);
            }
            rowNum++;
        }
        bufreader.close();
        return res;
    }
}
