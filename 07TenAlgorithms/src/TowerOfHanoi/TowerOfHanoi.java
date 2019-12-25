package TowerOfHanoi;

/**
 * 汉诺塔
 * （递归）
 * Created by Chris on 2019/12/16.
 */
public class TowerOfHanoi {
    int cycleIndex;
    public void hanoi(int num, char a, char b, char c){
        cycleIndex++;
        if(num == 1){
//            System.out.println("第 1 个盘从 " + a + "-->" + c);
        }
        else{
            hanoi(num - 1, a,c,b);
//            System.out.println("第 " + num + " 个盘从 " + a +"-->" + c);
            hanoi(num - 1, b,a,c);
        }
    }

    public static void main(String[] args) {
        TowerOfHanoi towerOfHanoi = new TowerOfHanoi();
        towerOfHanoi.hanoi(64,'A','B','C');
        System.out.println("SYSLE INDEX = " + towerOfHanoi.cycleIndex);
    }
}
