package cn.rong.zlx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.Collection;
import java.util.Collections;

public class Tools {
    public static String readTxtFile(String path) {
        return null;
    }

    public static void main(String[] args) {
//        getHtml();
        String html = "<html>\n" +
                "<body>\n" +
                "\n" +
                "<table border=\"1\">\n" +
                "  <tr>\n" +
                "    <td>Company</td>\n" +
                "    <td>Address</td>\n" +
                "    <td rowspan=\"3\">All the companies in USA</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Apple, Inc.</td>\n" +
                "    <td>1 Infinite Loop Cupertino, CA 95014</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Google, Inc.</td>\n" +
                "    <td>1600 Amphitheatre Parkway Mountain View, CA 94043</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
        String html2 = "<html>\n" +
                "<body>\n" +
                "\n" +
                "<table width=\"100%\" border=\"1\">\n" +
                "  <tr>\n" +
                "    <th>Month</th>\n" +
                "    <th>Savings</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td colspan=\"2\">January</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td colspan=\"2\">February</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
        String html3 = "<html>\n" +
                "<body>\n" +
                "<div name=\"\"test\">\n" +
                "<div id=\"child\"></div>\n" +
                "<div id=\"child1\"></div>\n" +
                "<div id=\"child2\"></div>\n" +
                "<div id=\"child3\"></div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n";


        Document parse = Jsoup.parse(html3);
        Element body = parse.getElementsByTag("body").get(0);
        Element element = body.children().get(0);
        Element newele = new Element("div");
        newele.attr("id", "childnew");
        /**
         * 01,23,45,67,89,偶数加奇数比对效果一致
         */
        element.insertChildren(2, newele);
        System.out.println(parse.html());

//        Document document = formatTable(parse);


//        System.out.println(document.html());


    }

//    private static void getHtml() {
//        //.html和.text   getAllElements和children的区别？？？？？
//        String html="<html>\n" +
//                "<body>\n" +
//                "\n" +
//                "<table border=\"1\">\n" +
//                "  <tr>\n" +
//                "    <th>Company</th>\n" +
//                "    <th>Address</th>\n" +
//                "    <th rowspan=\"3\">All the companies in USA</th>\n" +
//                "  </tr>\n" +
//                "  <tr>\n" +
//                "    <td>Apple, Inc.</td>\n" +
//                "    <td>1 Infinite Loop Cupertino, CA 95014</td>\n" +
//                "  </tr>\n" +
//                "  <tr>\n" +
//                "    <td>Google, Inc.</td>\n" +
//                "    <td>1600 Amphitheatre Parkway Mountain View, CA 94043</td>\n" +
//                "  </tr>\n" +
//                "</table>\n" +
//                "\n" +
//                "</body>\n" +
//                "</html>\n";
//        Document parse = Jsoup.parse(html);
//        Element body = parse.getElementsByTag("body").get(0);
//        Elements children = body.children();
//        //解析行
//        children.stream().forEach(term->{
//            Elements trs = term.select("tr");
//            System.out.println("解析body下的元素");
////            Elements td = term.getElementsByTag("tr");
//            trs.stream().forEach(term1->{
//                Elements tds = term1.select("td");
//                tds.stream().forEach(term2->{
//                    //若果没有rowspan那么返回“”
//                    String tdrowspan = term2.attr("rowspan");
//
//                    tdrowspan = (tdrowspan == null || tdrowspan.length() <= 0) ? "0" : tdrowspan;
//                    System.out.println("----------------------标记"+tdrowspan);
//
//                    //因为rowspan的value是当前列占的行数（一定是数字）
//                    // 针对 跨行进行处理，将rowspan消化。在下行增加
//                    int tdrowspannumber = Integer.parseInt(tdrowspan);
//
//                    if (tdrowspannumber>1){
//                        //移除跨行的列属性rowspan
//                        term2.removeAttr("rowspan");
//                        Element baseTdEle = null;
//                        int startRow = 0;
//                        int startTd = 0;
//                        String tdtext = term2.text();
//                        String tdstyle = term2.attr("style");
//                        //是否跨列
//                        String colspan = term2.attr("colspan");
//
//
//                    }
//
//
//
//                });
//
//            });
//        });
//    }

    /**
     * 将table进行格式化，拆分表格
     *
     * @param doc
     * @return
     */
    public static Document formatTable(Document doc) {
        Document document = Jsoup.parse(doc.outerHtml());
        try {
            Elements tableEle = document.getElementsByTag("table");

            int trindex = 0;
            int tdindex = 0;
            for (Element ele : tableEle) {
                //此处为正则
                Elements trs = ele.select("tr");
                //遍历该表格内的所有的<tr> <tr/>
                for (trindex = 0; trindex < trs.size(); trindex++) {
                    // 获取一个tr
                    Element tr = trs.get(trindex);
                    // 获取该行的所有td节点
                    Elements tds = tr.select("td");
                    // 选择某一个td节点
                    for (tdindex = 0; tdindex < tds.size(); tdindex++) {
                        Element td = tds.get(tdindex);

                        // 如果跨行了，需要占领行的知切割成多行，并保留colspan属性
                        String tdrowspan = td.attr("rowspan");
                        tdrowspan = (tdrowspan == null || tdrowspan.length() <= 0) ? "0" : tdrowspan;
                        // 针对 跨行进行处理，将rowspan消化。在下行增加
                        int tdrowspannumber = Integer.parseInt(tdrowspan);
                        if (tdrowspannumber > 1) {
                            //移除跨行属性
                            td.removeAttr("rowspan");
                            Element baseTdEle = null;
                            int startRow = 0;
                            int startTd = 0;
                            String tdtext = td.text();
                            String tdstyle = td.attr("style");
                            String colspan = td.attr("colspan");
                            //针对每一行做处理
                            for (int tempindex = 1; tempindex < tdrowspannumber; tempindex++) {
                                // 从哪一行开始，如果是rowspan本身自己不需要再进行扩展，只要其他的扩展就行了
//                                遍历到的那一行+当前列所占领的行数位置
                                startRow = trindex + tempindex;
                                // 从哪一列开始，从自己开始。
                                startTd = tdindex;

                                // 初始化一个basetd，用于复制到其他使用。
                                baseTdEle = new Element("td");
                                baseTdEle.attr("style", tdstyle);
                                baseTdEle.attr("colspan", colspan);
                                baseTdEle.text(tdtext);
                                if (startRow >= trs.size()) {
                                    continue;
                                }
                                // 插入一个basetd，到需要扩展的行中去。
                                trs.get(startRow).insertChildren(startTd+2, baseTdEle);

                            }
                        }

                        // 如果跨列了
                        String tdcolspan = td.attr("colspan");
                        tdcolspan = (tdcolspan == null || tdcolspan.length() <= 0) ? "0" : tdcolspan;
                        // 针对 跨列进行处理，将rowspan消化。在下行增加
                        int tdcolspannumber = Integer.parseInt(tdcolspan);
                        if (tdcolspannumber > 1) {
                            td.removeAttr("colspan");
                            Element baseTdEle = null;
                            String tdtext = td.text();
                            String tdstyle = td.attr("style");
                            // 跨列了只需要在原基础上追加单元格就行。游标从1开始，除去本身
                            for (int tempindex = 1; tempindex < tdcolspannumber; tempindex++) {
                                baseTdEle = new Element("td");
                                baseTdEle.attr("style", tdstyle);
                                baseTdEle.text(tdtext);
                                td.after(baseTdEle);
                            }
                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

}
