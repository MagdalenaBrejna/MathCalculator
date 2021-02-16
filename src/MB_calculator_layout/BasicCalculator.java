package MB_calculator_layout;

import MB_calculator_action.BasicEquationCount;
import MB_calculator_action.WrongExpressionException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCalculator extends Calculator{

    private String number1 = "", number2 = "", operationSymbol = "=", result = "";

    public BasicCalculator(){

        super();

        centerPanel.setLocation(60,300);

        functionalButtons = new JButton[7];

        //creating 11 basic buttons of numbers and dot and functional button of equality in the central panel
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
        functionalButtons[6] = new FunctionalButton("=", x, 4*y + 3*dly, dlx, dly, new CountReaction(), centerPanel);
        basicButtons[9] = new BasicButton("0", 2*x + dlx, 4*y + 3*dly, new PressReaction(), centerPanel);
        basicButtons[10] = new BasicButton(".", 3*x + 2*dlx, 4*y + 3*dly, new PressReaction(), centerPanel);

        //create 6 basic functional buttons
        dlx = 60;
        dly = 45;
        x = 37;
        y = 360;

        functionalButtons[0] = new FunctionalButton("+", x+13, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[1] = new FunctionalButton("-", 2*x + dlx+10, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[2] = new FunctionalButton("*", 3*x + 2*dlx+7, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[3] = new FunctionalButton("/", 4*x + 3*dlx+3, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[4] = new FunctionalButton("AC", 2*x + dlx+10, y + dly + 25, dlx, dly, new ACReaction(), centerPanel);
        functionalButtons[5] = new FunctionalButton("<<", 3*x + 2*dlx+7, y + dly + 25, dlx, dly, new DELReaction(), centerPanel);

    }

    class CountReaction implements ActionListener{
        public void actionPerformed(ActionEvent e){
        //count result
            number2 = text;
            if(!number1.equals(""))
                //if there are two numbers count a result
                try {
                    result = BasicEquationCount.count(number1, operationSymbol, number2);
                }catch(NumberFormatException | WrongExpressionException exception){
                    calculatorResultField.setText("");
                    calculatorTextField.setText("ERROR");
                }

            else
                //if there is only one number, the number is a result
                result = number2;

            calculatorTextField.setText(result);
            calculatorResultField.setText(result);
            text = result;
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
            number1 = "";
            number2 = "";
            result = "";
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

            char character = (pressEvent.getActionCommand()).charAt(0);
            String buttonText = ((JButton)pressEvent.getSource()).getText();

            if(!number1.equals("")){

                //if number1 exists and character is a number or dot add character to the text which builds number2
                if (character >= '0' && character <= '9' || (character == '.' && !text.contains("."))) {
                    text += buttonText;
                    calculatorTextField.setText(text);

                }else{

                    if(!result.equals(""))
                        result = "";

                    //if number1 exists, character is an operator and the text building number2 isn't empty count and set the result
                    if(!text.equals("")){
                        number2 = text;
                        try {
                            number1 = BasicEquationCount.count(number1, operationSymbol, number2);
                            calculatorResultField.setText(number1);
                            calculatorTextField.setText(number1);
                            number2 = "";
                            text = "";

                        }catch(NumberFormatException | WrongExpressionException exception){
                            calculatorResultField.setText("");
                            calculatorTextField.setText("ERROR");
                        }
                    }
                    operationSymbol = buttonText;
                }

            }else{

                //if number1 doesn't exist and character is a number or dot, add this character to the text building number1
                if (character >= '0' && character <= '9' || (character == '.' && !text.contains("."))){

                    if (result.equals(""))
                        text += buttonText;
                    else{
                        text = buttonText;
                        result = "";
                    }
                    calculatorTextField.setText(text);

                }else{
                    //if number1 doesn't exist and character is an operator, save operator and create number1
                    number1 = text;
                    calculatorResultField.setText(number1);
                    text = "";
                    operationSymbol = buttonText;
                }
            }
        }
    }
}
