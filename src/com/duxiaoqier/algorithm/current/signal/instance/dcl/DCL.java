package com.duxiaoqier.algorithm.current.signal.instance.dcl;

/**
 * 线程1的语句3不happens-before于线程2的语句2
 * <p>
 * Created by liumian on 2016/12/13.
 */
public class DCL {

    private static volatile DCL instance;


    private int status;

    private DCL() {
        status = 1;                         //1
    }

    public static DCL getInstance() {
        if (instance == null) {              //2
            synchronized (DCL.class) {       //3
                if (instance == null) {      //4
                    instance = new DCL();   //5
                }
            }
        }
        return instance;                    //6
    }

    public int getStatus() {
        return status;                      //7
    }
}