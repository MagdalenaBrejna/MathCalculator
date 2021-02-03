package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScientificCalculator extends Calculator {

    JPanel rightPanel;

    private String text1 = "", text2 = "";
    private ArrayList<String> textList1 = new ArrayList<String>();
    private boolean czyWynik = false;

    public ScientificCalculator() {

        super();

        int dlx = 80, dly = 60, x = 20, y = 20;

        basicButtons[0] = new BasicButton("1", x, y, new PressReaction(), centerPanel);
        basicButtons[1] = new BasicButton("2", 2 * x + dlx, y, new PressReaction(), centerPanel);
        basicButtons[2] = new BasicButton("3", 3 * x + 2 * dlx, y, new PressReaction(), centerPanel);
        basicButtons[3] = new BasicButton("4", x, 2 * y + dly, new PressReaction(), centerPanel);
        basicButtons[4] = new BasicButton("5", 2 * x + dlx, 2 * y + dly, new PressReaction(), centerPanel);
        basicButtons[5] = new BasicButton("6", 3 * x + 2 * dlx, 2 * y + dly, new PressReaction(), centerPanel);
        basicButtons[6] = new BasicButton("7", x, 3 * y + 2 * dly, new PressReaction(), centerPanel);
        basicButtons[7] = new BasicButton("8", 2 * x + dlx, 3 * y + 2 * dly, new PressReaction(), centerPanel);
        basicButtons[8] = new BasicButton("9", 3 * x + 2 * dlx, 3 * y + 2 * dly, new PressReaction(), centerPanel);
        basicButtons[9] = new BasicButton("=", x, 4 * y + 3 * dly, new PressReaction(), centerPanel);
        basicButtons[10] = new BasicButton("0", 2 * x + dlx, 4 * y + 3 * dly, new PressReaction(), centerPanel);
        basicButtons[11] = new BasicButton(".", 3 * x + 2 * dlx, 4 * y + 3 * dly, new PressReaction(), centerPanel);

        functionalButtons = new FunctionalButton[18];
        rightPanel = new JPanel();
        add(BorderLayout.EAST, rightPanel);

        functionalButtons[0] = new FunctionalButton("+", dlx, dly, x + 5, y, new PressReaction(), rightPanel);
        functionalButtons[1] = new FunctionalButton("-", dlx, dly, 2 * x + dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[2] = new FunctionalButton("*", dlx, dly, 3 * x + 2 * dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[3] = new FunctionalButton("/", dlx, dly, 4 * x + 3 * dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[4] = new FunctionalButton("(", dlx, dly, x + 5, y, new PressReaction(), rightPanel);
        functionalButtons[5] = new FunctionalButton(")", dlx, dly, 2 * x + dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[6] = new FunctionalButton("DEL", dlx, dly, 3 * x + 2 * dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[7] = new FunctionalButton("AC", dlx, dly, 4 * x + 3 * dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[8] = new FunctionalButton(",", dlx, dly, x + 5, y, new PressReaction(), rightPanel);
        functionalButtons[9] = new FunctionalButton("^", dlx, dly, 2 * x + dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[10] = new FunctionalButton("\u221a", dlx, dly, 3 * x + 2 * dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[11] = new FunctionalButton("\u03c0", dlx, dly, 4 * x + 3 * dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[12] = new FunctionalButton("log", dlx, dly, 3 * x + 2 * dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[13] = new FunctionalButton("ln", dlx, dly, 4 * x + 3 * dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[14] = new FunctionalButton("e", dlx, dly, 3 * x + 2 * dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[15] = new FunctionalButton("sin", dlx, dly, 4 * x + 3 * dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[16] = new FunctionalButton("cos", dlx, dly, 3 * x + 2 * dlx + 2, y, new PressReaction(), rightPanel);
        functionalButtons[17] = new FunctionalButton("tg", dlx, dly, 4 * x + 3 * dlx + 2, y, new PressReaction(), rightPanel);

    }

    class PressReaction implements ActionListener {
        public void actionPerformed(ActionEvent zdarzenie) {
            String buttonText = ((JButton) zdarzenie.getSource()).getText();

            if (pole.getText().equals("ERROR")) {
                if (buttonText == "DEL")
                    pole.setText(text1);
                if (buttonText == "AC") {
                    text1 = "";
                    pole.setText(text1);
                    textList1.clear();
                }
            } else {
                switch (buttonText) {
                    case ("DEL"):
                        if (text1.length() > 0) {
                            if (!czyWynik) {
                                char character = text1.charAt(text1.length() - 1);
                                if (character >= 'g' && character <= 's') {
                                    String character2 = text1.substring(text1.length() - 2);

                                    if (character2.equals("ln") || character2.equals("tg"))
                                        text1 = text1.substring(0, text1.length() - 2);
                                    else
                                        text1 = text1.substring(0, text1.length() - 3);
                                } else
                                    text1 = text1.substring(0, text1.length() - 1);

                                pole.setText(text1);
                                textList1.remove(textList1.size() - 1);

                            } else {
                                czyWynik = false;
                                pole.setText(text1);
                            }
                        }
                        break;

                    case ("AC"):
                        if (czyWynik)
                            czyWynik = false;
                        text1 = "";
                        pole.setText(text1);
                        textList1.clear();
                        break;

                    case ("\u221a"):
                        if (czyWynik) {
                            text1 = "";
                            textList1.clear();
                            czyWynik = false;
                        }
                        text1 += "\u221a";
                        textList1.add("\u221a");
                        text1 += "(";
                        textList1.add("(");
                        pole.setText(text1);
                        break;

                    case ("\u03c0"):

                        if (czyWynik) {
                            text1 = "";
                            textList1.clear();
                            czyWynik = false;
                        }
                        text1 += "\u03c0";
                        pole.setText(text1);
                        textList1.add("\u03c0");
                        break;

                    case ("="):

                        ArrayList<String> textList2 = new ArrayList<String>();
                        text2 = "";
                        boolean blad = false;
                        if (textList1.size() > 0) {
                            //textList2 = Porzadkowanie.Porz(textList1);
                            if (textList2.size() > 0) {
                                //textList2 = ONP.Przeksztalc(textList2);
                                if (textList2.size() > 0) {
                                    //text2 = ONP.Oblicz(textList2);
                                    if (text2 == "")
                                        blad = true;
                                } else
                                    blad = true;
                            } else
                                blad = true;
                        }
                        if (blad) {
                            pole.setText("ERROR");
                        } else {
                            czyWynik = true;
                            pole.setText(text2);
                        }
                        break;
                    default:
                        if (czyWynik) {
                            czyWynik = false;
                            if (!(buttonText == "+" || buttonText == "-" || buttonText == "*" || buttonText == "/" || buttonText == "^")) {
                                text1 = "";
                                textList1.clear();
                            } else {
                                text1 = text2;
                                textList1.clear();
                                textList1.add(text1);
                            }
                        }
                        text1 += buttonText;
                        textList1.add(buttonText);
                        if (buttonText == "ln" || buttonText == "sin" || buttonText == "cos" || buttonText == "tg" || buttonText == "log") {
                            text1 += "(";
                            textList1.add("(");
                        }
                        pole.setText(text1);

                        break;
                }
            }
        }
    }

}
