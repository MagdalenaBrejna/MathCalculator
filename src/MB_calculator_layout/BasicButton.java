package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BasicButton extends JButton {

    Font font = new Font("Helvetica", Font.ITALIC, 26);
    private final int x = 80;
    private final int y = 60;

    public BasicButton(String nazwa, int dx, int dy, ActionListener a, JPanel p){
        super(nazwa);
        setBounds(dx,dy,x,y);
        addActionListener(a);
        setFont(font);
        p.add(this);
    }
}
