package MB_calculator_layout;

import MB_calculator_action.TextResultPreparations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScientificCalculator extends Calculator {

    private ArrayList<String> textList = new ArrayList<String>();

    private boolean ifReady = false;

    private String textONP = "";

    public ScientificCalculator() {

        super();

        centerPanel.setPreferredSize(new Dimension(320,500));
        centerPanel.setLocation(0,300);

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
        basicButtons[9] = new BasicButton("=", x, 4 * y + 3 * dly, new CountReaction(), centerPanel);
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

        basicButtons[9].setBackground(new java.awt.Color(245, 197, 174));
        functionalButtons[16].setBackground(new java.awt.Color(245, 197, 174));
        functionalButtons[17].setBackground(new java.awt.Color(245, 197, 174));
    }

    class DELReaction implements ActionListener{
        public void actionPerformed(ActionEvent DELEvent){

            if (!text.equals(""))
                if (calculatorTextField.getText().equals("ERROR")) {
                    calculatorTextField.setText(text);

                }else if (!ifReady) {
                    text = TextResultPreparations.returnPreviousText(text);
                    calculatorTextField.setText(text);
                    textList.remove(textList.size() - 1);

                } else {
                    ifReady = false;
                    calculatorTextField.setText(text);
                }

            calculatorResultField.setText(TextResultPreparations.countResult(textList));
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

    class CountReaction implements ActionListener{
        public void actionPerformed(ActionEvent countEvent){

            if (TextResultPreparations.countResult(textList).equals(""))
                calculatorTextField.setText("ERROR");
             else {
                ifReady = true;
                textONP = TextResultPreparations.countResult(textList);
                calculatorTextField.setText(TextResultPreparations.countResult(textList));
            }
        }
    }

    class PressReaction implements ActionListener {
        public void actionPerformed(ActionEvent pressEvent) {

            String buttonText = ((JButton) pressEvent.getSource()).getText();

            if(ifReady){
                ifReady = false;
                if (!(buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("/") || buttonText.equals("^"))) {
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
            if (buttonText.equals("ln") || buttonText.equals("sin") || buttonText.equals("cos") || buttonText.equals("tg") || buttonText.equals("log") || buttonText.equals("\u221a")) {
                text += "(";
                textList.add("(");
            }

            calculatorTextField.setText(text);
            calculatorResultField.setText(TextResultPreparations.countResult(textList));
        }
    }

}
