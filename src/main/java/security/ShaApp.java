package security;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jenny on 2017/6/2.
 */
public class ShaApp {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        byte[] input = "sha".getBytes();
        //初始化
        MessageDigest md = MessageDigest.getInstance("SHA");
        //更新摘要信息
        md.update(input);
        //获取摘要信息
        byte[] output = md.digest();
    }
}
