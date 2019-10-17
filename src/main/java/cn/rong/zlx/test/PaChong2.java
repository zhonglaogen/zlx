package cn.rong.zlx.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 爬博客
 */
public class PaChong2 {
    private static String URL1 = "https://www.cnblogs.com/sitehome/p/";

    public static void main(String[] args) throws IOException {
        for (int n = 1; n <= 200; n++) {
            Connection c = null;

            c = Jsoup.connect(URL1 + n);

            //设置超时时间
            Document doc = c.timeout(10000).get();
            Elements list = doc.getElementsByClass("titlelnk");
            int len = list.size();
            //拿到所有img的url

            for (int i = 0; i < len; i++) {
                Element li = list.get(i);
                Element title = li.getElementsByTag("a").first();
                String tt = title.text();
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
