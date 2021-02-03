package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BasicButton extends Button {

    private final int x = 80;
    private final int y = 60;

    public BasicButton(String nazwa, int dx, int dy, ActionListener a, JPanel p){
        super(nazwa,a);
        setBounds(dx,dy,x,y);
        p.add(this);
    }
}
