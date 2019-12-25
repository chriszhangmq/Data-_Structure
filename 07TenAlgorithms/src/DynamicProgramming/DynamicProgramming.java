package DynamicProgramming;

import java.util.Arrays;

/**
 * 动态规划
 * 仅考虑0-1背包问题
 * Created by Chris on 2019/12/17.
 */
public class DynamicProgramming {

    public int optimalSolution(int[] itemWeight, int[] itemValue, int backpackCapacity){
        int res = 0;
        int itemCount = itemValue.length;                                               //物品数量

        //创建表格，存储每个容量下能存放的最大值。行：物品重量， 列：背包容量（0，1，2，3...backpackCapacity）
        //该表格多出一行、一列（用于计算每个单元格的数据）
        int[][] maxValueTable = new int[itemValue.length + 1][backpackCapacity + 1];

        //初始化表格：第一行和第一列都为0
        for(int i = 0; i < backpackCapacity; i++){
            maxValueTable[0][i] = 0;
        }
        for(int i = 0; i < itemCount; i++){
            maxValueTable[i][0] = 0;
        }

        //使用动态规划的公式计算 : 行：物品重量，  列：背包容量（0，1，2，3...backpackCapacity）
        for(int rowIndex = 1; rowIndex < itemCount + 1; rowIndex++){
            for(int columnIndex = 1; columnIndex < backpackCapacity + 1; columnIndex++){
                //当前物品超重，无法放入背包，直接使用同列的上一层的值
                if(itemWeight[rowIndex - 1] > columnIndex){
                    maxValueTable[rowIndex][columnIndex] = maxValueTable[rowIndex - 1][columnIndex];
                }
                //当前物品可以放入背包：其实这里可以直接使用 Math.max()来计算，也就一行代码
                else{
                    maxValueTable[rowIndex][columnIndex] = Math.max(maxValueTable[rowIndex - 1][columnIndex], itemValue[rowIndex - 1] +
                            maxValueTable[rowIndex - 1][columnIndex - itemWeight[rowIndex - 1]]);
                    //下面的代码和上面一句等价
//                    if(maxValueTable[rowIndex - 1][columnIndex] <
//                            itemValue[rowIndex - 1] + maxValueTable[rowIndex - 1][columnIndex - itemWeight[rowIndex - 1]]){
//                        //计算该容量为：columnIndex的背包，可放入的最大总价值
//                        maxValueTable[rowIndex][columnIndex] =  itemValue[rowIndex - 1] +
//                                maxValueTable[rowIndex - 1][columnIndex - itemWeight[rowIndex - 1]];
//                    }
//                    else{
//                        maxValueTable[rowIndex][columnIndex] = maxValueTable[rowIndex - 1][columnIndex];
//                    }
                }
            }
        }

        //显示结果
        System.out.println("不同容量的背包中，能放入的最大价值：");
        for(int[] i : maxValueTable){
            System.out.println(Arrays.toString(i));
        }
        return res;
    }

    public static void main(String[] args) {
        int[] itemWeight = {1,4,3};          //每件物品的重量
        int[] itemValue = {1500, 3000, 2000};//每物品的价值
        int backpackCapacity = 4;            //背包总容量

        DynamicProgramming dynamicProgramming = new DynamicProgramming();

        dynamicProgramming.optimalSolution(itemWeight,itemValue,backpackCapacity);
    }
}
