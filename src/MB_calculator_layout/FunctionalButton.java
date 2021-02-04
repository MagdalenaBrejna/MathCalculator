package MB_calculator_layout;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FunctionalButton extends Button{

    public FunctionalButton(String textNameButton, int xCoordinate, int yCoordinate, int buttonLength, int buttonHeight, ActionListener actionListenerButton, JPanel panel){
        super(textNameButton, actionListenerButton);
        setBounds(xCoordinate, yCoordinate, buttonLength, buttonHeight);
        panel.add(this);
    }
}
