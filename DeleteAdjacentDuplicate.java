package test6;

import java.util.ArrayDeque;

/**
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * 示例：
 *
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 */
public class DeleteAdjacentDuplicate {
    //栈结构特别擅长处理相邻数据的匹配问题
    public String removeDuplicates(String s) {
        //ArrayDeque会比LinkedList在除了删除元素这一点外会快一点
        ArrayDeque<Character> deque = new ArrayDeque<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (deque.isEmpty() || ch!=deque.peek()) {
                deque.push(ch);
            }else{
                deque.pop();
            }
        }
        String str = "";
        while (!deque.isEmpty()) {
            str = deque.pop() + str;
        }
        return str;
    }

    /*
    其实直接以字符串作为栈更方便(StringBuilder实现)
     */
}
