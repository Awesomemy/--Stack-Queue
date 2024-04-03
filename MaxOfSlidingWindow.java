package test7;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 */
public class MaxOfSlidingWindow {
    /*
    本例这里引入单调队列的概念(单调队列在不同场景有不同的实现,此处仅针对本题)
    这是一个自己建立的队列
    入队时比较新元素与队尾元素的大小,
    如果新元素比队尾元素大,那么就将队尾元素从队尾出列,
    一直这样比较直到新元素比队尾元素小,
    这样可以保证整个队列是单调的.
    这样做能保证出列口的元素总是整个队列中最大的

    每个元素其实都只被add()和poll()各一次,所以时间复杂度还是O(n)
 */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        int len = nums.length-k+1;
        //存放结果的数组
        int[] res = new int[len];
        //自定义队列
        MyQue myQue = new MyQue();
        //先将前k个元素放入队列
        for (int i = 0; i < k ; i++) {
            myQue.add(nums[i]);
        }
        int num=0;
        res[num++] = myQue.peek();
        for (int i = k; i < nums.length; i++) {
            //滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
            myQue.poll(nums[i-k]);
            //滑动窗口加入最后面的元素
            myQue.add(nums[i]);
            //记录对应的最大值
            res[num++] = myQue.peek();
        }
        return res;
    }
}

class MyQue{

    Deque<Integer> deque = new LinkedList<>();

    //弹出元素时，比较当前要弹出的数值是否等于队列出口的数值，如果相等则弹出
    //同时判断队列当前是否为空
    void poll(int val){
        if (!deque.isEmpty() && val==deque.peek()) {
            deque.poll();
        }
    }

    //添加元素时，如果要添加的元素大于入口处的元素，就将入口元素弹出
    //保证队列元素单调递减
    //比如此时队列元素3,1，2将要入队，比1大，所以1弹出，此时队列：3,2
    void add(int val){
        while (!deque.isEmpty() && val>deque.getLast()) {
            deque.removeLast();
        }
        deque.add(val);
    }

    //队列队顶元素始终为最大值
    int peek(){
        return deque.peek();
    }
}