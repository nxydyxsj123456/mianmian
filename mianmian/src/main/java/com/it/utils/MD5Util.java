package com.it.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;


/**
 * 密码加密工具类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class MD5Util {

    /**
     * 密码加密
     * @param password
     * @return
     * @throws Exception
     */
    public static String  md5(String password){
        try {
            return DigestUtils.md5Hex(password);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
