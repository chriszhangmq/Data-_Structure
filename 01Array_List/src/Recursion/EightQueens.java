package Recursion;

/**
 * 八皇后问题
 * 解决方法：使用递归（回溯）
 * 思路: 每放一个皇后都需判断与前面皇后的关系：同列、同斜线？
 * 解决方式：使用一个一维数组存储一次解：
 *           一维数组的元素序号表示：第几个皇后
 *           一维数组的元素值表示：该皇后放在第几个位置
 *           （本质：一位数组可以当成一个平面坐标系使用，可用于判断元素是否在同一直线上，详见：isPass()方法）
 * Created by Chris on 2019/11/15.
 */
public class EightQueens {
    private int[] queens;       //结果
    private final int queenNum; //多少个皇后
    public int resultNum;
    public int cycleIndex;     //循环次数

    public EightQueens(int queenNum){
        this.queenNum = queenNum;
        queens = new int[queenNum];
    }

    /**
     * 求解八皇后问题：使用递归（回溯算法）
     */
    public void getSolution(int queenNumber){
        //得到结果
        if(queenNumber == queenNum){
            resultNum++;
            display();
            return;
        }
        //从第一个皇后开始，摆放原则：第一行第1个位置开始 -> 第二行第1个位置 -> 第二行第2个位置 -> 第二行第3个位置.。。
        for(int i = 0; i < queenNum; i++){
            queens[queenNumber] = i;
            if(isPass(queenNumber)){
                getSolution(queenNumber+1);
            }
        }
    }

    /**
     * 判断第queenNumber 个皇后是否符合要求：
     * 要求：列、斜对角线与前面的皇后不冲突
     *              （1）在同一列：
     *              （2）在同一对角线: 相当于判断两个皇后是否在y=x的直线上
     * @param queenNumber
     * @return
     */
    private boolean isPass(int queenNumber){
        for(int i = 0; i < queenNumber; i++){
            cycleIndex++;
            if(queens[queenNumber] == queens[i] ||                                             //在同一列
                    (Math.abs(queenNumber - i) == Math.abs(queens[queenNumber] - queens[i])))  //在同一对角线
                return false;
        }
        return  true;
    }
    /**
     * 打印一次结果
     */
    private void display(){
        for(int i = 0; i < queenNum; i++){
            System.out.print(queens[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens(8);
        eightQueens.getSolution(0); //从第1个皇后开始摆放
        System.out.println("CYCLE INDEX = " + eightQueens.cycleIndex + '\n' + "RESULT NUMBER = " + eightQueens.resultNum);
    }
}
