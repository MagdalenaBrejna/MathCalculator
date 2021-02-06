package MB_calculator_action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

public class ONP {

    private static DecimalFormat df = new DecimalFormat("#.######");

    public static int priority(String operator) {

        if(operator.equals("+") || operator.equals("-"))
            return 1;
        else if(operator.equals("*") || operator.equals("/"))
            return 2;
        else if(operator.equals("^") || operator.equals("\u221a") )
            return 3;
        else
            return 0;
    }

    public static ArrayList<String> convertTextToONP(ArrayList<String> textList) throws WrongExpressionException{

        ArrayList<String> textListONP = new ArrayList<String>();
        Stack<String> stackSymbols = new Stack<String>();

        for(String symbol: textList){

            if (symbol.equals("log") || symbol.equals("ln") || symbol.equals("sin") ||
                    symbol.equals("cos") || symbol.equals("tg") || symbol.equals("\u221a"))
                stackSymbols.add(symbol);
            else {
                if (symbol.equals(",")) {
                    while (!stackSymbols.empty() && !(stackSymbols.peek().equals("(")))
                        textListONP.add(stackSymbols.pop());

                    if (stackSymbols.empty())
                        throw new WrongExpressionException("error");

                } else if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/") || symbol.equals("^")) {
                    while (!stackSymbols.empty() && priority(stackSymbols.peek()) >= priority(symbol))
                        textListONP.add(stackSymbols.pop());
                    stackSymbols.push(symbol);

                } else if (symbol.equals("(")) {
                    stackSymbols.push(symbol);

                } else if (symbol.equals(")")) {
                    while (!stackSymbols.empty() && !(stackSymbols.peek().equals("(")))
                        textListONP.add(stackSymbols.pop());

                    if (stackSymbols.empty())
                        throw new WrongExpressionException("error");
                    else {
                        stackSymbols.pop();
                        if (!stackSymbols.empty() && (stackSymbols.peek().equals("log") || stackSymbols.peek().equals("ln") || stackSymbols.peek().equals("sin") ||
                                stackSymbols.peek().equals("cos") || stackSymbols.peek().equals("tg") || stackSymbols.peek().equals("\u221a")))
                            textListONP.add(stackSymbols.pop());
                    }
                } else
                    textListONP.add(symbol);
            }
        }

        while(!stackSymbols.empty()){
            if((stackSymbols.peek().equals("log") || stackSymbols.peek().equals("ln") || stackSymbols.peek().equals("sin") || stackSymbols.peek().equals("cos") ||
                    stackSymbols.peek().equals("tg") || stackSymbols.peek().equals(")")))
                throw new WrongExpressionException("error");

            else {
                if(stackSymbols.peek().equals("("))
                    throw new WrongExpressionException("error");
                else
                    textListONP.add(stackSymbols.pop());

            }
        }

        if(stackSymbols.contains("("))
            throw new WrongExpressionException("error");

        return textListONP;
    }

    public static String count(ArrayList<String> textListONP) throws WrongExpressionException{

        Stack<Double> stackSymbols = new Stack<Double>();
        double number1, number2;

        for(String symbol: textListONP){

            if (symbol.equals("ln") || symbol.equals("sin") || symbol.equals("cos") || symbol.equals("tg") || symbol.equals("\u221a")) {
                if (stackSymbols.empty())
                    throw new WrongExpressionException("error");
                else {
                    number1 = stackSymbols.pop();

                    if (symbol.equals("ln"))
                        if(number1 > 0)
                            stackSymbols.add(Math.log(number1));
                        else
                            throw new WrongExpressionException("error");
                    else if (symbol.equals("sin"))
                        stackSymbols.add(Math.sin(number1));
                    else if (symbol.equals("cos"))
                        stackSymbols.add(Math.cos(number1));
                    else if (symbol.equals("tg"))
                        if(number1%(Math.PI/2) == 0 && number1%(Math.PI) != 0)
                            throw new WrongExpressionException("error");
                        else
                            stackSymbols.add(Math.tan(number1));
                    else if (symbol.equals("\u221a"))
                        if(number1 >= 0)
                            stackSymbols.add(Math.sqrt(number1));
                        else
                            throw new WrongExpressionException("error");
                }

            } else if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") ||
                    symbol.equals("/") || symbol.equals("^") || symbol.equals("log")) {
                if (stackSymbols.size() < 2)
                    throw new WrongExpressionException("error");

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
                            throw new WrongExpressionException("error");
                    else if (symbol.equals("^"))
                        stackSymbols.add(Math.pow(number1, number2));
                    else if (symbol.equals("log"))
                        if(number2 > 0 && number1 > 0 && number1 != 1)
                            stackSymbols.add(Math.log(number2) / Math.log(number1));
                        else
                            throw new WrongExpressionException("error");

                }
            } else if (symbol.equals("e")) {
                stackSymbols.add(Math.E);
            } else if (symbol.equals("\u03c0")) {
                stackSymbols.add(Math.PI);
            } else {
                stackSymbols.add(Double.parseDouble(symbol));
            }
        }

        if(stackSymbols.size() != 1)
            throw new WrongExpressionException("error");

        return (df.format(Double.valueOf(stackSymbols.peek()))).replace(",",".");
    }
}
