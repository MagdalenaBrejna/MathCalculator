package MB_calculator_layout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BasicButton extends Button{

    private final int buttonLength = 80;
    private final int buttonHeight = 60;
    private final Color color = new java.awt.Color(225, 225, 225);
    private final Color colorDark = new java.awt.Color(200, 200, 200);


    public BasicButton(String textNameButton, int xCoordinate, int yCoordinate, ActionListener actionListenerButton, JPanel panel){
        super(textNameButton, actionListenerButton);
        buttonName = textNameButton;
        addMouseListener(this);
        setBounds(xCoordinate, yCoordinate, buttonLength, buttonHeight);
        this.setBackground(color);
        panel.add(this);
    }

    public void mouseEntered(MouseEvent event){
        if(buttonName.equals("="))
            this.setBackground(specialColorDark);
        else
            this.setBackground(colorDark);
    }

    public void mouseExited(MouseEvent event){
        if(buttonName.equals("="))
            this.setBackground(specialColor);
        else
            this.setBackground(color);
    }

}
