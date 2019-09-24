package cn.rong;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test1 {

    public static void splitAndMerageFile(String opath, String npath) {
        RandomAccessFile orw = null;
        RandomAccessFile nrw = null;
        try {
            orw = new RandomAccessFile(new File(opath), "rw");
            nrw = new RandomAccessFile(new File(npath), "rw");
            //20份每份5M
            byte[] split = new byte[1024 * 1024 * 5];
            int len = 0;
            while ((len = orw.read(split)) != -1) {
                nrw.write(split,0,len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                nrw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                orw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean checkBySha1(String opath, String npath) {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");

            //获取文件字节数组
            byte[] oldfile = new byte[1024 * 1024 * 100];
            byte[] newfile = new byte[1024 * 1024 * 100];

            RandomAccessFile orw = null;
            RandomAccessFile nrw = null;

            orw = new RandomAccessFile(new File(opath), "rw");
            orw.read(oldfile);



            nrw = new RandomAccessFile(new File(npath), "rw");
            nrw.read(newfile);


            //错误匹配
//            newfile[99]=2;


            //获取密文
            sha.update(oldfile);
            byte[] oldDigest = sha.digest();

            sha.update(newfile);
            byte[] newDigest = sha.digest();

            System.out.println(newfile.length);
            System.out.println(newDigest.length);
            //进行比对

            int len=oldDigest.length;
            for (int i = 0; i < len; i++) {
                if(oldDigest[i]!=newDigest[i]){
                    return false;
                }
            }


        } catch (Exception e) {

        }


        return true;
    }

    public static void createFile() {
        try {
            RandomAccessFile rw = new RandomAccessFile(new File("/home/zhonglianxi/111111/file1"), "rw");
            byte[] content = new byte[1024 * 1024 * 100];
            for (int i = 0; i < 1024 * 1024 * 100; i++) {
                content[i] = 1;
            }
            rw.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        //创建100m文件
        createFile();
        //进行切分和创建新文件
        splitAndMerageFile("/home/zhonglianxi/111111/file1","/home/zhonglianxi/111111/file2");
        //sha1加密检查是否一致
        boolean b = checkBySha1("/home/zhonglianxi/111111/file1", "/home/zhonglianxi/111111/file1");
        System.out.println(b);

    }


}
