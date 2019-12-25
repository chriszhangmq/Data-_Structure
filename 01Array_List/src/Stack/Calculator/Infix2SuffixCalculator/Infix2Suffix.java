package Stack.Calculator.Infix2SuffixCalculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 中缀转后缀表达式：
 * 思路： 1、中缀表达式-->列表：方便后期操作
 *        2、中缀表达式-->后缀表达式：
 *           （1）创建1个栈：存储符号，一个列表：存储数字
 *                开始遍历表达式：
 *           （2）遇到数字：直接压入数字列表
 *           （3）遇到“（”：压入符号栈
 *           （4）遇到运算符：和符号栈的栈顶元素比较优先级；
 *                            当前运算符 <= 栈顶元素：将栈内元素一直出栈，放到队列（直到，栈顶元素 < 当前运算符，为止）
 *                                                    并且将当前元素入栈；
 *                            当前运算符 > 栈顶元素：直接将当前运算符入栈；
 *           （5）遇到“）”：将符号栈元素出栈，放到队列，直到遇到“（”为止，并且将该“（”出栈——消除一对括号
 *                表达式遍历完
 *           （6）将所有符号栈内的元素出栈，放到列表中——即：得到后缀表达式
 * Created by Chris on 2019/11/11.
 */
public class Infix2Suffix {
    private String expression;

    private final int ADD = 1;
    private final int SUB = 1;
    private final int MUL = 2;
    private final int DIV = 2;
    /**
     * 中缀表达式-->列表
     * @param expression
     * @return
     */
    public List<String> infix2List(String expression){
        List<String> list = new ArrayList<String>();
        int index = 0;      //遍历表达式
        String joint;       //保存拼接的数字
        char character;     //遍历的表达式的元素
        while (index < expression.length()){
            //符号：直接入栈
            if((character = expression.charAt(index)) < 48 || (character = expression.charAt(index)) > 57){
                list.add("" + character);
                index++;
            }
            //数字：需要考虑数字拼接问题
            else {
                joint = "";
                while (index < expression.length() &&
                        (character= expression.charAt(index)) >= 48 &&
                        (character= expression.charAt(index)) <= 57){
                    joint += character;
                    index++;
                }
                list.add(joint);
            }
        }
        return list;
    }

    /**
     * 中缀-->后缀表达式
     * @param list
     * @return
     */
    public List<String> infix2Suffix(List<String> list){
        Stack<String> symbolStack = new Stack<String>();
        List<String> res = new ArrayList<String>();

        for(String elem : list ){
            //遍历到数字
            if(elem.matches("\\d+")){
                res.add(elem);
            }
            //遍历到括号
            else if(elem.equals("(")){
                symbolStack.push(elem);
            }
            else if(elem.equals(")")){
                while (!symbolStack.peek().equals("(")){
                    res.add(symbolStack.pop());
                }
                symbolStack.pop(); //弹出 "("
            }
            //遇到运算符的操作
            else{
                //符号栈不为空, 当前运算符优先级 <= 栈顶元素：符号栈运算符出栈
                while (!symbolStack.isEmpty() && priority(elem) <= priority(symbolStack.peek())){
                    res.add(symbolStack.pop());
                }
                //将当前运算符入栈
                symbolStack.push(elem);
            }
        }

        //当遍历完中缀表达式后，将剩余的符号栈元素全部出栈
        while (!symbolStack.isEmpty()){
            res.add(symbolStack.pop());
        }

        return res;
    }

    public int priority(String elem){
        int prioirty = -1;
        switch (elem){
            case "+":
                prioirty = ADD;
                break;
            case "-":
                prioirty = SUB;
                break;
            case "*":
                prioirty = MUL;
                break;
            case "/":
                prioirty = DIV;
                break;
            default:    //即：“（”优先级为-1
                break;
        }
        return prioirty;
    }

    public static void main(String[] args) {
        Infix2Suffix infix2Suffix = new Infix2Suffix();
        String expression = "1+((2+3)*4)-5";
        List<String> list = infix2Suffix.infix2List(expression);

        System.out.println(list);
        System.out.println(infix2Suffix.infix2Suffix(list));
    }
}
