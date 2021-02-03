package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCalculator extends Calculator{

    public JButton[] przyciskiFunkcyjne;

    public JPanel panel2;
    public JPanel panel4;
    public JPanel panel5;

    public JLabel label;

    public String wyrazenie = "";

    public BasicCalculator(){

        panel2 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        panel2.setBackground(new java.awt.Color(173, 216, 230,200));
        panel4.setBackground(new java.awt.Color(173, 216, 230));
        panel5.setBackground(new java.awt.Color(173, 216, 230,200));

        add(BorderLayout.WEST, panel2);
        add(BorderLayout.EAST, panel4);
        add(BorderLayout.SOUTH, panel5);

        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/1.jpg"));
        label = new JLabel(imageIcon);
        panel5.add(label);

        przyciskiCyfry[0] = new BasicButton("1", x, y, new reakcjaPrzycisk(), centerPanel);
        przyciskiCyfry[1] = new BasicButton("2", 2*x + dlx, y, new reakcjaPrzycisk(), centerPanel);
        przyciskiCyfry[2] = new BasicButton("3", 3*x + 2*dlx, y, new BasicCalculator(), centerPanel);
        przyciskiCyfry[3] = new BasicButton("4", x, 2*y + dly, new BasicCalculator(), centerPanel);
        przyciskiCyfry[4] = new BasicButton("5", 2*x + dlx, 2*y + dly, new BasicCalculator(), centerPanel);
        przyciskiCyfry[5] = new BasicButton("6", 3*x + 2*dlx, 2*y + dly, new BasicCalculator(), centerPanel);
        przyciskiCyfry[6] = new BasicButton("7", x, 3*y + 2*dly, new BasicCalculator(), centerPanel);
        przyciskiCyfry[7] = new BasicButton("8", 2*x + dlx, 3*y + 2*dly, new BasicCalculator(), centerPanel);
        przyciskiCyfry[8] = new BasicButton("9", 3*x + 2*dlx, 3*y + 2*dly, new BasicCalculator(), centerPanel);
        przyciskiCyfry[9] = new BasicButton("=", x, 4*y + 3* dly, new BasicCalculator(), centerPanel);
        przyciskiCyfry[10] = new BasicButton("0", 2*x + dlx, 4*y + 3*dly, new BasicCalculator(), centerPanel);
        przyciskiCyfry[11] = new BasicButton(".", 3*x + 2*dlx, 4*y + 3*dly, new BasicCalculator(), centerPanel);
/*

        y = 370;
        dlx = dlx *3/4;
        dly = dly *3/4;
        x = x*3/4;

        przyciskiFunkcyjne = new JButton[8];
        przyciskiFunkcyjne[0] = new FunkcyjnyPrzycisk("+", dlx, dly, x + 5, y, new reakcjaPrzycisk(), panel3);
        przyciskiFunkcyjne[1] = new FunkcyjnyPrzycisk("-", dlx, dly, 2*x + dlx + 2, y, new reakcjaPrzycisk(), panel3);
        przyciskiFunkcyjne[2] = new FunkcyjnyPrzycisk("*", dlx, dly, 3*x + 2*dlx + 2, y, new reakcjaPrzycisk(), panel3);
        przyciskiFunkcyjne[3] = new FunkcyjnyPrzycisk("/", dlx, dly, 4*x + 3*dlx + 2, y, new reakcjaPrzycisk(), panel3);

        y = 20;
        x = 350;

        przyciskiFunkcyjne[4] = new FunkcyjnyPrzycisk("(", dly, dlx, x, y, new reakcjaPrzycisk(), panel3);
        przyciskiFunkcyjne[5] = new FunkcyjnyPrzycisk(")", dly, dlx, x, 2*y + dlx, new reakcjaPrzycisk(), panel3);
        przyciskiFunkcyjne[6] = new FunkcyjnyPrzycisk("^", dly, dlx, x, 2*(y + dlx) + y, new reakcjaPrzycisk(), panel3);
        przyciskiFunkcyjne[7] = new FunkcyjnyPrzycisk("!", dly, dlx, x, 3*(y + dlx) + y, new reakcjaPrzycisk(), panel3);

        ramka.pack();
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setResizable(false);
        ramka.setVisible(true);
        ramka.setSize(450,620);

 */
    }

    class reakcjaKoncowe implements ActionListener{
        public void actionPerformed(ActionEvent e){

            try {
                String onp = ONP.zamienNaONP(wyrazenie);
                double wynik = ONP.obliczONP(onp);
                pole.setText(String.valueOf(wynik));
                wyrazenie = "";
            }catch(Exception f){
                JOptionPane.showMessageDialog(null, "Wystapił błąd");
                wyrazenie = "";
                pole.setText("ERROR");
            }
        }
    }

    class reakcjaCofnij implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(!wyrazenie.equals("")) {
                char[] w = wyrazenie.toCharArray();
                String w2 = "";
                for (int i = 0; i < wyrazenie.length() - 1; i++)
                    w2 += w[i];
                wyrazenie = w2;
                pole.setText(wyrazenie);
            }
        }
    }

    class reakcjaPrzycisk implements ActionListener{
        public void actionPerformed(ActionEvent e){

            if(wyrazenie.equals(""))
                pole.setText("");

            Object zrodlo = e.getSource();
            if(zrodlo == przyciskiCyfry[10]) {
                pole.setText(pole.getText() + "0");
                wyrazenie += "0";
            }else if(zrodlo == przyciskiCyfry[0]) {
                pole.setText(pole.getText() + "1");
                wyrazenie += "1";
            }else if(zrodlo == przyciskiCyfry[1]) {
                pole.setText(pole.getText() + "2");
                wyrazenie += "2";
            }else if(zrodlo == przyciskiCyfry[2]) {
                pole.setText(pole.getText() + "3");
                wyrazenie += "3";
            }else if(zrodlo == przyciskiCyfry[3]) {
                pole.setText(pole.getText() + "4");
                wyrazenie += "4";
            }else if(zrodlo == przyciskiCyfry[4]) {
                pole.setText(pole.getText() + "5");
                wyrazenie += "5";
            }else if(zrodlo == przyciskiCyfry[5]) {
                pole.setText(pole.getText() + "6");
                wyrazenie += "6";
            }else if(zrodlo == przyciskiCyfry[6]) {
                pole.setText(pole.getText() + "7");
                wyrazenie += "7";
            }else if(zrodlo == przyciskiCyfry[7]) {
                pole.setText(pole.getText() + "8");
                wyrazenie += "8";
            }else if(zrodlo == przyciskiCyfry[8]) {
                pole.setText(pole.getText() + "9");
                wyrazenie += "9";
            }else if(zrodlo == przyciskiFunkcyjne[0]) {
                pole.setText(pole.getText() + "+");
                wyrazenie += "+";
            }else if(zrodlo == przyciskiFunkcyjne[1]) {
                pole.setText(pole.getText() + "-");
                wyrazenie += "-";
            }else if(zrodlo == przyciskiFunkcyjne[2]) {
                pole.setText(pole.getText() + "*");
                wyrazenie += "*";
            }else if(zrodlo == przyciskiFunkcyjne[3]) {
                pole.setText(pole.getText() + "/");
                wyrazenie += "/";
            }else if(zrodlo == przyciskiFunkcyjne[4]) {
                pole.setText(pole.getText() + ")");
                wyrazenie += ")";
            }else if(zrodlo == przyciskiFunkcyjne[5]) {
                pole.setText(pole.getText() + "(");
                wyrazenie += "(";
            }else if(zrodlo == przyciskiFunkcyjne[6]) {
                pole.setText(pole.getText() + "^");
                wyrazenie += "^";
            }else if(zrodlo == przyciskiFunkcyjne[7]) {
                pole.setText(pole.getText() + "!");
                wyrazenie += "!";
            }
        }
    }
}
