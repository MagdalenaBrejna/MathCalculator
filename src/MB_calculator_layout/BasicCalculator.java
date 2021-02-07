package MB_calculator_layout;

import MB_calculator_action.BasicEquationCount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCalculator extends Calculator{

    private String number1 = "", number2 = "", operationSymbol = "";

    public BasicCalculator(){

        super();
        centerPanel.setLocation(60,300);

        //create 12 basic buttons of numbers, dot and equality in the central panel
        int dlx = 80, dly = 60, x = 50, y = 20;

        basicButtons[0] = new BasicButton("1", x, y, new PressReaction(), centerPanel);
        basicButtons[1] = new BasicButton("2", 2*x + dlx, y, new PressReaction(), centerPanel);
        basicButtons[2] = new BasicButton("3", 3*x + 2*dlx, y, new PressReaction(), centerPanel);
        basicButtons[3] = new BasicButton("4", x, 2*y + dly, new PressReaction(), centerPanel);
        basicButtons[4] = new BasicButton("5", 2*x + dlx, 2*y + dly, new PressReaction(), centerPanel);
        basicButtons[5] = new BasicButton("6", 3*x + 2*dlx, 2*y + dly, new PressReaction(), centerPanel);
        basicButtons[6] = new BasicButton("7", x, 3*y + 2*dly, new PressReaction(), centerPanel);
        basicButtons[7] = new BasicButton("8", 2*x + dlx, 3*y + 2*dly, new PressReaction(), centerPanel);
        basicButtons[8] = new BasicButton("9", 3*x + 2*dlx, 3*y + 2*dly, new PressReaction(), centerPanel);
        basicButtons[9] = new BasicButton("=", x, 4*y + 3*dly, new CountReaction(), centerPanel);
        basicButtons[10] = new BasicButton("0", 2*x + dlx, 4*y + 3*dly, new PressReaction(), centerPanel);
        basicButtons[11] = new BasicButton(".", 3*x + 2*dlx, 4*y + 3*dly, new PressReaction(), centerPanel);

        //create 6 basic functional buttons
        dlx = 60;
        dly = 45;
        x = 37;
        y = 360;

        functionalButtons = new JButton[6];
        functionalButtons[0] = new FunctionalButton("+", x+13, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[1] = new FunctionalButton("-", 2*x + dlx+10, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[2] = new FunctionalButton("*", 3*x + 2*dlx+7, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[3] = new FunctionalButton("/", 4*x + 3*dlx+3, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[4] = new FunctionalButton("AC", 2*x + dlx+10, y + dly + 25, dlx, dly, new ACReaction(), centerPanel);
        functionalButtons[5] = new FunctionalButton("<<", 3*x + 2*dlx+7, y + dly + 25, dlx, dly, new DELReaction(), centerPanel);

        //setting a different colour for important functional buttons
        basicButtons[9].setBackground(new java.awt.Color(245, 197, 174));
        functionalButtons[4].setBackground(new java.awt.Color(245, 197, 174));
        functionalButtons[5].setBackground(new java.awt.Color(245, 197, 174));
    }

    class CountReaction implements ActionListener{
        public void actionPerformed(ActionEvent e){
        //count result
            number2 = text;
            if(!number1.equals(""))
                //if there are two numbers count a result
                result = BasicEquationCount.count(number1, operationSymbol, number2);
            else
                //if there is only one number, the number is a result
                result = number2;
            calculatorTextField.setText(result);
            calculatorResultField.setText(result);
            text = "";
            number1 = "";
            number2 = "";
        }
    }

    class ACReaction implements ActionListener{
        public void actionPerformed(ActionEvent ACEvent){
        //make everything clear
            text = "";
            operationSymbol = "";
            calculatorTextField.setText(text);
            calculatorResultField.setText(text);
        }
    }

    class DELReaction implements ActionListener{
        public void actionPerformed(ActionEvent DELEvent){
        //back the expression
            if(!text.equals("")) {
                text = text.substring(0, text.length() - 1);
                calculatorTextField.setText(text);
            }
        }
    }

    class PressReaction implements ActionListener{
        public void actionPerformed(ActionEvent pressEvent){
        //react to a button

            if(text.equals(""))
                calculatorTextField.setText("");

            char character = (pressEvent.getActionCommand()).charAt(0);
            String buttonText = ((JButton)pressEvent.getSource()).getText();

            //if a character is a number, add it to the expression
            if(character >= '0' && character <= '9') {
                text += character;
                calculatorTextField.setText(text);

            //if a character is a dot and there aren't any dots in the expression, add a dot to the expression
            }else if(character == '.'){
                if(!text.contains(".")) {
                    text += character;
                    calculatorTextField.setText(text);
                }

            //if a character is an mathematical operator set first number of the operation and operator
            }else if(buttonText.equals("/") || buttonText.equals("*") || buttonText.equals("-") || buttonText.equals("+")) {
                if(!text.equals("")){
                    number1 = text;
                    calculatorResultField.setText(number1);
                    operationSymbol = buttonText;
                    text = "";
                }
            }
        }
    }
}
