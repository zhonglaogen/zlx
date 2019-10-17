package cn.rong.zlx;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Each {
    public static void main(String[] args) {
//        list1();
//        list2();
//        list3();
//        list4();
//        map1();
//        map2();
//        map3();
//        map4();
//        set1();
        Set<String> s1=new HashSet();
        s1.iterator();
        for (String s:s1){

        }


    }

    private static void set1() {
        Set s1=new HashSet();
        s1.forEach(new Consumer() {
            @Override
            public void accept(Object o) {

            }
        });
    }

    private static void map4() {
        Map<String,String> m1=new HashMap<>();
        m1.put("1","a");
        m1.put("2","b");
        m1.put("3","c");
        m1.put("4","d");
        m1.put("5","e");
        for(Map.Entry<String,String> entry:m1.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
    }

    private static void map3() {
        Map<String,String> m1=new HashMap<>();
        m1.put("1","a");
        m1.put("2","b");
        m1.put("3","c");
        m1.put("4","d");
        m1.put("5","e");
        for (String key:m1.keySet()){
            System.out.println(key+"\t"+m1.get(key));
        }
    }

    private static void map2() {
        Map<String,String> m1=new HashMap<>();
        m1.put("1","a");
        m1.put("2","b");
        m1.put("3","c");
        m1.put("4","d");
        m1.put("5","e");
        Iterator<Map.Entry<String, String>> iterator = m1.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey()+"\t"+next.getValue());

        }
    }

    private static void map1() {
        Map<String,String> m1=new HashMap<>();
        m1.put("1","a");
        m1.put("2","b");
        m1.put("3","c");
        m1.put("4","d");
        m1.put("5","e");
        m1.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                System.out.println(s+"\t"+s2);
            }
        });
    }

    private static void list4() {
        Integer[] arry = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> l1 = new ArrayList<>(Arrays.asList(arry));
        Iterator<Integer> iterator = l1.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    private static void list3() {
        Integer[] arry = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> l1 = new ArrayList<>(Arrays.asList(arry));
        for (int i = 0; i < l1.size(); i++) {
            System.out.println(l1.get(i));

        }
    }

    private static void list2() {
        Integer[] arry = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> l1 = new ArrayList<>(Arrays.asList(arry));
        for (Integer i : l1) {
            System.out.println("遍历的值" + i);
        }
    }

    private static void list1() {
        Integer[] arry = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> l1 = new ArrayList<>(Arrays.asList(arry));
        l1.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("遍历的值" + integer);
            }
        });
    }
}
