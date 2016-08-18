package com.quan.car.qmeeting;

/**
 * Created by car on 2016/8/17.
 */
public class JniUtils {
    public static native String getStringFormC();
    public static native String updateString(String s);

    //加密本地方法
    public static native String encodeFromC(String text, int length);
    //解密本地方法
    public static native String decodeFromC(String text, int length);

    static {
        System.loadLibrary("NdkJniDemo");//之前在build.gradle里面设置的so名字，必须一致
    }
}
