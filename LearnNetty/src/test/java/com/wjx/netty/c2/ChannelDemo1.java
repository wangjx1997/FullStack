package com.wjx.netty.c2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author WangJX
 * @Date 2021/6/2 11:32
 * @Description
 */
@Slf4j
public class ChannelDemo1 {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("data.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            while (true) {
                int read = channel.read(byteBuffer);
                log.debug("读取的字节数: "+read);
                if (read == -1) { //  -1 是无内容    // 0本次没有读进内容 buffer写满了的情况
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
//            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
//            while (true) {
//                int read = channel.read(byteBuffer);
//                log.debug("读取的字节数: "+read);
//                if (read == 0) {
//                    break;
//                }
//
//            }
//        } catch (IOException e) {
//        }
    }
}
