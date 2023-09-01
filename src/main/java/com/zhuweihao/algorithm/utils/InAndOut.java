package com.zhuweihao.algorithm.utils;

import java.io.*;

/**
 * @Author zhuweihao
 * @Date 2023/8/5 16:46
 * @Description com.zhuweihao.algorithm.utils
 */
public class InAndOut {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int a = (int) in.nval;
            in.nextToken();
            int b = (int) in.nval;
            out.print(a + b);
            out.print(" ");
            out.print(a - b);
            out.println();
        }
        out.flush();
    }
}
