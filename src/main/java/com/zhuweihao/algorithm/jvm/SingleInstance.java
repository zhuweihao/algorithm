package com.zhuweihao.algorithm.jvm;

/**
 * @Author zhuweihao
 * @Date 2023/9/1 15:30
 * @Description com.zhuweihao.algorithm.jvm
 */
public class SingleInstance {
    private static class InstanceHolder{
        public static SingleInstance singleInstance=new SingleInstance();
    }
    public static SingleInstance getInstance(){
        return InstanceHolder.singleInstance;
    }
}
