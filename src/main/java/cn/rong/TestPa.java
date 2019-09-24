package cn.rong;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPa {
    public static void main(String[] args) {

        //将资源保存(分隔符，)
        StringBuilder stringBuilder = creatSource();

        //资源过滤(分隔符，)
        StringBuilder sb = matchPeople(stringBuilder);
        //获取参会人员和发行人
        getPeople(sb);

        StringBuilder sb2 = matchTitle(stringBuilder);
//        getTitle(stringBuilder);

//

    }

    private static StringBuilder matchTitle(StringBuilder stringBuilder) {
        String s = stringBuilder.toString();
        String[] split = s.split(",");
        int len = split.length;
        for (int i = 0; i < len; i++) {
            Pattern p = Pattern.compile("(<span id=\"lTitle\"></span>)");
            Matcher m = p.matcher(split[i]);
            if (m.find()) {
                stringBuilder.append(split[i]);
            }
        }

        return null;
    }

    private static void getTitle(StringBuilder stringBuilder) {
//        <span id="lTitle">第十七届发审委2018年第5次工作会议公告</span>

        String s = stringBuilder.toString();
        String[] split = s.split(",");
        int len = s.length();
        for (int i = 0; i < len; i++) {
            split[len].contains("");
        }
    }

    public static StringBuilder matchPeople(StringBuilder stringBuilder) {
        StringBuilder nsb = new StringBuilder();
        String s = stringBuilder.toString();
        String[] split = s.split(",");
        int len = split.length;
        for (int i = 0; i < len; i++) {


            Pattern p = Pattern.compile("(<P>　　<SPAN>)");
            Matcher m = p.matcher(split[i]);
            if (m.find()) {
                String temp = split[i].replaceAll("\\w|<|>|/", "");
                nsb.append(temp + ",");
            }
        }
        return nsb;
    }

    /**
     * 将html保存
     *
     * @return
     */
    private static StringBuilder creatSource() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            //建立连接
            URL url = new URL("http://www.csrc.gov.cn/pub/zjhpublic/G00306202/201712/t20171229_330048.htm?keywords");
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //获取输入流
            InputStream input = httpUrlConn.getInputStream();
            //将字节输入流转换为字符输入流
            InputStreamReader read = new InputStreamReader(input, "utf-8");
            //为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(read);
            // 读取返回结果
            String data = br.readLine();
            while (data != null) {
                data = br.readLine();
                System.out.println(data);
                stringBuilder.append(data + ",");
            }
            // 释放资源
            br.close();
            read.close();
            input.close();
            httpUrlConn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    //获取参会发审委委员和审核的发行人
    private static void getPeople(StringBuilder stringBuilder) {
        //拆分
        String s = stringBuilder.toString();
        String[] split = s.split(",");
        int len = split.length;
        for (int i = 0; i < len; i++) {
            if (split[i].contains("、参会发审委委员")) {
                System.out.println(split[i + 1]);
                System.out.println(split[i + 2]);
            }
            if (split[i].contains("审核的发行人：")) {
                System.out.println(split[i + 1]);
            }
        }

    }

    @Test
    public void test1() {
//        String s="1,2,3,4,5,67";
//        String[] split = s.split(",");
//        for (int i = 0; i < split.length; i++) {
//            System.out.println(split[i]);
//        }

//        String a="索 引 号:40000895X/ 分类: 发审会公告 ; 发审会公告";
//        String[] split = a.split(":");
//        System.out.println(split[1]+"\t");

//        String text="审核的发行人：";
        String text = "一、参会发审委委员";
        if (text.contains("、") && text.contains("审核的发行人：")) {
            System.out.println("yes");
        }
//


    }


}

