package test7;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 */
public class FirstKElement {
    /*
    本题采用优先级队列作为容器
    优先级队列其实就是一个披着队列外衣的堆，
    因为优先级队列对外接口只是从队头取元素，从队尾添加元素，再无其他取元素的方式，看起来就是一个队列。
    优先级队列内部元素是自动依照元素的权值排列
    缺省情况下priority_queue利用max-heap（大顶堆）完成对元素的排序，
    这个大顶堆是以vector为表现形式的complete binary tree（完全二叉树）
     */
    public int[] topKFrequent(int[] nums, int k) {
        //先用哈希法获取每个元素出现的频率
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0 ; i<nums.length ; i++){
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i])+1);
            }else{
                map.put(nums[i], 1);
            }
        }
        //在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
        //出现次数按从队头到队尾的顺序是从小到大排,出现次数最低的在队头(相当于小顶堆)
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2)->pair1[1]-pair2[1]);
        //小顶堆只需要维持k个元素有序
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            //小顶堆元素个数小于k个时直接加
            if (pq.size()<k) {
                pq.add(new int[]{entry.getKey(),entry.getValue()});
            }else{
                //当前元素出现次数大于小顶堆的根结点(这k个元素中出现次数最少的那个)
                if (entry.getValue() > pq.peek()[1]) {
                    //弹出队头(小顶堆的根结点),即把堆里出现次数最少的那个删除,留下的就是出现次数多的了
                    pq.poll();
                    pq.add(new int[]{entry.getKey(),entry.getValue()});
                }
            }
        }
        int[] ans = new int[k];
        //依次弹出小顶堆,先弹出的是堆的根,出现次数少,后面弹出的出现次数多
        for(int i=0; i<k; i++){
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
}
