package com.f.movie.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Md5 {
    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    static class MapKeyComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {

            return str1.compareTo(str2);
        }
    }

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }

    public static String tokenGenerator(Map<String,String> params,String key){
        Map<String,String> paramsIndex = sortMapByKey(params);
        String tokenStr = "";

        for (Map.Entry<String, String> entry : paramsIndex.entrySet()) {

            tokenStr = tokenStr + entry.getValue() + ";";

        }
        tokenStr += key;

        String token = Md5.stringToMD5(tokenStr);

        return token;

    }


    public static void main(String[] args) {
        String key = "0a3d45ba-6e25-418f-bcbb-2413a07110ff";
        Map<String,String> params = new HashMap<String,String>();
        params.put("id","user321");
        params.put("username","zr");
        params.put("password","123456");
        params.put("nickname","zhang");
        params.put("email","200797602@qq.com");
        params.put("phone","13124563726");
        params.put("hobbies","喜剧 爱情");

        String token = tokenGenerator(params,key);

        System.out.println(token);

    }
}
