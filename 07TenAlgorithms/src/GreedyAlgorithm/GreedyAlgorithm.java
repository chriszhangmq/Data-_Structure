package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法
 * 思想：每次遍历完所有可能的结果，选择最大值，这样的得到的数据不一定是最优的（可能存在多种结果），但是一定是接近最优的结果
 * Created by Chris on 2019/12/19.
 */
public class GreedyAlgorithm {
    /**
     * 贪心算法
     * @param broadcasts：需要进行检索的电台集合，每个电台都覆盖特定的区域
     * @param allAreas：所有的地区集合
     * @return： 返回最佳的电台名称：B2, B3, B4, B5,
     */
    public ArrayList<String> fit(HashMap<String, HashSet<String>> broadcasts, HashSet<String> allAreas){
        //存放未覆盖地区，和当前电台覆盖地区的交集
        HashSet<String> intersection = new HashSet<String>();
        //当前遍历的电台能够覆盖的区域
        HashSet<String> areas;
        //覆盖地区最大的电台：键值--索引
        String maxB = null;
        //存放结果：电台的键值
        ArrayList<String> res = new ArrayList<String>();

        while(allAreas.size() != 0){
            maxB = null;
            //遍历所有电台，覆盖地区最大的电台的键值
            for(String B : broadcasts.keySet()){
                intersection.clear();
                areas = broadcasts.get(B);
                intersection.addAll(areas);
                //求出该电台和当前剩余的为覆盖地区的交集
                intersection.retainAll(allAreas);
                //比较当前电台覆盖的区域 和 当前存在的最大覆盖区域的 大小
                if(intersection.size() > 0 && (maxB == null || intersection.size() > broadcasts.get(maxB).size())){
                    maxB = B;
                }
            }
            //遍历一次之后，存放结果，直到所有地区被完全覆盖
            if(maxB != null){
                res.add(maxB);
                //删除已经覆盖区域
                allAreas.removeAll(broadcasts.get(maxB));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm();

        //所有电台，和其包含的地区
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //所有地区
        HashSet<String> allAreas = new HashSet<String>();

        //添加电台数据
        HashSet<String>  hashSet01 = new HashSet<String>();
        hashSet01.add("Beijing");
        hashSet01.add("Shanghai");
        hashSet01.add("Tianjin");
        HashSet<String>  hashSet02 = new HashSet<String>();
        hashSet02.add("Guangzhou");
        hashSet02.add("Beijing");
        hashSet02.add("Shenzhen");
        HashSet<String>  hashSet03 = new HashSet<String>();
        hashSet03.add("Chengdu");
        hashSet03.add("Shanghai");
        hashSet03.add("Hangzhou");
        HashSet<String>  hashSet04 = new HashSet<String>();
        hashSet04.add("Shanghai");
        hashSet04.add("Tianjin");
        HashSet<String>  hashSet05 = new HashSet<String>();
        hashSet05.add("Hangzhou");
        hashSet05.add("Dalian");

        broadcasts.put("B1",hashSet01);     //添加电台名B1
        broadcasts.put("B2",hashSet02);
        broadcasts.put("B3",hashSet03);
        broadcasts.put("B4",hashSet04);
        broadcasts.put("B5",hashSet05);

        //添加地区数据
        allAreas.add("Beijing");
        allAreas.add("Shanghai");
        allAreas.add("Tianjin");
        allAreas.add("Guangzhou");
        allAreas.add("Shenzhen");
        allAreas.add("Chengdu");
        allAreas.add("Hangzhou");
        allAreas.add("Dalian");

        //使用贪心算法获取结果
        ArrayList<String> res = greedyAlgorithm.fit(broadcasts, allAreas);

        System.out.println("===== SELECT BROADCASTS =====");
        for(String str : res){
            System.out.print(str + ", ");
        }
    }

}
