package com.wjx.netty.c2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Author WangJX
 * @Date 2021/8/9 11:03
 * @Description
 */
public class TestFileChannelTransferTo {
    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("data.txt").getChannel();
             FileChannel to = new FileOutputStream("to.txt").getChannel()
        ) {
            // 效率高  底层会利用操作系统的零拷贝进行优化 一次2g  ---》  from.transferTo(0, size, to);
            long size = from.size();
            for (long left = size; left > 0; ) {
                /*// 每次拷贝起始位置
                long position = size - left;
                // 每次拷贝的大小
                long transferTo = from.transferTo(position, size, to);
                // left 剩余量大小
                left -= transferTo;*/

                left -= from.transferTo((size - left), size, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
