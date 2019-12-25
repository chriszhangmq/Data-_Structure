package KMP;

/**
 * 字符串匹配
 * （暴力查找）
 * Created by Chris on 2019/12/18.
 */
public class ViolenceSearch {
    /**
     * 暴力搜索，效率低下
     * @param sourceStr
     * @param targetStr
     * @return 返回匹配字符串的第一个索引位置
     */
    public int search(String sourceStr, String targetStr){
        char[] sourceStrArr = sourceStr.toCharArray();
        char[] targetStrArr = targetStr.toCharArray();

        int sourceStrLen = sourceStrArr.length;
        int targetStrLen = targetStrArr.length;

        int sourceIndex = 0;
        int targetIndex = 0;

        while(sourceIndex < sourceStrLen && targetIndex < targetStrLen){
            if(sourceStrArr[sourceIndex] == targetStrArr[targetIndex]){
                sourceIndex++;
                targetIndex++;
            }
            else{
                sourceIndex = sourceIndex - (targetIndex - 1);
                targetIndex = 0;
            }
        }
        if(targetIndex == targetStrLen){
            return sourceIndex - targetIndex;
        }
        else{
            return -1;
        }

    }

    public static void main(String[] args) {
        String sourceStr = "abcabcdesx";
        String targetStr = "abcd";
        ViolenceSearch violenceSearch = new ViolenceSearch();

        System.out.println(violenceSearch.search(sourceStr, targetStr));
    }
}

