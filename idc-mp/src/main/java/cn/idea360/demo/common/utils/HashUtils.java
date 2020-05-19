package cn.idea360.demo.common.utils;

public class HashUtils {

    public static int hash(String data) {
        return data.hashCode() & Integer.MAX_VALUE;
    }
}
