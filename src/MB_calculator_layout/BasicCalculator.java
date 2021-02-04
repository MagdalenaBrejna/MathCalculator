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

        int dlx = 80, dly = 60, x = 50, y = 40;

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

        dlx = 60;
        dly = 45;
        x = 37;
        y = 440;

        functionalButtons = new JButton[6];
        functionalButtons[0] = new FunctionalButton("+", x+13, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[1] = new FunctionalButton("-", 2*x + dlx+10, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[2] = new FunctionalButton("*", 3*x + 2*dlx+7, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[3] = new FunctionalButton("/", 4*x + 3*dlx+3, y, dlx, dly, new PressReaction(), centerPanel);
        functionalButtons[4] = new FunctionalButton("AC", 2*x + dlx+10, y + dly + 25, dlx, dly, new ACReaction(), centerPanel);
        functionalButtons[5] = new FunctionalButton("<<", 3*x + 2*dlx+7, y + dly + 25, dlx, dly, new BackReaction(), centerPanel);
    }

    class CountReaction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            number2 = text;
            result = BasicEquationCount.count(number1, operationSymbol, number2);
            calculatorTextField.setText(result);
            text = "";
            number1 = "";
            number2 = "";
        }
    }

    class ACReaction implements ActionListener{
        public void actionPerformed(ActionEvent ACEvent){
            text = "";
            operationSymbol = "";
            calculatorTextField.setText(text);
        }
    }

    class PressReaction implements ActionListener{
        public void actionPerformed(ActionEvent pressEvent){

            if(text.equals(""))
                calculatorTextField.setText("");

            char character = (pressEvent.getActionCommand()).charAt(0);
            String buttonText = ((JButton)pressEvent.getSource()).getText();

            if(character >= '0' && character <= '9') {
                text += character;
                calculatorTextField.setText(text);

            }else if(character == '.'){
                if(!text.contains(".")) {
                    text += character;
                    calculatorTextField.setText(text);
                }

            }else if(buttonText == "/" || buttonText == "*" || buttonText == "-" || buttonText == "+") {
                if(text != ""){
                    number1 = text;
                    operationSymbol = buttonText;
                    text = "";
                }
            }
        }
    }
}
