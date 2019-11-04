package cn.rong.zlx.test;

import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 测试字符串
 * @author: zhonglianxi
 * @date: 2019-10-25
 */
public class TestString {
    public static void main(String[] args) {
        String title="深圳市朗科智能电气股份有限公司创业板首次公开发行股票招股说明书（申报稿2015年12月9日报送）";
        int allNameIndex = title.indexOf("公司");
        String substring = title.substring(0, allNameIndex+2);
        //全称
        System.out.println(substring);
        NShortSegment nShortSegment = new NShortSegment().enablePlaceRecognize(true);
        List<Term> seg = nShortSegment.seg(substring);
        List<Term> ns = seg.stream().filter(e -> !e.nature.toString().equals("ns")).collect(Collectors.toList());

        //四字简称
        StringBuilder sNmaeBuilder=new StringBuilder();
        ns.stream().forEach(e->sNmaeBuilder.append(e.word));
        String sName = sNmaeBuilder.toString().substring(0, 4);
        System.out.println(sName);



    }

}
