package test6;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 1.左括号必须用相同类型的右括号闭合。
 * 2.左括号必须以正确的顺序闭合。
 * 3.每个右括号都有一个对应的相同类型的左括号。
 */
public class ValidBrackets {
    public boolean isValid(String s) {
        /*
        其实并没有那么多复杂的讨论,所有不匹配的情况都可以统一归纳到三种情况中:
        (1)左括号多了
        (2)括号没多但是不匹配
        (3)右括号多了
        只要代码中包含了这三种情况的讨论就必定不会漏
        */
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            //碰到左括号就把右括号入栈,方便之后比较
            if (ch=='(') {
                deque.push(')');
            }else if (ch == '[') {
                deque.push(']');
            }else if (ch == '{') {
                deque.push('}');
            }else if (deque.isEmpty() || ch!=deque.peek()) {
                //这里包括了(2)(3)两种不匹配的情况,同时利用短路运算符的特性避免了空指针异常
                return false;
            }else{
                deque.pop();//比较无误后别忘了把栈顶元素出栈!
            }
        }
        //整个字符串遍历完后再检查是否为第(1)种情况
        if (!deque.isEmpty()) {
            return false;
        }
        return true;
    }
}
