package com.hyp.learn.zk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class IDUtils {
    private static AtomicInteger count = new AtomicInteger();

    /**
     * 生成自增的ID
     *
     * @return 生成自增的ID
     */
    public static Integer getQualityNum() {
        return count.incrementAndGet();
    }

//    public static void main(String[] args) {
//        String f1 = "1.txt";
//        String f2 = "2.txt";
//
//        try (FileInputStream ios = new FileInputStream(f1);
//             PrintWriter pw=new PrintWriter(f2)) {
//
//            int num = ios.available();
//            byte[] data = new byte[num];
//            ios.read(data);
//            String s = new String(data);
//            String[] ns = s.split(",");
//            int[] integers = new int[ns.length];
//            for (int i = 0; i < ns.length; i++) {
//                integers[i] = Integer.parseInt(ns[i]);
//            }
//            Arrays.sort(integers);
//
//
//            StringBuilder sb=new StringBuilder();
//            for (int i : integers) {
//                sb.append(i).append(",");
//            }
//            sb.deleteCharAt(sb.length()-1);
//
//            File file2=new File(f2);
//
//            if (!file2.exists())
//            {
//                file2.createNewFile();
//            }
//
//            pw.println(sb.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    class menuItem{
        private Integer id;
        private String name;
        private Integer parent;
    }
    

}
