package MB_calculator_layout;

import MB_calculator_action.ONP;
import MB_calculator_action.Translation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScientificCalculator extends Calculator {

    private JPanel rightPanel;

    private ArrayList<String> textList = new ArrayList<String>();

    private boolean ifReady = false;

    private String textONP = "";

    public ScientificCalculator() {

        super();

        centerPanel.setPreferredSize(new Dimension(320,500));
        centerPanel.setLocation(0,300);

        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200,500));
        rightPanel.setLocation(510,300);
        rightPanel.setLayout(null);
        add(BorderLayout.EAST, rightPanel);

        int dlx = 88, dly = 60, x = 20, y = 20;

        basicButtons[0] = new BasicButton("1", x, y, new PressReaction(), centerPanel);
        basicButtons[1] = new BasicButton("2", 2 * x + dlx, y, new PressReaction(), centerPanel);
        basicButtons[2] = new BasicButton("3", 3 * x + 2 * dlx, y, new PressReaction(), centerPanel);
        basicButtons[3] = new BasicButton("4", x, 2 * y + dly, new PressReaction(), centerPanel);
        basicButtons[4] = new BasicButton("5", 2 * x + dlx, 2 * y + dly, new PressReaction(), centerPanel);
        basicButtons[5] = new BasicButton("6", 3 * x + 2 * dlx, 2 * y + dly, new PressReaction(), centerPanel);
        basicButtons[6] = new BasicButton("7", x, 3 * y + 2 * dly, new PressReaction(), centerPanel);
        basicButtons[7] = new BasicButton("8", 2 * x + dlx, 3 * y + 2 * dly, new PressReaction(), centerPanel);
        basicButtons[8] = new BasicButton("9", 3 * x + 2 * dlx, 3 * y + 2 * dly, new PressReaction(), centerPanel);
        basicButtons[9] = new BasicButton("=", x, 4 * y + 3 * dly, new countReaction(), centerPanel);
        basicButtons[10] = new BasicButton("0", 2 * x + dlx, 4 * y + 3 * dly, new PressReaction(), centerPanel);
        basicButtons[11] = new BasicButton(".", 3 * x + 2 * dlx, 4 * y + 3 * dly, new PressReaction(), centerPanel);

        x = 20;
        y = 20;
        dlx = 60;
        dly = 45;

        functionalButtons = new FunctionalButton[18];
        functionalButtons[0] = new FunctionalButton("+", x + 20, y, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[1] = new FunctionalButton("-", 2*x + dlx + 20, y, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[2] = new FunctionalButton("*", x + 20, 2*y + dly, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[3] = new FunctionalButton("/", 2*x + dlx + 20, 2*y + dly, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[4] = new FunctionalButton("(", x + 20, 3*y + 2*dly, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[5] = new FunctionalButton(")", 2*x + dlx + 20, 3*y + 2*dly, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[6] = new FunctionalButton(",", x + 20, 4*y + 3*dly, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[7] = new FunctionalButton("^", 2*x + dlx + 20, 4*y + 3*dly, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[8] = new FunctionalButton("\u221a", x + 20, 5*y + 4*dly, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[9] = new FunctionalButton("\u03c0", 2*x + dlx + 20, 5*y + 4*dly, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[10] = new FunctionalButton("log", x + 20, 6*y + 5*dly, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[11] = new FunctionalButton("ln", 2*x + dlx + 20, 6*y + 5*dly, dlx, dly, new PressReaction(), rightPanel);
        functionalButtons[12] = new FunctionalButton("e",  x, 6*y + 5*dly, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[13] = new FunctionalButton("sin", 2*x + dlx - 2, 6*y + 5*dly, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[14] = new FunctionalButton("cos", 3*x + 2*dlx - 2, 6*y + 5*dly, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[15] = new FunctionalButton("tg", 4*x + 3*dlx - 2, 6*y + 5*dly, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[16] = new FunctionalButton("<<", 2*x + dlx, 7*y + 6*dly, dlx, dly, new DELReaction(), centerPanel);
        functionalButtons[17] = new FunctionalButton("AC", 3*x + 2*dlx, 7*y + 6*dly, dlx, dly, new ACReaction(), centerPanel);
    }

    class DELReaction implements ActionListener{
        public void actionPerformed(ActionEvent DELEvent){

            if (text != "") {
                if (!ifReady) {
                    char lastCharacter = text.charAt(text.length() - 1);
                    if (lastCharacter >= 'g' && lastCharacter <= 's') {
                        String last2Characters = text.substring(text.length() - 2);
                        if (last2Characters.equals("ln") || last2Characters.equals("tg"))
                            text = text.substring(0, text.length() - 2);
                        else
                            text = text.substring(0, text.length() - 3);

                    } else
                        text = text.substring(0, text.length() - 1);

                    calculatorTextField.setText(text);
                    textList.remove(textList.size() - 1);

                } else {
                    ifReady = false;
                    calculatorTextField.setText(text);
                }
            }
        }
    }

    class ACReaction implements ActionListener{
        public void actionPerformed(ActionEvent ACEvent){
            ifReady = false;
            text = "";
            calculatorTextField.setText(text);
            textList.clear();
        }
    }

    class countReaction implements ActionListener{
        public void actionPerformed(ActionEvent countEvent){

            ArrayList<String> textList2 = new ArrayList<String>();
            textONP = "";
            boolean error = false;

            if (textList.size() > 0) {
                textList2 = Translation.createEntireNumbers(textList);
                if (textList2.size() > 0) {
                    textList2 = ONP.convertTextToONP(textList2);
                    if (textList2.size() > 0) {
                        textONP = ONP.Oblicz(textList2);
                        if (textONP == "")
                            error = true;
                    } else
                        error = true;
                } else
                    error = true;
            }

            if (error) {
                calculatorTextField.setText("ERROR");
            } else {
                ifReady = true;
                calculatorTextField.setText(textONP);
            }
        }
    }

    class PressReaction implements ActionListener {
        public void actionPerformed(ActionEvent pressEvent) {

            String buttonText = ((JButton) pressEvent.getSource()).getText();

            if (calculatorTextField.getText().equals("ERROR")) {
                if (buttonText == "<<")
                    calculatorTextField.setText(text);
                else if (buttonText == "AC") {
                    text = "";
                    calculatorTextField.setText(text);
                    textList.clear();
                }

            } else {
                switch (buttonText) {
                    case ("\u221a"):
                        if (ifReady) {
                            text = "";
                            textList.clear();
                            ifReady = false;
                        }
                        text += "\u221a";
                        textList.add("\u221a");
                        text += "(";
                        textList.add("(");
                        calculatorTextField.setText(text);
                        break;

                    case ("\u03c0"):

                        if (ifReady) {
                            text = "";
                            textList.clear();
                            ifReady = false;
                        }
                        text += "\u03c0";
                        calculatorTextField.setText(text);
                        textList.add("\u03c0");
                        break;

                    default:
                        if (ifReady) {
                            ifReady = false;
                            if (!(buttonText == "+" || buttonText == "-" || buttonText == "*" || buttonText == "/" || buttonText == "^")) {
                                text = "";
                                textList.clear();
                            } else {
                                text = textONP;
                                textList.clear();
                                textList.add(text);
                            }
                        }
                        text += buttonText;
                        textList.add(buttonText);
                        if (buttonText == "ln" || buttonText == "sin" || buttonText == "cos" || buttonText == "tg" || buttonText == "log") {
                            text += "(";
                            textList.add("(");
                        }
                        calculatorTextField.setText(text);

                        break;
                }
            }
        }
    }

}
