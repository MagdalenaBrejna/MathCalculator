package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class FunctionalButton extends Button{

    private final Color color = new java.awt.Color(200, 200, 200);
    private final Color colorDark = new java.awt.Color(180, 180, 180);
    private final Color specialColor = new java.awt.Color(245, 197, 174);
    private final Color specialColorDark = new java.awt.Color(230, 185, 164);

    private String buttonName = "";

    public FunctionalButton(String textNameButton, int xCoordinate, int yCoordinate, int buttonLength, int buttonHeight, ActionListener actionListenerButton, JPanel panel){
        super(textNameButton, actionListenerButton);
        buttonName = textNameButton;
        addMouseListener(this);
        setBounds(xCoordinate, yCoordinate, buttonLength, buttonHeight);
        if(buttonName.equals("AC") || buttonName.equals("<<") || buttonName.equals("="))
            this.setBackground(specialColor);
        else
            this.setBackground(color);
        panel.add(this);
    }

    public void mouseEntered(MouseEvent event){
        if(buttonName.equals("AC") || buttonName.equals("<<") || buttonName.equals("="))
            this.setBackground(specialColorDark);
        else
            this.setBackground(colorDark);
    }

    public void mouseExited(MouseEvent event){
        if(buttonName.equals("AC") || buttonName.equals("<<") || buttonName.equals("="))
            this.setBackground(specialColor);
        else
            this.setBackground(color);
    }
}
