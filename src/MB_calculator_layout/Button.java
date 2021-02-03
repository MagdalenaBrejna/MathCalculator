package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Button extends JButton {

    Font font = new Font("Helvetica", Font.ITALIC, 26);

    public Button(String nazwa, ActionListener a){
        super(nazwa);
        addActionListener(a);
        setFont(font);
    }
}
