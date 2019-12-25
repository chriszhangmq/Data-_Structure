package KMP;

/**
 * Created by Chris on 2019/12/18.
 */
public class KMP {
    //计算KMP的部分匹配表
    public int[] next(String source){
        int[] next = new int[source.length()];
        next[0] = 0;
        //index：遍历数组 ，prefix：记录前缀字符和后缀字符相等的个数
        for(int index = 1, prefix = 0; index < source.length(); index++){
            //计算前缀、后缀共有元素的最大长度
            while(prefix > 0 && source.charAt(index) != source.charAt(prefix)){
                prefix = next[prefix - 1];
            }
            if(source.charAt(index) == source.charAt(prefix)){
                prefix++;
            }
            next[index] = prefix;
        }
        return next;
    }

    //KMP搜索算法
    public int search(String source, String target, int[] next){
        for(int index = 0, nextIndex = 0; index < source.length(); index++){
            //nextIndex：记录已经匹配好的字符串长度，当出现不匹配字符时，需要回溯
            while(nextIndex > 0 && source.charAt(index) != target.charAt(nextIndex)){
                nextIndex = next[nextIndex-1];
            }
            if(source.charAt(index) == target.charAt(nextIndex)){
                nextIndex++;
            }
            if(nextIndex == target.length()){
                return index - nextIndex + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String sourceStr = "abc abcda esx";
        String targetStr = "abcda";

        KMP kmp = new KMP();
        int[] next = kmp.next(targetStr);
        for(int i : next){
            System.out.print(i + "--");
        }
        System.out.println();
        System.out.println(kmp.search(sourceStr, targetStr, next));
    }
}
