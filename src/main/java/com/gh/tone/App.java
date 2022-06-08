package com.gh.tone;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {

    public static final String PATH = "/Users/echolau/Documents/java/signkey.pem";

    public static void main(String[] args)
        throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String str = "123d45";
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
        String encdeStr = Hex.encodeHexString(hash);
        System.out.println(encdeStr.length());
    }

    public void connect(String userName, String host, int port, String password, String keyFilePath,
        String passphrase) throws Exception {
        try {
            JSch jsch = new JSch();
            if (keyFilePath != null) {
                if (passphrase != null) {
                    jsch.addIdentity(keyFilePath, passphrase);// 设置私钥
                } else {
                    jsch.addIdentity(keyFilePath);// 设置私钥
                }
                System.out.println("连接sftp，私钥文件路径：" + keyFilePath);
            }
            System.out.println("SFTP Host: " + host + "; UserName:" + userName);
            Session session = jsch.getSession(userName, host, port);
            if (password != null) {
                session.setPassword(password);
            }
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            session.setConfig("kex", "diffie-hellman-group1-sha1");
            session.connect();
            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect(3000);
            System.out.println("连接到SFTP成功.Host: " + host);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
