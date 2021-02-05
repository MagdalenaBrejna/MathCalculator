package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BasicButton extends Button {

    private final int buttonLength = 80;
    private final int buttonHeight = 60;
    private final Color color = new java.awt.Color(225, 225, 225);

    public BasicButton(String textNameButton, int xCoordinate, int yCoordinate, ActionListener actionListenerButton, JPanel panel){
        super(textNameButton, actionListenerButton);
        setBounds(xCoordinate, yCoordinate, buttonLength, buttonHeight);
        this.setBackground(color);
        panel.add(this);
    }

}
