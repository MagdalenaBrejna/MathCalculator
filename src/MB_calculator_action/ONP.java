package MB_calculator_action;

import java.util.ArrayList;
import java.util.Stack;

public class ONP {

    public static int priority(String operator) {

        if(operator.equals("+") || operator.equals("-"))
            return 1;
        else if(operator.equals("*") || operator.equals("/"))
            return 2;
        else if(operator.equals("^"))
            return 3;
        else
            return 0;
    }

    public static ArrayList<String> convertTextToONP(ArrayList<String> textList) {

        ArrayList<String> textListONP = new ArrayList<String>();
        Stack<String> stackSymbols = new Stack<String>();
        boolean error = false;

        for(String symbol: textList){
            if(!error) {

                if (symbol.equals("log") || symbol.equals("ln") || symbol.equals("sin") ||
                        symbol.equals("cos") || symbol.equals("tg") || symbol.equals("\u221a"))
                    stackSymbols.add(symbol);
                else {
                    if (symbol.equals(",")) {
                        while (!stackSymbols.empty() && !(stackSymbols.peek().equals("(")))
                            textListONP.add(stackSymbols.pop());

                        if (stackSymbols.empty())
                            error = true;

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
                                error = true;
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
            }
        }

        if(stackSymbols.contains("("))
            error = true;

        while(!stackSymbols.empty() && !error) {
            if((stackSymbols.peek().equals("log") || stackSymbols.peek().equals("ln") || stackSymbols.peek().equals("sin") || stackSymbols.peek().equals("cos") ||
                    stackSymbols.peek().equals("tg") || stackSymbols.peek().equals("\u221a") || stackSymbols.peek().equals(")")))
                error = true;
            else {
                if(stackSymbols.peek().equals("("))
                    error = true;
                else
                    textListONP.add(stackSymbols.pop());

            }
        }

        if(error)
            textListONP.clear();

        return textListONP;
    }

    public static String count(ArrayList<String> textListONP) {

        Stack<Double> stackSymbols = new Stack<Double>();
        double number1, number2;
        boolean error = false;
        String result = "";

        for(String symbol: textListONP){
            if(!error) {

                if (symbol.equals("ln") || symbol.equals("sin") || symbol.equals("cos") || symbol.equals("tg") || symbol.equals("\u221a")) {
                    if (stackSymbols.empty())
                        error = true;
                    else {
                        number1 = stackSymbols.pop();

                        if (symbol.equals("ln"))
                            stackSymbols.add(Math.log(number1));
                        if (symbol.equals("sin"))
                            stackSymbols.add(Math.sin(number1));
                        if (symbol.equals("cos"))
                            stackSymbols.add(Math.cos(number1));
                        if (symbol.equals("tg"))
                            stackSymbols.add(Math.tan(number1));
                        if (symbol.equals("\u221a"))
                            stackSymbols.add(Math.sqrt(number1));
                    }

                } else if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") ||
                        symbol.equals("/") || symbol.equals("^") || symbol.equals("log")) {
                    if (stackSymbols.size() < 2)
                        error = true;

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
                            stackSymbols.add(number1 / number2);
                        else if (symbol.equals("^"))
                            stackSymbols.add(Math.pow(number1, number2));
                        else if (symbol.equals("log"))
                            stackSymbols.add(Math.log(number2) / Math.log(number1));

                    }
                } else if (symbol.equals("e")) {
                    stackSymbols.add(Math.E);
                } else if (symbol.equals("\u03c0")) {
                    stackSymbols.add(Math.PI);
                } else {
                    stackSymbols.add(Double.parseDouble(symbol));
                }
            }
        }

        if(stackSymbols.size() != 1)
            error = true;

        if(error)
            return "";

        result = String.valueOf(stackSymbols.peek());
        if(result.substring(result.length() - 2).equals(".0"))
            result = result.substring(0, result.length() - 2);

        return result;
    }
}
