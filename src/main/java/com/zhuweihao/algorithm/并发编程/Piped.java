package com.zhuweihao.algorithm.并发编程;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Author zhuweihao
 * @Date 2023/9/1 20:33
 * @Description com.zhuweihao.algorithm.并发编程
 */
public class Piped {

    static class Print implements Runnable{
        private PipedReader in;
        public Print(PipedReader in){
            this.in=in;
        }
        @Override
        public void run() {
            int receive=0;
            try {
                while ((receive=in.read())!=-1){
                    System.out.print((char) receive);
                }
            } catch (IOException e){

            }

        }
    }

    public static void main(String[] args) throws IOException {
        PipedWriter out=new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);
        Thread thread = new Thread(new Print(in), "PrintThread");
        thread.start();
        int receive=0;
        try {
            while ((receive=System.in.read())!=-1){
                out.write(receive);
            }
        }finally {
            out.close();
        }
    }
}
