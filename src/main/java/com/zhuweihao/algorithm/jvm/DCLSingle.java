package com.zhuweihao.algorithm.jvm;

/**
 * @Author zhuweihao
 * @Date 2023/8/17 21:18
 * @Description com.zhuweihao.algorithm.jvm
 */
public class DCLSingle {
    private static volatile DCLSingle instance;
    public static DCLSingle getInstance(){
        if (instance==null){
            synchronized (DCLSingle.class){
                if (instance==null){
                    instance=new DCLSingle();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args){
        DCLSingle dclSingle = DCLSingle.getInstance();
    }
}
