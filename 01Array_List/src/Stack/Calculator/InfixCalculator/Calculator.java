package Stack.Calculator.InfixCalculator;

/**
 * 使用栈：实现计算器——中缀表达式求解
 * 思路：两个栈：存放数据、运算符，根据运算符的优先级，确定进行符号入栈还是数字运算
 * 问题：计算最后一位数字时，可能出错：
 * 原因：计算“3*4+5-4/2+1” 最终栈内表达式为“17-2+1”， 而计算顺序为 2+1 -> 17-3 -> res = 14
 *       无法识别负数
 * 解决方法：使用后缀表达式计算：3 4 * 5 + 4 2 / - 1 +
 * Created by Chris on 2019/11/10.
 */
public class Calculator {

    /**
     * 计算表达式
     * @param num1
     * @param num2
     * @param operator
     * @return
     */
    public int compute2num(int num1, int num2, int operator){
        int res = 0;
        switch (operator){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * 判断是否为运算符
     * @param val
     * @return
     */
    public boolean isOperatoe(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 运算符优先级判断
     * @param operator
     * @return
     */
    public int priority(int operator){
        if(operator == '*' || operator == '/'){
            return 1;
        }
        else if(operator == '+' || operator == '-'){
            return 0;
        }
        else {
            return -1;
        }
    }

    public int compute(String expression){
        //两个栈
        LinkedListStack numstack = new LinkedListStack(10);
        LinkedListStack operatorStack = new LinkedListStack(10);

        //使用的变量
        //1、扫描表达式：当前所在位置
        int index = 0;
        //2、存储两个数
        int num1 = 0;
        int num2 = 0;
        //3、存储运算符
        int operator = 0;
        char ch = ' ';
        //4、存储结果
        int res = 0;

        //开始计算表达式
        while (true) {
            //获得字符
            ch = expression.substring(index,index + 1).charAt(0);
            //字符为运算符
            if(isOperatoe(ch)){
                //当前的符号栈不为空
                if(!operatorStack.isEmpty()){
                    //当前的符号优先级小于栈内符号的优先级：计算两个数
                    if(priority(ch) <= priority(operatorStack.peek())){
                        num2 = numstack.pop();
                        num1 = numstack.pop();
                        operator = operatorStack.pop();
                        res = compute2num(num1,num2,operator);
                        numstack.push(res);
                        operatorStack.push(ch);
                    }
                    //当前的符号优先级大于栈内符号的优先级：运算符入栈
                    else {
                        operatorStack.push(ch);
                    }
                }
                //当前符号栈为空
                else {
                    operatorStack.push(ch);
                }
            }
            //字符为数字
            else {
                numstack.push(ch - 48);
            }
            index++ ;

            //是否扫描都表达式的最后一位
            if(index >= expression.length()){
                break;
            }
        }

        //当表达式扫描完毕，就从数字栈、运算符栈中pop数据，进行计算
        while ((true)) {
            if(operatorStack.isEmpty()){
                break;
            }
            num2 = numstack.pop();
            num1 = numstack.pop();
            operator = operatorStack.pop();
            res = compute2num(num1,num2,operator);
            numstack.push(res);
        }

        res = numstack.pop();

        return res;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String expression = "3*4+5-4/2+1";
        int res;

        res = calculator.compute(expression);
        System.out.println(expression + " = " + res);
    }
}
