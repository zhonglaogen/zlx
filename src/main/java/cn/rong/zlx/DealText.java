package cn.rong.zlx;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

public class DealText {
    public static void main(String[] args) {
        merage();
//        splitTime();


//        dirSplit();




    }



    private static void dirSplit() {
        String text="第一节 概览\n" +
                "一、发行人基本情况\n" +
                "1) 基本情况\n" +
                "2) 业务说明\n" +
                "二、发行人股东、董监高情况\n" +
                "（1）、股东简历\n" +
                "（2）、监事简历\n" +
                "（3）、高管简历\n" +
                "第二节 业务与技术\n" +
                "1、公司基本业务\n" +
                "（1）、业务介绍\n" +
                "（2）、竞争情况\n" +
                "2、公司技术实力\n" +
                "（1）、业务介绍";
        String[] split = text.split("\n");

        //存目录结构
        Map<String,Integer> dirs=new HashMap<>();
        List<String> dir=new ArrayList<>();


        int len =split.length;
        for (int i = 0; i < len; i++) {
            split[i]=structCreate(dirs,split[i]);
        }
        for (int i = 0; i < split.length; i++) {
            System.out.print(split[i]);
        }
    }

    //当前目录所属于的层级结构
    static int dircount=-1;

    private static String structCreate(Map<String,Integer> dirs,String sentence) {
        //用set代替list，
        //拆分关键字
        String[] keywords1={" ",")","、"};
        //替换数字
        String match="(\\d|[一二三四五六七八九十])+";
        int len=keywords1.length;

        for (int i = 0; i < len; i++) {
            //关键字的位置
            int index;
            //存在目录关键字
            if ((index=sentence.indexOf(keywords1[i]))!=-1){

                //匹配目录通用式
                String matchKey = sentence.substring(0, index + 1).replaceAll(match, "*");
                //不包含目录结构，存入
                if(!dirs.containsKey(matchKey)){
                    dirs.put(matchKey,++dircount);
                }else {
                    dircount=dirs.get(matchKey);
                }
                //拿到该目录层级层数
                Integer count = dirs.get(matchKey);
                for (int j = 0; j < count; j++) {
                    sentence="\t"+sentence;
                }
                return sentence+"\n";
            }
        }
        return sentence+"\n";
    }

    //日期拆分
    private static void splitTime() {
        String text = "2014-2016年，公司基本情况\n" +
                "2014年-2016年，公司基本情况\n" +
                "2014年至2016年，公司基本情况\n" +
                "2014至2016年，公司基本情况";
        String[] split = text.split("\n");
        int len = split.length;
        for (int i = 0; i < len; i++) {
            if (split[i].contains("年")) {
                split[i]= splitSentence(split[i])+"\n";
            }
        }
        for (int i = 0; i < len; i++) {
            System.out.print(split[i]);
        }
    }

    private static String splitSentence(String sentence) {
        //顺序要字符多的在前面，根据关键字符进行拆分的，避免拆分字符错误
        String[] splitKey = {"年-", "年至", "-", "至"};
        int len = splitKey.length;
        for (int i = 0; i < len; i++) {
            int location = 0;
            if ((location = sentence.indexOf(splitKey[i])) != -1) {
                //根据切分关键字切分
                String[] split = sentence.split(splitKey[i]);
                int first = Integer.parseInt(split[0]);
                int end = Integer.parseInt(split[1].substring(0, 4));
                int gap = end - first;

                String s1 = "";
                for (int j = 0; j < gap; j++) {
                    int year =first+j;
                    s1+=year+"年丶";
                }
                split[0]=s1;
//                System.out.println("起始时间" + first + "\t" + "结束时间：" + end);
                return split[0]+split[1];
            }
        }
        return sentence;

    }


    //关键字合并
    private static void merage() {
        String[] s1 = {"2014年", "公司", "营业", "利润",
                "利润", "收入", "2000.00万元", "利润"
                , "利润", "利润", "保持", "增长", "态势"};
        List<String> l1 = new CopyOnWriteArrayList<>(Arrays.asList(s1));
        String key = "利润";
        //标记第二个数组的位置
        int index = -1;
        List<String> l2 = new ArrayList<>();

        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i).equals(key)) {
                //检查前一个数是否是关键字
                if (i != 0 && l1.get(i - 1).equals(key)) {
                    l2.set(index, l2.get(index) + key);
                    continue;
                }
            }
            l2.add(l1.get(i));
            index++;


        }

        System.out.println(l2);
    }
}
