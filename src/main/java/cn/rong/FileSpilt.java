package cn.rong;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FileSpilt {

    public static List<byte[]> splitAndMerageFile(String opath, String npath) {
        RandomAccessFile orw = null;
        RandomAccessFile nrw = null;
        List<byte[]> blocks = new ArrayList<>();
        try {
            orw = new RandomAccessFile(new File(opath), "rw");
            nrw = new RandomAccessFile(new File(npath), "rw");
            //20份每份5M
            byte[] split = new byte[1024 * 1024 * 5];
            //读取的字节数
            int len = 0;
            while ((len = orw.read(split)) != -1) {

                //浅拷贝，错误！！
//                blocks.add(split);
                blocks.add(copyByte(split));

            }
            for (byte[] bytes : blocks) {
                nrw.write(bytes);
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
        return blocks;

    }

    /**
     * 深拷贝数组
     * @param bytes1
     * @return
     */
    private static byte[] copyByte(byte[] bytes1) {
        int len = bytes1.length;
        byte[] bytes2 = new byte[len];
        for (int i = 0; i < len; i++) {
            bytes2[i] = bytes1[i];
        }
        return bytes2;
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


            System.out.println("sha1加密的字节数：" + newDigest.length);
            //进行比对

            int len = oldDigest.length;
            for (int i = 0; i < len; i++) {
                if (oldDigest[i] != newDigest[i]) {
                    return false;
                }
            }


        } catch (Exception e) {

        }


        return true;
    }

    public static void createFile(String fileName) {
        try {
            RandomAccessFile rw = new RandomAccessFile(new File(fileName), "rw");
            byte[] content = new byte[1024 * 1024 * 100];
            for (int i = 0; i < 1024 * 1024 * 100; i++) {
                    content[i] = (byte) (i%100);

            }
            rw.write(content);
            System.out.println("创建文件的大小：" + rw.length() / (1024 * 1024) + "mb");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        //创建100m文件
        createFile("/home/zhonglianxi/111111/f1");
        //进行切分和创建新文件
        List<byte[]> blocks = splitAndMerageFile("/home/zhonglianxi/111111/f1", "/home/zhonglianxi/111111/f1x");
        System.out.println("文件分割的块数目：" + blocks.size());
        //sha1加密检查是否一致
        boolean b = checkBySha1("/home/zhonglianxi/111111/f1", "/home/zhonglianxi/111111/f1x");
        System.out.println("比对结果为：" + b);

    }


}
