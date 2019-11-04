package cn.rong.zlx;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.other.CharTable;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.BasicTokenizer;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.List;

public class TestHanlp {

    public static void main(String[] args) {



        List<Term> termList = IndexTokenizer.segment("主副食品");
        List<Term> termList1 = BasicTokenizer.segment("今天上午去北京买日本的和服");

        for (Term term : termList)
        {
            System.out.println(term + " [" + term.offset + ":" + (term.offset + term.word.length()) + "]");
        }


        System.out.println(NLPTokenizer.analyze("我的希望是希望张晚霞的背影被晚霞映红").translateLabels());
        System.out.println(NLPTokenizer.segment("我的希望是希望张晚霞的背影被晚霞映红"));
        System.out.println(NLPTokenizer.analyze("支援臺灣正體香港繁體：微软公司於1975年由比爾·蓋茲和保羅·艾倫創立。"));
        System.out.println(NLPTokenizer.analyze("在下钟老根"));

// 注意观察下面两个“希望”的词性、两个“晚霞”的词性

        System.out.println("=================nlp分词=====================");
        System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));

        System.out.println("==================普通分词====================");
        System.out.println(HanLP.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));

        System.out.println("====================索引分词==================");
        System.out.println(IndexTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
        System.out.println(IndexTokenizer.segment("主副食品"));

        System.out.println("=================关键字分词=====================");
        String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";
        List<String> keywordList = HanLP.extractKeyword(content, 5);
        System.out.println(keywordList);

        System.out.println("===================n最短路径========================");
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        String[] testCase = new String[]{
                "今天，刘志军案的关键人物,山西女商人丁书苗在市二中院出庭受审。",
                "刘喜杰石国祥会见吴亚琴先进事迹报告团成员",
        };
        for (String sentence : testCase)
        {
            System.out.println("N-最短分词：" + nShortSegment.seg(sentence) + "\n最短路分词：" + shortestSegment.seg(sentence));
        }


        String[] testCase1 = new String[]{
                "签约仪式前，秦光荣、李纪恒、仇和等一同会见了参加签约的企业家。",
                "王国强、高峰、汪洋、张朝阳光着头、韩寒、小四",
                "张浩和胡健康复员回家了",
                "王总和小丽结婚了",
                "编剧邵钧林和稽道青说",
                "这里有关天培的有关事迹",
                "龚学平等领导,邓颖超生前",
        };
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        for (String sentence : testCase1)
        {
            List<Term> termList12 = segment.seg(sentence);
            System.out.println(termList12);
        }

        //移除所有Hanlp词性
        CustomDictionary.trie = new BinTrie<>();

        //添加自定义词性 存入CustomDictionary
        //空格为分隔符，源码中，可以同时定义多个词性和词频，将词性和词频分别存入Attribute的两个数组中
        insert("钟老根", "自定义词性" + " 20000");

        //从自定义词典中删除该词
        CustomDictionary.remove("钟老根");

        // 初始化分词器
        NShortSegment nshort = new NShortSegment();
        nshort.enableCustomDictionary(true).enableCustomDictionaryForcing(true);
        nshort.enableOffset(true);
        nshort.enableAllNamedEntityRecognize(true);
        // 取消机构名的识别
        nshort.enableOrganizationRecognize(false);
        // 开始数量词的识别组合
        nshort.enableNumberQuantifierRecognize(true);

        List<Term> listTerms = nshort.seg("在下钟老根");
        System.out.println(listTerms);

    }

    //重写insert方法
    public static boolean insert(String word, String natureWithFrequency)
    {
        // 将所有的自定义的词频改成20000
        String param[] = natureWithFrequency.split(" ");
        natureWithFrequency = param[0]+" 20000";

        if (word == null) return false;
        if (HanLP.Config.Normalization) word = CharTable.convert(word);
        CoreDictionary.Attribute att = natureWithFrequency == null ? new CoreDictionary.Attribute(Nature.nz, 1) : CoreDictionary.Attribute.create(natureWithFrequency);
        if (att == null){
            return false;
        }
        //源码是先加到CustomDictionary.dat，没有再加到CustomDictionary.trie，这个是同时加
        //检验是否有当前键,更新其值
        if (CustomDictionary.dat != null){
            CustomDictionary.dat.set(word, att);
        }

        if (CustomDictionary.trie == null){
            CustomDictionary.trie = new BinTrie<CoreDictionary.Attribute>();
        }
        //插入字典树
        CustomDictionary.trie.put(word, att);
        return true;
    }


}
