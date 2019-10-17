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

/**
 * 爬图
 */
public class PaChong {
    private static String URL1 = "http://www.win4000.com/mt/gulinazha";

    public static void main(String[] args) throws IOException {
        for (int n = 1; n <= 5; n++) {
            Connection c = null;
            if (n == 1) {
                c = Jsoup.connect(URL1 + ".html");
            } else {
                c = Jsoup.connect(URL1 + "_" + n + ".html");
            }
            //设置超时时间
            Document doc = c.timeout(10000).get();
            Elements list = doc.getElementsByTag("img");
            int len = list.size();
            //拿到所有img的url

            for (int i = 0; i < len; i++) {
                Element li = list.get(i);
                Element img = li.getElementsByTag("img").first();
                String src = img.attr("src");
                if (src.indexOf("_250_300") != -1) {
                    System.out.println(src);
                    downLoad(src);
                }

            }


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
