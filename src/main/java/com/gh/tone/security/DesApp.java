package com.gh.tone.security;

import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jenny on 2017/6/2.
 */
public class DesApp {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        AlgorithmParameters ap = AlgorithmParameters.getInstance("DES");
        ap.init(new BigInteger("213124154").toByteArray());
        byte[] b = ap.getEncoded();
        System.out.println(new BigInteger(b).toString());
    }
}
