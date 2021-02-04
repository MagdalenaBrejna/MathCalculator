package MB_calculator_layout;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BasicButton extends Button {

    private final int buttonLength = 80;
    private final int buttonHeight = 60;

    public BasicButton(String textNameButton, int xCoordinate, int yCoordinate, ActionListener actionListenerButton, JPanel panel){
        super(textNameButton, actionListenerButton);
        setBounds(xCoordinate, yCoordinate, buttonLength, buttonHeight);
        panel.add(this);
    }
}
