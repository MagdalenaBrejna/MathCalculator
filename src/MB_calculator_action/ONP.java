package MB_calculator_action;

import java.util.ArrayList;
import java.util.Stack;

public class ONP {

    public static int priorytet(String operator) {

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
        int textPosition = 0;
        boolean error = false;

        while(textPosition < textList.size() && !error) {
            String symbol = textList.get(textPosition);

            if(symbol.equals("log") || symbol.equals("ln") || symbol.equals("sin") || symbol.equals("cos") || symbol.equals("tg") || symbol.equals("\u221a"))
                stackSymbols.add(symbol);
            else {
                if(symbol == ",") {
                    while(!stackSymbols.empty() && !(stackSymbols.peek() == "(")) {
                        textListONP.add(stackSymbols.peek());
                        stackSymbols.pop();
                    }
                    if(stackSymbols.empty())
                        error = true;

                } else {
                    if(symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/") || symbol.equals("^")) {
                        while(!stackSymbols.empty() && priorytet(stackSymbols.peek()) >= priorytet(symbol))
                            textListONP.add(stackSymbols.pop());
                        stackSymbols.push(symbol);

                    } else if(symbol == "(") {
                        stackSymbols.push(symbol);

                    } else if(symbol == ")") {
                        while (!stackSymbols.empty() && !(stackSymbols.peek() == "("))
                            textListONP.add(stackSymbols.pop());

                        if (stackSymbols.empty())
                            error = true;
                        else {
                            stackSymbols.pop();
                            if(!stackSymbols.empty() && (stackSymbols.peek() == "log" || stackSymbols.peek() == "ln" || stackSymbols.peek() == "sin" || stackSymbols.peek() == "cos" || stackSymbols.peek() == "tg" || stackSymbols.peek() == "\u221a"))
                                textListONP.add(stackSymbols.pop());
                        }
                    } else
                        textListONP.add(symbol);
                }
            }
            textPosition++;
        }

        while(!stackSymbols.empty() && !error) {
            if((stackSymbols.peek().equals("log") || stackSymbols.peek().equals("ln") || stackSymbols.peek().equals("sin") || stackSymbols.peek().equals("cos") || stackSymbols.peek().equals("tg") || stackSymbols.peek().equals("\u221a") || stackSymbols.peek().equals(")")))
                error = true;
            else {
                textListONP.add(stackSymbols.pop());
                //stackSymbols.pop();
            }
        }

        if(error)
            textListONP.clear();

        return textListONP;
    }

    public static String Oblicz( ArrayList<String> textListONP) {

        Stack<Double> stackSymbols = new Stack<Double>();
        double number1, number2;
        int position = 0;
        boolean error = false;
        String result = "";

        while(position < textListONP.size() && !error) {
            String symbol = textListONP.get(position);

            if (symbol.equals("ln") || symbol.equals("sin") || symbol.equals("cos") || symbol.equals("tg") || symbol.equals("\u221a")) {
                if (stackSymbols.empty())
                    error = true;
                else {
                    number1 = stackSymbols.peek();
                    stackSymbols.pop();

                    if (symbol == "ln")
                        stackSymbols.add(Math.log(number1));
                    if (symbol == "sin")
                        stackSymbols.add(Math.sin(number1));
                    if (symbol == "cos")
                        stackSymbols.add(Math.cos(number1));
                    if (symbol == "tg")
                        stackSymbols.add(Math.tan(number1));
                    if (symbol == "\u221a")
                        stackSymbols.add(Math.sqrt(number1));
                }

            } else if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/") || symbol.equals("^") || symbol.equals("log")) {
                if (stackSymbols.size() < 2)
                    error = true;
                else {
                    number2 = stackSymbols.peek();
                    stackSymbols.pop();
                    number1 = stackSymbols.peek();
                    stackSymbols.pop();

                    if (symbol == "+")
                        stackSymbols.add(number1 + number2);
                    if (symbol == "-")
                        stackSymbols.add(number1 - number2);
                    if (symbol == "*")
                        stackSymbols.add(number1 * number2);
                    if (symbol == "/")
                        stackSymbols.add(number1 / number2);
                    if (symbol == "^")
                        stackSymbols.add(Math.pow(number1, number2));
                    if (symbol == "log")
                        stackSymbols.add(Math.log(number2) / Math.log(number1));
                }
            } else if (symbol == "e") {
                stackSymbols.add(Math.E);
            } else if (symbol == "\u03c0") {
                stackSymbols.add(Math.PI);
            } else {
                stackSymbols.add(Double.parseDouble(symbol));
            }

            position++;
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
