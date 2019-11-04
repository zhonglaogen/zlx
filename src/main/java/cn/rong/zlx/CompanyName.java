package cn.rong.zlx;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @Description: 获取公司全称和简称
 * @author: zhonglianxi
 * @date: 2019-10-28
 */
public class CompanyName {

    public static void main(String[] args) {
        //首先获取目录\\192.168.6.180\hcb\招股说明书
        File file=new File("\\\\192.168.6.180\\hcb\\招股说明书");
        //测试目录是否存在
        System.out.println(file.exists());

        String a="在";
        char   b='在';

        byte[] bytes = a.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
        System.out.println(bytes.length);
//        System.out.println(a.charAt(1));
        System.out.println(b);
        String str="abcde";
        System.out.println(str.substring(1,3));
        System.out.println(str.indexOf('c'));
    }
}
