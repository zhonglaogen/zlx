package cn.rong;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class TakeNum {

    /**
     *  异常拒绝
     */
    private static final RejectedExecutionHandler defaultHandler =
            new ThreadPoolExecutor.AbortPolicy();

    public static ThreadPoolExecutor getThreadPool() {
        return
                new ThreadPoolExecutor(3,
                        3,
                        0L,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(),
                        Executors.defaultThreadFactory(),
                        defaultHandler
                );

    }

    /**
     * 并发修改
     *
     * @param l1
     * @param l2
     */
    public static synchronized void changeArry(List<Integer> l1, List<Integer> l2) {
        //随机获取一个数
        int random = (int) (Math.random() * l1.size());

        //移除并添加到另一个数组
        Integer value = l1.get(random);
        l1.remove(random);
        l2.add(value);

    }


    public static void main(String[] args) throws InterruptedException {



        Integer[] arry1 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        //原数组
        List<Integer> list = new ArrayList<>(Arrays.asList(arry1));

        int len = arry1.length;
        //新数组
        List<Integer> list2 = new ArrayList<>();


        CountDownLatch countDownLatch = new CountDownLatch(len);
        ThreadPoolExecutor threadPoolool = getThreadPool();
        for (int i = 0; i < len; i++) {
            threadPoolool.submit(new Thread(() -> {
                System.out.println("线程" + Thread.currentThread().getName() + "正在处理");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                changeArry(list, list2);
//                try{ TimeUnit.SECONDS.sleep(1);}catch(InterruptedException e){ e.printStackTrace();}
                System.out.println("线程" + Thread.currentThread().getName() + "处理结束");
                System.out.println();
                countDownLatch.countDown();
            }));
        }

        countDownLatch.await();
        System.out.println(list2);
        list2.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        System.out.println("排序后"+list2);
        System.out.println("原数组"+list);
        threadPoolool.shutdown();


    }

    @Test
    public void test() {
        String random = "";
        String[] doc = {"成功领取赠险", "安全到家", "已领取50万保障", "和家人团聚", "在家中贴春联"};
//        int index = (int) (Math.random() * doc.length);
        int index = (int) (Math.random() * 5);
//
        System.out.println(index);


        Integer[] arry1 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        List<Integer> list = new ArrayList<>(Arrays.asList(arry1));


    }
}
