package MB_calculator_layout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Calculator extends JPanel implements ActionListener {

    protected JButton[] basicButtons;
    protected JButton[] functionalButtons;

    protected ButtonGroup groupColourButtons;
    protected JRadioButton darkColoursButton;
    protected JRadioButton lightColoursButton;

    protected GridBagLayout northPanelLayout;

    protected JPanel northPanel;
    protected JPanel centerPanel;
    protected JPanel rightPanel;

    protected JTextField calculatorTextField;
    protected JTextField calculatorResultField;

    protected Font fontUpperPanel;

    protected String result = "";
    protected String text = "";

    protected Border loweredLevelBorder;

    public Calculator(){

        fontUpperPanel = new Font("Helvetica", Font.ITALIC, 18);
        loweredLevelBorder = BorderFactory.createLoweredBevelBorder();
        setBackground(new java.awt.Color(238, 238, 238));

        rightPanel = new JPanel();

        northPanelLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        northPanel = new JPanel();
        northPanel.setLayout(northPanelLayout);
        northPanel.setPreferredSize(new Dimension(400,100));
        northPanel.setBackground(new java.awt.Color(238, 238, 238));
        add(BorderLayout.NORTH, northPanel);

        basicButtons = new JButton[12];
        groupColourButtons = new ButtonGroup();

        darkColoursButton = new JRadioButton("dark");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        darkColoursButton.setFont(fontUpperPanel);
        darkColoursButton.setBackground(new java.awt.Color(238, 238, 238));
        darkColoursButton.addActionListener(this);
        groupColourButtons.add(darkColoursButton);
        northPanel.add(darkColoursButton,c);

        lightColoursButton = new JRadioButton("light");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.4;
        c.gridx = 1;
        c.gridy = 0;
        lightColoursButton.setFont(fontUpperPanel);
        lightColoursButton.setBackground(new java.awt.Color(238, 238, 238));
        lightColoursButton.addActionListener(this);
        groupColourButtons.add(lightColoursButton);
        northPanel.add(lightColoursButton,c);

        calculatorResultField = new JTextField();
        c.gridx = 2;
        c.gridy = 0;
        calculatorResultField.setFont(fontUpperPanel);
        calculatorResultField.setBorder(loweredLevelBorder);
        calculatorResultField.setBackground(new java.awt.Color(220, 220, 220));
        northPanel.add(calculatorResultField,c);

        calculatorTextField = new JTextField();
        c.ipady = 20;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        calculatorTextField.setFont(fontUpperPanel);
        calculatorTextField.setBorder(loweredLevelBorder);
        calculatorTextField.setBackground(new java.awt.Color(248, 248, 248));
        northPanel.add(calculatorTextField,c);

        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setPreferredSize(new Dimension(450,500));
        centerPanel.setBackground(new java.awt.Color(238, 238, 238));
        add(BorderLayout.CENTER, centerPanel);

    }

    public void actionPerformed(ActionEvent pressEvent){
        Object source = pressEvent.getSource();

        if(source == darkColoursButton){
            setBackground(new java.awt.Color(43,45,45));
            lightColoursButton.setBackground(new java.awt.Color(43,45,45));
            darkColoursButton.setBackground(new java.awt.Color(43,45,45));
            northPanel.setBackground(new java.awt.Color(43,45,45));
            centerPanel.setBackground(new java.awt.Color(43,45,45));
            rightPanel.setBackground(new java.awt.Color(43,45,45));
            lightColoursButton.setForeground(Color.white);
            darkColoursButton.setForeground(Color.white);

        }else if (source == lightColoursButton){
            setBackground(new java.awt.Color(238, 238, 238));
            lightColoursButton.setBackground(new java.awt.Color(238, 238, 238));
            darkColoursButton.setBackground(new java.awt.Color(238, 238, 238));
            northPanel.setBackground(new java.awt.Color(238, 238, 238));
            centerPanel.setBackground(new java.awt.Color(238, 238, 238));
            rightPanel.setBackground(new java.awt.Color(238, 238, 238));
            lightColoursButton.setForeground(Color.black);
            darkColoursButton.setForeground(Color.black);
        }
    }

    class BackReaction implements ActionListener{
        public void actionPerformed(ActionEvent backEvent){
            if(!text.equals("")) {
                text = text.substring(0, text.length() - 1);
                calculatorTextField.setText(text);
            }
        }
    }

}
