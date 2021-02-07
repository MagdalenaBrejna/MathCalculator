package MB_calculator_action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

public class ONP {

    private final static DecimalFormat df = new DecimalFormat("#.######");
    private final static String sqrt = "\u221a";
    private final static String pi = "\u03c0";

    public static int priority(String operator) {
    //set priority to different kind of mathematical operators

        if(operator.equals("+") || operator.equals("-"))
            return 1;
        else if(operator.equals("*") || operator.equals("/"))
            return 2;
        else if(operator.equals("^") || operator.equals(sqrt) )
            return 3;
        else
            return 0;
    }

    public static ArrayList<String> convertTextToONP(ArrayList<String> textList) throws WrongExpressionException{
    //create ONP expression

        ArrayList<String> textListONP = new ArrayList<String>();
        Stack<String> stackSymbols = new Stack<String>();

        for(String symbol: textList){

            if (symbol.equals("log") || symbol.equals("ln") || symbol.equals("sin") ||
                    symbol.equals("cos") || symbol.equals("tg") || symbol.equals(sqrt))
                stackSymbols.add(symbol);
            else {
                if (symbol.equals(",")) {
                    while (!stackSymbols.empty() && !(stackSymbols.peek().equals("(")))
                        textListONP.add(stackSymbols.pop());

                    //if (stackSymbols.empty())
                        //throw new WrongExpressionException("Lack of ' ( '.");

                } else {

                    if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/") || symbol.equals("^")) {
                        while (!stackSymbols.empty() && priority(stackSymbols.peek()) >= priority(symbol))
                            textListONP.add(stackSymbols.pop());
                        stackSymbols.push(symbol);

                    } else if (symbol.equals("(")) {
                        stackSymbols.push(symbol);

                    } else if (symbol.equals(")")) {
                        while (!stackSymbols.empty() && !(stackSymbols.peek().equals("(")))
                            textListONP.add(stackSymbols.pop());

                        if (stackSymbols.empty())
                            throw new WrongExpressionException("Lack of ' ( '.");
                        else {
                            stackSymbols.pop();
                            if (!stackSymbols.empty() && (stackSymbols.peek().equals("log") || stackSymbols.peek().equals("ln") || stackSymbols.peek().equals("sin") ||
                                    stackSymbols.peek().equals("cos") || stackSymbols.peek().equals("tg") || stackSymbols.peek().equals(sqrt)))
                                textListONP.add(stackSymbols.pop());
                        }
                    } else
                        textListONP.add(symbol);
                }
            }
        }

        while(!stackSymbols.empty()){
            if((stackSymbols.peek().equals("log") || stackSymbols.peek().equals("ln") || stackSymbols.peek().equals("sin") || stackSymbols.peek().equals("cos") ||
                    stackSymbols.peek().equals("tg") || stackSymbols.peek().equals(")")))
                throw new WrongExpressionException("Lack of function's argument.");

            else {
                if(stackSymbols.peek().equals("("))
                    throw new WrongExpressionException("Lack of ' ) '.");
                else
                    textListONP.add(stackSymbols.pop());

            }
        }

        if(stackSymbols.contains("("))
            throw new WrongExpressionException("Lack of ' ) '.");

        return textListONP;
    }

    public static String count(ArrayList<String> textListONP) throws WrongExpressionException{
    //count the ONP expression

        Stack<Double> stackSymbols = new Stack<Double>();
        double number1, number2;

        for(String symbol: textListONP){

            if (symbol.equals("ln") || symbol.equals("sin") || symbol.equals("cos") || symbol.equals("tg") || symbol.equals(sqrt)) {

                //if the symbol is a one argument function but there is no number (function argument) throw an exception
                if (stackSymbols.empty())
                    throw new WrongExpressionException("Lack of function's argument.");

                //if the symbol is a one argument function and there is a number (function argument), count an operation and push the result to the stack.
                else {
                    number1 = stackSymbols.pop();

                    if (symbol.equals("ln"))
                        if(number1 > 0)
                            stackSymbols.add(Math.log(number1));
                        else
                            throw new WrongExpressionException("' ln ' function argument is lower than 0.");
                    else if (symbol.equals("sin"))
                        stackSymbols.add(Math.sin(number1));
                    else if (symbol.equals("cos"))
                        stackSymbols.add(Math.cos(number1));
                    else if (symbol.equals("tg"))
                        if(number1%(Math.PI) == 0 && number1%(Math.PI) != 0)
                            throw new WrongExpressionException("function ' tg ' does't exist for this arcument.");
                        else
                            stackSymbols.add(Math.tan(number1));
                    else if (symbol.equals("\u221a"))
                        if(number1 >= 0)
                            stackSymbols.add(Math.sqrt(number1));
                        else
                            throw new WrongExpressionException("' sqrt ' argument lower than 0.");
                }

            } else if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") ||
                    symbol.equals("/") || symbol.equals("^") || symbol.equals("log")) {

                //if the symbol is a two argument function but there aren't at least 2 numbers on the stack, throw an exception
                if (stackSymbols.size() < 2)
                    throw new WrongExpressionException("Too many operators to create operation.");

               //if the symbol is a two argument function but there are at least 2 numbers on the stack, count an operation and push the result to the stack.
                else {
                    number2 = stackSymbols.pop();
                    number1 = stackSymbols.pop();

                    if (symbol.equals("+"))
                        stackSymbols.add(number1 + number2);
                    else if (symbol.equals("-"))
                        stackSymbols.add(number1 - number2);
                    else if (symbol.equals("*"))
                        stackSymbols.add(number1 * number2);
                    else if (symbol.equals("/"))
                        if(number2 != 0)
                            stackSymbols.add(number1 / number2);
                        else
                            throw new WrongExpressionException("Division by 0.");
                    else if (symbol.equals("^"))
                        stackSymbols.add(Math.pow(number1, number2));
                    else if (symbol.equals("log"))
                        if(number2 > 0 && number1 > 0 && number1 != 1)
                            stackSymbols.add(Math.log(number2) / Math.log(number1));
                        else
                            throw new WrongExpressionException("Wrong ' log ' function arguments");

                }

            //if the symbol equals number (including e and pi), add it to the stack
            } else if (symbol.equals("e")) {
                stackSymbols.add(Math.E);
            } else if (symbol.equals(pi)) {
                stackSymbols.add(Math.PI);
            } else {
                stackSymbols.add(Double.parseDouble(symbol));
            }
        }

        //if the stack contains a result and other symbols or doesn't contain a result, throw an exception.
        if(stackSymbols.size() != 1)
            throw new WrongExpressionException("Too many operators to create operation.");

        return (df.format(Double.valueOf(stackSymbols.peek()))).replace(",",".");
    }
}
