package com.microstone.app.util;
import org.apache.commons.codec.binary.Base64;

/**
 * @author XieXiaoDong
 * @date 2021/7/23/0023
 * @description base64 byte[] 互转
 */
public class UtilHelper {
    //base64字符串转byte[]
    public static byte[] base64String2ByteFun(String base64Str){
        return Base64.decodeBase64(base64Str);
    }
    //byte[]转base64
    public static String byte2Base64StringFun(byte[] b){
        return Base64.encodeBase64String(b);
    }
}