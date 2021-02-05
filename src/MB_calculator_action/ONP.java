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

        while(!stackSymbols.empty() && !error) {
            if((stackSymbols.peek().equals("log") || stackSymbols.peek().equals("ln") || stackSymbols.peek().equals("sin") || stackSymbols.peek().equals("cos") ||
                    stackSymbols.peek().equals("tg") || stackSymbols.peek().equals(")")))
                error = true;

            else {
                if(stackSymbols.peek().equals("("))
                    error = true;
                else
                    textListONP.add(stackSymbols.pop());

            }
        }

        if(stackSymbols.contains("("))
            error = true;

        if(error)
            textListONP.clear();

        return textListONP;
    }

    public static String count(ArrayList<String> textListONP) {

        Stack<Double> stackSymbols = new Stack<Double>();
        double number1, number2;
        boolean error = false;

        for(String symbol: textListONP){
            if(!error) {

                if (symbol.equals("ln") || symbol.equals("sin") || symbol.equals("cos") || symbol.equals("tg") || symbol.equals("\u221a")) {
                    if (stackSymbols.empty())
                        error = true;
                    else {
                        number1 = stackSymbols.pop();

                        if (symbol.equals("ln"))
                            if(number1 > 0)
                                stackSymbols.add(Math.log(number1));
                            else
                                error = true;
                        else if (symbol.equals("sin"))
                            stackSymbols.add(Math.sin(number1));
                        else if (symbol.equals("cos"))
                            stackSymbols.add(Math.cos(number1));
                        else if (symbol.equals("tg"))
                            if(number1%(Math.PI/2) == 0 && number1%(Math.PI) != 0)
                                error = true;
                            else
                                stackSymbols.add(Math.tan(number1));

                        else if (symbol.equals("\u221a"))
                            if(number1 >= 0)
                                stackSymbols.add(Math.sqrt(number1));
                            else
                                error = true;
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
                            if(number2 != 0)
                                stackSymbols.add(number1 / number2);
                            else
                                error = true;
                        else if (symbol.equals("^"))
                            stackSymbols.add(Math.pow(number1, number2));
                        else if (symbol.equals("log"))
                            if(number2 > 0 && number1 > 0 && number1 != 1)
                                stackSymbols.add(Math.log(number2) / Math.log(number1));
                            else
                                error = true;

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

        return (df.format(Double.valueOf(stackSymbols.peek()))).replace(",",".");
    }
}
