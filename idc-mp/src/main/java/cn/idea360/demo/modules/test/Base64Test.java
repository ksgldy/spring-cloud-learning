package cn.idea360.demo.modules.test;

import org.apache.commons.codec.binary.Base64;

public class Base64Test {

    public static void main(String[] args) {
        byte[] bytes = Base64.decodeBase64("GSvRzvUcxCJdgLL5sE6zc2s5CmESfbEjHqFRV636ExD" + "=");
        System.out.println(bytes);


    }
}
