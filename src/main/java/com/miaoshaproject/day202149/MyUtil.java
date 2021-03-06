package com.miaoshaproject.day202149;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName MyUtil
 * @Description TODO
 * @Author xbt
 * @Date 2021/4/9
 * @Version 1.0
 **/
public class MyUtil {
    private ByteBuffer buffer;

    private MyUtil(){
        throw new AssertionError();
    }

    public static void fileCopy(String source,String target) throws IOException {
        try(InputStream in = new FileInputStream(source)) {
            try(OutputStream out = new FileOutputStream(target)){
                byte[] buffer = new byte[4096];
                int bytesToRead;
                while ((bytesToRead = in.read())!=-1) {
                    out.write(buffer, 0, bytesToRead);
                }
            }

        }
    }
    public static void fileCopyNIO(String source,String target) throws IOException {
        try(FileInputStream in =new FileInputStream(source)){
            try(FileOutputStream out = new FileOutputStream(target)){
                FileChannel inChannel = in.getChannel();
                FileChannel outChannel = out.getChannel();
                ByteBuffer buffer =ByteBuffer.allocate(4096);
                while (inChannel.read(buffer)!=-1){
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }

            }
        }
    }
}
