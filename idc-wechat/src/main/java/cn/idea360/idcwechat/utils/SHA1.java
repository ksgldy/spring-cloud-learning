package cn.idea360.idcwechat.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA1 {

    public static String gen(String... arr) {
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        String[] parr = arr;
        int size = arr.length;

        for(int i = 0; i < size; ++i) {
            String a = parr[i];
            sb.append(a);
        }

        return sha1(sb.toString());
    }

    public static String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
