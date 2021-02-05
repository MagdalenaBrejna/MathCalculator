package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FunctionalButton extends Button{

    private final Color color = new java.awt.Color(200, 200, 200);

    public FunctionalButton(String textNameButton, int xCoordinate, int yCoordinate, int buttonLength, int buttonHeight, ActionListener actionListenerButton, JPanel panel){
        super(textNameButton, actionListenerButton);
        setBounds(xCoordinate, yCoordinate, buttonLength, buttonHeight);
        this.setBackground(color);
        panel.add(this);
    }
}
