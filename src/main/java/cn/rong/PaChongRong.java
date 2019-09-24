package cn.rong;

import cn.rong.entity.Subject;
import cn.rong.entity.Title;
import com.sun.jndi.toolkit.url.Uri;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * indexof!=-1 and contains
 */
public class PaChongRong {
    public static String URL_RONG = "http://www.csrc.gov.cn/pub/zjhpublic/G00306202/201712/t20171229_330048.htm?keywords";


    public static void getMessage() throws IOException, IllegalAccessException {
        Title tt = new Title();
        Subject sub = new Subject();
        //保存title信息
        Map<String, String> tmap = new LinkedHashMap<>();
        //保存subject信息
        List<StringBuilder> slist = new ArrayList<>();


        Connection connection = null;
        connection = Jsoup.connect(URL_RONG);
        Document document = connection.timeout(10000).get();
        Elements head = document.select("#headContainer tbody tr td");
        Elements subject = document.select(".Custom_UnionStyle p");


        //爬首部
        int size = head.size();
        for (int i = 0; i < size - 1; i++) {
            Element element = head.get(i);
            Element td1 = element.select("td").first();
            String text = td1.text();

            //通过：拆分字符串
            String[] split = text.split(":");
            tmap.put(split[0], split[1]);

        }
        //传值给title对象
        Field[] titFields = tt.getClass().getDeclaredFields();
        //指针n表示赋值字段的位置
        int n = 1;
        for (Map.Entry e : tmap.entrySet()) {
            // 要设置属性可达，不然会抛出IllegalAccessException异常
            titFields[n].setAccessible(true);
            titFields[n].set(tt, e.getValue());
            n++;
        }
//        System.out.println(tt.toString());


        //爬内容
        //title的size
        int size1 = subject.size();
        int contentIndex = 0;
        for (int i = 0; i < size1 - 2; i++) {
            Element element = subject.get(i);
            Element td1 = element.select("p").first();
            String text = td1.text();
//            System.out.println(text);

            //将内容信息存入list
            if (text.contains("、") || text.contains("审核的发行人：")) {
                contentIndex++;
                continue;
            }
            if (contentIndex < slist.size()) {
                slist.get(contentIndex).append("\t" + text);
            } else {
                slist.add(new StringBuilder(text));
            }

        }
//        System.out.println(slist);


        Field[] subFields = sub.getClass().getDeclaredFields();
        n = 1;
      for(StringBuilder sb:slist){
          subFields[n].setAccessible(true);
          subFields[n].set(sub,sb.toString());
          n++;
      }
        System.out.println(sub.toString());


    }

    public static void main(String[] args) throws IOException, IllegalAccessException {
        getMessage();
    }
}
