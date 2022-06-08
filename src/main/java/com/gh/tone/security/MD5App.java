package com.gh.tone.security;

import java.io.*;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jenny on 2017/6/2.
 */
public class MD5App {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        byte[] input = "md5".getBytes();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(input), md5);
        //流输入
        dis.read(input, 0, input.length);
        //摘要
        byte[] output = dis.getMessageDigest().digest();
        //关闭流
        dis.close();

        DigestOutputStream dos = new DigestOutputStream(new ByteArrayOutputStream(), md5);
        dos.write(input, 0, input.length);
        output = dis.getMessageDigest().digest();
        dos.flush();
        dos.close();

    }
}
