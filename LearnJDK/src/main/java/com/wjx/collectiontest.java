package com.wjx;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author WangJX
 * @Date 2022/12/12 10:47
 * @Description
 */
public class collectiontest {


    public static void main(String[] args) {
        List<Object> all = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            all.add(i);
        }

        groupListByQuantity(all,100);
    }


    public static void groupListByQuantity(List<Object> list, int quantity) {
        int count = 0;
        int i = 0;
        int size = list.size();
        List<Object> arrayList;
        while (count < size) {
            arrayList = list.subList(count, Math.min((count + quantity), size));
            System.out.println("第"+(++i)+"次插入-本次笔数"+arrayList.size());
            System.out.println(arrayList.toString());
            count += quantity;
        }

    }
}
