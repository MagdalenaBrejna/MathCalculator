package MB_calculator_layout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Button extends JButton {

    Font buttonFont = new Font("Helvetica", Font.ITALIC, 18);
    Border raisedLevelBorder = BorderFactory.createRaisedBevelBorder();

    public Button(String textNameButton, ActionListener actionListenerButton){
        super(textNameButton);
        addActionListener(actionListenerButton);
        setFont(buttonFont);
        setBorder(raisedLevelBorder);
    }
}
