package MB_calculator_action;

import java.util.ArrayList;
import java.util.Stack;

public class ONP {

    public static ArrayList<String> Przeksztalc(ArrayList<String> x)
    {
        ArrayList<String> w = new ArrayList<String>();
        Stack<String> stos = new Stack<String>();
        int i = 0;
        boolean blad = false;

        while(i < x.size() && !blad)
        {
            String a = x.get(i);

            if(a == "log" || a == "ln" || a == "sin" || a == "cos" || a == "tg" || a == "\u221a")
                stos.add(a);
            else {
                if(a == ",") {
                    while(!stos.empty() && !(stos.peek() == "(")) {
                        w.add(stos.peek());
                        stos.pop();
                    }
                    if(stos.empty())
                        blad = true;

                } else {
                    if(a == "+" || a == "-" || a == "*" || a == "/" || a == "^") {
                        if(a == "^") {
                            while(!stos.empty() && stos.peek() == "^") {
                                w.add(stos.peek());
                                stos.pop();
                            }

                        } else if(a == "*" || a == "/") {

                            while(!stos.empty() && (stos.peek() == "*" || stos.peek() == "/" || stos.peek() == "^")) {
                                w.add(stos.peek());
                                stos.pop();
                            }

                        }else if(a == "+" || a == "-") {

                            while(!stos.empty() && (stos.peek() == "+" || stos.peek() == "-" || stos.peek() == "*" || stos.peek() == "/" || stos.peek() == "^")) {
                                w.add(stos.peek());
                                stos.pop();
                            }
                        }
                        stos.add(a);

                    } else {

                        if(a == "(") {
                            stos.add(a);
                        } else {
                            if(a == ")") {
                                while(!stos.empty() && !(stos.peek() == "(")) {
                                    w.add(stos.peek());
                                    stos.pop();
                                }
                                if(stos.empty())
                                    blad = true;

                                else {
                                    stos.pop();
                                    if(!stos.empty() && (stos.peek() == "log" || stos.peek() == "ln" || stos.peek() == "sin" || stos.peek() == "cos" || stos.peek() == "tg" || stos.peek() == "\u221a")) {
                                        w.add(stos.peek());
                                        stos.pop();
                                    }
                                }

                            } else
                                w.add(a);
                        }
                    }
                }
            }
            i++;
        }

        while(!stos.empty() && !blad)
        {
            if((stos.peek() == "log" || stos.peek() == "ln" || stos.peek() == "sin" || stos.peek() == "cos" || stos.peek() == "tg" || stos.peek() == "\u221a"|| stos.peek() == "("))
                blad = true;
            else {
                w.add(stos.peek());
                stos.pop();
            }
        }

        if(blad)
            w.clear();

        return w;
    }

    public static String Oblicz( ArrayList<String> x) {
        Stack<Double> stos=new Stack<Double>();
        double b, c;
        int i = 0;
        boolean blad = false;
        String w = "";

        while(i < x.size() && !blad)
        {
            String a = x.get(i);
            if(a == "ln" || a == "sin" || a == "cos" || a == "tg" || a == "\u221a")
            {
                if(stos.empty())
                    blad = true;
                else
                {
                    b = stos.peek();
                    stos.pop();

                    if(a == "ln")
                        stos.add(Math.log(b));
                    if(a == "sin")
                        stos.add(Math.sin(b));
                    if(a == "cos")
                        stos.add(Math.cos(b));
                    if(a == "tg")
                        stos.add(Math.tan(b));
                    if(a == "\u221a")
                        stos.add(Math.sqrt(b));
                }
            } else {

                if(a == "+" || a == "-" || a == "*" || a == "/" || a == "^" || a == "log") {
                    if(stos.size() < 2)
                        blad = true;
                    else {
                        c = stos.peek();
                        stos.pop();
                        b = stos.peek();
                        stos.pop();

                        if(a == "+")
                            stos.add(b + c);
                        if(a == "-")
                            stos.add(b - c);
                        if(a == "*")
                            stos.add(b * c);
                        if(a == "/")
                            stos.add(b / c);
                        if(a == "^")
                            stos.add(Math.pow(b, c));
                        if(a == "log")
                            stos.add(Math.log(c) / Math.log(b));
                    }
                } else {

                    if(a == "e")
                        stos.add(Math.E);
                    else {
                        if(a == "\u03c0")
                            stos.add(Math.PI);
                        else
                            stos.add(Double.parseDouble(a));
                    }
                }
            }
            i++;
        }

        if(stos.size() != 1)
            blad = true;

        if(blad)
            return "";

        w = String.valueOf(stos.peek());
        if(w.substring(w.length() - 2).equals(".0"))
        {
            w = w.substring(0, w.length() - 2);
        }
        return w;
    }
}
