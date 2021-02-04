package MB_calculator_layout;

import javax.swing.*;
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

    protected JTextField calculatorTextField;

    protected Font fontUpperPanel;

    protected String result = "";
    protected String text = "";

    public Calculator(){

        fontUpperPanel = new Font("Helvetica", Font.ITALIC, 16);

        northPanelLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        northPanel = new JPanel();
        northPanel.setLayout(northPanelLayout);
        northPanel.setPreferredSize(new Dimension(500,100));
        add(BorderLayout.NORTH, northPanel);

        basicButtons = new JButton[12];
        groupColourButtons = new ButtonGroup();

        darkColoursButton = new JRadioButton("dark");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        darkColoursButton.setFont(fontUpperPanel);
        darkColoursButton.addActionListener(this);
        groupColourButtons.add(darkColoursButton);
        northPanel.add(darkColoursButton,c);

        lightColoursButton = new JRadioButton("light");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        lightColoursButton.setFont(fontUpperPanel);
        lightColoursButton.addActionListener(this);
        groupColourButtons.add(lightColoursButton);
        northPanel.add(lightColoursButton,c);

        calculatorTextField = new JTextField();
        c.ipady = 20;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        calculatorTextField.setFont(fontUpperPanel);
        northPanel.add(calculatorTextField,c);

        centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setPreferredSize(new Dimension(450,600));

        add(BorderLayout.CENTER, centerPanel);

    }

    public void actionPerformed(ActionEvent pressEvent){
        Object source = pressEvent.getSource();

        if(source == darkColoursButton){
            setBackground(new java.awt.Color(43, 45, 45));
        }else if (source == lightColoursButton){
            setBackground(new java.awt.Color(190, 208, 211));
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
