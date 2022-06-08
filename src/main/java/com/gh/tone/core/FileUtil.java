package com.gh.tone.core;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {
    public static void main(String[] args) {
        String from = "/Users/echolau/Downloads/原谅他77次2017.HD1080P.mp4";
        String to = "/Users/echolau/Downloads/原谅他77次2017.HD1080P2.mp4";
        long s = System.currentTimeMillis();
        nioCopy(from, to);

//        try {
//            FileUtils.copyFile(new File(from), new File(to));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ioCopy("/Users/echolau/Desktop/惊天魔盗团/Now_You_See_Me_2.mp4", "/Users/echolau/Desktop/惊天魔盗团/Now_You_See_Me_22.mp4");
        System.out.println(System.currentTimeMillis() - s);
    }

    public static void ioCopy(String source, String target) {
        File s = new File(source);
        File t = new File(target);
        try (FileInputStream in = new FileInputStream(s); FileOutputStream out = new FileOutputStream(t)) {
            byte[] buff = new byte[10240];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zoreCopy(String s, String t) {

        try (FileChannel source = new FileInputStream(s).getChannel(); FileChannel dest = new FileOutputStream(t).getChannel()) {
            source.transferTo(0, source.size(), dest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nioCopy(String s, String t) {

        try (FileChannel in = new FileInputStream(s).getChannel(); FileChannel out = new FileOutputStream(t).getChannel();) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (in.read(buffer) != -1) {
                out.write(buffer);
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(String from, String to) throws IOException {
        File source = new File(from);
        File dest = new File(to);
        FileChannel in = null, out = null;
        try {
            in = new FileInputStream(source).getChannel();
            out = new FileOutputStream(dest).getChannel();
            long size = in.size();
            MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
            out.write(buf);
            in.close();
            out.close();
//            source.delete();//文件复制完成后，删除源文件
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
    }
}
