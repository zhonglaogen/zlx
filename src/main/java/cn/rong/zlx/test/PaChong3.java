package cn.rong.zlx.test;

import com.sun.jndi.toolkit.url.Uri;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class PaChong3 {
    private static String URL1 = "https://www.qichacha.com/search?key=迷橙";

    @Test
    public  void creatSource() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            //建立连接
            URL url = new URL(URL1);
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
    }

    public static void main(String[] args) throws IOException {
        for (int n = 0; n <= 1; n++) {
            Connection c = null;

            c = Jsoup.connect(URL1);

            //设置超时时间
            Document doc = c.timeout(10000).get();
            Elements list = doc.getElementsByClass("m-t-xs");
            int len = list.size();
            //拿到所有img的url

            for (int i = 0; i < len; i++) {
                Element li = list.get(i);
                Element title = li.getElementsByTag("m-t-xs").first();
                String tt = li.text();
//                if (src.indexOf("_250_300") != -1) {
//                    System.out.println(src);
//                    downLoad(src);
//                }
                System.out.println(tt);

            }

            System.out.println();
            System.out.println();


        }

    }

    private static void downLoad(String src) throws IOException {
        URL url = new URL(src);
        InputStream inputStream = url.openStream();
        File dir = new File("/home/zhonglianxi/pachong/img");
        File file = new File(dir, UUID.randomUUID() + ".jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
        }
        inputStream.close();
        fileOutputStream.close();
    }

}
