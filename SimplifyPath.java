package test6;

import java.util.Stack;

/**
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 *
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 *
 * 请注意，返回的 规范路径 必须遵循下述格式：
 *
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 */
public class SimplifyPath {
    //这种需要往上一级找的问题很适合用栈来解决
    public String simplifyPath(String path) {
        //不必对字符一个个读取,只需要分部分比较集合(就类似遍历中一次跨K个一样)
        String[] parts = path.split("/");
        Stack<String> stack = new Stack<>();
        for(String part : parts){
            if (part.equals("") || part.equals(".")) {
                //这两种情况是连续斜杠或者就是当前目录,不会影响当前目录
                continue;
            }else if (part.equals("..")) {
                //如果是"..",说明要返回上一级目录,去栈里取出所保存的上一级目录
                if (!stack.isEmpty()) {
                    //如果栈是空的,说明当前是根目录,即便是".."也无法再向上找
                    stack.pop();
                }
            }else{
                //其他情况都当成普通目录处理,直接入栈
                stack.push(part);
            }
        }
        //经过遍历后现在栈里面只剩目录名
        StringBuilder result = new StringBuilder("/");//要求以"/"开头
        //这里用新循环遍历是顺序遍历的,并不是按栈的方式"先进后出"
        for(String dir : stack){
            result.append(dir).append("/");
        }
        //去掉刚才遍历过程中在最后添加的"/"(这样单独拎出来就不必在遍历中特别讨论是不是最后一个元素了)
        if (result.length()>1) { //说明不是只有"/"
            result.deleteCharAt(result.length()-1);
        }
        return result.toString();
    }
}
