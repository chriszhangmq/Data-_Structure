package Stack.Calculator.Infix2SuffixCalculator;


import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式计算
 * Created by Chris on 2019/11/11.
 */
public class SuffixCalculator {
    private List<String> suffixExpression;

    /**
     * 后缀表达式计算
     * 思路：从左到右，扫描表达式，遇到数字入栈，遇到运算符，将两个数字出栈并计算，然后将结果入栈。直到扫描完
     * @param suffixExpression
     * @return
     */
    public int calcultor(List<String> suffixExpression){
        Stack<String> stack = new Stack<String>();
        int res = 0;
        int num1;
        int num2;

        for(String ele : suffixExpression){
            //扫描到运算符
            if(ele.equals("+") || ele.equals("-") ||
               ele.equals("*") || ele.equals("/") ) {
                num2 = Integer.parseInt(stack.pop());
                num1 = Integer.parseInt(stack.pop());
                res = compute(num1,num2,ele);
                stack.push("" + res);
            }
            //扫描到数字
            else {
                stack.push(ele);
            }
        }

        return Integer.parseInt(stack.pop());
    }

    /**
     * 根据运算符，计算两个数
     * @param num1
     * @param num2
     * @param operator
     * @return
     */
    private int compute(int num1, int num2, String operator){
        int res=0;
        switch (operator){
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num1 / num2;
                break;
            default:
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        //后缀表达式处理类
        SuffixCalculator suffixCalculator = new SuffixCalculator();
        //中缀表达式->后缀表达式 处理类
        Infix2Suffix infix2Suffix = new Infix2Suffix();
        //中缀表达式
//        String expression = "1+((2+3)*4)-6";
        String expression = "(3*(4+5))-(4/2)*1";
        //计算结果
        int res=0;

        //中缀->后缀
        List<String> infixList = infix2Suffix.infix2List(expression);
        System.out.println(infixList);

        //中缀->后缀
        List<String> suffixExpression = infix2Suffix.infix2Suffix(infixList);
        System.out.println(suffixExpression);

        //后缀表达式计算
        res = suffixCalculator.calcultor(suffixExpression);
        System.out.println(res);
    }
}
