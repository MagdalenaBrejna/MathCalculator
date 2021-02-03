package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Calculator extends JPanel implements ActionListener {

    protected JButton[] przyciskiCyfry;

    protected JRadioButton darkColours;
    protected JRadioButton lightColours;

    protected GridBagLayout upperPanel;

    protected JPanel northPanel;
    protected JPanel centerPanel;

    protected JTextField pole;

    protected Font fontUpperPanel;

    int dlx = 80, dly = 60, x = 20, y = 20;

    public Calculator(){
        Font font2 = new Font("Helvetica", Font.ITALIC, 16);

        przyciskiCyfry = new JButton[12];

        upperPanel = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        northPanel = new JPanel();
        northPanel.setBackground(new Color(173, 216, 230,200));
        add(BorderLayout.NORTH, northPanel);

        northPanel.setLayout(upperPanel);
        ButtonGroup group = new ButtonGroup();

        darkColours = new JRadioButton("dark");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 10.5;
        c.gridx = 0;
        c.gridy = 0;
        darkColours.setFont(fontUpperPanel);
        darkColours.addActionListener(this);
        group.add(darkColours);
        northPanel.add(darkColours,c);

        lightColours = new JRadioButton("light");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        lightColours.setFont(fontUpperPanel);
        lightColours.addActionListener(this);
        group.add(lightColours);
        northPanel.add(lightColours,c);

        pole = new JTextField();
        c.ipady = 20;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        pole.setFont(fontUpperPanel);
        northPanel.add(pole,c);

        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(173, 216, 230));
        add(BorderLayout.CENTER, centerPanel);

        centerPanel.setLayout(null);

    }

    public void actionPerformed(ActionEvent pressEvent){
        Object source = pressEvent.getSource();

        if(source == darkColours){
            setBackground(new java.awt.Color(220, 220, 220));
        }else{
            setBackground(new java.awt.Color(220, 220, 220));
        }
    }

}
