package cn.rong.zlx;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description:
 * @author: zhonglianxi
 * @date: 2019-10-31
 */
public class JSONP {
    public static void main(String[] args) throws IOException {
        Connection connect = Jsoup.connect("http://www.sse.com.cn/disclosure/credibility/supervision/measures/").timeout(10000).header("Host", "query.sse.com.cn").header(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "zh-CN,zh;q=0.8").header("Cache-Control", "no-cache")
                .header("Connection", "keep-alive");
        Document document = connect.get();
        for (Element e:document.getElementsByTag("table")){
            System.out.println(e.html());
        }
        System.out.println(document);
    }


    //模拟浏览器客户端
    public static String getRandomUserAgent() {
        List<String> userAgents = new ArrayList<String>();
        // 360/chrome
        userAgents.add(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.117 Safari/537.36");
        // Safari mac
        userAgents.add(
                "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");
        // Safari windows
        userAgents.add(
                "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");
        // IE9
        userAgents.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0");
        // 火狐mac
        userAgents.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv,2.0.1) Gecko/20100101 Firefox/4.0.1");
        // 火狐windows
        userAgents.add("Mozilla/5.0 (Windows NT 6.1; rv,2.0.1) Gecko/20100101 Firefox/4.0.1");
        // 遨游
        userAgents.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)");
        // 搜狗
        userAgents.add(
                "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)");
        Random random = new Random();
        int randomNum = random.nextInt(userAgents.size());
        return userAgents.get(randomNum);
    }
}
