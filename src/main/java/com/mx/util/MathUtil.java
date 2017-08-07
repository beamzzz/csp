package com.mx.util;

/**
 * @aother zcl
 * @date 2017/8/7
 */
public class MathUtil {

    /**
     * 获取指定位数的随机数
     * @auther : beam
     * @date : 2017/8/7
     **/
    public static Integer getRandom(int num){
        Long temp = (long)Math.pow(10,num);
       return  (int)((Math.random()*9+1) * temp);
    }
}
