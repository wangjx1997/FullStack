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
//            System.out.println(channel.position());
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            while (true) {
                int read = channel.read(byteBuffer);
                if (read == -1) { //  -1 是无内容    // 0本次没有读进内容 buffer写满了的情况
                    break;
                }
                log.debug("读取的字节数: "+read);
//                ByteBufferUtil.debugAll(byteBuffer);
                byteBuffer.flip(); //切换读模式
//                ByteBufferUtil.debugAll(byteBuffer);
                while (byteBuffer.hasRemaining()) { // 是否还有剩余未读数据
                    byte b = byteBuffer.get();
                    log.debug("实际字节:"+(char) b);
                }
                byteBuffer.clear(); //切换写模式

            }
//            System.out.println(channel.position());
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
