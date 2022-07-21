package com.wjx.utiltest;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author WangJX
 * @Date 2022/6/17 13:46
 * @Description 工具测试
 */
public class lunzi {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);
        groupListByQuantity(list, 2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(4);
        groupListByQuantity(list2, 1);
/*//获取并集
        Collection<Integer> unionList = CollectionUtils.union(list, list2);
        System.out.println(unionList);

//获取交集
        Collection<Integer> intersectionList = CollectionUtils.intersection(list, list2);
        System.out.println(intersectionList);

//获取交集的补集
        Collection<Integer> disjunctionList = CollectionUtils.disjunction(list, list2);
        System.out.println(disjunctionList);

//获取差集
        Collection<Integer> subtractList = CollectionUtils.subtract(list, list2);
        System.out.println(subtractList);*/
    }



    public static <T> void groupListByQuantity(List<T> list, int quantity) {
        int count = 0;
        int i = 0;
        List<T> arrayList;
        while (count < list.size()) {
            arrayList = list.subList(count, Math.min((count + quantity), list.size()));
            System.out.println("第{"+(++i)+"}次插入-本次笔数{"+arrayList.size()+"}");
            System.out.println(arrayList);
            count += quantity;
        }

    }
}
