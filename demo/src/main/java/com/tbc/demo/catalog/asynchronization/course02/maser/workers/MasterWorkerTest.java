package com.tbc.demo.catalog.asynchronization.course02.maser.workers;


import java.util.Map;
import java.util.Set;

/**
 * Created by DELL on 2017/12/25.
 * Master-Worker模式的测试
 *
 * @author 犯罪嫌疑人卢某
 */
public class MasterWorkerTest {

    public static void main(String[] args) {
        /*
         * 设置6个worker和100个子任务 for循环里面的魔法值无视吧
         */
        Master master = new Master(new Workers(), 4);
        for (int i = 1; i < 101; i++) {
            master.missionSubmit(i + 0.99+99);
        }
        /*
         * Master让所有的Worker开始工作 并且在运作完毕后获得结果集
         */
        //打一个时间戳 对比测试消耗的时间
        long startTime = System.currentTimeMillis();
        master.startAllWorkes();
        Map<String, Object> resultMap = master.getResultMap();
        long endTime = System.currentTimeMillis();
        double finalScore = 0;
        while (true) {
            Set<String> keySet = resultMap.keySet();
            String key = null;
            for (String s : keySet) {
                key = s;
                break;
            }
            //计算结果变量
            Double score = null;
            if (key != null) {
                //计算结果并且从结果集中删除
                score = (Double) resultMap.get(key);
                resultMap.remove(key);
            }
            if (score != null) {
                finalScore += score;
            }
            if (master.isComplete() && resultMap.size() == 0) {
                System.out.println("任务执行完毕..");
                break;
            }
        }
        System.out.println("结果的值是 --->>> " + finalScore);
        System.out.println("总计耗时 : " + (endTime - startTime) + " ms");
    }
}

