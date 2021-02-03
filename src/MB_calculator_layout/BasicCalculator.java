package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCalculator extends Calculator{

    public JPanel panel5;

    public JLabel label;

    public String result = "";
    public String text = "";

    private String a="",b="0",znak="=";

    public BasicCalculator(){

        super();
        panel5 = new JPanel();
        add(BorderLayout.SOUTH, panel5);

        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/1.jpg"));
        label = new JLabel(imageIcon);
        panel5.add(label);

        int dlx = 80, dly = 60, x = 20, y = 20;

        basicButtons[0] = new BasicButton("1", x, y, new PressReaction(), centerPanel);
        basicButtons[1] = new BasicButton("2", 2*x + dlx, y, new PressReaction(), centerPanel);
        basicButtons[2] = new BasicButton("3", 3*x + 2*dlx, y, new PressReaction(), centerPanel);
        basicButtons[3] = new BasicButton("4", x, 2*y + dly, new PressReaction(), centerPanel);
        basicButtons[4] = new BasicButton("5", 2*x + dlx, 2*y + dly, new PressReaction(), centerPanel);
        basicButtons[5] = new BasicButton("6", 3*x + 2*dlx, 2*y + dly, new PressReaction(), centerPanel);
        basicButtons[6] = new BasicButton("7", x, 3*y + 2*dly, new PressReaction(), centerPanel);
        basicButtons[7] = new BasicButton("8", 2*x + dlx, 3*y + 2*dly, new PressReaction(), centerPanel);
        basicButtons[8] = new BasicButton("9", 3*x + 2*dlx, 3*y + 2*dly, new PressReaction(), centerPanel);
        basicButtons[9] = new BasicButton("=", x, 4*y + 3* dly, new CountReaction(), centerPanel);
        basicButtons[10] = new BasicButton("0", 2*x + dlx, 4*y + 3*dly, new PressReaction(), centerPanel);
        basicButtons[11] = new BasicButton(".", 3*x + 2*dlx, 4*y + 3*dly, new PressReaction(), centerPanel);

        y = 370;
        dlx = dlx *3/4;
        dly = dly *3/4;
        x = x*3/4;

        functionalButtons = new JButton[4];
        functionalButtons[0] = new FunctionalButton("+", dlx, dly, x + 5, y, new PressReaction(), centerPanel);
        functionalButtons[1] = new FunctionalButton("-", dlx, dly, 2*x + dlx + 2, y, new PressReaction(), centerPanel);
        functionalButtons[2] = new FunctionalButton("*", dlx, dly, 3*x + 2*dlx + 2, y, new PressReaction(), centerPanel);
        functionalButtons[3] = new FunctionalButton("/", dlx, dly, 4*x + 3*dlx + 2, y, new PressReaction(), centerPanel);

    }

    class CountReaction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //result = BasicEquation.count();
            pole.setText(result);
        }
    }

    class BackReaction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(!text.equals("")) {
                char[] w = text.toCharArray();
                String w2 = "";
                for (int i = 0; i < text.length() - 1; i++)
                    w2 += w[i];
                text = w2;
                pole.setText(text);
            }
        }
    }

    class PressReaction implements ActionListener{
        public void actionPerformed(ActionEvent pressEvent){

            if(text.equals(""))
                pole.setText("");

            char character = (pressEvent.getActionCommand()).charAt(0);
            String buttonText = ((JButton)pressEvent.getSource()).getText();

            if(character >= '0' && character <= '9') {
                if(text == "0")
                    text = "" + character;
                else
                    text += character;
                pole.setText(text);

            }else if(character == '.'){
                if(!text.contains(".")) {
                    text += character;
                    pole.setText(text);
                }

            }else if(buttonText == "AC") {
                text = "0";
                a = "";
                b = "0";
                znak = "=";
                pole.setText(text);

            }else if(buttonText == "/" || buttonText == "*" || buttonText == "-" || buttonText == "+" || buttonText == "=") {
                b = text;

                if(znak == "=") {
                    if(a == "")
                        a = b;
                    else
                        znak = buttonText;

                } else {
                    //a = BasicEquation.count(a, znak, b);
                    pole.setText(a);
                }

                text = "0";
                znak = buttonText;
            }


        }
    }
}
