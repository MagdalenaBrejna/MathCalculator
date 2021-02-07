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
    protected JPanel southPanel;

    protected JTextField calculatorTextField;
    protected JTextField calculatorResultField;

    protected Font fontUpperPanel;

    protected String text = "";

    protected Border loweredLevelBorder;

    public Calculator(){

        fontUpperPanel = new Font("Helvetica", Font.ITALIC, 18);
        loweredLevelBorder = BorderFactory.createLoweredBevelBorder();
        setBackground(new java.awt.Color(238, 238, 238));

        basicButtons = new JButton[11];

        rightPanel = new JPanel();
        southPanel = new JPanel();

        //Set features of the north panel and add it to the frame.
        northPanelLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        northPanel = new JPanel();
        northPanel.setLayout(northPanelLayout);
        northPanel.setPreferredSize(new Dimension(400,100));
        northPanel.setBackground(new java.awt.Color(238, 238, 238));
        add(BorderLayout.NORTH, northPanel);

        //Create the group of 2 radio buttons which change background colors.
        groupColourButtons = new ButtonGroup();

        //Set features of the radio button making background dark and add it to the north panel to the gridbaglayout.
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

        //Set features of the radio button making background light and add it to the north panel to the gridbaglayout.
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

        //Set features of the textField containing the current operation result and add it to the north panel.
        calculatorResultField = new JTextField();
        c.gridx = 2;
        c.gridy = 0;
        calculatorResultField.setFont(fontUpperPanel);
        calculatorResultField.setBorder(loweredLevelBorder);
        calculatorResultField.setBackground(new java.awt.Color(220, 220, 220));
        calculatorResultField.setEditable(false);
        northPanel.add(calculatorResultField,c);

        //Set features of the textField containing the expression and the final result and add it to the north panel.
        calculatorTextField = new JTextField();
        c.ipady = 20;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        calculatorTextField.setFont(fontUpperPanel);
        calculatorTextField.setBorder(loweredLevelBorder);
        calculatorTextField.setBackground(new java.awt.Color(248, 248, 248));
        calculatorTextField.setEditable(false);
        northPanel.add(calculatorTextField,c);

        //Set features of the central panel and add it to the frame.
        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setPreferredSize(new Dimension(450,500));
        centerPanel.setBackground(new java.awt.Color(238, 238, 238));
        add(BorderLayout.CENTER, centerPanel);

    }

    public void actionPerformed(ActionEvent pressEvent){
    //React to a radio button.

        Object source = pressEvent.getSource();

        //If the source of the event is the darkColoursButton, make everything dark.
        if(source == darkColoursButton){
            setBackground(new java.awt.Color(43,45,45));
            lightColoursButton.setBackground(new java.awt.Color(43,45,45));
            darkColoursButton.setBackground(new java.awt.Color(43,45,45));
            northPanel.setBackground(new java.awt.Color(43,45,45));
            centerPanel.setBackground(new java.awt.Color(43,45,45));
            rightPanel.setBackground(new java.awt.Color(43,45,45));
            southPanel.setBackground(new java.awt.Color(43,45,45));
            lightColoursButton.setForeground(Color.white);
            darkColoursButton.setForeground(Color.white);

        //If the source of the event is the lightColoursButton, make everything light.
        }else if (source == lightColoursButton){
            setBackground(new java.awt.Color(238, 238, 238));
            lightColoursButton.setBackground(new java.awt.Color(238, 238, 238));
            darkColoursButton.setBackground(new java.awt.Color(238, 238, 238));
            northPanel.setBackground(new java.awt.Color(238, 238, 238));
            centerPanel.setBackground(new java.awt.Color(238, 238, 238));
            rightPanel.setBackground(new java.awt.Color(238, 238, 238));
            southPanel.setBackground(new java.awt.Color(238, 238, 238));
            lightColoursButton.setForeground(Color.black);
            darkColoursButton.setForeground(Color.black);
        }
    }
}
