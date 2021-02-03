package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FunctionalButton extends JButton{

    Font font = new Font("Helvetica", Font.ITALIC, 26);

    public FunctionalButton(String nazwa, int dx, int dy, int x, int y, ActionListener a, JPanel p){
        super(nazwa);
        setBounds(x,y,dx,dy);
        addActionListener(a);
        setFont(font);
        p.add(this);
    }
}
